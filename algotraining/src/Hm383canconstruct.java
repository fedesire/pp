import java.util.HashMap;
import java.util.HashSet;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/2 10:22
 */
public class Hm383canconstruct {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        HashMap<Character,Integer> hm=new HashMap();
        for(int i=0;i<magazine.length();i++){
            hm.put(magazine.charAt(i),1+hm.getOrDefault(magazine.charAt(i),0));
        }
        HashSet<Integer> hs=new HashSet<>(hm.values());

        for(int i=0;i<ransomNote.length();i++){
            char c=ransomNote.charAt(i);
//            if(!hm.containsKey(c)) //hm的方法是containsKey containsValue不是contains
//                return false;
//            else if(hm.get(c)==0)
//                return false;
            if(hm.getOrDefault(c,0)==0)
                return false;
            else hm.put(c,hm.get(c)-1);
        }
        return true;


    }
}
