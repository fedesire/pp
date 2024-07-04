import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * @date 2024/4/25 9:53
 */
public class Poi438findAnagrams {
    /*在字符串 sss 中构造一个长度为与字符串 ppp 的长度相同的滑动窗口，并在滑动中维护窗口中每种字母的数量；当窗口中每种字母的数量
    与字符串 ppp 中每种字母的数量相同时，则说明当前窗口为字符串 ppp 的异位词。*/
    public List<Integer> findAnagrams(String s, String p) {
        int m=s.length(),n=p.length();
        List<Integer> res=new ArrayList<>();
        if(m<n)
            return res;
        int[] sCount=new int[26];
        int[] pCount=new int[26];
        for(int i=0;i<n;i++){
            sCount[s.charAt(i)-'a']++;
            pCount[p.charAt(i)-'a']++;
        }
        if(Arrays.equals(sCount,pCount))
            res.add(0);
        for(int i=0;i<m-n;i++){
            sCount[s.charAt(i)-'a']--;
            sCount[s.charAt(i+n)-'a']++;
            // 去除下标i处的字符 所以当前是从i+1开始
            if(Arrays.equals(sCount,pCount))
                res.add(i+1);
        }
        return res;
    }

    /*使用char数组来存放p字符串中的字符，并按字典顺序排序。然后每次截取s字符串的[i,i + p.length())的区间字符串，将之转化为按字典
    顺序排序的字符数组。每次比较两个数组是否相等即可。*/
    public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();
        if(sLen < pLen){return ans;}
        char[] pArray = p.toCharArray();
        Arrays.sort(pArray);
        for (int i = 0; i < sLen - pLen + 1; i++) {
            String str = s.substring(i,i + pLen);
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            if(Arrays.equals(chars, pArray)){
                ans.add(i);
            }
        }
        return ans;
    }

}
