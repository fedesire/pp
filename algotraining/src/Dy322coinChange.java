import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/26 11:58
 */
public class Dy322coinChange {
    public static int coinChange(int[] coins, int amount) {
        int[] dp=new int[amount+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=0;
        for(int i=1;i<=amount;i++){
            for(int j=0;j<coins.length;j++){
                if(i-coins[j]>=0){
                    if(dp[i-coins[j]]!=Integer.MAX_VALUE) //判断是因为Integer.MAX_VALUE+1=-2147483648
                        dp[i]=Math.min(dp[i],dp[i-coins[j]]+1);
                }
            }
        }
        if(dp[amount]==Integer.MAX_VALUE)
            return -1;
        return dp[amount];

    }

    public int coinChange1(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins ={2};
        int amount = 3;
        System.out.println(Integer.MAX_VALUE+1);
        System.out.println(coinChange(coins,amount));
    }
}
