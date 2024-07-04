/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/14 17:03
 */
import java.util.*;
/*
* 一个局域网内有很多台电脑，分别标注为 0 ~ N-1 的数字。相连接的电脑距离不一样，所以感染时间不一样，感染时间用 t 表示。
其中网络内一台电脑被病毒感染，求其感染网络内所有的电脑最少需要多长时间。如果最后有电脑不会感染，则返回-1。
给定一个数组 times 表示一台电脑把相邻电脑感染所用的时间。
如图：path[i] = {i, j, t} 表示：电脑 i->j，电脑 i 上的病毒感染 j，需要时间 t。
输入描述
4
3
2 1 1
2 3 1
3 4 1
2
输出描述
2
用例
输入 4
3
2 1 1
2 3 1
3 4 1
2
输出 2
说明
第一个参数：局域网内电脑个数N，1 ≤ N ≤ 200；
第二个参数：总共多少条网络连接
第三个 2 1 1 表示2->1时间为1
第六行：表示病毒最开始所在电脑号2

* 另外，本题题目描述和用例不符。因为题目说：
一个局域网内有很多台电脑，分别标注为 0 ~ N-1 的数字。
但是题目用例，第一行给出N=4，但是第五行出现了电脑编号也是4的情况。
这影响到了下面代码中dist、visited数组长度的定义。当前代码实现以用例数据为准，即认为电脑编号是 1~N。
具体考试时需要随机应变。
* */
public class GraphVirus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // 邻接表
        HashMap<Integer, ArrayList<int[]>> graph = new HashMap<>();

        int m = sc.nextInt();
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(); // 出发点
            int v = sc.nextInt(); // 目标点
            int w = sc.nextInt(); // 出发点到达目标点的耗时

            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(new int[] {v, w});
        }

        // 记录源点到其他剩余的最短耗时
        int[] dist = new int[n + 1];
        // 初始时，假设源点不可达其他剩余点，即源点到达其他点的耗时无限大
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 源点
        int src = sc.nextInt();
        // 源点到达源点的耗时为0
        dist[src] = 0;

        // 优先队列needCheck中记录的其实是：已被探索过的路径的终点（路径指的是源点->终点）即下一次可以通过该节点到达其他节点
        // 优先队列优先级规则是，路径终点的权重（即源点->终点的耗时）越小越优先
        PriorityQueue<Integer> needCheck = new PriorityQueue<>((a, b) -> dist[a] - dist[b]);
        // 初始被探索过的路径只有源点本身
        needCheck.add(src);

        // 记录对应点是否在needCheck中
        boolean[] visited = new boolean[n + 1];
        visited[src] = true;

        // 循环结束后 从源点能到达的其他点dist[i]里都会记录耗时 到达不了的就还是Integer.MAX_VALUE
        while (needCheck.size() > 0) {
            // 取出最优路径的终点（耗时最少的路径）作为新的起点 要从这个起点开始看有哪些能到达的其他点
            int cur = needCheck.poll();
            visited[cur] = false;

            // 如果cur有可达的其他点
            if (graph.containsKey(cur)) {
                for (int[] next : graph.get(cur)) {
                    // v是可达的其他店
                    // w是cur->v的耗时
                    int v = next[0], w = next[1];

                    // 那么如果从源点到cur点的耗时是dist[cur]，那么源点到v点的耗时就是dist[cur] + w
                    int newDist = dist[cur] + w;
                    // 而源点到v的耗时之前是dist[v](最开始是Integer.maxvalue)，因此如果newDist < dist[v]，则找到更少耗时的路径
                    if (dist[v] > newDist) {
                        // 更新源点到v的路径，即更新v点权重
                        dist[v] = newDist;
                        // 如果v点不在needCheck中，则加入，因为v作为终点的路径需要加入到下一次最优路径的评比中
                        if (!visited[v]) {
                            visited[v] = true;
                            needCheck.add(v);
                        }
                    }
                }
            }
        }

        // dist记录的是源点到达其他各点的最短耗时，我们取出其中最大的就是源点走完所有点的最短耗时
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans = Math.max(ans, dist[i]);
        }

        // 如果存在某个点无法到达，则源点到该点的耗时为Integer.MAX_VALUE
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}