import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/13 11:41
 */
public class Lc290wordPattern {
    public boolean wordPattern(String pattern,String s){
        String[] arr=s.split(" ");
        HashMap<Character,String> hm=new HashMap<>();
        if(arr.length!=pattern.length())
            return false;
        for (int i = 0; i < pattern.length(); i++) {
            /*if(!hm.containsKey(pattern.charAt(i))){
                if(!hm.containsValue(arr[i])) //这里第一次写的时候没有考虑到要判断value
                    hm.put(pattern.charAt(i),arr[i]);
                else return false;

            }
            else if(!hm.get(pattern.charAt(i)).equals(arr[i]))
                return false;*/
            //和上面的写法等价只用一条if语句就能概述所有情况
            if((hm.containsKey(pattern.charAt(i))&&!hm.get(pattern.charAt(i)).equals(arr[i]))||(!hm.containsKey(pattern.charAt(i))&&hm.containsValue(arr[i])))
                return false;
            hm.put(pattern.charAt(i),arr[i]);

        }
        return true;

    }

    //更好的方法(不一定是更好 代码确实简短 但是indexOf()复杂度高）
    // 不需要pattern的字符和s中的单词构建一个映射 只要比较这两个字符串自身的特点是否相同
    // 即每个字符在字符串中第一次出现的位置是否相同
    public boolean wordPattern1(String pattern, String s) {
        List<String> ls = Arrays.asList(s.split(" "));
        int n = pattern.length();
        if (n != ls.size()) return false;
        for (int i = 0; i < n; i++) {
            if (pattern.indexOf(pattern.charAt(i)) != ls.indexOf(ls.get(i)))
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        String pattern="abba",s="dog dog dog dog";
        System.out.println((new Lc290wordPattern()).wordPattern(pattern,s));
    }
}
