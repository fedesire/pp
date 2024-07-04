import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/19 9:32
 */
public class Lc279PerfectSquares {
    private static int[] memo;
    public static int perfectSquares(int num){
        memo=new int[num+1];
        Arrays.fill(memo,-1);
        for (int i = 1; i <= Math.sqrt(num); i++) {
            memo[i*i]=1;
        }
        if(memo[num]!=-1)
            return memo[num];

        return getPerfectSquares(num);
    }

    //使用记忆化搜索的方式
    public static int getPerfectSquares(int n){
        for (int i = 1; i < Math.sqrt(n); i++) {
            if(memo[n-i*i]!=-1) {
                if (memo[n] == -1)
                    memo[n] = memo[n - i*i] + 1;
                else
                    memo[n] = Math.min(memo[n - i*i] + 1, memo[n]);
            }
            else{
                if (memo[n] == -1)
                    memo[n] =getPerfectSquares(n-i*i) + 1;
                else
                    memo[n] = Math.min(getPerfectSquares(n-i*i) + 1, memo[n]);

            }
        }
        return memo[n];
    }

    //使用动态规划自底向上解决问题
    public static int squares(int num){
        int[] memo=new int[num+1];
        Arrays.fill(memo,-1);
        for (int i = 1; i <= Math.sqrt(num); i++) {
            memo[i*i]=1;
        }
        if(memo[num]!=-1)
            return memo[num];
        for (int i = 2; i <=num; i++) {
            for(int j=1;j*j<i;j++){
                if(memo[i]==-1)
                    memo[i]=memo[i-j*j]+1;
                else
                    memo[i]=Math.min(memo[i-j*j]+1,memo[i]);
            }

        }
        return memo[num];
    }

//    动态转移方程为：dp[i] = MIN(dp[i], dp[i - j * j] + 1)，i 表示当前数字，
//    j*j 表示平方数 dp[i]表示数字i最少可由dp[i]个平方数构成
    public static int squares2(int n){
        int[] dp=new int[n+1];
        for (int i = 1; i <= n; i++) {
            //最坏的情况就是数字i分割成n个1 结果就是i 有时候当边界不确定的时候还可以设为Integer.MAX_VALUE MIN_VALUE
            dp[i]=i;
            //必须是<= 上面的解法不需要=是因为已经提前将所有1 4 9这些结果都置为1了 加了=会导致一些memo[i]=0
            for(int j=1;j*j<=i;j++)
                dp[i]=Math.min(dp[i],dp[i-j*j]+1);
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(squares2(12));
    }
}
