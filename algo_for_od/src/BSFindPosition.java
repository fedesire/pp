import java.util.Arrays;
import java.util.Scanner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/12 11:56
 */
public class BSFindPosition {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int target=scanner.nextInt();
        int i=0,j=nums.length-1;
        // 二分查找插入位置 最后是返回i(这是索引 具体位置是i+1) 二分查找最后一次i=j进入循环里 此时位置上的值可能是比target大的第一个值
        // 也可能是比target小的最后一个值 比target大 该位置就是target要插入的位置 此时i直接返回是对的 比target小此时i++ 刚好target要
        // 插入的位置就是i后面的位置 ++后返回i也是对的 所以退出循环后i的位置就是第一个大于target的位置
        // 也可以返回j+1(这是索引 具体位置是j+2)
        while(i<=j){
            int mid=i+(j-i)/2;
            if(nums[mid]<target)
                i++;
            else j--;
        }
        System.out.println(i+1);
    }
}
