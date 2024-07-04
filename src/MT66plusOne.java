/**
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * @date 2024/2/6 9:29
 */
public class MT66plusOne {
    public int[] plusOne(int[] digits) {
        int i=digits.length-1;
        while(i>=0){
            if(digits[i]!=9){
                digits[i]+=1;
                break;
            }
            digits[i]=0;
            i--;
        }
        if(i==-1){
            int[] res=new int[digits.length+1];
            res[0]=1;
            return res;
        }
        return digits;
    }
}
