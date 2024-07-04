/**
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 * @date 2024/2/6 9:36
 */
public class MT172trailingZeroes {
    //直接计算会导致溢出 即使用了long也会溢出 因为n的范围是[0,10000] 13！是62亿多就会超过int的范围
    public int trailingZeroes(int n) {
        if(n==0||n==1) return 0;
        long res=1;
        for(int i=1;i<=n;i++){
            res=res*i;
        }
        System.out.println(res);
        int ans=0;
        while(res%10==0){
            ans++;
            res=res/10;
        }
        return ans;
    }

//
    public int trailingZeroes1(int num){
        int count=0;
        while(num>0){
            count+=num/5;
            num=num/5;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new MT172trailingZeroes().trailingZeroes1(13));
    }
}
