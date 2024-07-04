import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 s 则返回 true。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 * @date 2024/1/26 11:19
 */
public class DyL139wordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> hs=new HashSet<>(wordDict);
        int n=s.length();
        boolean[] dp=new boolean[n+1];
        //dp[i]表示s中的前i个字符串[0..i-1]能否由字典中的若干个单词拼出
        dp[0]=true;
        for(int i=1;i<=n;i++){
            //枚举所有分割点
            for(int j=0;j<i;j++){
                if(dp[j]&&hs.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[n];
    }

    /*对于检查一个字符串是否出现在给定的字符串列表里一般可以考虑哈希表来快速判断，同时也可以做一些简单的剪枝，枚举分割点
    的时候倒着枚举，如果分割点 j到 i 的长度已经大于字典列表里最长的单词的长度，那么就结束枚举*/
    public boolean wordBreak1(String s, List<String> wordDict) {
        Set<String> hs=new HashSet<>(wordDict);
        int n=s.length();
        boolean[] dp=new boolean[n+1];
        dp[0]=true;
        int maxLen=0;
        for (String s1 : wordDict) {
            maxLen=Math.max(maxLen,s1.length());
        }
        for(int i=1;i<=n;i++){
            //j>=0也是必须要有的
            for(int j=i-1;j>=0&&i-j<=maxLen;j--){
                if(dp[j]&&hs.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
