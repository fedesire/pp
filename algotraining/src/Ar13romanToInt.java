import java.util.HashMap;
import java.util.Map;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/30 11:34
 */
public class Ar13romanToInt {
    public int romanToInt(String s) {
        Map<String,Integer> hm=new HashMap<>();
        hm.put("IV",4);
        hm.put("IX",9);
        hm.put("XL",40);
        hm.put("XC",90);
        hm.put("CD",400);
        hm.put("CM",900);
        Map<String,Integer> hm1=new HashMap<>();
        hm1.put("I",1);
        hm1.put("V",5);
        hm1.put("X",10);
        hm1.put("L",50);
        hm1.put("C",100);
        hm1.put("D",500);
        hm1.put("M",1000);
        int sum=0;
        for(int i=0;i<s.length();i++){
            String sub;
            if(i<s.length()-1&&hm.containsKey(s.substring(i,i+2))){
                sub=s.substring(i,i+2);
                sum+=hm.get(sub);
                i++;
            }else{
                sub=s.substring(i,i+1);
                sum+=hm1.get(sub);
            }
        }
        return sum;
    }
    //只要发现，“当前位置的元素比下个位置的元素小，就减去当前值，否则加上当前值”。
    public int romanToInt1(String s){
        Map<Character,Integer> hm=new HashMap<Character,Integer>(){{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};
        int sum=0;
        for(int i=0;i<s.length();i++){
            int value=hm.get(s.charAt(i));
            if(i<s.length()-1&&value<hm.get(s.charAt(i+1)))
                sum-=value;
            else
                sum+=value;
        }
        return sum;
    }
}
