import java.util.PriorityQueue;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 19:51
 */
// https://fcqian.blog.csdn.net/article/details/134939367?spm=1001.2014.3001.5502
import java.util.PriorityQueue;
import java.util.Scanner;

// 本题实际考试为核心代码模式，即只需要写出下面Solution类实现即可
class Solution {
    static int[][] offsets = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    int calcTime(
            int[][] lights, int timePerRoad, int rowStart, int colStart, int rowEnd, int colEnd) {
        int n = lights.length;
        int m = lights[0].length;

        int[][][] dist = new int[n][m][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 到达位置(i,j)的路径有四个来源方向
                for (int k = 0; k < 4; k++) {
                    // dist[i][j][k] 表示从来源方向k到达位置(i,j)所需要的时间，初始化INT_MAX
                    dist[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        // 小顶堆，堆中元素是数组 [前一个位置行号，前一个位置列号，当前位置行号，当前位置列号，到达当前位置需要的时间]
        // 到达当前位置的时间越小，优先级越高
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[4] - b[4]);

        // 四个来源方向到达出发点位置 (rowStart, colStart) 所需时间均为 0
        for (int k = 0; k < 4; k++) {
            dist[rowStart][colStart][k] = 0;
            // 出发点位置没有前一个位置，因此前一个位置设为(-1,-1)
            pq.add(new int[] {-1, -1, rowStart, colStart, 0});
        }

        // 每次取出最短路
        while (pq.size() > 0) {
            int[] tmp = pq.poll();

            int preX = tmp[0];
            int preY = tmp[1];
            int curX = tmp[2];
            int curY = tmp[3];
            int cost = tmp[4];

            // 向四个方向探索
            for (int k = 0; k < 4; k++) {
                // 新位置
                int newX = curX + offsets[k][0];
                int newY = curY + offsets[k][1];

                // 新位置越界，则不可进入
                if (newX < 0 || newX >= n || newY < 0 || newY >= m) continue;

                // 本题不允许掉头，因此新位置处于掉头位置的话，不可进入
                if (newX == preX && newY == preY) continue;

                // 每走一步都要花费 timePerRoad 单位时间
                int newCost = cost + timePerRoad;

                // 出发的第一步，或者右拐，不需要等待红绿灯，其他情况需要等待红绿灯 lights[curX][curY] 单位时间
                if (preX != -1 && preY != -1 && getDirection(preX, preY, curX, curY, newX, newY) >= 0) {
                    newCost += lights[curX][curY];
                }

                // 如果以来源方向k到达位置（newX, newY）花费的时间 newCost 并非更优，则终止对应路径探索
                if (newCost >= dist[newX][newY][k]) continue;

                // 否则更新为更优时间
                dist[newX][newY][k] = newCost;
                // 并继续探索该路径
                pq.add(new int[] {curX, curY, newX, newY, newCost});
            }
        }

        // 最终取(rowEnd, colEnd)终点位置的四个来源方向路径中最短时间的作为题解
        int ans = Integer.MAX_VALUE;
        for (int k = 0; k < 4; k++) {
            ans = Math.min(ans, dist[rowEnd][colEnd][k]);
        }

        return ans;
    }

    /**
     * 根据三点坐标，确定拐弯方向
     *
     * @param preX 前一个点横坐标
     * @param preY 前一个点纵坐标
     * @param curX 当前点横坐标
     * @param curY 当前点纵坐标
     * @param nextX 下一个点横坐标
     * @param nextY 下一个点纵坐标
     * @return cur到next的拐弯方向， >0 表示向左拐， ==0 表示直行（含调头）， <0 表示向右拐
     */
    public static int getDirection(int preX, int preY, int curX, int curY, int nextX, int nextY) {
        // 向量 pre->cur
        int dx1 = curX - preX;
        int dy1 = curY - preY;

        // 向量 cur->next
        int dx2 = nextX - curX;
        int dy2 = nextY - curY;

        // 两个向量的叉积 >0 表示向左拐， ==0 表示直行（含调头）， <0 表示向右拐
        return dx1 * dy2 - dx2 * dy1;
    }
}

// 输入处理类，本题实际考试为核心代码模式，无需进行输入处理
public class GraphRoad_ {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] lights = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                lights[i][j] = sc.nextInt();
            }
        }

        int timePreRoad = sc.nextInt();

        int rowStart = sc.nextInt();
        int colStart = sc.nextInt();

        int rowEnd = sc.nextInt();
        int colEnd = sc.nextInt();

        System.out.println(
                new Solution().calcTime(lights, timePreRoad, rowStart, colStart, rowEnd, colEnd));
    }
}