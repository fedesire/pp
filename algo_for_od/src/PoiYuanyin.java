import java.util.*;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/15 9:28
 */
/*开头和结尾都是元音字母（aeiouAEIOU）的字符串为元音字符串，其中混杂的非元音字母数量为其瑕疵度。比如:

“a” 、 “aa”是元音字符串，其瑕疵度都为0
“aiur”不是元音字符串（结尾不是元音字符）
 “abira”是元音字符串，其瑕疵度为2
给定一个字符串，请找出指定瑕疵度的最长元音字符子串，并输出其长度，如果找不到满足条件的元音字符子串，输出0。

子串：字符串中任意个连续的字符组成的子序列称为该字符串的子串。

输入描述
首行输入是一个整数，表示预期的瑕疵度flaw，取值范围[0, 65535]。

接下来一行是一个仅由字符a-z和A-Z组成的字符串，字符串长度(0, 65535]。

输出描述
输出为一个整数，代表满足条件的元音字符子串的长度。

用例
输入	0
asdbuiodevauufgh
输出	3
说明	无
输入	2
aeueo
输出	0
*/
public class PoiYuanyin {
    // 首先将字符串中的所有元音字符的索引下标统计出来 形成一个新的idx数组 这样之后再idx数组里使用双指针
    // 就能保证左右指针指向的一直是元音字符了
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int m = scanner.nextInt();
        scanner.nextLine();
        String s=scanner.nextLine();

        char[] vowels={'a','e','i','o','u','A','E','I','O','U'};
        HashSet<Character> hs=new HashSet<>();
        for (char vowel : vowels) {
            hs.add(vowel);
        }
        ArrayList<Integer> idx=new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            // 记录元音字母在原字符串中的下标
            if(hs.contains(s.charAt(i)))
                idx.add(i);

        }
        int l=0,r=0;
        int res=0;
        while(r<idx.size()){
            int flaw=idx.get(r)-idx.get(l)-r+l;
            if(flaw<m)
                r++;
            else if(flaw>m)
                l++;
            else{
               res=Math.max(res,idx.get(r)-idx.get(l)+1);
               r++;
            }
        }
        System.out.println(res);
    }

}
