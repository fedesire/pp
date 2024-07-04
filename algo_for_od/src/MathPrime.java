import java.util.Scanner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 10:34
 */
/*
* RSA加密算法在网络安全世界中无处不在，它利用了极大整数因数分解的困难度，数据越大，安全系数越高，给定一个 32 位正整数，请对其进行因数分解
* ，找出是哪两个素数的乘积。
输入描述
一个正整数 num 0 < num < 2147483647
输出描述
如果成功找到，以单个空格分割，从小到大输出两个素数，分解失败，请输出-1, -1
用例
输入 15
输出 3 5
输入 27
输出 -1 -1*/
public class MathPrime {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n  = scanner.nextInt();
        int i=2;
        while(i<n){
            if(n%i==0){
                int j=n/i;
                if(isPrime(i)&&isPrime(j)){
                    System.out.println(i+" "+j);
                    break;
                }
                else{
                    System.out.println("-1"+" "+-1);
                    break;
                }
            }
            i++;
        }
    }
    // 求解素数之积
    public static String getResult(int n) {
        // 如果n为素数，则必然不可能是两个素数之积
        if (isPrime(n)) {
            return "-1 -1";
        }

        // 假设i为n的因子
        for (int i = 2; i < n; i++) {
            // 若n不能整除i,则i不是n的因子，继续下次循环，找新的i
            // 若n可以整除i,则i就是n的因子
            if (n % i == 0) {
                // j为n的另一因子
                int j = n / i;

                // 只有i,j因子都为素数时，n才是符合题意的素数之积
                if (isPrime(i) && isPrime(j)) {
                    // 如果n为两个素数之积，则n只能分解为这两个因子，因为素数无法再次分解出其他因子，也就是说n不再有其他因子了（因子不包含1和自身）
                    return i < j ? i + " " + j : j + " " + i;
                } else {
                    // 如果i，j有一个不是素数因子，则说明n存在非素数因子，此时n不可能是素数之积
                    // 如果i，j为相同的素数因子，则n不是满足题意的素数之积
                    // 此时可以判定n不符合要求了，直接退出循环
                    break;
                }
            }
        }

        return "-1 -1";
    }


    private static boolean isPrime(int n) {
        if(n<=3)
            return n>1;
        // 素数不能是非2的其他偶数
        if(n%2==0)
            return false;
        // 跳过了除以4 6 8 除的顺序是3 5 7 9
        for (int i = 3; i <=Math.sqrt(n); i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }
}

