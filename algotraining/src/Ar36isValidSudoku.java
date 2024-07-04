/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/2/11 9:50
 */
public class Ar36isValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            boolean[] flag = new boolean[10];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int x = board[i][j] - '0';
                    if (flag[x] == true) return false;
                    else flag[x] = true;
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            boolean[] flag = new boolean[10];
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != '.') {
                    int x = board[j][i] - '0';
                    if (flag[x] == true) return false;
                    else flag[x] = true;
                }
            }
        }
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (test(board, i, j) == false)
                    return false;
            }
        }
        return true;

    }

    public boolean test(char[][] board, int x, int y) {
        boolean[] flag = new boolean[10];
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (board[i][j] != '.') {
                    int v = board[i][j] - '0';
                    if (flag[v] == true) return false;
                    else flag[v] = true;
                }
            }
        }
        return true;
    }

    /*https://leetcode.cn/problems/valid-sudoku/solutions/1001859/you-xiao-de-shu-du-by-leetcode-solution-50m6/?envType=study-plan-v2&envId=top-interview-150*/
    public boolean isValidSudoku1(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    rows[i][index]++;//第i行的数字index+1出现次数加1
                    cols[j][index]++;//第j列的数字index+1出现次数加1
                    subboxes[i / 3][j / 3][index]++;//board[i][j]对应的小九宫格是subboxes[i/3][j/3]
                    if (rows[i][index] > 1 || cols[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
