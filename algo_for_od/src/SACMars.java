import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 8:54
 */
/*
* 已知火星人使用的运算符为#、$，其与地球人的等价公式如下：
x#y = 2*x+3*y+4
x$y = 3*x+y+2
其中x、y是无符号整数
地球人公式按C语言规则计算
火星人公式中，$的优先级高于#，相同的运算符，按从左到右的顺序计算
现有一段火星人的字符串报文，请你来翻译并计算结果。
输入描述
火星人字符串表达式（结尾不带回车换行）
输入的字符串说明：字符串为仅由无符号整数和操作符（#、$）组成的计算表达式。
例如：123#4$5#67$78。
用例保证字符串中，操作数与操作符之间没有任何分隔符。
用例保证操作数取值范围为32位无符号整数。
保证输入以及计算结果不会出现整型溢出。
保证输入的字符串为合法的求值报文，例如：123#4$5#67$78
保证不会出现非法的求值报文，例如类似这样字符串：
#4$5 //缺少操作数
4$5# //缺少操作数
4#$5 //缺少操作数
4 $5 //有空格
3+4-5*6/7 //有其它操作符
12345678987654321$54321 //32位整数计算溢出
输出描述
根据输入的火星人字符串输出计算结果（结尾不带回车换行）。
用例
输入 7#6$5#12
输出 226
说明
7#6$5#12
=7#(3*6+5+2)#12
=7#25#12
=(2*7+3*25+4)#12
=93#12
=2*93+3*12+4
=226*/
public class SACMars {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.println(getResult(str));
    }

    public static long getResult(String str) {
        Pattern pattern= Pattern.compile("(\\d+)\\$(\\d+)");
        while(true){
            Matcher matcher=pattern.matcher(str);
            if(!matcher.find()){
                break;
            }
            String substr = matcher.group(0);
            long x = Long.parseLong(matcher.group(1));
            long y = Long.parseLong(matcher.group(2));
            //replaceFirst方法的第一个参数匹配串，将被用作正则表达式的内容，而$又是正则表达式的元字符，因此我们需要对$进行转义，当成普通字符使用。
            // $匹配输入字符串结尾的位置
            str=str.replaceFirst(substr.replace("$","\\$"),3 * x + y + 2 + "");
        }
        // 必须是0L 否则返回的就是int型
       return Arrays.stream(str.split("#")).map(Long::parseLong).reduce((x, y) -> 2 * x + 3 * y + 4).orElse(0L);
    }
}
