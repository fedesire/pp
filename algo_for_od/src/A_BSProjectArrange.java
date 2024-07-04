import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 16:52
 */
/*
* 项目组共有 N 个开发人员，项目经理接到了 M 个独立的需求，每个需求的工作量不同，且每个需求只能由一个开发人员独立完成，不能多人合作。

假定各个需求直接无任何先后依赖关系，请设计算法帮助项目经理进行工作安排，使整个项目能用最少的时间交付。

输入描述
第一行输入为 M 个需求的工作量，单位为天，用逗号隔开。

例如：

X1 X2 X3 ... Xm

表示共有 M 个需求，每个需求的工作量分别为X1天，X2天，...，Xm天。其中：

0 < M < 30
0 < Xm < 200
第二行输入为项目组人员数量N

例如：

5

表示共有5名员工，其中 0 < N < 10

输出描述
最快完成所有工作的天数

例如：

25

表示最短需要25天完成所有工作

用例
输入	6 2 7 7 9 3 2 1 3 11 4
2
输出	28
说明	共有两位员工，其中一位分配需求 6 2 7 7 3 2 1 共需要28天完成，另一位分配需求 9 3 11 4 共需要27天完成，故完成所有工作至少需要28天。
题目解析
*/
import java.util.Scanner;

public class A_BSProjectArrange {
    static Integer[] balls;
    static int n;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        balls = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        n = Integer.parseInt(sc.nextLine());

        System.out.println(getResult());
    }

    public static int getResult() {
        // 这里对balls降序，有利于降低后面回溯操作的复杂度
        Arrays.sort(balls, (a, b) -> b - a);

        // 二分范围：即每个桶的容量最小，最大值
        int min = balls[0]; // 桶至少要有max(balls)的容量
        int max = Arrays.stream(balls).reduce(Integer::sum).get(); // 当只有一个桶时，此时该桶容量要装下所有balls

        // 记录题解
        int ans = max;

        // 二分找中间值作为桶容量
        while (min <= max) {
            int mid = (min + max) >> 1;

            if (check(0, new int[n], mid)) {
                // 如果k个mid容量的桶，可以装完所有balls，那么mid容量就是一个可能解，但不一定是最优解，我们应该尝试更小的桶容量
                ans = mid;
                max = mid - 1;
            } else {
                // 如果k个mid容量的桶，无法装完所有balls，那么说明桶容量取小了，我们应该尝试更大的桶容量
                min = mid + 1;
            }
        }

        return ans;
    }

    /**
     * @param index 当前轮次要被装入的球的索引（balls数组索引） 当前要完成的第index项任务
     * @param buckets 桶数组，buckets[i]记录的是第 i 个桶已使用的容量  buckets[i]=x代表i号员工的前x天都接了工作了
     * @param limit 每个桶的最大可使用容量
     * @return k个桶（每个桶容量limit）是否可以装下balls中所有球
     */
    public static boolean check(int index, int[] buckets, int limit) {
        // 如果balls已经取完，则说明k个limit容量的桶，可以装完所有balls
        if (index == balls.length) return true;

        // select是当前要装的球
        int selected = balls[index];

        // 遍历桶 balls[index]这个球 一个一个桶试 装到每个桶里是否可行
        // 当前第index项工作由bucket[i]完成
        for (int i = 0; i < buckets.length; i++) {
            // 剪枝优化 buckets[i] == buckets[i - 1] i桶剩余的容量和i-1桶相同 那球无法装到i-1桶 就肯定无法装到i桶
            if (i > 0 && buckets[i] == buckets[i - 1]) continue;

            // 如果当前桶装了当前选择的球后不超过容量限制，则可以装入
            if (selected + buckets[i] <= limit) {
                buckets[i] += selected;
                // 递归装下一个球
                if (check(index + 1, buckets, limit)) return true;
                // 如果这种策略无法装完所有球，则回溯
                buckets[i] -= selected;
            }
        }

        return false;
    }
}