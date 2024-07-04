/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/29 22:59
 */
public class Ar1961isPrefix {
    public boolean isPrefixString(String s, String[] words) {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<words.length;i++){
            sb.append(words[i]);
            int n=sb.length();
            if(n>s.length()||!s.substring(0,n).equals(sb.toString()))
                return false;
            if(n==s.length())
                return true;
        }
        return false;

    }
    public boolean isPrefixString1(String s, String[] words) {
        int index = 0, n = s.length();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                if (s.charAt(index++) != word.charAt(i)) {
                    return false;
                }
                if (index == n) {
                    if (i == word.length() - 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }

        return false;
    }
}
