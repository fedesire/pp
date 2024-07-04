/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 提示：
 * 1 <= num1.length, num2.length <= 200
 * num1 和 num2 只能由数字组成。
 * num1 和 num2 都不包含任何前导零，除了数字0本身。
 * @date 2024/5/1 9:05
 */
public class Str43Multiply {
    // 将num2的每一位和num1相乘 得到的所有结果相加 num1 = "123", num2 = "456" 123*4+123*50+123*400
    public String multiply(String num1, String num2){
        if(num1.equals("0") || num2.equals("0"))
            return "0";
        int m=num1.length();
        int n=num2.length();
        String ans="0";
        // num2 的第 i 位数字 与 num1 相乘
        for (int i = n-1; i >=0 ; i--) {
            StringBuilder builder = new StringBuilder();
            // 如果是最后一位就不用补0 倒数第二位 补一个0
            for (int j = n-1; j >i ; j--) {
                builder.append(0);
            }
            int y=num2.charAt(i)-'0';
            int carry=0;

            for (int j = m-1; j>=0 ; j--) {
                int x = num1.charAt(j)-'0';
                int product=x*y+carry;
                builder.append(product%10);
                carry=product/10;
            }
            if(carry!=0)
                builder.append(carry);
            ans=addString(ans,builder.reverse().toString());
        }
        return ans;

    }

    /**
     * 对两个字符串数字进行相加，返回字符串形式的和
     */
    private String addString(String s1, String s2) {
        int i=s1.length()-1;
        int j=s2.length()-1;
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        while(i>=0||j>=0||carry!=0){
            int x=i>=0?s1.charAt(i)-'0':0;
            int y=j>=0?s2.charAt(j)-'0':0;
            int sum=x+y+carry;
            builder.append(sum%10);
            carry=sum/10;
            i--;
            j--;
        }
        return builder.reverse().toString();
    }
    public static void main(String[] args) {
        String num1 = "9133";
        String num2 = "0";
        Str43Multiply str43Multiply = new Str43Multiply();
        System.out.println(str43Multiply.multiply(num1,num2));
    }
}
