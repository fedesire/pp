import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/11 9:19
 */
/*
* 给定一个非空字符串S，其被N个‘-’分隔成N+1的子串，给定正整数K，要求除第一个子串外，其余的子串每K个字符组成新的子串，并用‘-’分隔。
对于新组成的每一个子串，如果它含有的小写字母比大写字母多，则将这个子串的所有大写字母转换为小写字母；
反之，如果它含有的大写字母比小写字母多，则将这个子串的所有小写字母转换为大写字母；大小写字母的数量相等时，不做转换。
输入描述
输入为两行，第一行为参数K，第二行为字符串S。
输出描述
输出转换后的字符串。
用例
输入 3
12abc-abCABc-4aB@
输出 12abc-abc-ABC-4aB-@
说明
子串为12abc、abCABc、4aB@，第一个子串保留，
后面的子串每3个字符一组为abC、ABc、4aB、@，
abC中小写字母较多，转换为abc，
ABc中大写字母较多，转换为ABC，
4aB中大小写字母都为1个，不做转换，
@中没有字母，连起来即12abc-abc-ABC-4aB-@
输入 12
12abc-abCABc-4aB@
输出 12abc-abCABc4aB@
说明
子串为12abc、abCABc、4aB@，第一个子串保留，
后面的子串每12个字符一组为abCABc4aB@，
这个子串中大小写字母都为4个，不做转换，
连起来即12abc-abCABc4aB@*/
public class SACStringSplit {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int k=Integer.parseInt(scanner.nextLine());
        String[] split = scanner.nextLine().split("-");
        StringJoiner resJoiner=new StringJoiner("-");
        resJoiner.add(split[0]);

        StringBuilder sb=new StringBuilder();
        for (int i = 1; i < split.length; i++) {
            sb.append(split[i]);
        }
        String leftString = sb.toString();

        int n=leftString.length();
        for (int i = 0; i < n; i=i+k) {
            String substring;
            // 发现我else经常忘记写 导致多走了一次分支
            if(i+k>n)
                substring=leftString.substring(i,n);
            else
                substring= leftString.substring(i, i + k);
//            substring=leftString.substring(i,Math.min(i+k,n)); // 可以代替if else

            int lowerCount=0,upperCount=0;
            for (int j = 0; j < substring.length(); j++) {
                if(substring.substring(j,j+1).matches("[a-z]"))
                    lowerCount++;
                else if(substring.substring(j,j+1).matches("[A-Z]"))
                    upperCount++;
            }
            if(lowerCount>upperCount)
                substring=substring.toLowerCase();
            else if(lowerCount<upperCount)
                substring=substring.toUpperCase();
            resJoiner.add(substring);
        }
        System.out.println(resJoiner);
    }
    public static String convert(String str) {
        int lowerCount = 0;
        int upperCount = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'a' && c <= 'z') lowerCount++;
            else if (c >= 'A' && c <= 'Z') upperCount++;
        }

        if (lowerCount > upperCount) return str.toLowerCase();
        else if (lowerCount < upperCount) return str.toUpperCase();
        else return str;
    }
}
