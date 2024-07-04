/**
 * 盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * @date 2024/2/2 9:23
 */
/*双指针解法 容纳的水量是由两个指针指向的数字中较小值∗指针之间的距离 每次移动的是数字较小的那个指针
每次总是将高度较小的那个柱子排除掉 即将那一侧的下标向里移动*/
public class Ar11maxArea {
    public int maxArea(int[] height) {
        int res = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            int area = (j - i) * Math.min(height[i], height[j]);
            res = Math.max(res, area);
            //当heighti<heightj时 i++代表的意思是当前的i和后面的所有j的组合(即当前的j和j前面的情况）都可以排除掉 因为这些一定是
            // 比目前 area盛水容量更小的
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }
}
