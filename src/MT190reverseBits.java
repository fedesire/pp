/**
 * 颠倒给定的 32 位无符号整数的二进制位。输入一定会是32位的数
 * @date 2024/2/7 10:26
 */
public class MT190reverseBits {
    public static int reverseBits(int n) {
//        System.out.println(Integer.toString(n));
        StringBuilder sb=new StringBuilder(Integer.toString(n));
//        System.out.println(sb.reverse().toString());
        return Integer.parseInt(sb.reverse().toString(),2);

    }
//    如果某一位是 1 的话，则将答案相应的对称位置修改为 1。不用考虑某一位是0的情况 因为本来answer就是0 所有的
    //初始就是0
    public static int reverseBits1(int n) {
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int m=n>>i;
            int t = (n >> i) & 1;
            if (t == 1) {
                ans |= (1 << (31 - i));
            }
        }
        return ans;
    }

    //逐位颠倒
    public int reverseBits2(int n) {
        int rev=0;
        for(int i=0;i<32;i++){
            rev=rev|((n>>i)&1)<<(31-i);
        }
        return rev;
    }

    /*将 n视作一个长为 323232 的二进制串，从低位往高位枚举 n的每一位，将其倒序添加到翻转结果 rev 中。

代码实现中，每枚举一位就将 n右移一位，这样当前 n的最低位就是我们要枚举的比特位。当 n为 0时即可结束循环。

需要注意的是，在某些语言（如 Java）中，没有无符号整数类型，因此对 n的右移操作应使用逻辑右移*/
    public int reverseBits3(int n) {
        int rev = 0;
        //和上面的对比 多了n!=0的判断条件 所以可以提前结束循环 不过需要使用逻辑右移（无符号右移）否则遇到
        //高位是1的时候 视作负数 右移补1 那就会一直循环下去
        for (int i = 0; i < 32 && n != 0; ++i) {
            rev |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return rev;
    }

    //位运算分治
    /*0x55555555是01010101010101010101010101010101 n&这个数的效果就是提取了0 2 4这样所有的偶数位 结果再左移1位
    * 就能到奇数位上去*/
    public int reverseBits4(int n) {
        n = ((n & 0xAAAAAAAA) >>> 1)  | ((n & 0x55555555) << 1); //奇偶位互换
        n = ((n & 0xCCCCCCCC) >>> 2)  | ((n & 0x33333333) << 2); //每2位互换
        n = ((n & 0xF0F0F0F0) >>> 4)  | ((n & 0x0F0F0F0F) << 4);//每4位互换
        n = ((n & 0xFF00FF00) >>> 8)  | ((n & 0x00FF00FF) << 8); //每8位互换
        n = ((n & 0xFFFF0000) >>> 16) | ((n & 0x0000FFFF) << 16); //前16位和后16位互换
        return n;
    }

    public static void main(String[] args) {

//        System.out.println(reverseBits1(00000010100101000001111010011100));
    }
}
