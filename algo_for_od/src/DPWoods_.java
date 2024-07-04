import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/14 15:38
 */
/*
* 一根X米长的树木，伐木工切割成不同长度的木材后进行交易，交易价格为每根木头长度的乘积。规定切割后的每根木头长度都为正整数；也可以不切割，
* 直接拿整根树木进行交易。
请问伐木工如何尽量少的切割，才能使收益最大化？
输入描述
木材的长度（X ≤ 50）
输出描述
输出最优收益时的各个树木长度，以空格分隔，按升序排列
用例
输入 10
输出 3 3 4
说明
一根2米长的树木，伐木工不切割，为2 * 1，收益最大为2
一根4米长的树木，伐木工不需要切割为2 * 2，省去切割成本，直接整根树木交易，为4 * 1，收益最大为4
一根5米长的树木，伐木工切割为2 * 3，收益最大为6
一根10米长的树木，伐木工可以切割方式一：3，4，3，也可以切割为方式二：3，2，2，3，但方式二伐木工多切割一次，增加切割成本却买了一样的价格，
* 因此并不是最优收益。*/
public class DPWoods_ {
   static class Wood{
        int profit;
        ArrayList<Integer> parts=new ArrayList<>();
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int x = scanner.nextInt();
        // dp[i]存储的是长度为i的木材切割后的最大收益以及最大收益时的各个树木长度
        Wood[] dp=new Wood[x+1];
        // 不割时的收益
        for (int i = 1; i <=x; i++) {
            dp[i]=new Wood();
            dp[i].profit=i;
            dp[i].parts.add(i);
        }
        for (int i = 2; i <=x; i++) {
            for (int j = 1; j < i; j++) {
                // 如果该切割方案的收益更大，或者收益和之前切割方案的收益相同，但是切割数更少，则使用当前切割方案
                int temp = dp[j].profit * dp[i - j].profit;
                if(temp>dp[i].profit||(temp==dp[i].profit&&dp[i].parts.size()>dp[j].parts.size()+dp[i-j].parts.size())){
                    dp[i].profit=temp;
                    dp[i].parts.clear();
                    dp[i].parts.addAll(dp[j].parts);
                    dp[i].parts.addAll(dp[i-j].parts);
                }
            }
        }
        // dp[x].slices记录的是：长度x的木材对应的最大收益的切割方案
        // 按题目输出描述要求进行升序
        dp[x].parts.sort((a, b) -> a - b);
        for (Integer part : dp[x].parts) {
            System.out.print(part+" ");
        }
    }
}
