import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/21 21:15
 */
/*
* 围棋棋盘由纵横各19条线垂直相交组成，棋盘上一共19 x 19 = 361 个交点，对弈双方一方执白棋，一方执黑棋，落子时只能将棋子置于交点上。
“气”是围棋中很重要的一个概念，某个棋子有几口气，是指其上下左右方向四个相邻的交叉点中，有几个交叉点没有棋子，由此可知：
在棋盘的边缘上的棋子最多有 3 口气（黑1），在棋盘角点的棋子最多有2口气（黑2），其他情况最多有4口气（白1）
所有同色棋子的气之和叫做该色棋子的气，需要注意的是，同色棋子重合的气点，对于该颜色棋子来说，只能计算一次气，比如下图中，黑棋一共4口气，而不是5口气，因为黑1和黑2中间红色三角标出来的气是两个黑棋共有的，对于黑棋整体来说只能算一个气。
本题目只计算气，对于眼也按气计算，如果您不清楚“眼”的概念，可忽略，按照前面描述的规则计算即可。
* 本题棋盘中“气”的位置有如下特点：

该位置没有棋子
该位置上下左右存在至少一个棋子（若为黑棋，则当前位置就是黑棋的气，若为白棋，则当前位置就是白棋的气，若既有黑棋，也有白棋，则同时为两个颜色棋的气）
因此，我们只需要遍历19*19的棋盘中每一个位置，检查对应位置是否有棋子，若没有棋子，则检查其上下左右四个位置是否存在黑棋、白棋，若存在则对应颜色的棋子的气数+1，需要注意的是，一个位置只能产生一个气，因此如果该位置的上下左右位置有多个黑棋，也只能为黑棋加一个气，白棋同理。
*/
import java.util.Arrays;
import java.util.Scanner;

public class LogicWeiQi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] black = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] white = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 定义棋盘，没有棋子用0表示
        int[][] board = new int[19][19];

        for (int i = 0; i < black.length; i += 2) {
            int x = black[i];
            int y = black[i + 1];
            board[x][y] = 1; // 棋盘中黑棋用1表示
        }

        for (int i = 0; i < white.length; i += 2) {
            int x = white[i];
            int y = white[i + 1];
            board[x][y] = 2; // 棋盘中白棋用2表示
        }

        // 黑棋的气数
        int black_liberty_count = 0;
        // 白棋的气数
        int white_liberty_count = 0;

        // 上下左右四个方向的偏移量
        int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                // 如果当前位置没有棋子，则可能是黑棋或白棋的气
                if (board[i][j] == 0) {
                    // 当前位置是否为黑棋的气
                    boolean isBlackLiberty = false;
                    // 当前位置是否白棋的气
                    boolean isWhiteLiberty = false;

                    // 若为黑棋或者白棋的气，则当前位置的上下左右的位置上必有黑棋或白棋
                    for (int[] offset : offsets) {
                        int newI = i + offset[0];
                        int newJ = j + offset[1];

                        // 若当前位置的上下左右的位置越界，则不考虑
                        if (newI < 0 || newI >= 19 || newJ < 0 || newJ >= 19) continue;

                        // 若当前位置的上下左右的位置存在黑棋，则当前位置为黑棋的气
                        isBlackLiberty = isBlackLiberty || (board[newI][newJ] == 1);
                        // 若当前位置的上下左右的位置存在白棋，则当前位置为白棋的气
                        isWhiteLiberty = isWhiteLiberty || (board[newI][newJ] == 2);
                    }

                    if (isBlackLiberty) black_liberty_count++;
                    if (isWhiteLiberty) white_liberty_count++;
                }
            }
        }

        System.out.println(black_liberty_count + " " + white_liberty_count);
    }
}