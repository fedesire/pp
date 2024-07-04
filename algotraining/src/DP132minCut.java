import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串
 * 返回符合要求的 最少分割次数
 * @date 2024/5/6 17:08
 */
public class DP132minCut {
    public int minCut(String s) {
        int n=s.length();
        boolean[][] dp=new boolean[n][n];
        for(int i=n-1;i>=0;i--){
            for(int j=i;j<n;j++){
                dp[i][j]=s.charAt(i)==s.charAt(j)&&(j-i<=2||dp[i+1][j-1]);
            }
        }
        int[] f=new int[n];//f[i]表示以s[0..i]的子串分割存成回文串需要的最少分割次数
        Arrays.fill(f,n);
        f[0]=0;
        for(int i=1;i<n;i++){
            // 必须要有这个判断
            if(dp[0][i])
                f[i]=0;
            else{
                for(int j=0;j<i;j++){
                    // 保证 s[j+1..i]是一个回文串，那么 f[i]就可以从 f[j] 转移而来，附加 1次额外的分割次数。
                    if(dp[j+1][i])
                        f[i]=Math.min(f[i],f[j]+1);
                }
            }
        }
        return f[n-1];

    }
    public static void main(String[] args) {
        DP132minCut dp132minCut=new DP132minCut();
        System.out.println(dp132minCut.minCut("aab"));
    }
}
