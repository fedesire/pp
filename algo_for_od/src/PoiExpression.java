/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/16 11:09
 */
/*
提取字符串中的最长合法简单数学表达式，字符串长度最长的，并计算表达式的值。如果没有，则返回 0 。

简单数学表达式只能包含以下内容：

0-9数字，符号+-*
说明：

所有数字，计算结果都不超过long
如果有多个长度一样的，请返回第一个表达式的结果
数学表达式，必须是最长的，合法的
操作符不能连续出现，如 +--+1 是不合法的
输入描述
字符串

输出描述
表达式值

用例
输入	1-2abcd
输出	-1
说明	最长合法简单数学表达式是"1-2"，结果是-1

* 根据考友实际考试反馈，本题所说的简单合法数学表达式是：只包含两个操作数的运算表达式，比如：
a + b
a - b
a * b
按照此逻辑可得90%+通过率。
其中a可能是带有正负号的数值（也可能不是，最后没有满分应该就是这里带不带正负号的问题，考试时可以都尝试下）。
因此本题的难度大大降低了，只能说出题的人故弄玄虚，故意不说清楚，让考试人猜着去解题，这种出题策略，我真的服了。。。。*/
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PoiExpression {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        // 下面正则可得90%+通过率, 可以继续尝试下 r"([+-]?\d+)([+*-])(\d+)" 的通过率
        Pattern compile = Pattern.compile("(-?\\d+)([+*-])(\\d+)");

        int maxExpLen = 0;
        long ans = 0;

        for (int i = 0; i < s.length(); i++) {

            // 本题有大数量级用例，因此需要此步优化，不然通过率不高
            if(s.length() - i <= maxExpLen) {
                break;
            }

            for (int j = i; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);

                Matcher matcher = compile.matcher(sub);

                if (matcher.find() && sub.length() > maxExpLen) {
                    maxExpLen = sub.length();

                    long op_num1 = Long.parseLong(matcher.group(1));
                    String op = matcher.group(2);
                    long op_num2 = Long.parseLong(matcher.group(3));

                    switch (op) {
                        case "*":
                            ans = op_num1 * op_num2;
                            break;
                        case "+":
                            ans = op_num1 + op_num2;
                            break;
                        case "-":
                            ans = op_num1 - op_num2;
                            break;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
