import java.util.Scanner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/14 14:48
 */
/*一天一只顽猴想去从山脚爬到山顶，途中经过一个有个N个台阶的阶梯，但是这猴子有一个习惯：

每一次只能跳1步或跳3步，试问猴子通过这个阶梯有多少种不同的跳跃方式？

输入描述
输入只有一个整数N（0<N<=50）此阶梯有多少个台阶。

输出描述
输出有多少种跳跃方式（解决方案数）。

用例
输入	50
输出	122106097
说明	无
输入	3
输出	2
说明	无
*/
public class DPStep {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] dp=new int[n+1];
        if (n >= 1) dp[1] = 1;
        if (n >= 2) dp[2] = 1;
        if (n >= 3) dp[3] = 2;

        for (int i = 4; i <= n; i++) {
            dp[i]=dp[i-1]+dp[i-3];
        }
        System.out.println(dp[n]);
    }
}
