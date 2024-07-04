/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 15:17
 */
/*
* 题目描述
老李是货运公司承运人，老李的货车额定载货重量为 wt。
现有两种货物：
货物 A 单件重量为 wa，单件运费利润为 pa
货物 B 单件重量为 wb，单件运费利润为 pb
老李每次发车时载货总重量刚好为货车额定的载货重量 wt，车上必须同时有货物 A 和货物 B ，货物A、B不可切割。
老李单次满载运输可获得的最高利润是多少？
输入描述
第一列输入为货物 A 的单件重量 wa
0 < wa < 10000
第二列输入为货物 B 的单件重量 wb
0 < wb < 10000
第三列输入为货车的额定载重 wt
0 < wt < 100000
第四列输入为货物 A 的单件运费利润 pa
0 < pa < 1000
第五列输入为货物 B 的单件运费利润 pb
0 < pb < 1000
输出描述
单次满载运输的最高利润
用例
输入 10 8 36 15 7
输出 44
说明 无
输入 1 1 2 1 1
输出 2
说明 无*/
/*
* 本题其实第一眼看过去就是完全背包问题，且是需要装满背包的完全背包问题。

但是具体读题后，发现这题的货物只有两种，因此使用完全背包解题反而有点复杂了。

如果货物有多种的话，那么此题用完全背包解题是非常合适的。

因此，我这里提供了两种解法，一种是暴力枚举，一种是完全背包。其中完全背包解法，主要是让大家熟悉下背包问题中，需要恰好装满背包时的dp初始化逻辑。

但是本题考试时可以选择更简单的暴力枚举方式。
*/
import java.util.Arrays;
import java.util.Scanner;

public class DPTransfer {
    // 输入输出处理
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int wa = sc.nextInt();
        int wb = sc.nextInt();
        int wt = sc.nextInt();
        int pa = sc.nextInt();
        int pb = sc.nextInt();

        // 装入货车的A货物数量至少1件，至多(wt - wb) / wa件
        int minX = 1;
        int maxX = (wt - wb) / wa;

        // 记录最大利润
        int ans = 0;

        // 枚举A货物的可能数量
        for (int x = minX; x <= maxX; x++) {
            // B货物可能的总重量
            int remain = wt - wa * x;

            if (remain % wb == 0) {
                // B货物的数量
                int y = remain / wb;

                // 计算利润，保留最大利润
                ans = Math.max(ans, pa * x + pb * y);
            }
        }

        System.out.println(ans);
    }
}
 class Main2 {
    // 输入输出处理
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int wa = sc.nextInt();
        int wb = sc.nextInt();
        int wt = sc.nextInt();
        int pa = sc.nextInt();
        int pb = sc.nextInt();

        // 背包承重, 这里减去必然需要装入的一件A货物和一件B货物
        wt -= wa + wb;

        // 物品的重量
        int[] w = new int[] {wa, wb};

        // 物品的价值
        int[] p = new int[] {pa, pb};

        // maxP是装满承重为 wt 的背包的最大价值
        int maxP = getResult(wt, 2, w, p);

        if (maxP >= 0) {
            // 如果maxP是非负数，则存在装满背包的方案，注意最后返回结果需要加上初始装上车的一件A和一件B的利润
            System.out.println(maxP + pa + pb);
        } else {
            // 如果maxP是负数，则说明不存在装满wt的方案，此时直接直接0
            System.out.println(0);
        }
    }

    /**
     * 完全背包
     *
     * @param bag 背包承重
     * @param n 物品种数
     * @param w 物品的重量数组
     * @param p 物品的价值数组
     * @return 装满背包的最大价值
     */
    public static int getResult(int bag, int n, int[] w, int[] p) {
        // dp[i]表示装满承重为 i 的背包的最大价值
        int[] dp = new int[bag + 1];

        // 装满背包的背包问题，初始化时需要将除了dp[0]外的dp元素值设为负无穷
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) { // 遍历物品
            for (int j = w[i]; j <= bag; j++) { // 遍历背包承重，完全背包这里要正序遍历
                dp[j] = Math.max(dp[j], dp[j - w[i]] + p[i]);
            }
        }

        // 返回装满承重为bag的背包的最大价值
        return dp[bag];
    }
}