/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * @date 2024/4/27 22:55
 */
public class DP42Rain {
    // 能接雨水的柱子一定是左右两边不是空（即最左和最右不行）且左右两边都要有比当前柱子高的柱子才行
    public int trap(int[] height) {
        int n=height.length;
        int[] left=new int[n];
        int[] right=new int[n];
        // left[i]表示的是柱子i的左边柱子的最高高度（不包括柱子i) 注意这里height也是i-1
        for (int i = 1; i < n; i++) {
            left[i]=Math.max(height[i-1], left[i-1]);
        }
        for (int i = n-2; i >=0 ; i--) {
            right[i] = Math.max(height[i+1],right[i+1]);
        }
        int res=0;
        for (int i = 0; i < n; i++) {
            // 第i根柱子最多能蓄水的量，取决于其左边最高的柱子和右边最高的柱子的较矮的那个，且较矮的那根柱子 - 第i根柱子的高度
            // 就是第i根柱子的蓄水量，注意蓄水量最少为0
            res+=Math.max(0,Math.min(left[i],right[i])-height[i]);
        }
        return res;
    }

    public int trap1(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
//        leftMax[i] 表示下标i及其左边的位置中，height 的最大高度 所以下面的计算过程和上面的解有些地方不一样
        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }
        // 双指针解法 如果前缀最大值比后缀最大值小，就可以确定左边柱子能接的水量 反之如果后缀最大值小 就可以确定右边柱子能接的水量

    // 左指针右侧最大值一定>=右指针的右侧最大值 右指针左侧最大值一定>=左指针的左侧最大值
    // 那么现在问题就很简单了，遍历整个数组，比较左指针左侧的最大值和右指针的右侧最大值情况就能计算完整个数组 如果：
        // 左指针左侧最大值<右指针右侧最大值<=(左指针右侧最大值一定)，说明对于左指针而言，我的左边最高列小于右边最高列，
        // 而我的左边最高列我是知道具体值的，那么就计算左指针当前列可承载雨滴数，并右移左指针，右指针原地不动。 反之：(右指针
        // 左侧最大值一定)>=左指针左侧最大值>右指针右侧最大值，说明对于右指针而言，我左边的最高列>右边的最高列，那么就可依据
        // 右边最高列值进行计算，并左移右指针，左指针原地不动。
        public int trap2(int[] height) {
            int ans = 0;
            int left = 0, right = height.length - 1;
            int leftMax = 0, rightMax = 0;
            while (left < right) {
                leftMax = Math.max(leftMax, height[left]);
                rightMax = Math.max(rightMax, height[right]);
                if (leftMax < rightMax) {
                    ans += leftMax - height[left];
                    ++left;
                } else {
                    ans += rightMax - height[right];
                    --right;
                }
            }
            return ans;
        }



    public static void main(String[] args) {
        System.out.println(new DP42Rain().trap(new int[]{1,1,2,1}));
    }
}
