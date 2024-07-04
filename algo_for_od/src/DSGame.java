import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 21:01
 */
/*
* 幼儿园里有一个放倒的圆桶，它是一个线性结构，允许在桶的右边将篮球放入，可以在桶的左边和右边将篮球取出。

每个篮球有单独的编号，老师可以连续放入一个或多个篮球，小朋友可以在桶左边或右边将篮球取出，当桶只有一个篮球的情况下，必须从左边取出。

如老师按顺序放入1、2、3、4、5 共有 5 个编号的篮球，那么小朋友可以依次取出编号为1、2、3、4、5 或者 3、1、2、4、5 编号的篮球，无法取出 5、1、3、2、4 编号的篮球。

其中 3、1、2、4、5 的取出场景为：

连续放入1、2、3号
从右边取出3号
从左边取出1号
从左边取出2号
放入4号
从左边取出4号
放入5号
从左边取出5号
简答起见，我们以 L 表示左，R表示右，此时取出篮球的依次取出序列为“RLLLL”。

输入描述
每次输入包含一个测试用例：

第一行的数字作为老师依次放入的篮球编号
第二行的数字作为要检查是否能够按照放入的顺序取出给定的篮球的编号，其中篮球的编号用逗号进行分隔。
其中篮球编号用逗号进行分隔。

输出描述
对于每个篮球的取出序列，如果确实可以获取，请打印出其按照左右方向的操作取出顺序，如果无法获取则打印“NO”。

备注
1 ≤ 篮球编号，篮球个数 ≤ 200
篮球上的数字不重复
输出的结果中 LR 必须为大写
用例
输入	4,5,6,7,0,1,2
6,4,0,1,2,5,7
输出	RLRRRLL
说明	篮球的取出顺序依次为“右、左、右、右、右、左、左”
输入	4,5,6,7,0,1,2
6,0,5,1,2,4,7
输出	NO
说明	无法取出对应序列的篮球
输入	1,2,3,4
1,2,3,5
输出	NO
说明	不存在编号为5的篮球，所以无法取出对应编号的数据
*/
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class DSGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] inputs = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] outputs = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        // 利用队列结构模拟圆桶
        LinkedList<Integer> queue = new LinkedList<>();

        // outputs[index]是要被取出的篮球的编号
        int index = 0;

        // 记录题解
        StringBuilder sb = new StringBuilder();

        for (int input : inputs) {
            // 按照放入顺序，从圆桶右边放入
            queue.addLast(input);

            // 然后开始尝试取出
            while (!queue.isEmpty() && index < outputs.length) {
                // 圆桶左边的篮球的编号
                int left = queue.getFirst();
                // 圆桶右边的篮球的编号
                int right = queue.getLast();

                if (left == outputs[index]) {
                    // 优先比较圆桶左边的篮球是不是当前要取出的篮球，优先左边的原因是：当桶只有一个篮球的情况下，必须从左边取出
                    sb.append("L");
                    queue.removeFirst();
                    index++;
                } else if (right == outputs[index]) {
                    // 比较圆桶右边的篮球是不是当前要取出的篮球
                    sb.append("R");
                    queue.removeLast();
                    index++;
                } else {
                    // 如果圆桶左右两边都不是要取出的球，则本轮取出流程结束
                    break;
                }
            }
        }

        // 最终如果圆桶空了，则说明所有球都取出了，否则按照给定要求无法取出所有球
        // 注意本题可能放入的球数量 > 取出球的数量，因此此处不能判断queue为空
        if (index != outputs.length) {
            System.out.println("NO");
        } else {
            System.out.println(sb);
        }
    }
}