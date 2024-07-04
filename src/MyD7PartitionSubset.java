/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/23 14:42
 */
public class MyD7PartitionSubset {
    public static boolean canPartition(int[] nums){
        int sum=0;
        for(int i=0;i<nums.length;i++)
            sum+=nums[i];
        if(sum%2==1)
            return false;
        int n=nums.length,c=sum/2;
        //dp[i][j]表示使用索引为[0...i]的这些元素,是否可以完全填充一个容量为j的背包 即元素是否能够和为j
        boolean[][] dp=new boolean[n][c+1];
        for(int j=0;j<=c;j++)
            dp[0][j]=(j==nums[0]);
//        dp[0][nums[0]]=true; 不能使用这种方式代替上面的for循环语句 因为有可能nums[0]>c 比如nums数组只有一个数100
        for (int i = 1; i < n; i++) {
            for(int j=0;j<=c;j++){
                dp[i][j]=dp[i-1][j];
                if(j>=nums[i])
                    dp[i][j]=dp[i][j]||dp[i-1][j-nums[i]];
            }
        }
        return dp[n-1][c];
    }

    //空间优化 只需要一维dp数组
    public static boolean canPartition2(int[] nums){
        int sum=0;
        for(int i=0;i<nums.length;i++)
            sum+=nums[i];
        if(sum%2==1)
            return false;
        int c=sum/2;
        boolean[] dp=new boolean[c+1];
        for(int j=0;j<=c;j++)
            dp[j]=(nums[0]==j);
        for(int i=1;i<nums.length;i++){
            //当j<nums[i]的时候 不用计算了 因为此时肯定不用考虑nums[i]的值 此时的memo[j]就是上一轮考虑i-1个
            // 物品时所得到的结果能否装满
            for(int j=c;j>=nums[i];j--)
                dp[j]=dp[j]||dp[j-nums[i]];
        }
        return dp[c];
    }
}
