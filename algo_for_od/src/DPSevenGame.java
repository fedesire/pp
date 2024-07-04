import java.math.BigInteger;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 20:34
 */
/*
* A、B两个人玩抢7游戏，游戏规则为：

A先报一个起始数字 X（10 ≤ 起始数字 ≤ 10000），B报下一个数字 Y （X - Y < 3），A再报一个数字 Z（Y - Z < 3），以此类推，直到其中一个抢到7，抢到7即为胜者；

在B赢得比赛的情况下，一共有多少种组合？

输入描述
起始数字 M

10 ≤ M ≤ 10000
如：

100

输出描述
B能赢得比赛的组合次数

用例
输入	10
输出	1
说明	无
*/
import java.math.BigInteger;
import java.util.Scanner;

public class DPSevenGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();

        // dpA[i] 表示 A 叫 数字i 的方案数
        BigInteger[] dpA = new BigInteger[m + 2];
        // 初始化dpA[i]
        for (int i = 0; i < m + 2; i++) dpA[i] = new BigInteger("0");
        // 由于是A从m开始叫，因此A叫m的方案数为1
        dpA[m] = new BigInteger("1");

        // dpB[i] 表示 B叫 数字i 的方案数
        BigInteger[] dpB = new BigInteger[m + 2];
        // 初始化dpB[i]
        for (int i = 0; i < m + 2; i++) dpB[i] = new BigInteger("0");

        for (int i = m - 1; i >= 7; i--) {
            // B叫数字i的方案数 = A叫数字i+1的方案数 + A叫数字i+2的方案数
            dpB[i] = dpA[i + 1].add(dpA[i + 2]);
            // A叫数字i的方案数 = B叫数字i+1的方案数 + B叫数字i+2的方案数
            dpA[i] = dpB[i + 1].add(dpB[i + 2]);
        }

        // 返回B叫7的方案数
        System.out.println(dpB[7]);
    }
}