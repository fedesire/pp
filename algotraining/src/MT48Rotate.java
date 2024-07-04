/**
 *给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像
 * @date 2024/5/12 8:33
 */
public class MT48Rotate {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    // 对于矩阵中第 iii 行的第 jjj 个元素，在旋转后，它出现在倒数第 iii 列的第 jjj 个位置。
    //
    //我们将其翻译成代码。由于矩阵中的行列从 000 开始计数，因此对于矩阵中的元素 matrix[row][col]\textit{matrix}[\textit{row}][\textit{col}]matrix[row][col]，在旋转后，它的新位置为 matrixnew[col][n−row−1]\textit{matrix}_\textit{new}[\textit{col}][n - \textit{row} - 1]matrix
    public void rotate1(int[][] matrix) {
        int n = matrix.length;
// 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
// 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
