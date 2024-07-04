import java.beans.PropertyEditorSupport;
import java.util.HashSet;
import java.util.Scanner;

/**
 *小华按照地图去寻宝，地图上被划分成 m 行和 n 列的方格，横纵坐标范围分别是 [0, n-1] 和 [0, m-1]。
 *
 * 在横坐标和纵坐标的数位之和不大于 k 的方格中存在黄金（每个方格中仅存在一克黄金），但横坐标和纵坐标之和大于 k 的方格存在危险不可进入。
 * 小华从入口 (0,0) 进入，任何时候只能向左，右，上，下四个方向移动一格。
 *
 * 请问小华最多能获得多少克黄金？
 *
 * 输入描述
 * 坐标取值范围如下：
 *
 * 0 ≤ m ≤ 50
 * 0 ≤ n ≤ 50
 * k 的取值范围如下：
 *
 * 0 ≤ k ≤ 100
 * 输入中包含3个字数，分别是m, n, k
 *
 * 输出描述
 * 输出小华最多能获得多少克黄金
 * @date 2024/4/12 15:08
 */
public class DBFSMaxGold {
    static HashSet<Integer> visited=new HashSet<>();
    static int result=0;
    static int[] digitSum;
    static int m;
    static int n;
    static int k;

    // 上下左右偏移量
    static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
         m=scanner.nextInt();
         n=scanner.nextInt();
         k=scanner.nextInt();

        getDigitSum(Math.max(m,n));
        dfs(0,0);
        System.out.println(result);

    }

    private static void dfs(int x, int y) {
        if(x<0||x>=n||y<0||y>=m||visited.contains(x*m+y)||digitSum[x]+digitSum[y]>k)
            return ;
        visited.add(x*m+y);
        result++;
//        // 继续遍历上、下、左、右方向上的新位置继续深搜
//        for (int[] offset : offsets) {
//            int newX = x + offset[0];
//            int newY = y + offset[1];
//            dfs(newX, newY);
//        }
        dfs(x-1,y);
        dfs(x+1,y);
        dfs(x,y-1);
        dfs(x,y+1);
    }

    // 该方法用于求解 0 ~ maxSize - 1 各个数对应的数位和，提前计算好，避免后期重复计算某个数的数位和
    private static void getDigitSum(int max) {
        digitSum=new int[max];
        for (int i = 0; i < max; i++) {
            int number=i;
            while(number>0){
                digitSum[i]+=number%10;
                number=number/10;
            }
        }
    }
//
//    // 深度优先搜索遍历矩阵
//    public static void dfs(int x, int y) {
//        // 如果对应位置越界，或者已访问过，或者横坐标、纵坐标的数位和之和超过了k，则不能进入
//        if (x < 0
//                || x >= m
//                || y < 0
//                || y >= n
//                || visited.contains(x * n + y)
//                || digitSum[x] + digitSum[y] > k) return;
//
//        // 否则可以进入，且获得黄金
//        visited.add(x * n + y);
//        result++;
//
//        // 继续遍历上、下、左、右方向上的新位置继续深搜
//        for (int[] offset : offsets) {
//            int newX = x + offset[0];
//            int newY = y + offset[1];
//
//            dfs(newX, newY);
//        }
//    }




}
