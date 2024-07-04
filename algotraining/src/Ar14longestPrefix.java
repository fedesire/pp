import java.util.Arrays;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 *
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * @date 2024/2/5 9:50
 */
public class Ar14longestPrefix {
    public String longestCommonPrefix(String[] strs) {
        String res="";
        int minLen=strs[0].length();
        for(int i=1;i<strs.length;i++){
            minLen=Math.min(minLen,strs[i].length());
        }
//        Arrays.stream(strs).map(s->s.length()).min(Integer::compareTo).orElse(0);
        int index=1;
        while(index<=minLen){
            String temp=strs[0].substring(0,index);
            int i;
            for(i=1;i<strs.length;i++){
                if(!strs[i].substring(0,index).equals(temp))  return res;
            }
            res=temp;
            index++;
        }
        return res;
    }

    /*纵向扫描时，从前往后遍历所有字符串的每一列，比较相同列上的字符是否相同，如果相同则继续对下一列进行比较，
    如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀。*/
    public String longestCommonPrefix1(String[] strs){
        int curIndex=0;
        while(curIndex<strs[0].length()){
            char c = strs[0].charAt(curIndex);
            for (int i = 1; i < strs.length; i++) {
                if(curIndex==strs[i].length()||strs[i].charAt(curIndex)!=c){
                    return strs[0].substring(0,curIndex);
                }
            }
            curIndex++;
        }
        return strs[0].substring(0,curIndex);
    }
}
