/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/2/7 9:25
 */
public class MT67addBinary {
    public String addBinary(String a, String b) {
        int m=a.length(),n=b.length();
        int carry=0;
        StringBuilder res=new StringBuilder();
        int i,j;
        for(i=m-1,j=n-1;i>=0&&j>=0;i--,j--){
            char c1=a.charAt(i),c2=b.charAt(j);
            if(c1=='1'&&c2=='1'){
                if(carry==1)
                    res.insert(0,"1");
                else res.insert(0,"0");
                carry=1;
            }
            else if(c1=='1'||c2=='1'){
                if(carry==1){
                    res.insert(0,"0");
                }
                else res.insert(0,"1");
            }
            else{
                if(carry==1){
                    res.insert(0,"1");
                    carry=0;
                }
                else res.insert(0,"0");
            }
        }
        if(i<0&&j<0&&carry==1) res.insert(0,"1");
        if(i>=0) helper(a,i,carry,res);
        if(j>=0) helper(b,j,carry,res);
        return res.toString();

    }
    public void helper(String a, int i,int carry,StringBuilder res){
        while(i>=0){
            if(carry==0){
                res.insert(0,a.substring(0,i+1));
                break;
            }
            else if(a.charAt(i)=='0'){
                res.insert(0,"1");
                carry=0;
                if(i>0)
                    res.insert(0,a.substring(0,i));
                break;
            }
            else {
                res.insert(0,"0");
                i--;
            }
        }
        if(carry==1) res.insert(0,"1");
    }

    //当字符串超过33位时 int就无法表示了
    public String addBinary1(String a,String b){
        int sum=Integer.parseInt(a,2)+Integer.parseInt(b,2);
        return Integer.toBinaryString(sum);
    }

    //将短的字符串视为高位补0了变成和长的字符串相同的位数 从末位开始向前遍历 carry作为上一位的进位 当前位置
    //对应的两个位为ai,bi,则计算后该位的结果是(carry+ai+bi)%2 下一位的进位carry=(carry+ai+bi)/2
    public String  addBinary2(String a,String b){
        int carry=0;
        StringBuilder res=new StringBuilder();
        for(int i=a.length()-1,j=b.length()-1;i>=0||j>=0;i--,j--){
            int sum=carry;
            sum+=i>=0?a.charAt(i)-'0':0;
            sum+=j>=0?b.charAt(j)-'0':0;
            res.append(sum%2);
            carry=sum/2;
        }
        if(carry==1) res.append(1);
//        res.append(carry==1?"1":"");
        return res.reverse().toString();

    }

    public static void main(String[] args) {
        System.out.println(new MT67addBinary().addBinary("101111", "10"));
        System.out.println(Integer.parseInt("110",2));
    }
}
