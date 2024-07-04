/**
 * 给定一个只包含大写英文字母的字符串S，要求你给出对S重新排列的所有不相同的排列数。
 * 如：S为ABA，则不同的排列有ABA、AAB、BAA三种。
 * 输入描述
 * 输入一个长度不超过10的字符串S，我们确保都是大写的。
 * 输出描述
 * 输出S重新排列的所有不相同的排列数（包含自己本身）。
 * @date 2024/4/14 12:49
 */
import java.util.HashMap;
import java.util.Scanner;
//本题是一个数学问题。即求解无重复的全排列个数。
//
//首先我们可以求解出有重复的全排列个数，假设共有n个元素，则有重复的全排列个数为n!个。比如ABA有三个元素，则有重复的全排列个数为3! = 3x2x1 = 6。
//
//然后，我们需要找出重复的元素的个数，比如ABA中的A重复了2次，因此会生成2！组相同排列。
//
//因此不重复的全排列个数有 3！/ 2! = 3个。
//
//假设一共n个元素，其中某元素α重复x次，某元素β重复了y次，则最终不重复全排列个数有：
//
//n! / x! / y! 个

public class MathPermutation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        System.out.println(getResult(s));
    }

    public static int getResult(String s) {
        int total = getFact(s.length());

        HashMap<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char k = s.charAt(i);
            count.put(k, count.getOrDefault(k, 0) + 1);
        }

        for (Character k : count.keySet()) {
            int n = count.get(k);
            if (n > 1) total /= getFact(n);
        }

        return total;
    }

    public static int getFact(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) fact *= i;
        return fact;
    }
}
