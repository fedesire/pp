import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 11:26
 */
/*
* 用数组代表每个人的能力，一个比赛活动要求参赛团队的最低能力值为N，每个团队可以由1人或者2人组成，且1个人只能参加1个团队，
* 计算出最多可以派出多少只符合要求的团队。

输入描述
第一行代表总人数，范围1-500000
第二行数组代表每个人的能力
数组大小，范围1-500000
元素取值，范围1-500000
第三行数值为团队要求的最低能力值，范围1-500000
输出描述
最多可以派出的团队数量

用例
输入	5
3 1 5 7 9
8
输出	3
说明	说明 3、5组成一队   1、7一队  9自己一队  输出3
输入	7
3 1 5 7 9 2 6
8
输出	4
说明	3、5组成一队，1、7一队，9自己一队，2、6一队，输出4
输入	3
1 1 9
8
输出	1
说明	9自己一队，输出1
*/
import java.util.Arrays;
import java.util.Scanner;

public class PointMaxTeam {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        int[] capacities =
                Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int minCap = Integer.parseInt(sc.nextLine());

        System.out.println(getResult(n, capacities, minCap));
    }

    public static int getResult(int n, int[] capacities, int minCap) {
        // 升序
        Arrays.sort(capacities);

        int l = 0;
        int r = n - 1;

        // 记录题解
        int ans = 0;

        // 为了组更多队伍，我们应该尽量让单人组队，即：需要将能力值大于等于minCap的筛选出来，单人组队
        // 单人组队
        while (r >= l && capacities[r] >= minCap) {
            r--;
            ans++;
        }

        // 双人组队
        while (l < r) {
            int sum = capacities[l] + capacities[r];

            // 如果两个人的能力值之和>=minCap，则组队
            if (sum >= minCap) {
                ans++;
                l++;
                r--;
            } else {
                // 否则将能力低的人剔除，换下一个能力更高的人
                l++;
            }
        }

        return ans;
    }
}