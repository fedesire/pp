import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/2/5 8:39
 */
public class Ar58lengthOfLastWord {
    public int lengthOfLastWord(String s) {
        String[] split = s.split(" {1,}");
        System.out.println(Arrays.toString(split));
        return split[split.length-1].length();
    }

    public int lengthOfLastWord1(String s) {
        char[] arr=s.toCharArray();
        int i=arr.length-1;
        while(i>=0&&arr[i]==' '){
            i--;
        }
        int res=0;
        while(i>=0&&arr[i]!=' '){
            res++;
            i--;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Ar58lengthOfLastWord().lengthOfLastWord("   fly me   to   the moon  "));
    }
}
