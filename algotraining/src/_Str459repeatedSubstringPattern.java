import java.util.regex.Pattern;

/**
 *给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
 * 示例 1:
 *
 * 输入: s = "abab"
 * 输出: true
 * 解释: 可由子串 "ab" 重复两次构成。
 * 示例 2:
 *
 * 输入: s = "aba"
 * 输出: false
 * 示例 3:
 *
 * 输入: s = "abcabcabcabc"
 * 输出: true
 * 解释: 可由子串 "abc" 重复四次构成。 (或子串 "abcabc" 重复两次构成。)
 * @date 2024/5/1 8:23
 */
public class _Str459repeatedSubstringPattern {
    // 不对
    public boolean repeatedSubstringPattern(String s) {
        char first=s.charAt(0);
        int end=0;
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==first){
                end=i;
                break;
            }
        }
        String pattern=s.substring(0,end);
        return Pattern.compile("("+pattern+")+").matcher(s).matches();
    }

    /*如果一个长度为 nnn 的字符串 sss 可以由它的一个长度为 n′n'n
′
  的子串 s′s's
′
  重复多次构成，那么：

nnn 一定是 n′n'n
′
  的倍数；

s′s's
′
  一定是 sss 的前缀；

对于任意的 i∈[n′,n)i \in [n', n)i∈[n
′
 ,n)，有 s[i]=s[i−n′]s[i] = s[i-n']s[i]=s[i−n
′
 ]。

也就是说，sss 中长度为 n′n'n
′
  的前缀就是 s′s's
′
 ，并且在这之后的每一个位置上的字符 s[i]s[i]s[i]，都需要与它之前的第 n′n'n
′
  个字符 s[i−n′]s[i-n']s[i−n
′
 ] 相同。

因此，我们可以从小到大枚举 n′n'n
′
 ，并对字符串 sss 进行遍历，进行上述的判断。注意到一个小优化是，因为子串至少需要重复一次，所以 n′n'n
′
  不会大于 nnn 的一半，我们只需要在 [1,n2][1, \frac{n}{2}][1,
2
n
​
 ] 的范围内枚举n'*/
    public boolean repeatedSubstringPattern1(String s) {
        int n = s.length();
        // i代表子字符串的长度 因为子串至少需要重复一次 所以i只需要枚举到n/2
        for (int i = 1; i * 2 <= n; ++i) {
            if (n % i == 0) {
                boolean match = true;
                for (int j = i; j < n; ++j) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern2(String s) {
        String str = s + s;
        return str.substring(1, str.length() - 1).contains(s);
    }
    public static void main(String[] args) {
        System.out.println(new _Str459repeatedSubstringPattern().repeatedSubstringPattern("abcabcabcabc"));
    }
}
