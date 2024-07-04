import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/23 8:53
 */
public class Lc64minPath  {
    public static int minPathSum(int[][] grid){
        int m=grid.length;
        int n=grid[0].length;
        int[][]dp=new int[m][n];//dp[i][j]表示grid[i][j]到右下角所需的最短路径
        dp[m-1][n-1]=grid[m-1][n-1];
        for(int i=m-2;i>=0;i--)
            dp[i][n-1]=dp[i+1][n-1]+grid[i][n-1];
        for(int j=n-2;j>=0;j--)
            dp[m-1][j]=dp[m-1][j+1]+grid[m-1][j];
        for(int i=m-2;i>=0;i--)
            for(int j=n-2;j>=0;j--){
                dp[i][j]=Math.min(dp[i+1][j],dp[i][j+1])+grid[i][j];
            }

 /*       //不单独写初始化 全写在一起
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                if(i==m-1&&j==n-1)
                    dp[i][j]=grid[i][j];
                else if(i==m-1)
                    dp[m-1][j]=dp[m-1][j+1]+grid[m-1][j];
                else if(j==n-1)
                    dp[i][n-1]=dp[i+1][n-1]+grid[i][n-1];
                else
                dp[i][j]=Math.min(dp[i+1][j],dp[i][j+1])+grid[i][j];
            }
        }*/
        return dp[0][0];
    }

    // 还是记这种 不容易出错
    public int minPathSum1(int[][] grid) {
        int m=grid.length,n=grid[0].length;
        int[][] dp=new int[m][n]; // 还有一种方法将dp[i][j]定义为从左上角到grid[i][j]所需的最短路径
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(i==0&&j==0)
                    dp[i][j]=grid[i][j];
                else if(i==0)
                    dp[i][j]=grid[i][j]+dp[i][j-1];
                else if(j==0)
                    dp[i][j]=grid[i][j]+dp[i-1][j];
                else
                    dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        /*
        dp[0][0]=grid[0][0];
        int pre=dp[0][0];
        for(int i=1;i<n;i++){
            dp[0][i]=grid[0][i]+pre;
            pre=dp[0][i];
        }
        pre=grid[0][0];
        for(int i=1;i<m;i++){
            dp[i][0]=grid[i][0]+pre;
            pre=dp[i][0];
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }*/
        return dp[m-1][n-1];

    }
    public int minPathSum3(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m+1][n+1];     // dp[i+1][j+1]表示到达grid[i][j]的最小路径和
        for(int i = 0; i <= m; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][1] = dp[1][0] = 0;  // 特殊处理处理dp[1][1]的左侧和上侧的状态，保证dp[1][1]=grid[0][0]
        // 状态转移
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                dp[i+1][j+1] = Math.min(dp[i+1][j], dp[i][j+1]) + grid[i][j];
            }
        }
        return dp[m][n];   // 最终存储的是到达grid[m-1][n-1]的最小路径和
    }
}

