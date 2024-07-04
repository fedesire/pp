import java.util.HashSet;

/**
 * 原始的ASCII编码只包含128个字符，对应编号0到127。而扩展的ASCII编码使用8位来表示字符，可以表示256个字符
 * @date 2023/12/12 19:18
 */
public class My308LongestSubstringWithoutRepeating {
    public static int lengthOfLongestSubstring(String s){
        int l=0,r=-1; //注意r=-1 滑动窗口算法里刚开始r并不是在最右边而是在左边
        int res=0;
        int[] freq=new int[256]; // 这里用128其实也行
        while(r+1<s.length()){
            if(freq[s.charAt(r+1)]==0)
                freq[s.charAt(++r)]++;
            else
                freq[s.charAt(l++)]--;
            res=Math.max(res,r-l+1);

        }
        return res;

    }
    public static int lengthOfLongestSubstring3(String s){
        int l=0,r=0; //[l..r)
        int res=0;
        int[] freq=new int[256];
        while(r<s.length()){
            while(freq[s.charAt(r)]!=0){
                freq[s.charAt(l++)]--;
            }
            // 因为这里r++了 所以下面计算长度是r-l 也可以像下面注释的那样r先不++ 计算长度就是r-l+1 最后r++
            freq[s.charAt(r++)]++;
            res=Math.max(res,r-l);

//            freq[s.charAt(r)]++;
//            res=Math.max(res,r-l+1);
//            r++;
        }
        return res;
    }

    public static int lengthOfLongestSubstring1(String s){
        int l=0,r=0; //[l..r)
        int res=0;
        int[] freq=new int[256];
        while(r<s.length()){
            if(freq[s.charAt(r)]==0)
                freq[s.charAt(r++)]++;
            else
                freq[s.charAt(l++)]--;
            res=Math.max(res,r-l);

        }
        while(r<s.length()){
            while(freq[s.charAt(r)]!=0){
                freq[s.charAt(l++)]--;
            }
            freq[s.charAt(r++)]++;
            res=Math.max(res,r-l);
        }
        return res;
    }
    public static int lengthOfLongestSubstring2(String s){
        int l=0,r=-1;
        int res=0;
        HashSet<Character> hs=new HashSet<>();
        while(r+1<s.length()){
            if(r+1<s.length()&&!hs.contains(s.charAt(r+1)))
                hs.add(s.charAt(++r));
            else
                hs.remove(s.charAt(l++));
            res=Math.max(res,r-l+1);

        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("ghrihgori"));
    }
}
