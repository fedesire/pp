import java.util.HashSet;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/21 20:24
 */
/*
* 现有两组服务器A和B，每组有多个算力不同的CPU，其中 A[i] 是 A 组第 i 个CPU的运算能力，B[i] 是 B组 第 i 个CPU的运算能力。

一组服务器的总算力是各CPU的算力之和。

为了让两组服务器的算力相等，允许从每组各选出一个CPU进行一次交换，

求两组服务器中，用于交换的CPU的算力，并且要求从A组服务器中选出的CPU，算力尽可能小。

输入描述
第一行输入为L1和L2，以空格分隔，L1表示A组服务器中的CPU数量，L2表示B组服务器中的CPU数量。

第二行输入为A组服务器中各个CPU的算力值，以空格分隔。

第三行输入为B组服务器中各个CPU的算力值，以空格分隔。

1 ≤ L1 ≤ 10000
1 ≤ L2 ≤ 10000
1 ≤ A[i] ≤ 100000
1 ≤ B[i] ≤ 100000
输出描述
对于每组测试数据，输出两个整数，以空格分隔，依次表示A组选出的CPU算力，B组选出的CPU算力。

要求从A组选出的CPU的算力尽可能小。

备注
保证两组服务器的初始总算力不同。
答案肯定存在
用例
输入	2 2
1 1
2 2
输出	1 2
说明	从A组中选出算力为1的CPU，与B组中算力为2的进行交换，使两组服务器的算力都等于3。
输入	2 2
1 2
2 3
输出	1 2
说明	无
输入	1 2
2
1 3
输出	2 3
说明	无
输入	3 2
1 2 5
2 4
输出	5 4
说明	无
题目解析
假设A组服务器算力之和为sumA，B组服务器算力之和为sumB，将A组的a和B组的b交换后，A组算力之和等于B组算力之和，则可得公式如下：

sumA - a + b = sumB - b + a

sumA - sumB = 2 * (a - b)

a - b = (sumA - sumB) / 2

其中 sumA, sumB 是已知的，因此，我们可以遍历A组所有元素a，计算出b= a - (sumA - sumB)/2，看B组中是否存在对应b，若存在，则a b就是题解。
*/
import java.util.HashSet;
import java.util.Scanner;

public class LogicCPU {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 可能有多组测试数据
        while (sc.hasNext()) {
            int l1 = sc.nextInt();
            int l2 = sc.nextInt();

            int sumA = 0;
            int[] A = new int[l1];
            for (int i = 0; i < l1; i++) {
                int a = sc.nextInt();
                sumA += a;
                A[i] = a;
            }

            int sumB = 0;
            HashSet<Integer> setB = new HashSet<>();
            for (int i = 0; i < l2; i++) {
                int b = sc.nextInt();
                sumB += b;
                setB.add(b);
            }

            // 由于本题必然存在解，因此sumA-sumB的结果肯定可以整除2，如果不能整除则half_diff为小数，
            // 而half_diff = a - b，其中a,b都是整数，因此不可能存在half_diff是小数的情况
            int half_diff = (sumA - sumB) / 2;

            // 记录用于交换的最小的a
            int minA = Integer.MAX_VALUE;
            String ans = "";

            for (int a : A) {
                int b = a - half_diff;

                if (setB.contains(b)) {
                    if (a < minA) {
                        minA = a;
                        ans = a + " " + b;
                    }
                }
            }

            System.out.println(ans);
        }
    }
}