/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/14 16:51
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
/*幼儿园组织活动，老师布置了一个任务：
每个小朋友去了解与自己同一个小区的小朋友还有几个。
我们将这些数量汇总到数组 garden 中。
请根据这些小朋友给出的信息，计算班级小朋友至少多少个？
输入描述
输入：garden[] = {2, 2, 3}
输出描述
输出：7
备注
garden 数组长度最大为 999
每个小区的小朋友数量最多 1000 人，也就是 garden[i] 的范围为 [0, 999]*/
public class GreedyCommunity {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.println(getResult(nums));
        } catch (Exception e) {
            System.out.println("0");
        }
    }

    /*
    * 假设：有x个小朋友都反馈有y个人与自己小区相同

那么此时“至多”有 x * (y+1) 个小朋友，即这x个小朋友的小区各不相同，而这x个小朋友的每一个人都反馈还有y个人的小区和自己相同，即每一个小区都有 y + 1 人。

那么“至少”情况该如何分析呢？此时需要利用到前面的“合并”操作。

由于这x个小朋友都各自反馈有y个人和自己小区相同。因此我们可以将这x个小朋友，分成多段，每段y+1个小朋友，而每段的这些小朋友是可以合并的，即最终会有：

ceil( x / (y + 1) ) * (y+1)

其中 ceil( x / (y+1) ) 就是分段数，这里向上取整的原因是，对于某一段不足y+1个小朋友时，

比如有4个小朋友都反馈有2个人和自己小区相同，那么这4个小朋友中，可以选择3人组成一段，剩余的4-3=1人单独组成一段，如下所示，3人组成一段的小区都是A，而单独组成一段的小区是B

A  A  A | B

其中 A A A 三个小朋友互相弥补，组成一段，因此不需要外人加入。而B小朋友说还有2个人和自己小区一样，而当前进行反馈的小朋友已经组合完了，因此需要额外加入2个外人。

A A A | B B B

上面策略其实就是求出，根据反馈相同小区y的小朋友的数量x，求出至少的分段数，而每个分段就是y+1人。
*/

    public static int getResult(int[] nums) {
        // num是反馈，cnts[num]是给出相同反馈的（小朋友）数量  key=3 value=6 代表有6个人反馈有3个小朋友和自己一个小区
        HashMap<Integer, Integer> cnts = new HashMap<>();

        for (int num : nums) {
            cnts.put(num, cnts.getOrDefault(num, 0) + 1);
        }

        // ans 记录题解
        int ans = 0;
        for (int key : cnts.keySet()) {
            // key是反馈，假设某小朋友反馈有key个人和自己一个小区，那么该小区总人数为total = key+1
            int total = key + 1;
            ans += Math.ceil(cnts.get(key) * 1.0 / total) * total;
        }

        return ans;
    }
}
