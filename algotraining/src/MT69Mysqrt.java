/**
 *给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 * @date 2024/2/24 10:09
 */
public class MT69Mysqrt {
    // 二分查找法 要点在于要处理可能溢出的情况 所以必须转成long
    public int mySqrt(int x) {
        int i=0,j=x;
        while(i<=j){ //必须有= 否则x=0返回值就是-1
            int mid=i+(j-i)/2;
            long t=(long)mid*mid; //不能只是long t=mid*mid 得先转
            if(t==x) return mid;
            else if(t>x)
                j=mid-1;
            else{
                if((long)(mid+1)*(mid+1)>x) //注意这里也要转
                    return mid;
                else
                    i=mid+1;
            }
        }
        return -1;
    }
    //由于 x平方根的整数部分 ans是满足 k^2 ≤x 的最大 k值，因此我们可以对 k进行二分查找，从而得到答案。
    public int mySqrt1(int x){
        int i=0,j=x,res=-1;
        while(i<=j){
            int mid=i+(j-i)/2;
            if((long)mid*mid<=x){
                res=mid;
                i=mid+1;
            }
            else
                j=mid-1;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Math.pow(100,100));
        System.out.println(new MT69Mysqrt().mySqrt(10000));
    }
}
