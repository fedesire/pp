/**
 * 最大子数组和
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组是数组中的一个连续部分
 * @date 2024/2/14 9:27
 */
public class DP53MaxSubArray {
    // dp[i]表示以nums[i]为结尾的「连续子数组的最大和」
    public int maxSubArray(int[] nums) {
        int n=nums.length;
        int[] dp=new int[n];
        dp[0]=nums[0];
        int res=nums[0];
        for(int i=1;i<n;i++){
            dp[i]=Math.max(dp[i-1]+nums[i],nums[i]);
            res=Math.max(res,dp[i]);
        }
        return res;
    }

    /*考虑到dp[i]只和dp[i−1] 相关，于是我们可以只用一个变量 pre来维护对于当前dp[i]的dp[i-1]的值是多少，从而让空间复杂度
    降低到 o(1)*/
    public int maxSubArray1(int[] nums) {
        int n=nums.length;
        int pre=0;
        int res=nums[0];
        for(int i=0;i<n;i++){
            pre=Math.max(pre+nums[i],nums[i]);
            res=Math.max(res,pre);
        }
        return res;
    }
}
