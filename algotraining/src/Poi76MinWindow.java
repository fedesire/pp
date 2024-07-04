import java.util.HashMap;
import java.util.Map;

/**
 * 最小覆盖子串
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 * 示例 1：
 *
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * @date 2024/5/8 10:31
 */
public class Poi76MinWindow {
    // 滑动窗口每次都会移动right指针 加入当前right处的元素 如果此时窗口不满足要求 就继续下一轮 否则满足要求了就记录答案并开始
    // 移动left指针 直到left的移动使得窗口收缩后不满足要求就停止 进入下一轮循环
    public String minWindow(String s,String t){
        Map<Character,Integer> need=new HashMap<>();
        for(int i=0;i<t.length();i++){
            char c=t.charAt(i);
            need.put(c,need.getOrDefault(c,0)+1);
        }

        int left = 0, right = 0; // 窗口的左右边界
        int valid = 0; // 已经匹配上的字符数量 如果t中包含两个a 只有当s中出现两个a时 valid才++
        int start = 0, minLen = Integer.MAX_VALUE; // 最小窗口的起始位置和长度
        // 只统计t中的那些字符在s中出现的次数
        Map<Character,Integer> curWindow=new HashMap<>();

        while(right<s.length()){
            // c是当前加入滑动窗口的元素 此时滑动窗口是[left,right]
            char c=s.charAt(right);
            if(need.containsKey(c)){
                curWindow.put(c,curWindow.getOrDefault(c,0)+1);
                // 这里必须用equals 否则用==会有那种超大案例会报错
                if(curWindow.get(c).equals(need.get(c))){
                    valid++;
                }
            }
            // 当窗口内的字符已经完全包含了 t 中的所有字符时更新答案 并尝试移动左边界
            while(valid == need.size()){
                if(right-left+1<minLen){
                    minLen=right-left+1;
                    start=left;
                }
                // 缩小窗口，移动左边界
                if(need.containsKey(s.charAt(left))){
                    curWindow.put(s.charAt(left),curWindow.get(s.charAt(left))-1);
                    if(curWindow.get(s.charAt(left))<need.get(s.charAt(left))){
                        valid--;
                    }
                }
                left++;
            }
            right++;
        }
        return minLen==Integer.MAX_VALUE?"":s.substring(start,start+minLen);
    }
    public static void main(String[] args) {
        Poi76MinWindow minWindow=new Poi76MinWindow();
        String s="ADOBECODEBANC";
        String t="ABC";
        System.out.println(minWindow.minWindow(s,t));
    }
}
