import java.util.LinkedList;
import java.util.Scanner;

/**
 * 请实现一个简易内存池,根据请求命令完成内存分配和释放。
 * 内存池支持两种操作命令，REQUEST和RELEASE，其格式为：
 * REQUEST=请求的内存大小 表示请求分配指定大小内存，如果分配成功，返回分配到的内存首地址；如果内存不足，或指定的大小为0，则输出error。
 * RELEASE=释放的内存首地址 表示释放掉之前分配的内存，释放成功无需输出，如果释放不存在的首地址则输出error。
 * 注意：
 * 内存池总大小为100字节。
 * 内存池地址分配必须是连续内存，并优先从低地址分配。
 * 内存释放后可被再次分配，已释放的内存在空闲时不能被二次释放。
 * 不会释放已申请的内存块的中间地址。
 * 释放操作只是针对首地址所对应的单个内存块进行操作，不会影响其它内存块。
 * 输入描述
 * 首行为整数 N , 表示操作命令的个数，取值范围：0 < N <= 100。
 * 接下来的N行, 每行将给出一个操作命令，操作命令和参数之间用 “=”分割。
 * 输出描述
 * 请求分配指定大小内存时，如果分配成功，返回分配到的内存首地址；如果内存不足，或指定的大小为0，则输出error
 * 释放掉之前分配的内存时，释放成功无需输出，如果释放不存在的首地址则输出error。
 *
 * 5
 * REQUEST=10
 * REQUEST=20
 * RELEASE=0
 * REQUEST=20
 * REQUEST=10
 * 输出	0
 * 10
 * 30
 * 0
 * 说明
 * 第一条指令，申请地址0~9的10个字节内存，返回首地址0
 *
 * 第二条指令，申请地址10~29的20字节内存，返回首地址10
 *
 * 第三条指令，释放首地址为0的内存申请，0~9地址内存被释放，变为空闲，释放成功，无需输出
 *
 * 第四条指令，申请20字节内存，09地址内存连续空间不足20字节，往后查找到3049地址，返回首地址30
 *
 * 第五条指令，申请10字节，0~9地址内存空间足够，返回首地址0
 * @date 2024/4/14 8:57
 */
public class IntervalMemoryAllocate {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n= scanner.nextInt();
        scanner.nextLine();
        String[][] command=new String[n][2];
        for (int i = 0; i < n; i++) {
            command[i] = scanner.nextLine().split("=");
        }

        LinkedList<int[]> usedMemory=new LinkedList<>();
        // 内存池总大小为100字节 所以100之后肯定是用不到了 之所以要一开始就添加这块把[100,101]当做已使用的内存 是为了给分配的内存
        // 设立一个边界 添加的时候比较到最后只要不和[100,101]相交 就一定符合要求
        usedMemory.add(new int[]{100,101});
        for (int i = 0; i < n; i++) {

            if(command[i][0].equals("REQUEST")){
                int requestMemory = Integer.parseInt(command[i][1]);
                if(requestMemory==0){
                    System.out.println("error");
                    // 这里是continue;不是break
                    continue;
                }
                boolean allocatedFlag=false;
                // 我们默认从start=0位置开始检查可用内存区间
                int start=0;
                for (int j = 0; j < usedMemory.size(); j++) {
                    int end=start+requestMemory-1;
                    // 当前申请的区间是上一区间usedMemory[j-1]的结束+1的位置开始 和现在的区间usedMemory[j]相比 如果不相交就说明和
                    // 后面的区间也不会相交了 因为usedMemory里添加的元素都是按照起始位置升序的 所以可以输出了
                    // cur是要申请的内存区间
                    int[] cur={start,end};
                    // 检查要申请的内存区间和已占有的内存区间是否交叉
                    if(!hasIntersection(usedMemory.get(j),cur)){
                        usedMemory.add(j,cur);
                        System.out.println(start);
                        allocatedFlag=true;
                        break;
                    }
                    else{
                        start=usedMemory.get(j)[1]+1;
                    }
                }
                if(!allocatedFlag)
                    System.out.println("error");
            }
            else{
                int releaseAddr=Integer.parseInt(command[i][1]);
                if(releaseAddr>=100){
                    System.out.println("error");
                    continue;
                }

                boolean releasedFlag=false;
                for (int j = 0; j < usedMemory.size(); j++) {
                    if(usedMemory.get(j)[0]==releaseAddr){
                        usedMemory.remove(j);
                        releasedFlag=true;
                        break;
                    }
                }
                if(!releasedFlag)
                    System.out.println("error");
            }
        }
    }

    // 判断是否有交集 return那里并不是[1] [1]比较
    private static boolean hasIntersection(int[] part1, int[] part2) {
        if(part1[0]==part2[0])
            return true;
        else if(part1[0]<part2[0])
            return part1[1]>=part2[0];
        else return part1[0]<=part2[1];
    }
}
