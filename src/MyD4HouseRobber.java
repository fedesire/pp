import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/19 11:43
 */
public class MyD4HouseRobber {
    private static int[] memo;
    public static int rob(int[] nums){
        memo=new int[nums.length];
        Arrays.fill(memo,-1);
        memo[0]=nums[0];
        memo[1]=nums[1];
        return tryRob(nums,nums.length-1);
    }
    public static int tryRob(int[] nums,int n){
        if(n<0)
            return 0;
        if(memo[n]!=-1)
            return memo[n];
        int res=0;
        for (int i = 0; i <= n; i++) {
            res=Math.max(res,nums[i]+tryRob(nums,i-2));
        }
        memo[n]=res;
        return res;
    }

    public int rob1(int[] nums) {
        int n=nums.length;
        if(n==1)
            return nums[0]; //很容易漏写 因为下面直接取值dp[n-2]了 所以必须要判断
        // dp[i] 表示考虑抢劫 nums[i...n) 所能获得的最大收益
        int dp[]=new int[n];
        dp[n-1]=nums[n-1];
        dp[n-2]=Math.max(nums[n-1],nums[n-2]);
        for(int i=n-3;i>=0;i--){
            // 或者当前房子放弃, 从下一个房子开始考虑
            // 或者抢劫当前的房子, 从i+2以后的房子开始考虑
            dp[i]=Math.max(nums[i]+dp[i+2],dp[i+1]);
        }
        return dp[0];
    }
}
