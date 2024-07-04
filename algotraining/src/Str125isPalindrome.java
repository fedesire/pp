/**
 * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
 *
 * 字母和数字都属于字母数字字符。
 *
 * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
 * @date 2024/1/9 10:39
 */
public class Str125isPalindrome {
    public boolean isPalindrome(String s){
        // replaceAll方法参数支持正则表达式 replace方法不支持 会当成字符串来看
        s=s.replaceAll("[^A-Za-z0-9]","");
//        s.replace("[^A-Za-z0-9]","");
        s=s.toLowerCase();
        int i=0,j=s.length()-1;
        while(i<=j){
            if(s.charAt(i++)!=s.charAt(j--))
                return false;
        }
        return true;
    }
    public boolean isValid(char c){
        if(c>='0'&&c<='9'||c>='a'&&c<='z')
            return true;
        return false;
    }
    public boolean isPalindrome1(String s) {
        s=s.toLowerCase();
        StringBuilder s1=new StringBuilder();
        for (char c : s.toCharArray()) {
//            if(isValid(c))
//                s1.append(c);
            //可以直接用Character提供的判断是字符数字的方法
            if(Character.isLetterOrDigit(c))
                s1.append(c);
        }
        int i=0,j=s1.length()-1;
        while(i<j){
            if(s1.charAt(i++)!=s1.charAt(j--))
                return false;
        }
        return true;
    }

    public boolean isPalindrome2(String s) {
        s=s.toLowerCase();
        int i=0,j=s.length()-1;
        //不用stringbuilder来保存有效的数字字符 直接while循环来找 注意里面的while循环必须也判断i<j
        while(i<j){
            while(i<j&&!isValid(s.charAt(i))) i++;
            while(i<j&&!isValid(s.charAt(j))) j--;
            if(i==j)
                return true;
            if(s.charAt(i++)!=s.charAt(j--))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Str125isPalindrome().isPalindrome2("&"));
    }
    }
