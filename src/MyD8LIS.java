import java.util.Arrays;
// 这里的子序列不要求必须是连续的
/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/19 16:47
 */
public class MyD8LIS {
    public static int lengthOfLIS(int[] nums){
        if(nums.length==0)
            return 0;
        //memo[i]表示以nums[i]为结尾的最长上升子序列的长度
        int[] memo=new int[nums.length];
        Arrays.fill(memo,1);
        for (int i = 1;i < nums.length; i++) {
            for(int j=0;j<i;j++){
                if(nums[j]<nums[i])
                    memo[i]=Math.max(memo[i],memo[j]+1);
            }
        }
        return Arrays.stream(memo).max().getAsInt();
    }

    //使用贪心+二分查找
    /*状态设计思想：依然着眼于某个上升子序列的 结尾的元素，如果 已经得到的上升子序列的结尾的数越小，那么遍历
    的时候后面接上一个数，会有更大的可能构成一个长度更长的上升子序列。既然结尾越小越好，我们可以记录 在长度
    固定的情况下，结尾最小的那个元素的数值，这样定义以后容易得到「状态转移方程」。*/
    public int lengthOfLIS1(int[] nums) {
        int n=nums.length;
        if(n<=1)
            return n;
        int[] tail=new int[n];//tail[i]表示长度为i+1的上升子序列的末尾最小是几
        tail[0]=nums[0];
        int index=0; //index表示有序数组 tail 的最后一个已经赋值元素的索引
        for(int i=1;i<n;i++){
            if(nums[i]>tail[index]){
                tail[++index]=nums[i];
            }
            else{
                //在tail数组的[0..index]中寻找第一个比nums[i]大的数 将其换成nums[i]
                int left=0,right=index;
                while(left<right){
                    int mid=left+(right-left)/2;
                    if(tail[mid]<nums[i])
                        left=mid+1;
                    else
                        right=mid;
                }
                tail[left]=nums[i];
            }
        }
        return index+1;
    }

    public static void main(String[] args) {

        int nums1[] = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums1));
        // 4

        // ---

        int nums2[] = {4, 10, 4, 3, 8, 9};
        System.out.println(lengthOfLIS(nums2));
        // 3

        // ---

        int nums3[] = {2, 2};
        System.out.println(lengthOfLIS(nums3));
        // 1

        // ---

        int nums4[] = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(lengthOfLIS(nums4));
        // 6
    }
}
