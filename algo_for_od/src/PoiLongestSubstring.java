import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/11 17:36
 */
/*
* 给定一个字符串，只包含字母和数字，按要求找出字符串中的最长（连续）子串的长度，字符串本身是其最长的子串，子串要求：

1、 只包含1个字母(a~z, A~Z)，其余必须是数字；

2、 字母可以在子串中的任意位置；

如果找不到满足要求的子串，如全是字母或全是数字，则返回-1。

输入描述
字符串(只包含字母和数字)

输出描述
子串的长度

用例
输入	abC124ACb
输出	4
说明	满足条件的最长子串是C124或者124A，长度都是4
输入	a5
输出	2
说明	字符串自身就是满足条件的子串，长度为2
输入	aBB9
输出	2
说明	满足条件的子串为B9，长度为2
输入	abcdef
输出	-1
说明	没有满足要求的子串，返回-1
*/
public class PoiLongestSubstring {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s = sc.nextLine();
        // 滑动窗口里只有一个字母 就右边界移动 多了字母 就左边界向右拓展
        // 这里的滑动窗口是保证窗口里最多只有一个字母 每次都会计算窗口的长度 也有可能到结束时一直没有字母 所以返回时要判断
        // 这里的res其实一直表示的是最多只包含一个字母的滑动窗口的长度 并不代表最终结果
        int l=0,r=0;
        int alphaCount=0,lastAlphaIndex=-1;
        int res=-1;
        // 碰到字母后 除第一次之外 之后每次l都要后移 因为有可能字符串全是字母 滑动窗口里也每次都维护了一个字母 但这种不能算 所以遇到字母时需要
        // 判断l==r =就说明只有一个字母 不能算入结果
        while(r<s.length()){
            char c=s.charAt(r);
            if(Character.isAlphabetic(c)) {
                if(alphaCount==0){
                    alphaCount++;
                }
                else {
                    l=lastAlphaIndex+1;
                }
                lastAlphaIndex=r;
                if(l==r){
                    r++;
                    continue;
                }

            }
            res=Math.max(res,r-l+1);
            r++;
        }
        System.out.println(alphaCount==0?-1:res);
    }
    private static int getResult(String str) {
        int maxLen = -1;
        boolean hasLetter = false;

        int l = 0, r = 0;
        LinkedList<Integer> letterIdx = new LinkedList<>();

        while (r < str.length()) {
            char c = str.charAt(r);

            if (isLetter(c)) {
                hasLetter = true;
                letterIdx.add(r);

                if (letterIdx.size() > 1) {
                    l = letterIdx.removeFirst() + 1;
                }

                if (r == l) {
                    r++;
                    continue;
                }
            }

            maxLen = Math.max(maxLen, r - l + 1);
            r++;
        }

        if (!hasLetter) return -1;
        return maxLen;
    }

    public static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

}
