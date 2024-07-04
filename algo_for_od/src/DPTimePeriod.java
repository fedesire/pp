import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/14 15:06
 */
/*服务之间交换的接口成功率作为服务调用关键质量特性，某个时间段内的接口失败率使用一个数组表示，
数组中每个元素都是单位时间内失败率数值，数组中的数值为0~100的整数，
给定一个数值(minAverageLost)表示某个时间段内平均失败率容忍值，即平均失败率小于等于minAverageLost，
找出数组中最长时间段，如果未找到则直接返回NULL。
输入描述
输入有两行内容，第一行为{minAverageLost}，第二行为{数组}，数组元素通过空格(” “)分隔，
minAverageLost及数组中元素取值范围为0~100的整数，数组元素的个数不会超过100个。
输出描述
找出平均值小于等于minAverageLost的最长时间段，输出数组下标对，格式{beginIndex}-{endIndx}(下标从0开始)，
如果同时存在多个最长时间段，则输出多个下标对且下标对之间使用空格(” “)拼接，多个下标对按下标从小到大排序。
用例
输入 1
0 1 2 3 4
输出 0-2
说明
输入解释：minAverageLost=1，数组[0, 1, 2, 3, 4]
前3个元素的平均值为1，因此数组第一个至第三个数组下标，即0-2
输入 2
0 0 100 2 2 99 0 2
输出 0-1 3-4 6-7
说明
输入解释：minAverageLost=2，数组[0, 0, 100, 2, 2, 99, 0, 2]
通过计算小于等于2的最长时间段为：
数组下标为0-1即[0, 0]，数组下标为3-4即[2, 2]，数组下标为6-7即[0, 2]，这三个部分都满足平均值小于等于2的要求，
因此输出0-1 3-4 6-7
*/
public class DPTimePeriod {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int minAverageLost = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n=nums.length;

        int[] preSum=new int[n+1];
        // preSum[i]表示前i个数的和 第i个数下标是i-1
        for (int i =1; i < n+1; i++) {
            preSum[i]=preSum[i-1]+nums[i-1];
        }
        ArrayList<int[]> res=new ArrayList<>();
        int maxLen=0;
        // 因为j是从i+1开始的 所以i的取值范围不能到n 否则j会越界 但是i也可以写到=n 因为下面j判断条件那里不会过
        for (int i = 0; i <n; i++) {
            for (int j = i+1; j <= n; j++) {
                // sum是第i+1个数到第j个数的和 即区间下标是[i,j-1]
                int sum = preSum[j] - preSum[i];
                int len=j-i;
                int lost=len*minAverageLost;
                // 找出平均值小于等于minAverageLost的最长时间段 两个约束条件
                if(sum<=lost){
                    if(len>=maxLen){
                        if(len>maxLen){
                            res=new ArrayList<>();
                        }
                        res.add(new int[]{i,j-1});
                        maxLen=len;
                    }
                }

            }
        }
        if (res.size() == 0)
            System.out.println("NULL");

        res.sort((a, b) -> a[0] - b[0]);

        StringJoiner joiner=new StringJoiner(" ");
        for (int[] re : res) {
            joiner.add(re[0]+"-"+re[1]);
        }
        System.out.println(joiner);
    }
}
