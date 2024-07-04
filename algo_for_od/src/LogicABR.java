import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数轴×有两个点的序列 A={A1， A2, …, Am}和 B={B1, B2, ..., Bn}， Ai 和 Bj 均为正整数， A、 B 已经从小到大排好序，
 * A、 B 均肯定不为空，
 *
 * 给定一个距离 R（正整数），列出同时满足如下条件的所有（Ai， Bj）数对
 *
 * 条件：
 *
 * Ai <= Bj
 * Ai,Bj 距离小于等于 R，但如果 Ai 找不到 R 范围内的 Bj，则列出距它最近的 1 个 Bj，当然此种情况仍然要满足 1，
 * 但如果仍然找不到，就丢弃 Ai。
 *
 * 原型：
 *
 * 车路协同场景，一条路上发生了有很多事件（ A），要通过很多路测设备（ B）广播给路上的车，需要给每个事件找到一个合适的路测设备去发送广播消息。
 *
 * 输入描述
 * 按照人易读的格式输入一行数据，参见输入样例，其中“ ABR={， }”中的每个字符都是关键分割符，输入中无空格，其他均为任意正整数，
 *
 * 输入 A 和 B 已经排好序， A 和 B 的大小不超过 50，正整数范围不会超过 65535。
 *
 * 输出描述z
 * （ Ai,Bj）数对序列，排列顺序满足序列中前面的 Ax<=后面的 Ay，前面的 Bx<=后面的 By，
 *
 * 因为输入 A 和 B 已经排好序，所以实际上输出结果不用特意排序，排序不是考察点。
 *
 * 用例
 * 输入	A={1,3,5},B={2,4,6},R=1
 * 输出	(1,2)(3,4)(5,6)
 * 说明	无
 * @date 2024/4/13 10:28
 */
public class LogicABR {

    // matches是查找整个输入字符串与模式串是否完全匹配 find是只要输入字符串中存在子串与模式串匹配就会返回true
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String input = scanner.nextLine();
        Matcher matcher = Pattern.compile("A=\\{(.+)},B=\\{(.+)},R=(.+)").matcher(input);
        int[] arr1 = new int[0],arr2=new int[0];
        int r=0;
        if(matcher.matches()){
            arr1 = Arrays.stream(matcher.group(1).split(",")).mapToInt(Integer::parseInt).toArray();
            arr2 = Arrays.stream(matcher.group(2).split(",")).mapToInt(Integer::parseInt).toArray();
            r = Integer.parseInt(matcher.group(3));
        }
        StringBuilder sb=new StringBuilder();
        for (int i : arr1) {
            int count=0;
            for (int j : arr2) {
                if(j<i) continue;
                if(j-i<=r||count==0){
                    sb.append("(").append(i).append(",").append(j).append(")");
                    count++;
                }
                else break;
            }
        }
        System.out.println(sb.toString());
    }
}
