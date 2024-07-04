import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/12 9:11
 */
/*
* 给定用户密码输入流 input，输入流中字符 '<' 表示退格，可以清除前一个输入的字符，请你编写程序，输出最终得到的密码字符，并判断密码是否满足如下的密码安全要求。
密码安全要求如下：
密码长度 ≥ 8；
密码至少需要包含 1 个大写字母；
密码至少需要包含 1 个小写字母；
密码至少需要包含 1 个数字；
密码至少需要包含 1 个字母和数字以外的非空白特殊字符；
注意空串退格后仍然为空串，且用户输入的字符串不包含 '<' 字符和空白字符。
输入描述
用一行字符串表示输入的用户数据，输入的字符串中 '<' 字符标识退格，用户输入的字符串不包含空白字符，例如：
ABC<c89%000<
输出描述
输出经过程序处理后，输出的实际密码字符串，并输出改密码字符串是否满足密码安全要求。两者间由 ',' 分隔， 例如：
ABc89%00,true
用例
输入 ABC<c89%000<
输出 ABc89%00,true
说明 多余的C和0由于退格被去除,最终用户输入的密码为ABc89%00，且满足密码安全要求，输出true
题目解析
本题可以利用栈的压栈来模拟用户输入字符，弹栈来模拟'<'退格操作。
最后统计栈中：所有字符数量>=8 && 小写字母数量 >= 1 && 大写字母数量 >= 1 && 数字数量 >= 1 && 非字母数字空白字符数量 >= 1
若满足，则将栈中字符拼接为字符串后再追加“true”，否则追加“false”*/
public class DSValidPassword {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(validatePassword1(input));
    }

    // 不能这么做 *$2RgmPm<d<<4q%K<p 直接替换就没法删除P
    private static String validatePassword(String input) {
        input = input.replaceAll(".<", "");
        input=input.replaceAll("<","");
        StringJoiner joiner=new StringJoiner(",");
        joiner.add(input);

        if(input.length()<8){
            joiner.add("false");
        }
        else if (!input.matches(".*[A-Z]+.*")||!input.matches(".*[a-z]+.*")||!input.matches(".*[0-9]+.*")||!input.matches(".*[^A-Za-z0-9]+.*")) {
            joiner.add("false");
        }
        else
            joiner.add("true");
        return joiner.toString();
    }
    private static String validatePassword1(String input){
//        Stack<Character> stack=new Stack();
//        for (int i = 0; i < input.length(); i++) {
//            char c = input.charAt(i);
//            if(c!='<')
//                stack.push(c);
//            else if(!stack.isEmpty())
//                stack.pop();
//        }
        StringBuilder sb=new StringBuilder();
//        while(!stack.isEmpty())
//            sb.append(stack.pop());
//        input = sb.reverse().toString();
        // 也可以for循环里面是addLast removeLast while里面是removeFirst
        LinkedList<Character> linkedList=new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char c=input.charAt(i);
            if(c!='<')
                linkedList.push(c);
            else if(!linkedList.isEmpty())
                linkedList.pop();
        }
        while(!linkedList.isEmpty()){
            sb.append(linkedList.removeLast());
        }
        input=sb.toString();

        StringJoiner joiner=new StringJoiner(",");
        joiner.add(input);

        if(input.length()<8){
            joiner.add("false");
        }
        else if (!input.matches(".*[A-Z]+.*")||!input.matches(".*[a-z]+.*")||!input.matches(".*[0-9]+.*")||!input.matches(".*[^A-Za-z0-9]+.*")) {
            joiner.add("false");
        }
        else
            joiner.add("true");

        // 还是不对
//        Pattern p= Pattern.compile("(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[^A-Za-z0-9])");
//        Matcher m=p.matcher(input);
//        if (m.matches()) {
//            joiner.add("true");
//        }
//        else joiner.add("false");

        return joiner.toString();

    }
}
