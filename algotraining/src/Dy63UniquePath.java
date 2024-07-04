/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/29 11:27
 */
public class Dy63UniquePath {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m=obstacleGrid.length,n=obstacleGrid[0].length;
        int[][] dp=new int[m][n];//dp[i][j]表示从左上角到达i,j处共有多少条不同路径
        for(int i=0;i<m;i++){
            if(obstacleGrid[i][0]==0)
                dp[i][0]=1;
            else break;
        }
        for(int i=0;i<n;i++){
            if(obstacleGrid[0][i]==0)
                dp[0][i]=1;
            else break;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(obstacleGrid[i][j]==0)
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];

            }
        }
        return dp[m-1][n-1];

    }
}
