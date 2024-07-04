import java.util.*;

/**
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 * @date 2024/2/5 10:37
 */
public class Ar151reverseWords {
    public String reverseWords(String s) {
        String[] arr = s.split(" {1,}");
        List<String> list=new ArrayList<>(Arrays.asList(arr));
        Collections.reverse(list);
        if(list.get(list.size()-1).equals("")){
            list.remove(list.size()-1);
        }
        return String.join(" ", list);
    }

    public String reverseWords1(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    //倒着遍历，每次取出其中的单词，从前往后加入ans中即可 双指针解法
    // 要反转就可以想到从后往前读 但不能读一个字符就保留一次 要读一个单词保留一次
    public String reverseWords2(String s){
        s=s.trim();
        StringBuilder res=new StringBuilder();
        int i=s.length()-1;
        while(i>=0){
            int right=i; //right指向当前要添加的单词的尾字符所在的位置
            while(i>=0&&s.charAt(i)!=' ') i--;
            res.append(s, i+1, right+1).append(" ");
            while(i>=0&&s.charAt(i)==' ') i--;

        }
        return res.toString().trim(); //为了去除最后的空格 因为上面每次添加一个单词时后面都添加了一个空格

    }
}
