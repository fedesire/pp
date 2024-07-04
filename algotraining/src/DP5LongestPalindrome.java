/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/29 11:53
 */
public class DP5LongestPalindrome {
    //不对 只看lastIndex 假如lastIndex不符合但中间其他位置的有符合的 那也没计入
    public String longestPalindrome(String s) {
        char[] arr=s.toCharArray();
        int res=0;
        String ans="";
        for(int i=0;i<s.length();i++){
            int next=s.lastIndexOf(s.substring(i,i+1));
            if(next!=i){
                String st=s.substring(i,next+1);
                StringBuilder sb=new StringBuilder(st);
                if(sb.reverse().toString().equals(st)){
                    if(next-i+1>res){
                        res=next-i+1;
                        ans=st;
                    }
                }
            }
        }
        return ans;
    }

    public String longestPalindrome1(String s) {
        int n=s.length();
        if(n==1)
            return s;
        boolean[][] dp=new boolean[n][n];//dp[i][j]表示s[i..j]是否是回文串
        int maxLen=1;
        int begin=0;
//        String res=s.substring(0,1);
        for(int i=0;i<n;i++)
            dp[i][i]=true;
        for(int l=2;l<=n;l++){ //以长度length作为循环变量是为了保证下面计算dp[i][j]时 dp[i+1][j-1]一定是已经计算过的了
            //因为dp[i+1][j-1]长度更小
            for(int i=0;i+l-1<n;i++){
                int j=i+l-1;

                if(s.charAt(i)!=s.charAt(j)){
                    dp[i][j]=false;
                }else{
                    if(l<=3)
                        dp[i][j]=true;
                    else
                        dp[i][j]=dp[i+1][j-1];
                    //不能写在这里 因为上面的dp[i][j]=dp[i+1][j-1]不一定是true
                    /*if(l>maxLen){
                        maxLen=l;
                        res=s.substring(i,i+l);
                    }*/
                }

                if(dp[i][j]&&l>maxLen){
                    maxLen=l;
                    begin=i;
//                    res=s.substring(i,i+l);
                }
            }
        }
        return s.substring(begin,begin+maxLen);
//        return res;

    }
    /*dp[j][j]依赖dp[i+1][j-1] 只有先知道了 i+1才会知道i，所以遍历i的时候只需要倒着遍历就行了。*/
    public String longestPalindrome2(String s) {
        int n = s.length();
        String res = "";
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]); //j - i 代表长度减去 1
                // 和上面的一条语句等价
//                if(s.charAt(i)==s.charAt(j)){
//                    if(j-i<=2)
//                        dp[i][j]=true;
//                    // 注意这里必须要有else 否则会出界
//                    else dp[i][j]=dp[i+1][j-1];
//                }

                if (dp[i][j] &&  j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new DP5LongestPalindrome().longestPalindrome1("bb"));
    }
}
