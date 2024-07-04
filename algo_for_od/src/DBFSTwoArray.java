import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 22:19
 */
/*
* 给定两个只包含数字的数组a，b，调整数组 a 里面的数字的顺序，使得尽可能多的a[i] > b[i]。

数组a和b中的数字各不相同。

输出所有可以达到最优结果的a数组的结果。

输入描述
输入的第一行是数组 a 中的数字，其中只包含数字，每两个数字之间相隔一个空格，a数组大小不超过10。

输入的第二行是数组 b 中的数字，其中只包含数字，每两个数字之间相隔一个空格，b数组大小不超过10。

输出描述
输出所有可以达到最优结果的 a 数组的数量。

用例
输入	11 8 20
10 13 7
输出	1
说明	最优结果只有一个，a = [11, 20, 8]，故输出1
输入	11 12 20
10 13 7
输出	2
说明	有两个a数组的排列可以达到最优结果，[12, 20, 11] 和 [11, 20, 12]，故输出2
输入	1 2 3
4 5 6
输出	6
说明	a无论如何都会全输，故a任意排列都行，输出所有a数组的排列，6种排法。
*/
import java.util.Arrays;
import java.util.Scanner;

public class DBFSTwoArray {
    static int[] a;
    static int[] b;

    static int maxBiggerCount = 0;
    static int ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        b = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(a);

        // 求解a的去重全排列
        dfs(0, new boolean[a.length], 0);

        System.out.println(ans);
    }

    public static void dfs(int level, boolean[] used, int biggerCount) {
        if (level >= a.length) {
            if (biggerCount > maxBiggerCount) {
                maxBiggerCount = biggerCount;
                ans = 1;
            } else if (biggerCount == maxBiggerCount) {
                ans++;
            }

            return;
        }

        for (int i = 0; i < a.length; i++) {
            if (used[i]) continue;

            // 树层去重
            if (i > 0 && a[i] == a[i - 1] && !used[i - 1]) continue;

            used[i] = true;
            // biggerCount记录当前全排列中a[level] > b[level]的位置的数量, 此时a[level] == a[i]
            dfs(level + 1, used, biggerCount + (a[i] > b[level] ? 1 : 0));
            used[i] = false;
        }
    }
}