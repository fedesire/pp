import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 20:16
 */
/*
* 给定 M（0 < M ≤ 30）个字符（a-z），从中取出任意字符（每个字符只能用一次）拼接成长度为 N（0 < N ≤ 5）的字符串，

要求相同的字符不能相邻，计算出给定的字符列表能拼接出多少种满足条件的字符串，

输入非法或者无法拼接出满足条件的字符串则返回0。

输入描述
给定的字符列表和结果字符串长度，中间使用空格(" ")拼接

输出描述
满足条件的字符串个数

用例
输入	abc 1
输出	3
说明	给定的字符为a,b,c，结果字符串长度为1，可以拼接成a,b,c，共3种
输入	dde 2
输出	2
说明	给定的字符为dde，结果字符串长度为2，可以拼接成de,ed，共2种
*/
import java.util.Arrays;
import java.util.Scanner;

public class DBFSStringConcat {
    static String s;
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        s = sc.next();
        n = sc.nextInt();

        System.out.println(getResult());
    }

    public static int getResult() {
        if (s.length() < n) {
            // 无法拼接出满足条件的字符串
            return 0;
        }

        char[] cArr = s.toCharArray();

        for (char c : cArr) {
            // 输入非法
            if (c < 'a' || c > 'z') return 0;
        }

        // 排序cArr，可以方便后面求解全排列时，进行树层去重
        Arrays.sort(cArr);
        return dfs(cArr, -1, 0, new boolean[cArr.length], 0);
    }

    /**
     * 全排列求解
     *
     * @param cArr 基于cArr数组求解全排列
     * @param pre 排列最后一个字符在cArr中的位置
     * @param level 排列的长度
     * @param used used[i] 用于标记 cArr[i] 元素是否已使用
     * @param count 符号要求的排列有几个
     * @return count
     */
    public static int dfs(char[] cArr, int pre, int level, boolean[] used, int count) {
        // 当排列长度到达n，则是一个符合要求的排列
        if (level == n) {
            // 符合要求的排列个数+1
            return ++count;
        }

        for (int i = 0; i < cArr.length; i++) {
            // 每个字符只能用一次
            if (used[i]) continue;

            // 相同的字符不能相邻， pre指向前面一个被选择的字符的在cArr中的位置，i指向当前被选择的字符在cArr中的位置
            if (pre >= 0 && cArr[i] == cArr[pre]) continue;

            // 树层去重(去除重复排列)
            if (i > 0 && cArr[i] == cArr[i - 1] && !used[i - 1]) continue;

            used[i] = true;
            count = dfs(cArr, i, level + 1, used, count);
            used[i] = false;
        }

        return count;
    }
}
