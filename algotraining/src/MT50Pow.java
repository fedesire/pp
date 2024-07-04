/**
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * n 是一个整数
 * 要么 x 不为零，要么 n > 0 。
 * -104 <= xn <= 104
 * @date 2024/2/24 11:04
 */
public class MT50Pow {
    public double myPow(double x, int n) {
        long m=n; //要转为long 是因为n=-2的31次时 转为正数int会溢出
        return m>=0?calculatePow(x,m):1/calculatePow(x,-m);
    }
    public double calculatePow(double x,long m){
        if(m==0)
            return 1.0;
        double y=calculatePow(x,m/2);
        return m%2==0?y*y:y*y*x;
    }
}
