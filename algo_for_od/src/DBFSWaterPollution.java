import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/15 14:09
 */
/*
* 输入一行字符串，字符串可转换为N*N的数组，数组可认为是一个水域，判断多少天后，水域被全部污染。
数组中只有0和1，0表示纯净，1表示污染，每天只可污染上下左右的水域，如果开始全部被污染，或永远无法污染，则返回-1。
输入描述
无
输出描述
无
用例
输入 1,0,1,0,0,0,1,0,1
输出 2
说明
输入转化为数组为：
1 0 1
0 0 0
1 0 1
第一天后水域变为
1 1 1
1 0 1
1 1 1
第二天全部被污染
输入 0,0,0,0
输出 -1
* */
public class DBFSWaterPollution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int m = (int) Math.sqrt(array.length);
        int[][] matrix = new int[m][m];

        // count表示目前已被污染的水域数量
        int count = 0;
        ArrayList<int[]> srcPos = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = array[i * m + j];
                if (matrix[i][j] == 1) {
                    srcPos.add(new int[]{i, j});
                    count++;
                }
            }
        }
        if (count == 0||srcPos.size()==array.length) {
            System.out.println("-1");
            return;
        }

        int[][] moveDirection = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int day = 0;
        // 没有srcPos.size()>0也能ac 因为感觉只要有一个1 就一定能污染所有水域 所以肯定是count到达length值 不会前面就出现srcPos为空了
        while (srcPos.size()>0&&count <array.length) {
            ArrayList<int[]> newSrcPos = new ArrayList<>();
            for (int[] pos : srcPos) {
                int x = pos[0];
                int y = pos[1];
                for (int[] offset : moveDirection) {
                    int newx = x + offset[0];
                    int newy = y + offset[1];
                    if (newx >= 0 && newx < m && newy >= 0 && newy < m && matrix[newx][newy] == 0) {
                        matrix[newx][newy] = 1;
                        newSrcPos.add(new int[]{newx, newy});
                        count++;
                    }
                }
            }
            srcPos=newSrcPos;

            day++;
        }
        System.out.println(day);
    }

}
