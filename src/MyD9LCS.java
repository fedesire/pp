import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description: 总结最长公共子序列和最长公共子串
 * @date 2023/12/19 17:36
 */
public class MyD9LCS {
    private static int[][] memo;
    //使用记忆化搜索的方式
    public static int lengthOfLCS(String s1,String s2){
        memo=new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            Arrays.fill(memo[i],-1);
        }
        return getLengthofLCS(s1,s2,s1.length()-1,s2.length()-1);
    }
    public static int getLengthofLCS(String s1,String s2,int m,int n){
        if(m<0||n<0)
            return 0;
        if(memo[m][n]!=-1)
            return memo[m][n];
        if(s1.charAt(m)==s2.charAt(n))
            memo[m][n]=1+getLengthofLCS(s1,s2,m-1,n-1);
        else
            memo[m][n]=Math.max(getLengthofLCS(s1,s2,m-1,n),getLengthofLCS(s1,s2,m,n-1));
        return memo[m][n];
    }

    //使用动态规划的方式
    public static int lcs(String s1,String s2){
        int m=s1.length();
        int n=s2.length();
        int[][] dp=new int[m][n]; //dp[i][j]表示s1[0...i] s2[0..j]的最长公共子序列长度
        //对dp的第0行和第0列初始化
        int start;
        if((start=s2.indexOf(s1.charAt(0)))!=-1){
            for(int j=start;j<n;j++)
                dp[0][j]=1;
        }
        start=-1;
        if((start=s1.indexOf(s2.charAt(0)))!=-1){
            for(int i=start;i<m;i++)
                dp[i][0]=1;
        }
        for (int i = 1; i <m ; i++) {
            for(int j=1;j<n;j++){
                if(s1.charAt(i)==s2.charAt(j))
                    dp[i][j]=dp[i-1][j-1]+1;
                else
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }

        //通过dp[i][j]反向生成最长公共子序列
        m=m-1;
        n=n-1;
        StringBuilder res=new StringBuilder();
        while(m>=0&&n>=0){
            if(s1.charAt(m)==s2.charAt(n)){
                res.insert(0,s1.charAt(m));
                m--;
                n--;
            }
            else if(m==0)
                n--;
            else if(n==0)
                m--;
            else{
                if(dp[m-1][n]>dp[m][n-1])
                    m--;
                else
                    n--;
            }
        }
        System.out.println(res.toString());
        return dp[m-1][n-1];
    }

    //看lc上大部分代码都是这版
    public static int lcs2(String s1,String s2){
        int m=s1.length(),n=s2.length();
        // memo 是 (m + 1) * (n + 1) 的动态规划表格
        // memo[i][j] 表示s1的前i个字符和s2前j个字符的最长公共子序列的长度
        // 其中memo[0][j] 表示s1取空字符串时, 和s2的前j个字符作比较
        // memo[i][0] 表示s2取空字符串时, 和s1的前i个字符作比较
        // 所以, memo[0][j] 和 memo[i][0] 均取0
        // 我们不需要对memo进行单独的边界条件处理 :-)
        int[][] dp=new int[m+1][n+1];
        for (int i = 1; i <=m ; i++) {
            for(int j=1;j<=n;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1))  //这里第i个字符索引是i-1
                    dp[i][j]=1+dp[i-1][j-1];
                else
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[m][n];

        // 通过dp反向求解s1和s2的最长公共子序列 注意此时是dp[m+1][n+1] 所以开始就从m n开始
 /*       m = s1.length();
        n = s2.length();
        StringBuilder res = new StringBuilder("");
        while(m > 0 && n > 0)
            if(s1.charAt(m-1 ) == s2.charAt(n-1)){
                res.insert(0, s1.charAt(m-1));
                m --;
                n --;
            }
            else if(dp[m - 1][n] > dp[m][n - 1])
                m --;
            else
                n --;

        return res.toString();*/
    }

    //---------最长公共子串---------------
    /*dp[i][j]表示以A中第i个字符结尾的子串和B中第j个字符结尾的子串的的最大公共子串
    * dp[i][0] = 0  dp[0][j] = 0
      if(str1[i] === str2[j]) dp[i][j] = dp[i-1][j-1] + 1  else dp[i][j] = 0*/
    public static String getResult(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        int[][] dp = new int[n + 1][m + 1];

        int max = 0;
        String ans = "";

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;

                    if (dp[i][j] > max) {
                        max = dp[i][j];
                        // 以第i个字符结尾的长度为max的子串 第i个字符下标是i-1 substring左闭右开 所以右边是i
                        ans = str1.substring(i - max, i);
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        String s1 = "ABCDGH";
        String s2 = "AEDFHR";
        System.out.println(lcs(s1,s2));
    }
}
