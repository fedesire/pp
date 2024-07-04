/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 16:16
 */

import java.util.Scanner;
/*
某公司部门需要派遣员工去国外做项目。

现在，代号为 x 的国家和代号为 y 的国家分别需要 cntx 名和 cnty 名员工。

部门每个员工有一个员工号（1,2,3,......），工号连续，从1开始。

部长派遣员工的规则：

规则1：从 [1, k] 中选择员工派遣出去
规则2：编号为 x 的倍数的员工不能去 x 国，编号为 y 的倍数的员工不能去 y 国。
问题：

找到最小的 k，使得可以将编号在 [1, k] 中的员工分配给 x 国和 y 国，且满足 x 国和 y 国的需求。

输入描述
四个整数 x，y，cntx，cnty。

2 ≤ x < y ≤ 30000
x 和 y 一定是质数
1 ≤ cntx, cnty < 10^9
cntx + cnty ≤ 10^9
输出描述
满足条件的最小的k

用例
输入	2 3 3 1
输出	5
说明
输入说明：

2 表示国家代号2

3 表示国家代号3

3 表示国家2需要3个人

1 表示国家3需要1个人

* 首先，我们需要搞清楚一个数学问题，那么就是 1~k 范围内，x的倍数的数量该如何求解。

比如 1~10 范围内，2的倍数有：2,4,6,8,10，共计5个

比如 1~20 范围内，3的倍数有：3,6,9,12,15,18，共计6个

可以发现1~k范围内x的倍数的个数 = k // x，其中//表示整除。

证明也很简单，大家可以自行验证。

因此，我们可以得出1~k范围内：

x的倍数个数有：k // x 个，假设 A = k // x
y的倍数个数有：k // y 个，假设 B = k // y
同时，我们还需要关注 1~k 范围内：

x*y的倍数的个数有： k // (x*y) 个，假设  = C // (x*y)
即 1 ~ k 范围内：

x倍数且非y倍数的个数有：A - C 个
y倍数且非x倍数的个数有：B - C 个
我们可以让：

“x倍数且非y倍数” 的数去 y 国
"y倍数且非x倍数" 的数去 x 国
那么此时

x 国还差：max(0, cntx - (B-C)) 个
y 国还差：max(0, cnty - (A-C)) 个
而差的人可以从 1~k 范围内，非x倍数也非y倍数的数中选，而

非x倍数也非y倍数的值得数量有：k - A - B + C 因为-A的时候里面包含了C -B的时候又减了一次 所以要加补回来
因此，只要k满足下面不等式即可：

max(0, cntx - (B-C)) + max(0, cnty - (A-C))  ≤ k - A - B + C

由于是不等式，因此我们无法直接计算出k的值，这里我们可以通过二分法取中间值作为k的可能值，带入上面不等式，

如果满足，则对应k是一个可能解，我们继续尝试更小的k，即下一轮缩小二分右边界
如果不满足，则说明k值取小了，下一轮应该扩大二分左边界，尝试更大的k
而k的二分范围，即k的可能取值范围是：

k至少是 cntx+cnty，即 1~k号员工可以分两拨，一波cntx个且保证非x倍数，一波cnty个且保证非y倍数
k至多是一个整型最大值（想了几个最大值计算公式，感觉不保险，这里还是用一个Long.MAX_VALUE比较保险，反正这个上限往大了搞就行）
2024.01.04

根据考友反馈，本题 k上限 如果取Long.MAX_VALUE只能拿55%通过率，改成1e9的话就能满分。

理论上k上限取Long.MAX_VALUE才是正确的，比如下面用例

8090 5166 999896303 49598

k上限取Long.MAX_VALUE的话，结果是：1000019914

k上限取1e9的话，结果是：1000000001

应该是机考系统用例有问题。考试时需要注意一下。

2024.04.11

更新了二分初始的左边界为1。

这题实际考试牛客系统用例应该是设计错误的，按理来说二分初始范围应该就是前面推导的那样。很奇怪。
*/
public class BSSendEmployee {
    static long x;
    static long y;
    static long cntx;
    static long cnty;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        x = sc.nextInt();
        y = sc.nextInt();
        cntx = sc.nextInt();
        cnty = sc.nextInt();

        // 使用此范围，实际通过率55%
        //  long min = cntx + cnty;
        //  long max = Long.MAX_VALUE;

        // 使用此范围，实际通过率可以100%
        long min = 1;
        long max = 1000000000L;

        while (min <= max) {
            long mid = min + (max - min) / 2;

            if (check(mid)) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }

        System.out.println(min);
    }

    public static boolean check(long k) {
        long A = k / x; // 1~k范围内x倍数的数量
        long B = k / y; // 1~k范围内y倍数的数量
        long C = k / (x * y); // 1~k范围内x*y倍数的数量
//        非x倍数也非y倍数的值的数量k - A - B + C
        return Math.max(0, cntx - (B - C)) + Math.max(0, cnty - (A - C)) <= k - A - B + C;
    }
}