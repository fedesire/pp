import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 15:00
 */
/*
* 某部门计划通过结队编程来进行项目开发，
已知该部门有 N 名员工，每个员工有独一无二的职级，每三个员工形成一个小组进行结队编程，结队分组规则如下：
从部门中选出序号分别为 i、j、k 的3名员工，他们的职级分贝为 level[i]，level[j]，level[k]，
结队小组满足 level[i] < level[j] < level[k] 或者 level[i] > level[j] > level[k]，
其中 0 ≤ i < j < k < n。
请你按上述条件计算可能组合的小组数量。同一员工可以参加多个小组。
输入描述
第一行输入：员工总数 n
第二行输入：按序号依次排列的员工的职级 level，中间用空格隔开
限制：
1 ≤ n ≤ 6000
1 ≤ level[i] ≤ 10^5
输出描述
可能结队的小组数量
用例
输入 4
1 2 3 4
输出 4
说明 可能结队成的组合(1,2,3)、(1,2,4)、(1,3,4)、(2,3,4)
输入 3
5 4 7
输出 0
说明 根据结队条件，我们无法为该部门组建小组*/

import java.util.Arrays;
import java.util.Scanner;

public class TeamCode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[] levels = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult(n, levels));
    }

    public static long getResult(int n, int[] levels) {
        long ans = 0;
// levels[i]作为中间值 从左边 右边各取一个满足条件的值 所以i从1出发 到倒数第二个数
        for (int i = 1; i < n - 1; i++) {
            int mid = levels[i];

            long leftSmallerCount = 0;
            long leftBiggerCount = 0;

            for (int j = 0; j < i; j++) {
                if (levels[j] > mid) {
                    leftBiggerCount++;
                } else {
                    leftSmallerCount++;
                }
            }

            long rightSmallerCount = 0;
            long rightBiggerCount = 0;

            for (int k = i + 1; k < n; k++) {
                if (levels[k] > mid) {
                    rightBiggerCount++;
                } else {
                    rightSmallerCount++;
                }
            }
            // levels[i]作为中间值 从左边 右边各取一个满足条件的值
            ans += leftSmallerCount * rightBiggerCount + leftBiggerCount * rightSmallerCount;
        }

        return ans;
    }
}