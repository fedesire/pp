/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/21 23:30
 */
/*
* 有位客人来自异国，在该国使用 m 进制计数。

该客人有个幸运数字n（n < m），每次购物时，其总是喜欢计算本次支付的花费（折算为异国的价格后）中存在多少幸运数字。

问：当其购买一个在我国价值 k 的产品时，其中包含多少幸运数字?

输入描述
第一行输入为 k，n，m。

其中：

k 表示该客人购买的物品价值（以十进制计算的价格）
n 表示该客人的幸运数字
m 表示该客人所在国度采用的进制
输出描述
输出幸运数字的个数，行末无空格

备注
当输入非法内容时，输出0

用例
输入	10 2 4
输出	2
说明	10用4进制表示时为22，同时，异国客人的幸运数字是2，故而此处输出为2，表示有2个幸运数字。
输入	10 4 4
输出	0
说明
此时客人的幸运数字为4，但是由于该国最大为4进制，故而在该国的进制下不可能出现幸运数字，故而返回0。
*/
import java.util.Scanner;

public class MathGuest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long k = sc.nextLong();
        long n = sc.nextLong();
        long m = sc.nextLong();

        System.out.println(getResult(k, n, m));
    }

    public static long getResult(long k, long n, long m) {
        // 如果幸运数>=进制基数，比如m=2进制，要找n>=2的幸运数，那么肯定是没有的
        if (n >= m) {
            return 0;
        }

        long count = 0;

        // 除留取余
        while (k > 0) {
            long remain = k % m; // 余数就是m进制的每一位上“位值”

            // 按照 m进制的 “位值” 来对比幸运数 n
            if (remain == n) {
                count++;
            }

            k /= m;
        }

        return count;
    }
}