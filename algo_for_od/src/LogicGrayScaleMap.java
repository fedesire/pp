/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 8:27
 */
/*
* 黑白图像常采用灰度图的方式存储，即图像的每个像素填充一个灰色阶段值，256阶灰图是一个灰阶值取值范围为 0~255 的灰阶矩阵，0表示全黑，255表示全白，范围内的其他值表示不同的灰度。
但在计算机中实际存储时，会使用压缩算法，其中一个种压缩格式描述如如下：
10 10 255 34 0 1 255 8 0 3 255 6 0 5 255 4 0 7 255 2 0 9 255 21
所有的数值以空格分隔；
前两个数分别表示矩阵的行数和列数；
从第三个数开始，每两个数一组，每组第一个数是灰阶值，第二个数表示该灰阶值从左到右，从上到下（可理解为二维数组按行存储在一维矩阵中）的连续像素个数。比如题目所述的例子， “255 34” 表示有连续 34 个像素的灰阶值是 255。
如此，图像软件在打开此格式灰度图的时候，就可以根据此算法从压缩数据恢复出原始灰度图矩阵。
请从输入的压缩数恢复灰度图原始矩阵，并返回指定像素的灰阶值。
输入描述
10 10 255 34 0 1 255 8 0 3 255 6 0 5 255 4 0 7 255 2 0 9 255 21
3 4
输入包行两行：
第一行是灰度图压缩数据
第二行表示一个像素位置的行号和列号，如 0 0 表示左上角像素
输出描述
0
输出数据表示的灰阶矩阵的指定像素的灰阶值。
备注
系保证输入的压缩数据是合法有效的，不会出现数据起界、数值不合法等无法恢复的场景
系统保证输入的像素坐标是合法的，不会出现不在矩阵中的像素
矩阵的行和列数范图为:(0,100]
灰阶值取值范图:[0,255]
用例
输入 10 10 56 34 99 1 87 8 99 3 255 6 99 5 255 4 99 7 255 2 99 9 255 21
3 4
输出 99
说明 将压缩数据恢复后的灰阶矩阵第3行第4列的像素灰阶值是99
输入 10 10 255 34 0 1 255 8 0 3 255 6 0 5 255 4 0 7 255 2 0 9 255 21
3 5
输出 255
说明 将压缩数据恢复后的灰阶矩阵第3行第5列的像案灰阶值是255*/
import java.util.Arrays;
import java.util.Scanner;

public class LogicGrayScaleMap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] pos = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int rows = nums[0];
        int cols = nums[1];

        int[] graph = new int[rows * cols];

        int start = 0;

        // 将二维坐标转为一维坐标
        int target = pos[0] * cols + pos[1];
    /*    for (int i = 2; i < nums.length; i += 2) {
            // 灰阶值
            int gray = nums[i];
            // 该灰阶值从左到右，从上到下（可理解为二维数组按行存储在一维矩阵中）的连续像素个数
            int len = nums[i + 1];
            Arrays.fill(graph, start, start + len, gray);
            start += len;
        }*/
//        System.out.println(graph[target]);

        // 法二 必须lastIndex从-1开始 结合下面的+len
        int lastIndex=-1;
        for (int i = 2; i < nums.length; i += 2) {
            // 灰阶值
            int gray = nums[i];
            // 该灰阶值从左到右，从上到下（可理解为二维数组按行存储在一维矩阵中）的连续像素个数
            int len = nums[i + 1];
            lastIndex+=len;
            if(lastIndex>=target){
                System.out.println(gray);
                return ;
            }
        }

    }
}
