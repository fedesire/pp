import java.util.Scanner;
// https://fcqian.blog.csdn.net/article/details/134759904?spm=1001.2014.3001.5502
public class DPStringPath_ {
    static String A;
    static String B;
    static int m;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        A = sc.next();
        B = sc.next();

        m = B.length();
        n = A.length();

        System.out.println(getResult());
    }
    // 动态规划解法（下面解法会超出内存限制，但是好理解，是后面一种压缩数组解法的基础）
    public static int getResult() {
        // dp[i][j] 记录的是(0,0)到达点(i, j)的最短路径
        int[][] dp = new int[m + 1][n + 1];

        // (0,0)到达矩阵第一行上的各点的最短路径，即为(0,0) -> (0,j) 的直线路径
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // (0,0)到达矩阵第一列上的各点的最短路径，即为(0,0) -> (i,0) 的直线路径
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(j - 1) == B.charAt(i - 1)) {
                    // 如果可以走斜线，则选走斜线的点
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 如果不能走斜线，则从当前点的上方点、左方点中选择一个较小值
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }

        return dp[m][n];
    }
    // 只用两行
    public static int getResult1() {
        // dp[i][j] 记录的是(0,0)到达点(i, j)的最短路径
        int[][] dp = new int[2][n + 1];

        // (0,0)到达矩阵第一行上的各点的最短路径，即为(0,0) -> (0,j) 的直线路径
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // (0,0)到达矩阵第一列上的各点的最短路径，即为(0,0) -> (i,0) 的直线路径
//        for (int i = 0; i <= m; i++) {
//            dp[i][0] = i;
//        }

        for (int i = 1; i <= m; i++) {
            dp[i%2][0]=i;
            for (int j = 1; j <= n; j++) {
                if (A.charAt(j - 1) == B.charAt(i - 1)) {
                    // 如果可以走斜线，则选走斜线的点
                    dp[i%2][j] = dp[(i - 1)%2][j - 1] + 1;
                } else {
                    // 如果不能走斜线，则从当前点的上方点、左方点中选择一个较小值
                    dp[i%2][j] = Math.min(dp[(i - 1)%2][j], dp[i%2][j - 1]) + 1;
                }
            }
        }

        return dp[m%2][n];
    }

    public static int getResult2() {
// 初始时preRow记录第一行上各点到(0,0)点的最短距离，即为(0,0) -> (0,j) 的直线路径
        int[] preRow = new int[n + 1];
        for (int j = 0; j <= n; j++) {
            preRow[j] = j;
        }
// 初始时curRow记录第二行上各点到(0,0)点的最短距离
        int[] curRow = new int[n + 1];
        for (int i = 1; i <= m; i++) {
// curRow[0]是指 (i, 0)点 到 (0,0)点 的最短距离，即为(0,0) -> (i, 0) 的直线路径
            curRow[0] = i;
            for (int j = 1; j <= n; j++) {
                if (A.charAt(j - 1) == B.charAt(i - 1)) {
// 如果可以走斜线，则选走斜线的点
                    curRow[j] = preRow[j - 1] + 1;
                } else {
// 如果不能走斜线，则从当前点的上方点、左方点中选择一个较小值
                    curRow[j] = Math.min(preRow[j], curRow[j - 1]) + 1;
                }
            }
// 压缩
            System.arraycopy(curRow, 0, preRow, 0, n + 1);
        }
        return curRow[n];
    }
}