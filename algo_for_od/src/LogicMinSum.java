import java.math.BigInteger;
import java.util.Scanner;

/**
 * 题目描述
 * 输入字符串s，输出s中包含所有整数的最小和。
 * 说明：
 * 字符串s，只包含 a-z A-Z ± ；
 * 合法的整数包括
 * 1） 正整数 一个或者多个0-9组成，如 0 2 3 002 102
 * 2）负整数 负号 – 开头，数字部分由一个或者多个0-9组成，如 -0 -012 -23 -00023
 *
 * 输入	bb1234aa
 * 输出	10
 * 说明	无
 * 输入	bb12-34aa
 * 输出	-31
 * 说明	1+2+（-34） = -31
 * 题目解析
 * 本题看上去很难，其实想清楚三点那就很简单
 *
 * 正数字符串的最小值如何计算？比如1234的最小值是多少，那肯定是1+2+3+4，即每位都是一个独立数，且都是一个小于10的数，它们之和就是最小的。
 * 负数字符串的最小值如何计算？比如-34，那肯定是整体当成一个负数时，最小。
 * 正数负数混合字符串最小值如何计算？比如12-34，那肯定是正数部分12每位单独计算，负数部分当成整体
 * @date 2024/4/11 13:42
 */
public class LogicMinSum {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s = scanner.nextLine();
//        long sum=0;
        BigInteger res=new BigInteger("0");

        // 遇到字母+跳过 遇到-就一直往后寻找所有的数字 直到遇到不是数字的 此时就可以计算当前这个负数的值了 由于可能会出现-后面直接就是字母
        // 此时就不需要计算 Long.parseLong("-") new BigInteger("-")都会报错 所以substring从i+1开始 前面用减法
        // 有可能会遇到大数 所以用了BigInteger
        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            if(String.valueOf(c).matches("[a-zA-Z+]"))
                continue;
            if(c=='-'){
                int j=i+1;

                while(j<s.length()&&s.charAt(j)<='9'&&s.charAt(j)>='0' )j++;
                if(i+1!=j){
//                    sum-=Long.parseLong(s.substring(i+1,j));
                    res=res.subtract(new BigInteger(s.substring(i+1,j)));
                }
                i=j-1;
            }
            else{
//                sum+=s.charAt(i)-'0';
                res=res.add(new BigInteger(c+""));
            }
        }
        System.out.println(res);
    }
}
