import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/21 11:53
 */
/*
* 攀登者喜欢寻找各种地图，并且尝试攀登到最高的山峰。

地图表示为一维数组，数组的索引代表水平位置，数组的元素代表相对海拔高度。其中数组元素0代表地面。

例如：[0,1,2,4,3,1,0,0,1,2,3,1,2,1,0]，代表如下图所示的地图，地图中有两个山脉位置分别为 1,2,3,4,5 和 8,9,10,11,12,13，最高峰高度分别为 4,3。最高峰位置分别为3,10。

一个山脉可能有多座山峰(高度大于相邻位置的高度，或在地图边界且高度大于相邻的高度)。



登山者想要知道一张地图中有多少座山峰。

输入描述
输入为一个整型数组，数组长度大于1。

输出描述
输出地图中山峰的数量。

用例
输入	0,1,4,3,1,0,0,1,2,3,1,2,1,0
输出	3
说明	山峰所在索引分别为3，10，12
*/
import java.util.Arrays;
import java.util.Scanner;

public class LogicClimb1 {
    // 输入处理
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] heights = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        System.out.println(getResult(heights));
    }

    // 算法实现（本题实际考试为核心代码模式，因此考试时只需要写出此函数实现即可）
    public static int getResult(int[] heights) {
        int count = 0;

        for (int i = 0; i < heights.length; i++) {
            // 山峰(高度大于相邻位置的高度，或在地图边界且高度大于相邻的高度)
            int leftHeight = i - 1 >= 0 ? heights[i - 1] : 0;
            int rightHeight = i + 1 < heights.length ? heights[i + 1] : 0;

            if (heights[i] > leftHeight && heights[i] > rightHeight) {
                count++;
            }
        }

        return count;
    }
}