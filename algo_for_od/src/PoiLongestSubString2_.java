import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 22:55
 */
/*
* 给你一个字符串 s，字符串 s 首尾相连成一个环形，请你在环中找出 'l'、'o'、'x' 字符都恰好出现了偶数次最长子字符串的长度。
输入描述
输入是一串小写的字母组成的字符串
输出描述
输出是一个整数
备注
1 ≤ s.length ≤ 5 * 10^5
s 只包含小写英文字母
用例
输入 alolobo
输出 6
说明 最长子字符串之一是 "alolob"，它包含 'l'，'o' 各2个，以及 0 个 'x'。
输入 looxdolx
输出 7
说明 最长的子字符串是"oxdolxl"，由于是首尾连接在一起的，所以最后一个 'x' 和开头的 'l' 是连接在一起的，此字符串包含 2 个 'l'，2个'o'，2个'x'
输入 bcbcbc
输出 6
说明 这个示例中，字符串 "bcbcbc" 本身就是最长的，因为 'l'、'o'、'x' 都出现了 0 次。*/
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class PoiLongestSubString2_ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getResult(s));
    }

    public static int getResult(String s) {
        int status = 0b000;

        // map.get(i) 用于记录 状态i 出现的过的所有位置
        ArrayList<LinkedList<Integer>> map = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            map.add(new LinkedList<>());
        }

        map.get(0).add(-1);

        int maxLen = 0;

        for (int i = 0; i < s.length() * 2; i++) {
            // 第二轮时，i>=s.length()，此时i需要对s.length()求余，避免后面越界
            char c = s.charAt(i % s.length());

            switch (c) {
                case 'l':
                    status ^= 0b100;
                    break;
                case 'o':
                    status ^= 0b010;
                    break;
                case 'x':
                    status ^= 0b001;
                    break;
            }

            if (i < s.length()) {
                // 第一轮时，i ∈ [0, s.length()), 左闭右开
                // 记录该状态出现过的所有位置
                map.get(status).add(i);
            }

            while (map.get(status).size() > 0) {
                // status状态最早出现的位置
                int earliest = map.get(status).getFirst();

                // i 是当前位置，和 earliest 位置的状态相同
                if (i - earliest > s.length()) {
                    // 如果 (earliest, i] 范围子串长度超过s串长度，则说明earliest左越界，应该尝试更大一点的earliest
                    map.get(status).removeFirst();
                } else {
                    // 如果 (earliest, i] 范围子串长度未超过s串长度，则该范围子串就是一个符合要求的子串，记录此时子串长度
                    maxLen = Math.max(maxLen, i - earliest);
                    break;
                }
            }
        }

        return maxLen;
    }
}