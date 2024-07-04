/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/31 9:49
 */
public class Ar12intToRoman {
    StringBuilder sb=new StringBuilder();
    public String intToRoman(int num) {
        int i=0;
        while(num>0){
            int x=num%10;
            if(i==0)
                toRoman(x,"I","V","X");
            else if(i==1)
                toRoman(x,"X","L","C");
            else if(i==2)
                toRoman(x,"C","D","M");
            else {
                while(x-->0)
                    sb.insert(0,"M");
            }
            num=num/10;
            i++;
        }
        return sb.toString();
    }
    public void toRoman(int x,String a,String b,String c){
        if(x<4){
            while(x-->0)
                sb.insert(0,a);
        }
        else if(x==4){
            sb.insert(0,a+b);
        }
        else if(x==9)
            sb.insert(0,a+c);
        else {
            x=x-5;
            while(x-->0)
                sb.insert(0,a);
            sb.insert(0,b);
        }
    }

    /*根据罗马数字的唯一表示法，为了表示一个给定的整数num，我们寻找不超过 num的最大符号值，将 num减去该符号值，
    然后继续寻找不超过 num的最大符号值，将该符号拼接在上一个找到的符号之后，循环直至 num为 0。最后得到的字符串即为
     num的罗马数字表示*/
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public String intToRoman1(int num){
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            int value=values[i];
            String symbol=symbols[i];
            while(num>=value){
                sb.append(symbol);
                num=num-value;
            }
            if(num==0)
                break;
        }
        return sb.toString();

    }
}
