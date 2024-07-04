import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 17:35
 */
/*
* 在某个项目中有多个任务（用task数组表示）需要你进行处理，其中：

task[i] = [si, ei]
你可以在 si ≤ day ≤ ei 中的任意一天处理该任务，请返回你可以处理的最大任务数。

输入描述
第一行为任务数量 n

1 ≤ n ≤ 100000
后面 n 行表示各个任务的开始时间和终止时间，使用 si，ei 表示

1 ≤ si ≤ ei ≤ 100000
输出描述
输出为一个整数，表示可以处理的最大任务数。

用例
输入	3
1 1
1 2
1 3
输出	3
说明	无
*
* 1、统计输入的所有任务的时间段，仅按照结束时间降序，得到数组ranges

2、定义一个优先队列pq，仅用于保存任务的开始时间（开始时间越大，优先级越高），我们可以认为优先队列中保存的任务（的开始时间）对应的结束时间
* 都是相同的，我们定义这个公共结束时间为pq_end

3、遍历ranges，得到每一个任务的开始，结束时间range：[start, end]，然后比较遍历到任务的end 和 优先队列中所有任务的公共结束时间pq_end：

如果 end < pq_end，则在end ~ pq_end 这段间隔时间内，我们可以从pq中挑选出pq_end - end 个 较短任务进行执行，执行前需要检查对应任务的
* 开始时间 start <= pq_end，若不满足则不执行。每执行一个任务，则pq_end -= 1，count += 1（count是已执行的任务数量）。当pq_end ==
* end时，则将当前遍历的任务的start 加入 优先队列。

*/

import java.util.Scanner;

public class DSMaxTask_ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] ranges = new int[n][2];
        for (int i = 0; i < n; i++) {
            ranges[i][0] = sc.nextInt();
            ranges[i][1] = sc.nextInt();
        }

        // 将所有任务按照结束时间降序
        Arrays.sort(ranges, (a, b) -> b[1] - a[1]);

        // 优先队列中记录的是任务的开始时间，并且开始时间越大，优先级越高
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        // 优先队列中记录的是结束时间相同的任务的开始时间，pq_end就是优先队列中任务的相同结束时间
        int pq_end = Integer.MAX_VALUE;

        // 最大任务数
        int count = 0;

        for (int[] range : ranges) {
            // 当前任务的开始和结束时间
            int start = range[0];
            int end = range[1];

            // 如果当前任务的结束时间 小于 优先队列中记录的任务的结束时间，则两个结束时间之间的间隔时间段，可以处理一些紧急任务
            while (pq.size() > 0 && end < pq_end) {
                // 这里的紧急任务即指时间短的任务，即开始时间比较大的任务
                if (pq.poll() <= pq_end) {
                    // 如果紧急任务的开始时间未超过其结束时间，则可以执行
                    count++;
                    pq_end--; // 一个时刻只执行一个任务
                }
            }

            // 间隔时间消耗完后，优先队列中的任务的结束时间全部更新为当前任务的结束时间
            pq.add(start);
            pq_end = end;
        }

        // 收尾处理
        while (pq.size() > 0) {
            if (pq.poll() <= pq_end) {
                count++;
                pq_end--;
            }
        }

        System.out.println(count);
    }
}
