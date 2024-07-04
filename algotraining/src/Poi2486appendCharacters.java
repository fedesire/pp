/**
 * 追加字符以获得子序列
 * 已解答
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给你两个仅由小写英文字母组成的字符串 s 和 t 。
 *
 * 现在需要通过向 s 末尾追加字符的方式使 t 变成 s 的一个 子序列 ，返回需要追加的最少字符数。
 *
 * 子序列是一个可以由其他字符串删除部分（或不删除）字符但不改变剩下字符顺序得到的字符串。
 * 示例 1：
 *
 * 输入：s = "coaching", t = "coding"
 * 输出：4
 * 解释：向 s 末尾追加字符串 "ding" ，s = "coachingding" 。
 * 现在，t 是 s ("coachingding") 的一个子序列。
 * 可以证明向 s 末尾追加任何 3 个字符都无法使 t 成为 s 的一个子序列。
 * @date 2024/5/1 9:51
 */
public class Poi2486appendCharacters {
    public int appendCharacters(String s, String t) {
        int m=s.length(),n=t.length();
        int i=0,j=0;
        while(i<m&&j<n){
            if(s.charAt(i)==t.charAt(j))
                j++;
            i++;
        }
        return n-j;
    }
}
