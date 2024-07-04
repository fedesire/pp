import java.util.ArrayList;
import java.util.List;

/**
 * 你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * @date 2024/2/11 11:08
 */
public class Ar54SpiralOrder {
  /*  四个指针转圈圈
    从左到右，顶部一层遍历完往下移一位，top++；
    从上到下，遍历完右侧往左移一位，right--；
    从右到左，判断top <= bottom，即是否上下都走完了。遍历完底部上移，bottom--；
    从下到上，判断left <= right，遍历完左侧右移，left++；*/
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = n - 1, top = 0, bottom = m - 1;
        while (left <= right && top <= bottom) {
            // 从左到右
            for (int i = left; i <= right; i++) {
                ans.add(matrix[top][i]);
            }
            top++;
            // 从上到下
            for (int i = top; i <= bottom; i++) {
                ans.add(matrix[i][right]);
            }
            right--;
            // 从右到左
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    ans.add(matrix[bottom][i]);
                }
            }
            bottom--;
            // 从下到上
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    ans.add(matrix[i][left]);
                }
            }
            left++;
        }
        return ans;
    }
    List<Integer> res=new ArrayList<>();
    public List<Integer> spiralOrder(int[][] matrix) {
        int m=matrix.length,n=matrix[0].length;
        for(int i=0;i<=Math.min(m,n)/2;i++){
            helper(i,matrix,m,n);
        }
        return res;
    }
    public void helper(int leftStart,int[][] matrix,int m,int n){
        int rightEndX=m-1-leftStart,rightEndY=n-1-leftStart;
        if(leftStart>rightEndX||leftStart>rightEndY) return ;
        for(int j=leftStart;j<=rightEndY;j++)
            res.add(matrix[leftStart][j]);
        for(int i=leftStart+1;i<rightEndX;i++)
            res.add(matrix[i][rightEndY]);
        for(int j=rightEndY;rightEndX!=leftStart&&j>=leftStart;j--)
            res.add(matrix[rightEndX][j]);
        for(int i=rightEndX-1;rightEndY!=leftStart&&i>leftStart;i--)
            res.add(matrix[i][leftStart]);
    }

    /*可以模拟螺旋矩阵的路径。初始位置是矩阵的左上角，初始方向是向右，当路径超出界限或者进入之前访问过的位置时，顺时针
    旋转，进入下一个方向。判断路径是否进入之前访问过的位置需要使用一个与输入矩阵大小相同的辅助矩阵 visited，其中的每个
    元素表示该位置是否被访问过。当一个元素被访问时，将 visited\textit{visited}visited 中的对应位置的元素设为已访问。
如何判断路径是否结束？由于矩阵中的每个元素都被访问一次，因此路径的长度即为矩阵中的元素数量，当路径的长度达到矩阵中的
元素数量时即为完整路径，将该路径返回。*/
    public List<Integer> spiralOrder1(int[][] matrix){
        List<Integer> res=new ArrayList<>();
        int m=matrix.length,n=matrix[0].length;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int[][] directions={{0,1},{1,0},{0,-1},{-1,0}};
        boolean[][] visited=new boolean[m][n];
        int row=0,col=0;
        int count=0,total=m*n;
        int directionIndex=0;
        while (count<total){
            res.add(matrix[row][col]);
            visited[row][col]=true;
            count++;
            int nextRow=row+directions[directionIndex][0];
            int nextCol=col+directions[directionIndex][1];
            if(nextRow<0||nextRow>=m||nextCol<0||nextCol>=n||visited[nextRow][nextCol])
                directionIndex=(directionIndex+1)%4;
            row+=directions[directionIndex][0];
            col+=directions[directionIndex][1];
        }
        return res;
    }
}
