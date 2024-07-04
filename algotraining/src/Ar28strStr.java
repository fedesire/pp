/**
 * 找出字符串中第一个匹配项的下标
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果 needle 不是 haystack 的一部分，则返回  -1 。
 * @date 2024/2/20 10:53
 */
public class Ar28strStr {
    public int strStr(String haystack, String needle) {
        //枚举所有起始点 还可以不用substring方法 里面用while循环一个一个匹配
        //注意循环里的条件 否则可能会导致下标溢出
        for(int i=0;i<haystack.length()-needle.length()+1;i++){
            if(haystack.charAt(i)==needle.charAt(0)){
                if(haystack.substring(i,i+needle.length()).equals(needle))
                    return i;
            }
        }
        return -1;
    }
}
