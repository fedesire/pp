import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 14:10
 */
/*
* 现需要在某城市进行5G网络建设，已经选取N个地点设置5G基站，编号固定为1到N，接下来需要各个基站之间使用光纤进行连接以确保基站能互联互通，
* 不同基站之间假设光纤的成本各不相同，且有些节点之间已经存在光纤相连。

请你设计算法，计算出能联通这些基站的最小成本是多少。

注意：基站的联通具有传递性，比如基站A与基站B架设了光纤，基站B与基站C也架设了光纤，则基站A与基站C视为可以互相联通。

输入描述
第一行输入表示基站的个数N，其中：

0 < N ≤ 20
第二行输入表示具备光纤直连条件的基站对的数目M，其中：

0 < M < N * (N - 1) / 2
从第三行开始连续输入M行数据，格式为

X Y Z P

其中：

X，Y 表示基站的编号

0 < X ≤ N
0 < Y ≤ N
X ≠ Y
Z 表示在 X、Y之间架设光纤的成本

0 < Z < 100
P 表示是否已存在光纤连接，0 表示未连接，1表示已连接

输出描述
如果给定条件，可以建设成功互联互通的5G网络，则输出最小的建设成本

如果给定条件，无法建设成功互联互通的5G网络，则输出 -1

用例
输入	3
3
1 2 3 0
1 3 1 0
2 3 5 0
输出	4
说明	只需要在1，2以及1，3基站之间铺设光纤，其成本为3+1=4
输入	3
1
1 2 5 0
输出	-1
说明	3基站无法与其他基站连接，输出-1
输入	3
3
1 2 3 0
1 3 1 0
2 3 5 1
输出	1
说明	2，3基站已有光纤相连，只要在1，3基站之间铺设光纤，其成本为1
*/
import java.util.Arrays;
import java.util.Scanner;

public class Tree5G {
    // 边
    static class Edge {
        int from; // 边起点
        int to; // 边终点
        int weight; // 边权重

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 基站数量（节点数）
        int m = sc.nextInt(); // 基站对数量（边数）

        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            int p = sc.nextInt();

            // 如果p==1，则可以认为x-y边权为0
            edges[i] = new Edge(x, y, p == 0 ? z : 0);
        }

        System.out.println(kruskal(edges, n));
    }

    public static int kruskal(Edge[] edges, int n) {
        int minWeight = 0;

        // 按照边权升序
        Arrays.sort(edges, (a, b) -> a.weight - b.weight);

        UnionFindSet ufs = new UnionFindSet(n + 1);

        // 最先遍历出来是边权最小的边
        for (Edge edge : edges) {
            // 如果edge.from节点 和 edge.to节点 是同一个连通分量（即都在最小生成树中），则此时会产生环
            // 因此只有当edge.from节点 和 edge.to节点不在同一个连通分量时，才能合并（纳入最小生成树）
            if (ufs.find(edge.from) != ufs.find(edge.to)) {
                minWeight += edge.weight;
                ufs.union(edge.from, edge.to);

                // 需要注意的是，上面初始化并查集的节点数为n+1个，因此并查集底层fa数组的长度就是n+1，即索引范围是[0, n]，左闭右闭，
                // 其中0索引是无用的，1~n索引对应最小生成树中各个节点，如果者n个节点可以变为最小生成树，那么1~n节点会被合并为一个连通分量
                // 而0索引虽然无用，但是也会自己形成一个连通分量，因此最终如果能形成最小生成树，则并查集中会有两个连通分量
                if (ufs.count == 2) {
                    return minWeight;
                }
            }
        }

        return -1;
    }
}

// 并查集
class UnionFindSet {
    int[] fa;
    int count;

    public UnionFindSet(int n) {
        this.fa = new int[n];
        this.count = n;
        for (int i = 0; i < n; i++) this.fa[i] = i;
    }

    public int find(int x) {
        if (x != this.fa[x]) {
            return (this.fa[x] = this.find(this.fa[x]));
        }
        return x;
    }

    public void union(int x, int y) {
        int x_fa = this.find(x);
        int y_fa = this.find(y);

        if (x_fa != y_fa) {
            this.fa[y_fa] = x_fa;
            this.count--;
        }
    }
}