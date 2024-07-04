/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/15 8:25
 */
import java.util.Scanner;
/*吃货"和"馋嘴"两人到披萨店点了一份铁盘（圆形）披萨，并嘱咐店员将披萨按放射状切成大小相同的偶数个小块。但是粗心的服务员将披萨切成了每块大小都完全不同奇数块，且肉眼能分辨出大小。
由于两人都想吃到最多的披萨，他们商量了一个他们认为公平的分法：从"吃货"开始，轮流取披萨。除了第一块披萨可以任意选取外，其他都必须从缺口开始选。
他俩选披萨的思路不同。"馋嘴"每次都会选最大块的披萨，而且"吃货"知道"馋嘴"的想法。
已知披萨小块的数量以及每块的大小，求"吃货"能分得的最大的披萨大小的总和。
输入描述
第 1 行为一个正整数奇数 N，表示披萨小块数量。
3 ≤ N < 500
接下来的第 2 行到第 N + 1 行（共 N 行），每行为一个正整数，表示第 i 块披萨的大小
1 ≤ i ≤ N
披萨小块从某一块开始，按照一个方向次序顺序编号为 1 ~ N
每块披萨的大小范围为 [1, 2147483647]
输出描述
"吃货"能分得到的最大的披萨大小的总和。
用例
输入 5
8
2
10
5
7
输出 19
说明
此例子中，有 5 块披萨。每块大小依次为 8、2、10、5、7。
按照如下顺序拿披萨，可以使"吃货"拿到最多披萨：
"吃货" 拿大小为 10 的披萨
"馋嘴" 拿大小为 5 的披萨
"吃货" 拿大小为 7 的披萨
"馋嘴" 拿大小为 8 的披萨
"吃货" 拿大小为 2 的披萨
至此，披萨瓜分完毕，"吃货"拿到的披萨总大小为 10 + 7 + 2 = 19
可能存在多种拿法，以上只是其中一种。*/
public class RecPizza_ {
    static int[] pizza;
    // 缓存
    static long[][] cache;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        pizza = new int[n];
        for (int i = 0; i < n; i++) {
            pizza[i] = sc.nextInt();
        }

        // 缓存
        cache = new long[n][n];

        long ans = 0;

        // i 指向首轮被"吃货"选取的披萨位置，可以理解为缺口位置，相当于给环切了一个口
        for (int i = 0; i < n; i++) {
            // i - 1 是缺口的左边披萨，check函数作用是防止 i - 1 越界, 进行绕环运动
// i + 1 是缺口的右边披萨，check函数作用是防止 i + 1 越界，进行绕环运动
// recursive的作用求解是"吃货"从缺失了 第 i 块的披萨铁盘 开始选，最终可得的最大披萨大小，
// 而第 i 块是首轮就被"吃货"拿走的，因此是recursive + pizza[i
            ans = Math.max(ans, recursive(check(i - 1), check(i + 1)) + pizza[i]);
        }

        System.out.println(ans);
    }

    public static long recursive(int l, int r) {
        // 进入递归前，"吃货"已经拿了披萨，因此进入递归后，轮到"馋嘴"拿
// 而"馋嘴"拿披萨的策略固定是：缺口左右两边中较大的那块
        if (pizza[l] > pizza[r]) {
            l = check(l - 1);
        } else {
            r = check(r + 1);
        }

        // 缓存优化，如果对应缺口状态的披萨铁盘结果已经算过了，则无需再次重复递归
        if (cache[l][r] > 0) {
            return cache[l][r];
        }

        if (l == r) {
            // 缓存对应缺口状态下，吃货可得的最大披萨大小
            cache[l][r] = pizza[l];
        } else {
            // 缓存对应缺口状态下，吃货可得的最大披萨大小
            // 如果还剩多块披萨，那么"吃货"有两种选择：
// 1、拿缺口左边的披萨
// 2、拿缺口右边的披萨
// 因此这里直接开两个递归分支，最终结果取较大值
            cache[l][r] =
                    Math.max(recursive(check(l - 1), r) + pizza[l], recursive(l, check(r + 1)) + pizza[r]);
        }

        return cache[l][r];
    }

    public static int check(int idx) {
        if (idx < 0) {
            idx = pizza.length - 1;
        } else if (idx >= pizza.length) {
            idx = 0;
        }

        return idx;
    }
}
