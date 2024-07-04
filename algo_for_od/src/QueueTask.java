/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/14 14:12
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.function.Function;

/*现有N个任务需要处理，同一时间只能处理一个任务，处理每个任务所需要的时间固定为1。

每个任务都有最晚处理时间限制和积分值，在最晚处理时间点之前处理完成任务才可获得对应的积分奖励。

可用于处理任务的时间有限，请问在有限的时间内，可获得的最多积分。

输入描述
第一行为一个数 N，表示有 N 个任务

1 ≤ N ≤ 100
第二行为一个数 T，表示可用于处理任务的时间

1 ≤ T ≤ 100
接下来 N 行，每行两个空格分隔的整数（SLA 和 V），SLA 表示任务的最晚处理时间，V 表示任务对应的积分。

1 ≤ SLA ≤ 100
0 ≤ V ≤ 100000
输出描述
可获得的最多积分

用例
输入
4
3
1 2
1 3
1 4
1 5
输出	5
说明	虽然有3个单位的时间用于处理任务，可是所有任务在时刻1之后都无效。
所以在第1个时间单位内，选择处理有5个积分的任务。1-3时无任务处理。
输入
4
3
1 2
1 3
1 4
3 5
输出	9
说明
第1个时间单位内，处理任务3，获得4个积分

第2个时间单位内，处理任务4，获得5个积分

第3个时间单位内，无任务可处理

共获得9个积分
*/
public class QueueTask {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int t = sc.nextInt();

        int[][] wos = new int[n][2];
        for (int i = 0; i < n; i++) {
            wos[i][0] = sc.nextInt();
            wos[i][1] = sc.nextInt();
        }

        System.out.println(getResult(wos, t));
    }

    // 这样做不行 并不是每个时刻积分最多的任务就是执行的任务 可能其他组的任务积分有更高的
    //3
    //3
    //1 1
    //2 10
    //2 20
    public static int getResult1(int[][] wos,int t){
        HashMap<Integer,Integer> hm=new HashMap<>();
        for (int i = 0; i < wos.length; i++) {
            int endTime=wos[i][0];
            int integration=wos[i][1];
            if(!hm.containsKey(endTime))
                hm.put(endTime,integration);
            else{
                if(hm.get(endTime)<integration)
                    hm.put(endTime,integration);

            }
        }
        int res=0;
        for (Integer i : hm.keySet()) {
            if(i<=t)
                res+=hm.get(i);
            else break;
        }
        return res;
    }

    public static int getResult(int[][] wos, int t) {
        // 按照任务截止时间升序
        Arrays.sort(wos, (a, b) -> a[0] - b[0]);

        // pq用于按照积分对任务进行优先级排序，积分越小，优先级越高，目的是为了每次替换掉最少积分的工单
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);

        // 当前时间
        int curTime = 0;
        // 已获得的积分
        int ans = 0;

        // 遍历任务 每个任务都尝试执行一下 只不过后续如果发现有积分更高的任务就会替代前面积分小的任务 前面积分小的任务就等于没有执行过
        for (int[] wo : wos) {
            int endTime = wo[0]; // 任务截止时间
            int score = wo[1]; // 任务积分

            if (curTime < endTime) {
                // 如果 curTime < 当前任务的截止时间，则curTime时刻可以指向当前任务
                pq.offer(score);
                ans += score;
                curTime++;
            } else {
                // 如果 curTime >= 当前任务的截止时间，则当前任务只能在curTime时刻之前找一个时间点执行

                // pq中记录的就是curTime之前时刻执行的任务 注释掉也能ac 没看出来这里的作用 什么时候会size=0
                if (pq.size() == 0) {
                    continue;
                }

                // 此时取出pq记录的可执行的任务中最小积分的那个
                int min_score = pq.peek();

                // 如果当前任务的积分 > 前面时间内可执行的任务中最小积分
                if (score > min_score) {
                    // 则我们应该将执行pq中最小积分任务的时间，用于执行当前任务，因为这样可以获得更大积分
                    pq.poll();
                    pq.offer(score);
                    ans += score - min_score;
                }
            }
        }

        // 由于时间限制为t单位，而每个任务花费1单位时间，因此最多完成t个任务，对于多出任务应该去除，且优先去除积分少的
        while (pq.size() > t && t > 0) {
            ans -= pq.poll();
        }

        return ans;
    }
}
