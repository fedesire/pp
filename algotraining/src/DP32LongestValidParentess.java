import java.util.Deque;
import java.util.LinkedList;

/**
 * 最长有效括号 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号
 * 子串的长度。
 * @date 2024/4/27 22:41
 */

class DP32LongestValidParentess {
    public int longestValidParentheses(String s) {
        int res=0;
        // dp[i] 表示以下标 i字符结尾的最长有效括号的长度
        int[] dp=new int[s.length()];
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==')'){
                if(s.charAt(i-1)=='('){
                    dp[i]=(i>=2?dp[i-2]:0)+2;
                }
                else if(i-dp[i-1]>=1&&s.charAt(i-dp[i-1]-1)=='('){
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
            }
            res=Math.max(res,dp[i]);
        }
        return res;
    }

 // https://leetcode.cn/problems/longest-valid-parentheses/solutions/314827/shou-hua-tu-jie-zhan-de-xiang-xi-si-lu-by-hyj8/
    public int longestValidParentheses1(String s) {
        /*始终保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」，这样的做法主要是考虑了边界条件的处理，
        栈里其他元素维护左括号的下标, 主要思想就是遇到左括号入栈 遇到右括号就出栈 出栈的时候计算长度 如果不提前push一个-1 就会出现
        ()()这种情况两次出栈计算的长度都是2 没法得出4 即没法和前面的连起来 所以push一个-1 之后栈底始终维护成最后一个没有被匹配的
        右括号的下标 这样出栈的第一次计算是1--1=2 第二次计算的长度就是3--1=4 就能得到正确的长度*/
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    // 计算方式：当前索引 - 出栈后新的栈顶索引
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
    public static void main(String[] args) {
        DP32LongestValidParentess dp32LongestValidParentess=new DP32LongestValidParentess();
        System.out.println(dp32LongestValidParentess.longestValidParentheses1("()()"));
    }

}