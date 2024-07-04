import java.util.Scanner;

/**
 * 题目描述
 * 输入两个字符串 S 和 L ，都只包含英文小写字母。S长度 ≤ 100，L长度 ≤ 500,000。判定S是否是L的有效子串。
 * 判定规则：S 中的每个字符在 L 中都能找到（可以不连续），且 S 在Ｌ中字符的前后顺序与 S 中顺序要保持一致。
 * （例如，S = ”ace” 是 L= ”abcde” 的一个子序列且有效字符是a、c、e，而”aec”不是有效子序列，且有效字符只有a、e）
 * 输入描述
 * 输入两个字符串 S 和 L，都只包含英文小写字母。S长度 ≤ 100，L长度 ≤ 500,000。
 * 先输入S，再输入L，每个字符串占一行。
 * 输出描述
 * S 串最后一个有效字符在 L 中的位置。（首位从0开始计算，无有效字符返回-1）
 *
 * 坑 并不是判断是否是有效子串 而是返回S 串最后一个有效字符在 L 中的位置
 * @date 2024/4/11 17:06
 */
public class PoiSubstring {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s = sc.nextLine();
        String l=sc.nextLine();
        System.out.println(isValid(s,l));
    }

    private static int isValid(String s, String l) {
        int i=0,j=0;
        int m=s.length(),n=l.length();
        if(m>n)
            return -1;
        int last=-1;
        while(j<n&&i<m){
            if(s.charAt(i)==l.charAt(j)){
                i++;
                last=j;
            }
            j++;
        }
        return last;
    }
}
