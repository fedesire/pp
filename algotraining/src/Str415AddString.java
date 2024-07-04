/**
 *415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 *
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 * @date 2024/4/29 22:29
 */
public class Str415AddString {
    public String addStrings(String num1, String num2) {
        StringBuilder sb=new StringBuilder();
        int m=num1.length(),n=num2.length();
        int carry=0;
        int i,j;
        // 注意这里条件是或 否则循环退出后还要做判断
        for ( i = m-1,j=n-1; i >=0||j>=0||carry!=0 ; i--,j--) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int temp = carry + x+y;
            carry=temp/10;
            temp=temp%10;
            sb.append(temp);
        }

        return sb.reverse().toString();

    }

    public static void main(String[] args) {
        System.out.println(new Str415AddString().addStrings("123","456"));
    }
}
