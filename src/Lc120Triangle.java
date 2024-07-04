import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/18 20:15
 */
public class Lc120Triangle {
    private static ArrayList<Integer> res;
    public static int minPath(List<List<Integer>> nums){
        if(nums.size()==1)
            return nums.get(0).get(0);
        res=new ArrayList<>();
        getPath(nums,0,0,0);

        return Collections.min(res);

    }
    //属于递归的方式实现 在lc上42/44通过 和下面正确的递归算法一样 但由于没有正确建模得出递归求解式代码不够简洁
    public static void getPath(List<List<Integer>> nums,int n,int i,int sum){
        if(n==nums.size()){
            res.add(sum);
            return ;
        }
        sum+=nums.get(n).get(i);
        getPath(nums,n+1,i,sum);
        getPath(nums,n+1,i+1,sum);
    }

    /*若定义 f(i,j)为 (i,j)点到底边的最小路径和，则易知递归求解式为:
f(i,j)=min(f(i+1,j),f(i+1,j+1))+triangle[i][j] 由此，我们将任一点到底边的最小路径和，转化成了
与该点相邻两点到底边的最小路径和中的较小值，再加上该点本身的值。*/
    public static int minmumTotal(List<List<Integer>> triangle){
        return dfs(triangle,0,0);
    }
    public static int dfs(List<List<Integer>> triangle,int i,int j){
        if(i==triangle.size())
            return 0;
        return Math.min(dfs(triangle,i+1,j),dfs(triangle,i+1,j+1))+triangle.get(i).get(j);
    }

    //加入记忆化搜索进行优化
    private static Integer[][]memo; //此时memo不能像之前的问题用int类型 因为用int无法找到一个数能代表
    public static int minmumTotal1(List<List<Integer>> triangle){
        memo=new Integer[triangle.size()][triangle.size()];
        return dfs1(triangle,0,0);
    }
    public static int dfs1(List<List<Integer>> triangle,int i,int j){
        if(i==triangle.size())
            return 0;
        if(memo[i][j]!=null)
            return memo[i][j];
        return Math.min(dfs1(triangle,i+1,j),dfs(triangle,i+1,j+1))+triangle.get(i).get(j);
    }

    //使用动态规划的方法 自底向上解决问题
    public static int minimumTotal2(List<List<Integer>> triangle){
        int[][] dp=new int[triangle.size()][triangle.size()];
        for (int i = 0; i < triangle.size(); i++) {
            dp[triangle.size()-1][i]=triangle.get(triangle.size()-1).get(i);
        }
        for (int i = triangle.size()-2; i >=0; i--) {
            for (int j = 0; j <=i; j++) {
                dp[i][j]=Math.min(dp[i+1][j],dp[i+1][j+1])+triangle.get(i).get(j);
            }
        }
        return dp[0][0];

        /*int n = triangle.size();
        // dp[i][j] 表示从点 (i, j) 到底边的最小路径和。
        int[][] dp = new int[n + 1][n + 1];
        // 从三角形的最后一行开始递推。
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];*/

    }

    //空间优化 只使用两行数组 其实还可以优化只使用一行数组
    public static int minimumTotal(List<List<Integer>> triangle) {
        int n=triangle.size();
        if(n==1)
            return triangle.get(0).get(0);
        int[][] dp=new int[2][n];
        for(int i=0;i<n;i++){
            dp[(n-1)%2][i]=triangle.get(n-1).get(i);//不能写成dp[1][i]=
        }
        for(int i=n-2;i>=0;i--){
            for(int j=0;j<=i;j++){
                dp[i%2][j]=Math.min(dp[(i+1)%2][j],dp[(i+1)%2][j+1])+triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        List<List<Integer>> nums=new ArrayList<>();
        nums.add(Arrays.asList(new Integer[]{-1}));
        nums.add(Arrays.asList(new Integer[]{2,3}));
        nums.add(Arrays.asList(new Integer[]{1,-1,-3}));
        System.out.println(minimumTotal(nums));

    }
}
