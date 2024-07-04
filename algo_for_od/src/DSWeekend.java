import java.util.ArrayList;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/20 8:52
 */
/*
* 小华和小为是很要好的朋友，他们约定周末一起吃饭。

通过手机交流，他们在地图上选择了多个聚餐地点（由于自然地形等原因，部分聚餐地点不可达），求小华和小为都能到达的聚餐地点有多少个？

输入描述
第一行输入 m 和 n

m 代表地图的长度
n 代表地图的宽度
第二行开始具体输入地图信息，地图信息包含：

0 为通畅的道路
1 为障碍物（且仅1为障碍物）
2 为小华或者小为，地图中必定有且仅有2个 （非障碍物）
3 为被选中的聚餐地点（非障碍物）
输出描述
可以被两方都到达的聚餐地点数量，行末无空格。

备注
地图的长宽为 m 和 n，其中：

4 ≤ m ≤ 100
4 ≤ n ≤ 100
聚餐的地点数量为 k，则

1< k ≤ 100
用例
输入	4 4
2 1 0 3
0 1 2 1
0 3 0 0
0 0 0 0
输出	2
说明
第一行输入地图的长宽为4和4。

第二行开始为具体的地图，其中：3代表小华和小明选择的聚餐地点；2代表小华或者小明（确保有2个）；0代表可以通行的位置；1代表不可以通行的位置。

此时两者能都能到达的聚餐位置有2处。

输入	4 4
2 1 2 3
0 1 0 0
0 1 0 0
0 1 0 0
输出	0
说明
第一行输入地图的长宽为4和4。

第二行开始为具体的地图，其中：3代表小华和小明选择的聚餐地点；2代表小华或者小明（确保有2个）；0代表可以通行的位置；1代表不可以通行的位置。

由于图中小华和小为之间有个阻隔，此时，没有两人都能到达的聚餐地址，故而返回0。
*/
import java.util.ArrayList;
import java.util.Scanner;

public class DSWeekend {

    // 并查集实现
    static class UnionFindSet {
        int[] fa;

        public UnionFindSet(int n) {
            fa = new int[n];
            for (int i = 0; i < n; i++) fa[i] = i;
        }

        public int find(int x) {
            if (x != this.fa[x]) {
                this.fa[x] = this.find(this.fa[x]);
                return this.fa[x];
            }
            return x;
        }

        public void union(int x, int y) {
            int x_fa = this.find(x);
            int y_fa = this.find(y);

            if (x_fa != y_fa) {
                this.fa[y_fa] = x_fa;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 长度m表示行数
        int m = sc.nextInt();
        // 宽度n表示列数
        int n = sc.nextInt();

        int[][] matrix = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println(getResult(matrix));
    }

    public static int getResult(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        UnionFindSet ufs = new UnionFindSet(rows * cols);

        // 记录小华，小为的位置
        ArrayList<Integer> huawei = new ArrayList<>();
        // 记录餐厅的位置
        ArrayList<Integer> restaurants = new ArrayList<>();

        // 上下左右四个方向偏移量
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != 1) {
                    // 二维坐标(i, j) 转为 一维坐标pos
                    int pos = i * cols + j;

                    if (matrix[i][j] == 2) {
                        // 收集小华，小为的位置
                        huawei.add(pos);
                    } else if (matrix[i][j] == 3) {
                        // 收集餐厅的位置
                        restaurants.add(pos);
                    }

                    for (int[] offset : offsets) {
                        int newI = i + offset[0];
                        int newJ = j + offset[1];
                        if (newI >= 0 && newI < rows && newJ >= 0 && newJ < cols && matrix[newI][newJ] != 1) {
                            // 如果(i,j)和（newI,newJ）位置都是非1，则合并
                            ufs.union(pos, newI * cols + newJ);
                        }
                    }
                }
            }
        }

        // 小华所在连通分量的根
        int hua_fa = ufs.find(huawei.get(0));
        // 小为所在连通分量的根
        int wei_fa = ufs.find(huawei.get(1));

        // 如果小华和小为的不属于同一个连通分量，则二人无法去往相同餐厅
        if (hua_fa != wei_fa) {
            return 0;
        }

        // 找出和小华、小为在同一个连通分量里面的餐厅
        int ans = 0;
        for (Integer restaurant : restaurants) {
            if (ufs.find(restaurant) == hua_fa) {
                ans++;
            }
        }

        return ans;
    }
}
