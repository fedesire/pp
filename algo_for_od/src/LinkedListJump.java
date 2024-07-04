import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 给一个正整数数列 nums，一个跳数 jump，及幸存数量 left。
 * 运算过程为：从索引0的位置开始向后跳，中间跳过 J 个数字，命中索引为 J+1 的数字，该数被敲出，并从该点起跳，以此类推，直到幸存 left 个数为止，然后返回幸存数之和。
 * 约束：
 * 0是第一个起跳点
 * 起跳点和命中点之间间隔 jump 个数字，已被敲出的数字不计入在内。
 * 跳到末尾时无缝从头开始（循环查找），并可以多次循环。
 * 若起始时 left > len(nums) 则无需跳数处理过程。
 *
 * 输入描述
 * 第一行输入正整数数列
 *
 * 第二行输入跳数
 *
 * 第三行输入幸存数量
 *
 * 输出描述
 * 输出幸存数之和
 *
 * 用例
 * 输入	1,2,3,4,5,6,7,8,9
 * 4
 * 3
 * 输出	13
 * 说明	从1（索引为0）开始起跳，中间跳过 4 个数字，因此依次删除 6,2,8,5,4,7。剩余1,3,9，返回和为13
 * @date 2024/4/14 11:15
 */
public class LinkedListJump {
    // 注释前的方案有5个测试用例没通过 报MLE memory limit exceed 程序运行内存空间超出限制 说明解法可以 但是效率太低
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Integer[] nums = Arrays.stream(scanner.nextLine().split(",")).map(Integer::parseInt).toArray(Integer[]::new);
        int jump = scanner.nextInt();
        int survivals = scanner.nextInt();
        LinkedList<Integer> dq=new LinkedList<>(Arrays.asList(nums));
        int count=0;
        int start=1;
        while(dq.size()>survivals){
           /* Integer out = dq.removeFirst();
            if(count==jump+1){
                count=1;
            }
            else{
                dq.addLast(out);
                count++;
            }*/
            // +jump就直接到达要敲出的元素了
            start+=jump;
            start=start%dq.size();
            // start位置的元素移除了 那现在start索引其实代表的是下一个位置上的元素
            dq.remove(start);

        }
        System.out.println(dq.stream().reduce(Integer::sum).orElse(0));
    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int jump = Integer.parseInt(sc.nextLine());
        int left = Integer.parseInt(sc.nextLine());
        System.out.println(sumOfLeft(nums, jump, left));
    }
    public static int sumOfLeft(int[] nums, int jump, int left) {
        ArrayList<Integer> list =
                (ArrayList<Integer>) Arrays.stream(nums).boxed().collect(Collectors.toList());
// 从起跳点开始的话，需要跳jump+1次，到达需要删除的节点
// 从起跳点下一个节点开始的话，需要跳jump次，到达需要删除的节点
// 这里我们从起跳点的下一个节点开始,初始时起跳点为索引0，因此下一个节点为索引1
        int start = 1;
// 如果剩余节点数 > 幸存数量，则还需要继续删除节点
        while (list.size() > left) {
// 跳 jump 次
            start += jump;
// 为了避免越界，新起跳点索引位置对剩余节点数取余
            start %= list.size();
            list.remove(start);
        }
        return list.stream().reduce(Integer::sum).orElse(0);
    }
}
