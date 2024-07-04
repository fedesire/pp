/**
 *Z字形变换 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * @description: TODO
 * @date 2024/2/20 9:48
 */
public class Ar6convert {
    public String convert(String s, int numRows) {
        if(numRows < 2) return s;

        StringBuilder[] arr=new StringBuilder[numRows];
        for(int i=0;i<numRows;i++){
            arr[i]=new StringBuilder();
        }
        int index=0;
        int flag=-1;
        for(int i=0;i<s.length();i++){
            arr[index].append(s.charAt(i));
            if(index==numRows-1||index==0){ //上面必须先做numRows<2就直接返回的判断 否则numRows=1时 index=0下一步变成1或-1都不对
                flag=-flag;
            }
            index+=flag;
        }
        StringBuilder res=new StringBuilder();
        for(StringBuilder sb:arr){
            res.append(sb.toString());
        }
        return res.toString();

    }

    public static void main(String[] args) {
        System.out.println(new Ar6convert().convert("PAYPALISHIRING", 3));
    }
}
