import java.util.Arrays;
import java.util.Scanner;

/**
 * 给出数字个数 n （0 < n ≤ 999）和行数 m（0 < m ≤ 999），从左上角的 1 开始，按照顺时针螺旋向内写方式，依次写出2,3,....,n，最终形成一个 m 行矩阵。
 *
 * 小明对这个矩阵有些要求：
 *
 * 每行数字的个数一样多
 * 列的数量尽可能少
 * 填充数字时优先填充外部
 * 数字不够时，使用单个 * 号占位
 * 输入描述
 * 两个整数，空格隔开，依次表示 n、m
 *
 * 输出描述
 * 符合要求的唯一矩阵
 * @date 2024/4/11 10:36
 */
public class LogicMatrix {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt(),m=scanner.nextInt();
        int c=n%m==0?n/m:n/m+1;
        // 螺旋矩阵列数
//        int c = (int) Math.ceil(n * 1.0 / m);
        int[][] matrix=new int[m][c];

        int[][] moveDirection={{0,1},{1,0},{0,-1},{-1,0}};
        int x=0,y=-1,number=1;
        while(number<=n){
            for (int i =0; i < c&&number<=n; i++) {
//                x+=moveDirection[0][0];
//                y+=moveDirection[0][1];
//                matrix[x][y]=number++;
                 matrix[x][++y]=number++;
            }
            for (int i = 0; i < m-1&&number<=n; i++) {
//                x+=moveDirection[1][0];
//                y+=moveDirection[1][1];
//                matrix[x][y]=number++;
                matrix[++x][y]=number++;

            }
            for (int i = 0; i < c-1&&number<=n; i++) {
//                x+=moveDirection[2][0];
//                y+=moveDirection[2][1];
//                matrix[x][y]=number++;
                matrix[x][--y]=number++;

            }
            for (int i = 0; i < m-2&&number<=n; i++) {
//                x+=moveDirection[3][0];
//                y+=moveDirection[3][1];
//                matrix[x][y]=number++;
                matrix[--x][y]=number++;

            }
            c-=2;
            m-=2;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print((matrix[i][j]!=0?matrix[i][j]:"*")+" ");
            }
            System.out.println();
        }
    }
}
