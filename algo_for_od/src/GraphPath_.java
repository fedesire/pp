import java.util.PriorityQueue;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 20:40
 */
/*
* 评估一个网络的信号质量，其中一个做法是将网络划分为栅格，然后对每个栅格的信号质量计算。
路测的时候，希望选择一条信号最好的路线（彼此相连的栅格集合）进行演示。
现给出 R 行 C 列的整数数组 Cov，每个单元格的数值 S 即为该栅格的信号质量（已归一化，无单位，值越大信号越好）。
要求从 [0, 0] 到 [R-1, C-1]设计一条最优路测路线。返回该路线得分。
规则：
路测路线可以上下左右四个方向，不能对角
路线的评分是以路线上信号最差的栅格为准的，例如路径 8→4→5→9 的值为4，该线路评分为4。线路最优表示该条线路的评分最高。
输入描述
第一行表示栅格的行数 R
第二行表示栅格的列数 C
第三行开始，每一行表示栅格地图一行的信号值，如5 4 5
输出描述
最优路线的得分
备注
1 ≤ R，C ≤ 20
0 ≤ S ≤ 65535
用例
输入 3
3
5 4 5
1 2 6
7 4 6
输出 4
说明 路线为：5→4→5→6→6
输入 6
5
3 4 6 3 4
0 2 1 1 7
8 8 3 2 7
3 2 4 9 8
4 1 2 0 0
4 6 5 4 3
输出 3
说明 路线为：3→4→6→3→4→7→7→8→9→4→3→8→8→3→4→4→6→5→4→3*/
import java.util.PriorityQueue;
import java.util.Scanner;

public class GraphPath_ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();

        int[][] matrix = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        // dist[i]记录的 起点0 到 终点i 的所有路径中“最大的”最小权值节点的权值
        // 其中 i 是将二维坐标一维化后的值，比如(x,y)坐标一维化后为 x * c + y; (c是列数)
        // dist[i] 记录的 “最大的”最小权值节点的权值，因此需要初始化为一个 "最小的" 最小权值节点的权值，方便后面被更大者取代，
        // 由于本题节点的最小权值>=0，因此这里可以初始化为0
        int[] dist = new int[r * c];
        // 起点0 到 终点0 路径的最小权值节点就是自身，即matrix[0][0]点的权重
        dist[0] = matrix[0][0];

        // 优先队列记录路径（终点），并且路径中的最小权值节点的权值越大，优先级越高
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> dist[b] - dist[a]);
        // 初始时将(0,0)入队
        pq.add(0);

        // 上下左右的方向偏移量
        int[][] offsets = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        while (pq.size() > 0) {
            // 取出优先队列中优先级最大的路径（终点）
            int u = pq.poll();

            // 将一维化坐标u，解析为二维坐标(x,y)
            int x = u / c;
            int y = u % c;

            // 已找到dist[r-1][c-1]最优解，则可以提前结束
            if (x == r - 1 && y == c - 1) break;

            // 向上下左右四个方向探索
            for (int[] offset : offsets) {
                // 新位置坐标
                int newX = x + offset[0];
                int newY = y + offset[1];

                // 新位置越界则无法访问
                if (newX < 0 || newX >= r || newY < 0 || newY >= c) continue;

                // 新位置的一维化坐标
                int v = newX * c + newY;
                // 当前路径（终点u）的最小权值节点的权值为dist[u]
                // 要加入当前路径的新位置的点的权值 matrix[newX][newY]
                // 那么形成的新路径的最小权值节点的权值即为 w = min(dist[u], matrix[newX][newY])
                int w = Math.min(dist[u], matrix[newX][newY]);

                // 形成的新路径的终点为 v（即新位置一维化坐标）
                // 而dist[v]记录的是起点到点v的所有路径中“最大的”最小权值节点
                if (dist[v] < w) {
                    // 因此如果dist[v] < w的话，则更新dist[v]
                    dist[v] = w;
                    // 并将新路径加入优先队列，参与下一轮比较
                    pq.add(v);
                }
            }
        }

        // 返回起点（0，0）到终点(r-1, c-1)的所有路径中"最大的"最小权值节点的权值
        System.out.println(dist[r * c - 1]);
    }
}