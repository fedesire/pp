/**
 * 给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的回文串 。
 *
 * 在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。
 *
 *
 *
 * 示例 1:
 *
 * 输入:s = "abccccdd"
 * 输出:7
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 * @date 2024/4/26 10:07
 */
public class Str409LongestPalindrome {
    public int longestPalindrome(String s) {
        int[] count=new int[128];
        for(char c:s.toCharArray()){
            count[c]++;
        }
        // 记录有多少个字符出现了奇数次 回文串中只能有一个字符出现奇数次 所以构造回文串时每个本来出现奇数次的字符要减去一次 最后再+1
        int record=0;
        for(int i=0;i<128;i++){
            record+=count[i]%2;
        }
        return record==0?s.length():s.length()-record+1;
    }
}
