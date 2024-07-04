import java.util.Arrays;

/**
 * 环形数组的最大子数组之和
 * @date 2024/2/14 10:14
 */
public class DP918SubArrSumCircular {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int preMax = nums[0], maxRes = nums[0];
        int preMin = nums[0], minRes = nums[0];
        int sum = nums[0];
        for (int i = 1; i < n; i++) {
            preMax = Math.max(preMax + nums[i], nums[i]);
            maxRes = Math.max(maxRes, preMax);
            preMin = Math.min(preMin + nums[i], nums[i]);
            minRes = Math.min(minRes, preMin);
            sum += nums[i];
        }

        if (maxRes < 0) {
            return maxRes;
        } else {
            return Math.max(maxRes, sum - minRes); //当数组中所有数都是负数 sum=minRes=数组中所有元素之和
        }
    }

    public static void main(String[] args) {
        System.out.println(new DP918SubArrSumCircular().maxSubarraySumCircular(new int[]{-1, -2, -3}));
    }
}
