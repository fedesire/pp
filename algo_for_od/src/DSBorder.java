import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/20 9:09
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
/*
* 给定一个二维数组M行N列，二维数组里的数字代表图片的像素，为了简化问题，仅包含像素1和5两种像素，每种像素代表一个物体，2个物体相邻的格子为边界，求像素1代表的物体的边界个数。

像素1代表的物体的边界指与像素5相邻的像素1的格子，边界相邻的属于同一个边界，相邻需要考虑8个方向（上，下，左，右，左上，左下，右上，右下）。

其他约束

地图规格约束为：

0<M<100
0<N<100

1）如下图，与像素5的格子相邻的像素1的格子（0,0）、（0,1）、（0,2）、（1,0）、（1,2）、（2,0）、（2,1）、（2,2）、（4,4）、（4,5）、（5,4）为边界，另（0,0）、（0,1）、（0,2）、（1,0）、（1,2）、（2,0）、（2,1）、（2,2）相邻，为1个边界，（4,4）、（4,5）、（5,4）相邻，为1个边界，所以下图边界个数为2。



2）如下图，与像素5的格子相邻的像素1的格子（0,0）、（0,1）、（0,2）、（1,0）、（1,2）、（2,0）、（2,1）、（2,2）、（3,3）、（3,4）、（3,5）、（4,3）、（4,5）、（5,3）、（5,4）、（5,5）为边界，另这些边界相邻，所以下图边界个数为1。



 注：（2,2）、（3,3）相邻。

输入描述
第一行，行数M，列数N

第二行开始，是M行N列的像素的二维数组，仅包含像素1和5

输出描述
像素1代表的物体的边界个数。

如果没有边界输出0（比如只存在像素1，或者只存在像素5）。

用例
输入	6 6
1 1 1 1 1 1
1 5 1 1 1 1
1 1 1 1 1 1
1 1 1 1 1 1
1 1 1 1 1 1
1 1 1 1 1 5
输出	2
说明	参考题目描述部分
输入	6 6
1 1 1 1 1 1
1 5 1 1 1 1
1 1 1 1 1 1
1 1 1 1 1 1
1 1 1 1 5 1
1 1 1 1 1 1
输出	1
*/
public class DSBorder {

   static class UnionFindSet {
        int[] fa;
        int count;

        public UnionFindSet(int n) {
            this.count = n;
            this.fa = new int[n];
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println(getResult(matrix, m, n));
    }

    public static int getResult(int[][] matrix, int m, int n) {
        // 上、下、左、右、左上、左下、右上、右下的横坐标、纵坐标偏移量
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        // 记录所有边界位置
        HashSet<Integer> brands = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果当前点是像素5
                if (matrix[i][j] == 5) {

                    // 遍历像素5的相邻位置
                    for (int[] offset : offsets) {
                        int newI = i + offset[0];
                        int newJ = j + offset[1];

                        // 如果该位置不越界，且为像素1，则是边界
                        if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && matrix[newI][newJ] == 1) {
                            brands.add(newI * n + newJ);
                        }
                    }
                }
            }
        }

        ArrayList<Integer> brands_list = new ArrayList<>(brands);
        int k = brands_list.size();

        // 使用并查集，对所有边界位置进行合并
        UnionFindSet ufs = new UnionFindSet(k);

        for (int i = 0; i < k; i++) {
            int x1 = brands_list.get(i) / n;
            int y1 = brands_list.get(i) % n;

            for (int j = i + 1; j < k; j++) {
                int x2 = brands_list.get(j) / n;
                int y2 = brands_list.get(j) % n;

                // 如果两个边界像素1的位置 横向、纵向距离均小于1，则相邻，可以进行合并
                if (Math.abs(x1 - x2) <= 1 && Math.abs(y1 - y2) <= 1) {
                    ufs.union(i, j);
                }
            }
        }

        return ufs.count;
    }
}
