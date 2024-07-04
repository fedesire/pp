import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/15 11:13
 */
/*一个整数可以由连续的自然数之和来表示。
给定一个整数，计算该整数有几种连续自然数之和的表达式，且打印出每种表达式
输入描述
一个目标整数T (1 <=T<= 1000)
输出描述
该整数的所有表达式和表达式的个数。
如果有多种表达式，输出要求为：自然数个数最少的表达式优先输出，每个表达式中按自然数递增的顺序输出，具体的格式参见样例。
在每个测试数据结束时，输出一行”Result:X”，其中X是最终的表达式个数。
用例
输入 9
输出
9=9
9=4+5
9=2+3+4
Result:3
说明
整数 9 有三种表示方法，第1个表达式只有1个自然数，最先输出，
第2个表达式有2个自然数，第2次序输出，
第3个表达式有3个自然数，最后输出。
每个表达式中的自然数都是按递增次序输出的。
数字与符号之间无空格
输入 10
输出
10=10
10=1+2+3+4
Result:2
说明 无
*/
public class PoiNaturalNumber {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums=new int[n+1];
        for (int i = 0; i <=n; i++) {
            nums[i]=i;
        }
        int l=1,r=1;// 滑动窗口是[l,r) 所以nums[r]是当前要判断的元素
        ArrayList<int[]> res=new ArrayList<>();
        int sum=0;
        while(l<=n){
            if(sum+nums[r]<n){
                sum+=nums[r++];
            }
            else if(sum+nums[r]==n){

                res.add(Arrays.copyOfRange(nums,l,r+1));
                sum-=nums[l++];
                // 这个条件是为了当l和r都到了最后 21=21 此时就要退出了 不然下一步再进入循环就会索引越界了
                if(r>=n)
                    break;
                sum+=nums[r++];

            }
            else{
                sum-=nums[l++];
            }
        }

        res.sort((a,b)->(b[0]-a[0]));
        for (int[] re : res) {
        StringJoiner joiner=new StringJoiner("+");
            for (int i : re) {
                joiner.add(i+"");
            }
            System.out.println(n+"="+joiner);
        }
        System.out.println("Result:"+res.size());
    }
}
