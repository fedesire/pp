/**
 * 有一个书店老板，他的书店开了 n 分钟。每分钟都有一些顾客进入这家商店。给定一个长度为 n 的整数数组 customers ，其中 customers[i] 是在第 i 分钟开始时进入商店的顾客数量，所有这些顾客在第 i 分钟结束后离开。
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
 * 当书店老板生气时，那一分钟的顾客就会不满意，若老板不生气则顾客是满意的。
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 minutes 分钟不生气，但却只能使用一次。
 * 请你返回 这一天营业下来，最多有多少客户能够感到满意 。
 * 示例 1：
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
 * 输出：16
 * 解释：书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 * @date 2024/5/1 10:07
 */
public class Poi1052maxSatisfied {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int total = 0;
        int n = customers.length;
        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) {
                total += customers[i];
            }
        }
        int increase = 0;
        for (int i = 0; i < minutes; i++) {
            increase += customers[i] * grumpy[i];
        }
        int maxIncrease = increase;
        for (int i = minutes; i < n; i++) {
            increase = increase - customers[i - minutes] * grumpy[i - minutes] + customers[i] * grumpy[i];
            maxIncrease = Math.max(maxIncrease, increase);
        }
        return total + maxIncrease;
    }

    public int maxSatisfied1(int[] customers, int[] grumpy, int minutes) {
        int ans = 0;
        // 先计算出 在没有使用抑制情绪下 最多能使多少客户满意
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                ans += customers[i];
            }
        }
        // 通过滑动窗口思想计算出,在 哪个区间使用抑制情绪能挽回最多的顾客
        int left = 0;
        int max = 0;
        int sum = 0;
        for (int right = 0; right < grumpy.length; right++) {
            // 流失的客户数
            if (grumpy[right] == 1) {
                sum += customers[right];
            }
            // 抑制情绪区间
            if ((right - left + 1) == minutes) {
                // 计算最大挽回数
                max = Math.max(sum, max);
                // 滑动窗口
                if (grumpy[left] == 1) {
                    sum -= customers[left];
                }
                left++;
            }
        }
        // 在没有使用抑制情绪下客户满意数 + 抑制情绪最多能挽回的客户数
        return ans + max;
    }

}
