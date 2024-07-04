/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 14:48
 */
/*
* 园区某部门举办了Family Day，邀请员工及其家属参加；
将公司园区视为一个矩形，起始园区设置在左上角，终点园区设置在右下角；
家属参观园区时，只能向右和向下园区前进，求从起始园区到终点园区会有多少条不同的参观路径。
* 输入描述
第一行为园区的长和宽；

后面每一行表示该园区是否可以参观，0表示可以参观，1表示不能参观

输出描述
输出为不同的路径数量

用例
输入	3 3
0 0 0
0 1 0
0 0 0
输出	2
说明	无*/
/*
* 本题如果地图矩阵数量级过大的话，深搜解题会超时。因此，更优解法是利用动态规划，我们可以定义一个dp二维数组，dp[i][j]的含义是：
* 从坐标(0,0)到达坐标(i, j)的路径数

而本题说只能向下或者向右运动，因此到达一个坐标点，可能来自其上方，亦可能来自其左方

因此 dp[i][j] = dp[i-1][j] + dp[i][j-1]
*/
import java.util.Scanner;

public class DPVisitCount {
    static int[][] matrix;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // OD题目描述中，长一般对应行数，宽一般对应列数
        int n = sc.nextInt(); // 长 -> 行数
        int m = sc.nextInt(); // 宽 -> 列数

         matrix = new int[n][m]; // 地图矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        // 如果起点和终点不能参观，则没有路径
        if (matrix[0][0] == 1 || matrix[n - 1][m - 1] == 1) {
            System.out.println(0);
            return;
        }

//        dfs(0,0,n,m);
        long[][] dp = new long[n][m];
        dp[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) continue;

                if (i > 0) {
                    dp[i][j] += dp[i - 1][j];
                }

                if (j > 0) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }

        System.out.println(dp[n - 1][m - 1]);
//        System.out.println(res);
    }
    static int res=0;
    static int[][] offsets={{0,1},{1,0}};

    //这种写法也行 刚开始是y+写成x+导致栈溢出错误了
   static void dfs(int x,int y,int m,int n){
        if(x==m-1&&y==n-1){
            res++;
            return ;
        }
        for (int[] offset : offsets) {
            int newx=x+offset[0];
            int newy=y+offset[1];
            if(newx>=m||newy>=n||matrix[newx][newy]==1) continue;
            dfs(newx,newy,m,n);
        }
    }

    // DFS可能不是解决这类问题的最高效方法，特别是对于大型矩阵，因为它可能会重复访问许多相同的格子。然而，对于小型的或者结构特殊的矩阵，
    // DFS是可行的。
    //在实际应用中，对于此类只能向下或向右移动的路径问题，动态规划通常更为高效，因为它可以避免重复计算并直接得到每个格子的路径数。
    // 对于大规模数据或需要高性能的情况，应该优先考虑使用动态规划。
/*   static void dfs(int i, int j,int m,int n) {
        // 如果当前位置超出矩阵范围或不可走，则直接返回
        if (i >= m || j >= n || matrix[i][j] == 1) {
            return;
        }

        // 如果到达右下角，则找到一条路径，增加计数
        if (i == m - 1 && j == n - 1) {
            res++;
            return;
        }

        // 向下搜索
        dfs(i + 1, j,m,n);
        // 向右搜索
        dfs(i, j + 1,m,n);
    }*/
}