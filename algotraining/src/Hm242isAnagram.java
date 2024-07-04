import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author xqi
 * @version 1.0
 * @description: 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 * @date 2024/1/3 10:24
 */
public class Hm242isAnagram {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length())
            return false;
        HashMap<Character,Integer> hm=new HashMap<>();
        for(int i=0;i<s.length();i++){
            hm.put(s.charAt(i),1+hm.getOrDefault(s.charAt(i),0));
        }
        for(char c:t.toCharArray()){
            if(hm.getOrDefault(c,0)==0)
                return false;
            hm.put(c,hm.get(c)-1);
        }
        return true;
    }
    //t是s的异位词等价于「两个字符串排序后相等」
    public boolean isAnagram1(String s,String t){
        char[] arr1=s.toCharArray();
        char[] arr2=t.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1,arr2);
    }
    public boolean isAnagram2(String s,String t){
        int[] charData=new int[26];
        Arrays.fill(charData,0);//省略也可以
        for(char c:s.toCharArray()){
            charData[c-97]+=1;
        }
        for(char c:t.toCharArray()){
            charData[c-97]-=1;
        }
        for(int n:charData){
            if(n!=0)
                return false;
        }
        return true;
    }
}
