/**
 * @author xqi
 * @version 1.0
 * @description 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 * @date 2024/5/1 8:11
 */
public class MT540convertToBase7 {
    public String convertToBase7(int num) {
        StringBuilder sb=new StringBuilder();
        if(num==0)
            return "0";
        boolean flag=false;
        if(num<0){
            num=-num;
            flag=true;
        }
//        boolean negative = num < 0;
//        num = Math.abs(num);
        while(num!=0){
            sb.append(num%7);
            num=num/7;
        }
        if(flag){
            sb.append("-");
        }
        return sb.reverse().toString();

    }

    public static void main(String[] args) {
        MT540convertToBase7 mt540convertToBase7=new MT540convertToBase7();
        System.out.println(mt540convertToBase7.convertToBase7(-7));
    }
}
