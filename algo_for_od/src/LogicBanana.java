import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 16:35
 */
/*
* 一只贪吃的猴子，来到一个果园，发现许多串香蕉排成一行，每串香蕉上有若干根香蕉。每串香蕉的根数由数组numbers给出。

猴子获取香蕉，每次都只能从行的开头或者末尾获取，并且只能获取N次，求猴子最多能获取多少根香蕉。

输入描述
第一行为数组numbers的长度

第二行为数组numbers的值每个数字通过空格分开

第三行输入为N，表示获取的次数

输出描述
按照题目要求能获取的最大数值

备注
1 ≤ numbers.length ≤ 100000
1 ≤ numbers ≤ 100
1 ≤ N ≤ numbers.length
用例
输入	7
1 2 2 7 3 6 1
3
输出	10
说明	第一次获取香蕉，无论是从行的开头或者末尾获取，得到的香蕉根数目为1, 但是，从行末尾获取能获取到最优的策略，后面可以直接得到香蕉根数目6和3。因此最终根数为1+6+3=10
输入	3
1 2 3
3
输出	6
说明	全部获取所有的香蕉，因此最终根数为1+2+3=6
输入	4
4 2 2 3
2
输出	7
说明	第一次获取香蕉为行的开头，第二次获取为行的末尾，因此最终根数为4+3=7
*
* 无论我们怎么选，左边选择的，以及右边选择的，必然都是连续的，且是从头尾开始的连续
因此，本题其实可以简化为，将n次分解为左边选择的个数，以及右边选择的个数。
* 从左边选0个 右边选n个逐步过渡到左边选n个 右边选0个 过程中不断更新最大值
*/
import java.util.Arrays;
import java.util.Scanner;

public class LogicBanana {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int len = Integer.parseInt(sc.nextLine());
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = Integer.parseInt(sc.nextLine());

        System.out.println(getResult(len, nums, n));
    }

    // len是数组的长度 n是获取的次数
    public static int getResult(int len, int[] nums, int n) {
        // 初始时，左边选择0个，因此左边选择的香蕉数为 0
        int leftSum = 0;

        // 初始时，右边选择n个，因此右边选择的香蕉数为 nums[len-n] ~ nums[len - 1] 这个n个元素之和
        int rightSum = 0;
        for (int i = len - n; i < len; i++) {
            rightSum += nums[i];
        }

        // 如果选择数n == len，即全选，此时直接返回初始rightSum
        if (len == n) {
            return rightSum;
        }

        // 如果不是全选
        // sum记录当前选择结果
        int sum = leftSum + rightSum;
        // ans记录所有选择结果中最大的
        int ans = sum;

        // l指向左边将要获得的，即左边获得一个
        int l = 0;
        // r指向右边将要失去的，即右边失去一个
        int r = len - n;

        while (l < n) {
            sum += nums[l++] - nums[r++];
            ans = Math.max(ans, sum);
        }

        return ans;
    }
}