import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 示例 1：
 *
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 *
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * @date 2024/4/25 11:03
 */
public class Str394decodeString {

    public String decodeString(String s) {
        LinkedList<Character> linkedList=new LinkedList<>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(c!=']'){
                linkedList.addLast(c);
            }
            else{
                StringBuilder sb=new StringBuilder();
                while(true){
                    char temp=linkedList.removeLast();
                    if(temp=='[')
                        break;
                    sb.append(temp);
                }
                StringBuilder sb2 = new StringBuilder();
                // 获得所有数字 注意这里不能直接上来就linkedList.removeLast() 这样会把字母多移除一个
                while(!linkedList.isEmpty()){
                    char temp=linkedList.getLast();
                    if(!Character.isDigit(temp))
                        break;
                    sb2.append(linkedList.removeLast());
                }
                int count=Integer.parseInt(sb2.reverse().toString());
                String str=sb.reverse().toString();
                for(int j=0;j<count;j++){
                    for(int k=0;k<str.length();k++)
                        linkedList.addLast(str.charAt(k));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < linkedList.size(); i++) {
//            sb.append(linkedList.get(i));
//        }
        linkedList.stream().forEach(c->sb.append(c));
        return sb.toString();
    }

    // https://leetcode.cn/problems/decode-string/solutions/264879/zhan-de-ji-yi-nei-ceng-de-jie-ma-liao-bie-wang-lia/?envType=study-plan-v2&envId=top-100-liked
    public String decodeString1(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        String result = "";
        int num = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9')
                num = num * 10 + s.charAt(i) - '0';
            else if(s.charAt(i) == '['){
                numStack.push(num);
                num = 0;
                strStack.push(result);
                result = "";
            }
            else if(s.charAt(i) == ']'){
                String temp = "";
                int times = numStack.pop();
                for(int j = 0; j < times; j++)
                    temp += result;
                result = strStack.isEmpty() ? temp : strStack.pop() + temp;
            }
            else
                result += s.charAt(i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Str394decodeString().decodeString1("2[abc]3[cd]ef"));
    }
}

