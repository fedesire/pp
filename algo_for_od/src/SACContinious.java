import java.util.Arrays;
import java.util.HashMap;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 9:50
 */
/*
* 给定一个字符串，只包含大写字母，求在包含同一字母的子串中，长度第 k 长的子串的长度，相同字母只取最长的那个子串。
输入描述
第一行有一个子串(1<长度<=100)，只包含大写字母。
第二行为 k的值
输出描述
输出连续出现次数第k多的字母的次数。
用例
输入
AAAAHHHBBCDHHHH
3
输出 2
说明
同一字母连续出现的最多的是A和H，四次；
第二多的是H，3次，但是H已经存在4个连续的，故不考虑；
下个最长子串是BB，所以最终答案应该输出2。
输入
AABAAA
2
输出 1
说明
同一字母连续出现的最多的是A，三次；
第二多的还是A，两次，但A已经存在最大连续次数三次，故不考虑；
下个最长子串是B，所以输出1。
输入 ABC
4
输出 -1
说明 只含有3个包含同一字母的子串，小于k，输出-1
输入 ABC
2
输出 1
说明 三个子串长度均为1，所以此时k = 1，k=2，k=3这三种情况均输出1。特此说明，避免歧义。*/
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class SACContinious {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int k = sc.nextInt();
        System.out.println(getResult(s, k));
    }

    public static int getResult(String s, int k) {
        if (k <= 0) return -1;

        // 在结尾加一个字符 这样保证前面的字符次数都能算进去
        s += "0";

        HashMap<Character, Integer> count = new HashMap<>();

        char pre = s.charAt(0);
        int len = 1;

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);

            if (pre == c) {
                len++;
            } else {
                if (!count.containsKey(pre) || count.get(pre) < len) {
                    count.put(pre, len);
                }
                len = 1;
                pre = c;
            }
        }

        Integer[] arr = count.values().toArray(new Integer[0]);

        if (k > arr.length) return -1;
        else {
            Arrays.sort(arr, (x, y) -> y - x);
            return arr[k - 1];
        }
    }
}