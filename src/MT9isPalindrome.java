/**
 *给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 你能不将整数转为字符串来解决这个问题吗？
 * @date 2024/2/6 8:44
 */
public class MT9isPalindrome {
    public boolean isPalindrome(int x) {
        if(x<0) return false;
        String s=String.valueOf(x);
        int i=0,j=s.length()-1;
        while(i<=j){
            if(s.charAt(i++)!=s.charAt(j--))
                return false;
        }
        return true;
    }

    /*将数字本身反转，然后将反转后的数字与原始数字进行比较，如果它们是相同的，那么这个数字就是回文。
但是，如果反转后的数字大于 int.MAX\text{int.MAX}int.MAX，我们将遇到整数溢出问题。
为了避免数字反转可能导致的溢出问题，为什么不考虑只反转 int\text{int}int 数字的一半？毕竟，如果该数字是回文，
其后半部分反转后应该与原始数字的前半部分相同。现在的问题是，我们如何知道反转数字的位数已经达到原始数字位数的
一半？由于整个过程我们不断将原始数字除以 10，然后给反转后的数字乘上 10，所以，当原始数字小于或等于反转后的数字
时，就意味着我们已经处理了一半位数的数字了。
*/
    public boolean isPalindrome1(int x){
        if(x<0||(x%10==0&&x!=0)) return false;
        int reversedVal = 0;
        while (x>reversedVal){
            reversedVal=reversedVal*10+x%10;
            x=x/10;
        }
        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除
        return x==reversedVal||x==reversedVal/10;


    }
}
