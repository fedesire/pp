/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/19 8:37
 */
public class MyD3IntegerBreak {
    public static int integerBreak(int n){
        return breakInteger(n);
    }
    public static int max3(int a,int b,int c){
        return Math.max(Math.max(a,b),c);
    }
    public static  int breakInteger(int n){
        if(n<1)
            throw new IllegalArgumentException("n should be greater than zero");
        int[] dp=new int[n+1];
        dp[1]=1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j <i; j++) { //分割成j,i-j
                dp[i]=max3(dp[i],j*(i-j),j*dp[i-j]);
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {

        System.out.println(integerBreak(2));
        System.out.println(integerBreak(10));
    }
}
