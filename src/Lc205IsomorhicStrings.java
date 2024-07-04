import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/13 15:50
 */
public class Lc205IsomorhicStrings {
    //代码简单 但是indexOf()的时间复杂度是on的 所以总的时间复杂度是on2 用哈希表时间复杂度是on
    public static boolean isIsomorphic(String s,String t){
        if(s.length()!=t.length())
            return false;
        for (int i = 0; i < s.length(); i++) {
            if(s.indexOf(s.charAt(i))!=t.indexOf((t.charAt(i))))
                return false;
        }
        return true;
    }
    //维护一张哈希表 最后看获得的hash表的values()中是否有重复的值即可
    public static boolean isIsomorphic1(String s,String t){
        if(s.length()!=t.length())
            return false;
        HashMap<Character,Character> s2t=new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c1=s.charAt(i),c2=t.charAt(i);
            if(!s2t.containsKey(c1))
                s2t.put(c1,c2);
            else {
                if(s2t.get(c1)!=c2)
                    return false;
            }
        }
        HashSet<Character> hs=new HashSet<>(s2t.values());
        if(hs.size()<s2t.values().size())
            return false;
        return true;
    }

    //同样的只维护一张哈希表 不过containsValue()时间复杂度太高
    public static boolean isIsomorphic3(String s,String t){
        if(s.length()!=t.length())
            return false;
        HashMap<Character,Character> s2t=new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c1=s.charAt(i),c2=t.charAt(i);
            if((s2t.containsKey(c1)&&s2t.get(c1)!=c2)||(!s2t.containsKey(c1)&&s2t.containsValue(c2))){
                return false;
            }
            s2t.put(c1,c2);

        }
        return true;
    }
/*维护两张哈希表，第一张哈希表 s2t 以 s中字符为键，映射至 t的字符为值，第二张哈希表 t2s以 t中字符为键，映射至 s的字符为值。
从左至右遍历两个字符串的字符，不断更新两张哈希表，如果出现冲突（即当前下标 index对应的字符 s[index]已经存在映射
且不为 t[index]或当前下标 index对应的字符 t[index]已经存在映射且不为 s[index]时说明两个字符串无法构成同构，返回 false\*/
    public boolean isIsomorphic2(String s, String t) {
        Map<Character, Character> s2t = new HashMap<Character, Character>();
        Map<Character, Character> t2s = new HashMap<Character, Character>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(isIsomorphic("egg","add"));
    }
}
