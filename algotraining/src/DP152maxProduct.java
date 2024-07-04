/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/5/10 8:39
 */
public class DP152maxProduct {
    /*dp[i][1] 表示：以 nums[i] 结尾的连续子序列的乘积的最大值；
dp[i][0] 表示：以 nums[i] 结尾的连续子序列的乘积的最小值。
https://leetcode.cn/problems/maximum-product-subarray/solutions/251440/dong-tai-gui-hua-li-jie-wu-hou-xiao-xing-by-liweiw/?envType=study-plan-v2&envId=top-100-liked*/
    public int maxProduct(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        // 状态定义：以索引 i 结尾
        int[][] dp = new int[len][2];

        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        for (int i = 1; i < len; i++) {
            if (nums[i] >= 0) {
                dp[i][1] = Math.max(nums[i], dp[i - 1][1] * nums[i]);
                dp[i][0] = Math.min(nums[i], dp[i - 1][0] * nums[i]);
            } else {
                dp[i][1] = Math.max(nums[i], dp[i - 1][0] * nums[i]);
                dp[i][0] = Math.min(nums[i], dp[i - 1][1] * nums[i]);
            }
        }

        int res = dp[0][1];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, dp[i][1]);
        }
        return res;
    }

    public static void main(String[] args) {
        DP152maxProduct dp152maxProduct=new DP152maxProduct();
        int[] nums=new int[]{2,3,-2,4};
        System.out.println(dp152maxProduct.maxProduct(nums));
    }
}
