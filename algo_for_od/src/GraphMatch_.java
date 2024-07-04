import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 21:16
 */
// https://blog.csdn.net/qfc_128220/article/details/134628637?spm=1001.2014.3001.5501
import java.util.Arrays;
import java.util.Scanner;

public class GraphMatch_ {
    static int n;
    static int m;
    static int k;
    static int[][] matrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        int min = 1;
        int max = Integer.MIN_VALUE;

        matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
                max = Math.max(max, matrix[i][j]);
            }
        }

        // 二分枚举第K大值
        while (min <= max) {
            // mid就是被枚举出来的N个数中的第K大值
            int mid = (min + max) >> 1;

            // 检查mid作为N个数中第K大值时，是否存在N-K+1个不大于它的值
            if (check(mid)) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(min);
    }

    public static boolean check(int kth) {
        // 利用二分图最大匹配来求解，小于等于kth（第K大值）的元素个数（即二分图最大匹配）
        int smallerCount = 0;

        // 记录每个列号的匹配成功的行号
        int[] match = new int[m];
        // 初始时每个列号都处于未配对状态，此时将列号配对的行号赋值为-1
        Arrays.fill(match, -1);

        // 遍历行号，每个行号对互相心仪的列号发起配对请求
        for (int i = 0; i < n; i++) {
            // 记录增广路访问过的列号
            boolean[] vis = new boolean[m];
            if (dfs(i, kth, match, vis)) smallerCount++;
        }

        return smallerCount >= n - k + 1;
    }

    public static boolean dfs(int i, int kth, int[] match, boolean[] vis) {
        // 行号 i 发起了配对请求

        // 遍历每一个列号j
        for (int j = 0; j < m; j++) {
            // 如果当前列号j未被增广路探索过 && 当前列j行i可以配对（如果行列号位置(i,j)对应矩阵元素值小于等于kth（第K大值），则可以配对）
            if (!vis[j] && matrix[i][j] <= kth) {
                vis[j] = true;

                // 如果对应列号j未配对，或者，已配对但是配对的行号match[j]可以找到其他列号重新配对
                if (match[j] == -1 || dfs(match[j], kth, match, vis)) {
                    // 则当前行号i 和 列号j 可以配对
                    match[j] = i;
                    return true;
                }
            }
        }

        return false;
    }
}