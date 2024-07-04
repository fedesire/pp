import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 * 示例 1:
 *
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * @date 2024/1/3 10:57
 */
public class Hm49groupAnagrams {
    public static boolean isAnagram1(String s,String t){
        char[] arr1=s.toCharArray();
        char[] arr2=t.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        return Arrays.equals(arr1,arr2);
    }
    //可以运行通过 但是lc上提交时还有一些测试案例(当strs数组特别长）没通过 说明时间复杂度高了 主要是每个字符串都要从
    //res中依次比较找到符合的异位词 太浪费时间了
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res= new ArrayList<>();
        // res.add(new ArrayList<String>(strs[0]));
        int count=0;
        for(String str:strs){
            int i;
            for(i=0;i<count;i++){
                if(isAnagram1(str,res.get(i).get(0))){
                    res.get(i).add(str);
                    break;
                }
            }
            if(i==count){
                List<String> l=new ArrayList<>();
                l.add(str);
                res.add(l);
                count++;
            }

        }
        return res;

    }
    public static List<List<String>> groupAnagrams1(String[] strs) {
        HashMap<String,List<String>> hm=new HashMap<>();
        // 遍历数组中的每一个字符串 将其加入到所属的map的value里
        for(String str:strs){
            char[] arr=str.toCharArray();
            Arrays.sort(arr);
//            String key= Arrays.toString(arr); //也可以 但是这种Arrays.toString([a,b,c])返回的是[a,b,c]而不是abc
            String key=new String(arr);

//            if(hm.containsKey(key))
//                hm.get(key).add(str);
//            else{
//                List<String> l=new ArrayList<>();
//                l.add(str);
//                hm.put(key,l);
//            }

            List<String> list= hm.getOrDefault(key,new ArrayList<>());
            list.add(str);
            hm.put(key,list);
        }
        return new ArrayList<List<String>>(hm.values());
    }



        public static void main(String[] args) {
        String[] s={"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams1(s));
    }

}
