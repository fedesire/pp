import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/19 14:25
 */
public class MyD501knapsack {
    private static int[][] memo;
    public static int knapsack(int[] w,int[] v,int c){
        int n=w.length;
        memo=new int[n][c+1];
        for (int i = 0; i <n; i++) {
            for(int j=0;j<c+1;j++)
                memo[i][j]=-1;
        }
        return bestValue(w,v,n-1,c);
    }
    //用[0..index]中的物品来装容量为c的背包得到最大价值
    public static int bestValue(int[] w,int[] v,int index,int c){
        if(c<=0||index<0)
            return 0;

        if(memo[index][c]!=-1)
            return memo[index][c];
        int res=bestValue(w,v,index-1,c); //不将index处物品装入背包
        if(c>=w[index]){//将index处物品装入背包
            res=Math.max(res,v[index]+bestValue(w,v,index-1,c-w[index]));
        }
        return memo[index][c]=res;
    }

    //动态规划的方法
    public static int knapsack1(int[] w,int[] v,int c){
        int n=w.length;
        //memo[i][j]表示前[0..i]的物品填充容量为j的背包能得到的最大价值
        memo=new int[n][c+1];
        if(n==0||c==0)
            return 0;
        for (int j = 0; j <=c; j++) {
            memo[0][j]=(j>=w[0])?v[0]:0;
        }
        for (int i = 1; i <n; i++) {
            for(int j=0;j<=c;j++){
                memo[i][j]=memo[i-1][j];
                if(j>=w[i])
                memo[i][j]=Math.max(memo[i][j],v[i]+memo[i-1][j-w[i]]);

            }
        }
        return memo[n-1][c];
    }

    public static void main(String[] args) {
        int[] w={1,2,3};
        int[] v={6,10,12};
        System.out.println(knapsack(w,v,5));
        System.out.println(knapsack1(w,v,5));

    }
}
