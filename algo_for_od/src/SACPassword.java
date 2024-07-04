import java.util.Scanner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/16 17:08
 */
/*
* 给定一段“密文”字符串 s，其中字符都是经过“密码本”映射的，现需要将“密文”解密并输出。
映射的规则（'a' ~ 'i'）分别用（'1' ~ '9'）表示；（'j' ~ 'z'）分别用（"10*"~ "26*"）表示。
约束：映射始终唯一。
输入描述
“密文”字符串
输出描述
明文字符串
备注
翻译后的文本长度在100以内
用例
输入 20*19*20*
输出 tst
说明 无*/
public class SACPassword {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s = scanner.nextLine();
        for (int i = 26; i >=1; i--) {
            String key=i+(i>9?"\\*":"");
            String value=String.valueOf((char)('a'+i-1));
            s=s.replaceAll(key,value);
        }
        System.out.println(s);
    }
}
