/**
 * 给定一个含有N个正整数的数组, 求出有多少个连续区间（包括单个正整数）, 它们的和大于等于x。
 *
 * 输入描述
 * 第一行两个整数N x（0 < N <= 100000, 0 <= x <= 10000000)
 *
 * 第二行有N个正整数（每个正整数小于等于100)。
 *
 * 输出描述
 * 输出一个整数，表示所求的个数。
 *
 * 注意：此题对效率有要求，暴力解法通过率不高，请考虑高效的实现方式。
 *
 * 用例
 * 输入
 * 3 7
 * 3 4 7
 *
 * 输出	4
 * 说明	第一行的3表示第二行数组输入3个数，第一行的7是比较数，用于判断连续数组是否大于该数；组合为 3 + 4; 3 + 4 + 7; 4 + 7; 7; 都大于等于指定的7；所以共四组。
 * 输入
 * 10 10000
 * 1 2 3 4 5 6 7 8 9 10
 *
 * 输出	0
 * 说明	所有元素的和小于10000，所以返回0。
 * @date 2024/5/3 8:26
 */

import java.util.Scanner;

public class PoiContinuiousSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int x = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        System.out.println(getResult(n, x, arr));
    }

    public static long getResult(int n, int x, int[] arr) {
        int[] preSum = new int[n + 1];
        // presum[i]表示前i个数的和 preSum[0] = 0 求arr[l...r]的和 arr[l]是第l+1个数 求第l+1个数到第r+1
        // 个数的和即是preSum[r+1] - preSum[l]
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + arr[i - 1];
        }

        int l = 0;
        int r = 1;
        long ans = 0;

        while (r <= n) {
            // 前r个数-前l个数 >= x 则从第r个数到第n个数都是满足条件的区间
            if (preSum[r] - preSum[l] >= x) {
                ans += n - r + 1;
                l++;
                r = l + 1;
            } else {
                r++;
            }
        }

        return ans;
    }

    // 滑动窗口的解法
    private static long getAns(int N, int x, int[] nums) {
        // 算法逻辑
        long ans = 0;

        int l = 0;
        int r = 0;

        long window_sum = 0;
        while (r < N) {
            if (r < l || window_sum + nums[r] < x) {
                window_sum += nums[r++];
            } else {
                // nums[l..r]>=x 则从下标r到n-1的区间都是满足条件的区间
                ans += N - r;
                window_sum -= nums[l++];
            }
        }
        return ans;
    }
    public static long getResultMy(int n, int x, int[] arr) {
        // 1.初始化左右指针
        int left = 0;
        int right = 0;

        // 2.记录最值：保存最大的满足题目要求的 子数组/子串 长度、个数
        long ans = 0;

        // 3.记录状态值：比如Map记录窗口、string记录合法字串、int记录合法个数等，本题中就是窗口的和
        int windowSum = 0;

        while (right < n) {
            // num[r]进入窗口
            windowSum += arr[right];

            while (windowSum >= x&&right>=left) {
                // 注意：每当找到一个满足条件的区间[l,r]时，[l,r+1]..[l, n-1]也一定是满足条件的，因此对应的方案数就是n - 1 - r + 1
                ans += n - right;
                // 缩小窗口
                windowSum -= arr[left];
                left++;
            }

            right++;
        }

        return ans;
    }
}