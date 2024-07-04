import java.util.Scanner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/16 8:48
 */
/*
* 给你一个字符串 s，首尾相连成一个环形，请你在环中找出 'o' 字符出现了偶数次最长子字符串的长度。

输入描述
输入是一个小写字母组成的字符串

输出描述
输出是一个整数

备注
1 ≤ s.length ≤ 500000
s 只包含小写英文字母
用例
输入	alolobo
输出	6
说明	最长子字符串之一是 "alolob"，它包含2个'o'
输入	looxdolx
输出	7
说明	最长子字符串"oxdolxl"，由于是首尾连接一起的，所以最后一个'x'和开头的'l'是连接在一起的，此字符串包含2个'o'
输入	bcbcbc
输出	6
说明	这个示例中，字符串"bcbcbc"本身就是最长的，因为'o'都出现了0次。
*/
    /*本题很简单，只要统计出输入字符串s中'o'的个数：
如果 'o' 为偶数个，则s本身就是一个含有偶数个'o'的子字符串，结果输出s.length
如果 'o' 为奇数个，由于s是环形的，因此只要任选环中任意一个'o'解开（删除），剩下的就是含有偶数个 'o' 的子串，该子串长度为 s.length - 1*/
public class LogicLongestOString {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int ocount=0;
        int count=0;

        /*while(scanner.hasNext()){
            if (scanner.next().equals("o")) {
                ocount++;
            }
            count++;
        }*/
        String s = scanner.nextLine();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='o') {
                ocount++;
            }

        }
        if(ocount%2==0)
            System.out.println(count);
        else
            System.out.println(count-1);
    }
}
