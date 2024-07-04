/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/16 14:32
 */
/*
* 给定一个正整数 n，如果能够分解为 m（m > 1）个连续正整数之和，请输出所有分解中，m最小的分解。

如果给定整数无法分解为连续正整数，则输出字符串"N"。

输入描述
输入数据为一整数，范围为 (1, 2^30]

输出描述
比如输入为：

21

输出：

21=10+11

用例
输入	21
输出	21=10+11
说明
21可以分解的连续正整数组合的形式有多种：

21=1+2+3+4+5+6

21=6+7+8

21=10+11

其中 21=10+11，是最短的分解序列。
*/

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

// 此题数据范围太大 使用滑动窗口可能会超时
// 但是这个数学解法太麻烦 还是用滑动窗口吧 评论说滑动窗口解法也ac100了 更新了一种使用等差思路的解法
public class MathNumberBreak {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 必须用nextLong
//        System.out.println(getResult(sc.nextLong()));
        getResult1(sc.nextLong());
    }


    public static String solution(long n) {
        // 连续正整数序列长度m至少为2
        long m = 2;

        while (true) {
            // 初始序列  [1, 2, ..., m-1, m] 的序列和
            long Sn = (1 + m) * m / 2; // 等差数列求和公式：(首项 + 末项) * 项数 / 2

            // 初始序列的和 Sn
            // 目标序列的和 n

            // 初始序列之和不能大于目标序列之和，否则无解
            if (Sn > n) {
                break;
            }

            // 如果存在长度为m的“和n连续正整数序列”，那么必然 n - Sn 可以整除m
            if ((n - Sn) % m != 0) {
                m++;
                continue;
            }

            // 由于m从最小长度开始尝试，因此一旦找到，就是本题题解
            // 长度m，和n的连续正整数序列的首项计算
            long a1 = (n - Sn) / m + 1;

            StringJoiner sj = new StringJoiner("+", n + "=", "");
            for (int i = 0; i < m; i++) {
                sj.add(a1 + i + "");
            }

            return sj.toString();
        }

        return "N";
    }

    public static void getResult1(long n){
        long[] nums=new long[(int) n+1];
        for (int i = 1; i <= n; i++) {
            nums[i]=i;
        }
        int l=1,r=1;
        int sum=0;
        HashMap<Integer,Integer> record=new HashMap<>();
        // 前面那道所有组合的题要输出21=21这种 此题最小也需要两个 所以l不用到n
        while(l<n){
            if(sum+nums[r]==n){
                record.put(r-l,l);
                sum-=nums[l++];
                if(r>=n)
                    break;
                sum+=nums[r++];
            }
            else if(sum+nums[r]<n){
                sum+=nums[r++];
            }
            else sum-=nums[l++];
        }
        int min = record.keySet().stream().min((a, b) -> a - b).orElse(0);
        if(min==0){
            System.out.println("N");
            return ;
        }
        StringJoiner joiner=new StringJoiner("+");
        int start = record.get(min);
        for (int i = start; i <=min+start; i++) {
            joiner.add(i+"");
        }
        System.out.println(n+"="+joiner);
    }

    public static String getResult(long n) {
        if (n == 1) {
            return "N";
        }

        // 如果 n 是奇数, 那么固定分解为两个数
        if (n % 2 != 0) {
            return n + "=" + (n / 2) + "+" + (n / 2 + 1);
        }

        // 如果 n 是偶数, 那么首先求出 n 的最大奇因数 x
        long x = n / 2;
        while (x % 2 == 0) {
            x /= 2;
        }

        // 如果偶数 n 分解出来的连续正整数数列的长度是偶数，则最短长度为minEvenLen
        long minEvenLen = n / x * 2;
        //  minEvenLen_start 是偶数长度连续正整数数列的首元素，其中
        //    x/2是连续正整数中间两个数的左边那个数
        //    (len / 2 - 1) 是连续正整数数列首元素 ~ 连续正整数中间两个数的左边那个数 的距离
        long minEvenLen_start = x / 2 - (minEvenLen / 2 - 1);

        // 如果偶数 n 分解出来的连续正整数数列的长度是奇数，那么最短长度为minOddLen
        long minOddLen = getMinOddLen(n, x);
        //  minOddLen_start 是奇数长度连续正整数数列的首元素，其中
        //    n/len是连续正整数中间的那个数
        //    ((len - 1) / 2) 是是连续正整数数列首元素 ~ 连续正整数中间那个数 的距离
        long minOddLen_start = n / minOddLen - ((minOddLen - 1) / 2);

        long len;
        long start;

        // 连续正整数数列中每个元素都是正整数，因此求解出来的首元素需要大于等于1
        if (minEvenLen_start >= 1 && minOddLen_start >= 1) {
            if (minEvenLen < minOddLen) {
                len = minEvenLen;
                start = minEvenLen_start;
            } else {
                len = minOddLen;
                start = minOddLen_start;
            }
        } else if (minEvenLen_start >= 1) {
            len = minEvenLen;
            start = minEvenLen_start;
        } else if (minOddLen_start >= 1) {
            len = minOddLen;
            start = minOddLen_start;
        } else {
            return "N";
        }

        StringJoiner sj = new StringJoiner("+", n + "=", "");
        for (long i = start; i < start + len; i++) {
            sj.add(i + "");
        }
        return sj.toString();
    }

    /**
     * @param n 要被分解的正整数（偶数）
     * @param x n的最大奇因数
     * @return n分解出来的连续正整数数列的最短奇数长度
     */
    public static long getMinOddLen(long n, long x) {
        // 我们需要遍历 3~x 中的所有奇数，尝试作为 n 分解出来的连续正整数数列的长度
        if (x < 3) {
            return -1;
        }

        // 找到可以被n整除的最小奇数，由于x是n的最大奇因数，因此这里直接对奇数x进行因数分解
        for (long i = 3; i * i <= x; i += 2) { // 如果x可以分解为两个奇数y,z，则必然：y <= z，因此x分解出来的最小奇数不可能超过sqrt(x)
            if (n % i == 0) return i;
        }

        return x; // 如果上面x无法分解，则返回x
    }
}
