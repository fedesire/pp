/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/15 13:43
 */
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
/*小明和朋友们一起玩跳格子游戏，

每个格子上有特定的分数 score = [1, -1, -6, 7, -17, 7]，

从起点score[0]开始，每次最大的步长为k，请你返回小明跳到终点 score[n-1] 时，能得到的最大得分。

输入描述
第一行输入总的格子数量 n

第二行输入每个格子的分数 score[i]

第三行输入最大跳的步长 k

输出描述
输出最大得分

备注
格子的总长度 n 和步长 k 的区间在 [1, 100000]
每个格子的分数 score[i] 在 [-10000, 10000] 区间中
用例
输入
6
1 -1 -6 7 -17 7
2
输出	14
*/
public class PoiJump_ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[] scores = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(sc.nextLine());

        System.out.println(getResult(n, scores, k));
    }

    // n总格子数量 k最大步长
    public static int getResult(int n, int[] scores, int k) {
        // 第i个格子，可以从第i-k个格子~第i-1个格子调过来，因此本题滑窗的长度相当于k+1
        int len = k + 1;

        // dp[i]表示跳到第i个格子能得到的最大分数
        int[] dp = new int[n];
        dp[0] = scores[0];

        // 单调队列（单调递减，队首是滑窗最大值）
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(dp[0]);

        // 初始滑窗的形成过程（即只有新增尾部元素的过程）
        for (int i = 1; i < Math.min(len, n); i++) { // 注意当len > n时, 需要取n, 此时只有滑窗形成过程，没有滑窗移动过程
            // dp[i] = max(dp[0]~dp[i-1]) + scores[i]
            // 即单调队列队首保存的是max(dp[0]~dp[i-1])
            dp[i] = queue.getFirst() + scores[i];

            // 保持单调队列的单调递减性，即如果后入队的dp[i] 大于 队尾元素，则队尾元素出队
            while (queue.size() > 0 && dp[i] > queue.getLast()) {
                queue.removeLast();
            }

            // 当队尾没有比dp[i]更小的元素后，dp[i]才能入队
            queue.addLast(dp[i]);
        }

        // 滑窗的右移过程（即相较于老滑窗失去一个首元素，新增一个尾元素）
        for (int i = len; i < n; i++) {
            // 如果滑窗失去的元素dp[i - len]，和单调队列的队首元素queue[0]相同，则单调队列需要弹出头部元素
            if (dp[i - len] == queue.getFirst()) {
                queue.removeFirst();
            }

            // 下面逻辑同之前
            dp[i] = queue.getFirst() + scores[i];

            while (queue.size() > 0 && dp[i] > queue.getLast()) {
                queue.removeLast();
            }

            queue.addLast(dp[i]);
        }

        return dp[n - 1];
    }
}
