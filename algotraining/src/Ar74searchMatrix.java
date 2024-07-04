/**
 * 搜索二维矩阵 给你一个满足下述两条属性的 m x n 整数矩阵：
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 * @date 2024/1/31 10:34
 */
public class Ar74searchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length,n=matrix[0].length;
        //在最后一列的数字里进行二分搜索 确定target所在的行
        int left=0,right=m-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(target==matrix[mid][n-1])
                return true;
            else if(target<matrix[mid][n-1])
                right=mid-1;
            else
                left=mid+1;
        }
        int row=left;
        if(row==m) //很容易忘记写
            return false;
        left=0;
        right=n-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(target==matrix[row][mid])
                return true;
            else if(target<matrix[row][mid])
                right=mid-1;
            else
                left=mid+1;
        }
        return false;
    }

    //一次二分查找 若将矩阵每一行拼接在上一行的末尾，则会得到一个升序数组，我们可以在该数组上二分找到目标元素
    //代码实现时，可以二分升序数组的下标，将其映射到原矩阵的行和列上。
    public boolean searchMatrix1(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int x = matrix[mid / n][mid % n];
            if (x < target) {
                low = mid + 1;
            } else if (x > target) {
                high = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
