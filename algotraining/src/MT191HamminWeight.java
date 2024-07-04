/**
 *编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 * @date 2024/2/8 10:26
 */
public class MT191HamminWeight {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count=0;
        for(int i=0;i<32&&n!=0;i++){
            count+=n&1;
            n=n>>>1;;
        }
        return count;
    }
}
