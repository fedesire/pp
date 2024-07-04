import java.util.LinkedList;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 17:07
 */
/*
* 宝宝和妈妈参加亲子游戏，在一个二维矩阵（N*N）的格子地图上，宝宝和妈妈抽签决定各自的位置，地图上每个格子有不同的糖果数量，部分格子有障碍物。

游戏规则是妈妈必须在最短的时间（每个单位时间只能走一步）到达宝宝的位置，路上的所有糖果都可以拿走，不能走障碍物的格子，只能上下左右走。

请问妈妈在最短到达宝宝位置的时间内最多拿到多少糖果（优先考虑最短时间到达的情况下尽可能多拿糖果）。

输入描述
第一行输入为 N，N 表示二维矩阵的大小

之后 N 行，每行有 N 个值，表格矩阵每个位置的值，其中：

-3：妈妈
-2：宝宝
-1：障碍
≥0：糖果数（0表示没有糖果，但是可以走）
输出描述
输出妈妈在最短到达宝宝位置的时间内最多拿到多少糖果，行末无多余空格

备注
地图最大 50*50

用例
输入	4
3 2 1 -3
1 -1 1 1
1 1 -1 2
-2 1 2 3
输出	9
*/
import java.util.LinkedList;
import java.util.Scanner;

public class BFSCandyGame {
    static int n;
    static int[][] matrix;
    static int[][] candy;
    static int[][] offsets = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        matrix = new int[n][n];
        candy = new int[n][n];

        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                candy[i][j] = -1;

                matrix[i][j] = sc.nextInt();
                // 妈妈的位置
                if (matrix[i][j] == -3) {
                    candy[i][j] = 0;
                    queue.add(i * n + j); // 二维坐标一维化
                }
            }
        }

        // 记录题解
        int ans = -1;

        // bfs 按层扩散
        while (queue.size() > 0) {
            // 记录当前扩散层的点
            LinkedList<Integer> newQueue = new LinkedList<>();

            // 当前层是否有宝宝所在的点
            boolean flag = false;

            for (int pos : queue) {
                // 源点坐标
                int x = pos / n;
                int y = pos % n;

                // 向四个方向扩散
                for (int[] offset : offsets) {
                    // 当前扩散点坐标
                    int newX = x + offset[0];
                    int newY = y + offset[1];

                    // 当前扩散点坐标越界，或者扩散点是墙，则无法扩散
                    if (newX < 0 || newX >= n || newY < 0 || newY >= n || matrix[newX][newY] == -1) continue;

                    // 当前扩散点坐标对应的糖果数量为-1，说明对应扩散点坐标位置还没有加入到当前扩散层
                    if (candy[newX][newY] == -1) {
                        newQueue.addLast(newX * n + newY); // 加入当前扩散层
                    }

                    // 当前扩散点可能会被多个源点扩散到，因此比较保留扩散过程中带来的较大糖果数
                    // candy[newX][newY] 记录的是当前扩散点获得的糖果数
                    // candy[x][y] + Math.max(0, matrix[newX][newY]) 记录的是从源点(x,y)带来的糖果数 + (newX,newY)位置原本的糖果数
                    // Math.max(0,matrix[newX][newY])是因为最后到达宝宝节点上的matrix[newX][newY]=-2 但该节点应该是没有糖果+0
                    candy[newX][newY] =
                            Math.max(candy[newX][newY], candy[x][y] + Math.max(0,matrix[newX][newY]));

                    // 如果当前扩散点是宝宝位置，则可以停止后续层级的bfs扩散，因为已经找到宝宝的最短路径长度（即扩散层数）
                    if (matrix[newX][newY] == -2) {
                        ans = candy[newX][newY];
                        flag = true;
                    }
                }
            }

            // 已经找到去宝宝位置的最短路径和最大糖果数，则终止bfs
            if (flag) break;

            // 否则继续
            queue = newQueue;
        }

        System.out.println(ans);
    }
}