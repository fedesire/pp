/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/12 10:33
 */
/*
* 寿司店周年庆，正在举办优惠活动回馈新老客户。

寿司转盘上总共有 n 盘寿司，prices[i] 是第 i 盘寿司的价格，

如果客户选择了第 i 盘寿司，寿司店免费赠送客户距离第 i 盘寿司最近的下一盘寿司 j，前提是 prices[j] < prices[i]，如果没有满足条件的 j，则不赠送寿司。

每个价格的寿司都可无限供应。


输入描述
输入的每一个数字代表每盘寿司的价格，每盘寿司的价格之间使用空格分隔，例如:

3 15 6 14

表示：

第 0 盘寿司价格 prices[0] 为 3
第 1 盘寿司价格 prices[1] 为 15
第 2 盘寿司价格 prices[2] 为 6
第 3 盘寿司价格 prices[3] 为 14
寿司的盘数 n 范围为：1 ≤ n ≤ 500

每盘寿司的价格 price 范围为：1 ≤ price ≤ 1000


输出描述
输出享受优惠后的一组数据，每个值表示客户选择第 i 盘寿司时实际得到的寿司的总价格。使用空格进行分隔，例如：

3 21 9 17

用例
输入	3 15 6 14
输出	3 21 9 17
说明	无
*/
import java.util.*;
import java.util.function.Function;

public class DSSushi {
    // 本题的单调栈是栈底到栈顶是递增的 单调栈保存的是数组元素下标 如果直接保存数组元素值 出栈的时候就不知道这个元素是哪个位置的 就无法向res[]
    // 保存结果 递增的单调栈 用来解决寻找循环数组中每个元素的下一个更小值元素
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int[] prices = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n=prices.length;
        int[] res = Arrays.copyOf(prices, n);

        LinkedList<Integer> linkedList=new LinkedList<>();
        for (int i = 0; i <2*n-1 ; i++) {
            // 新来的元素价格小于栈顶元素价格 说明栈顶元素找到它的最近的下一个小的值
            while(!linkedList.isEmpty()&&prices[i%n]<prices[linkedList.peek()]){
                res[linkedList.pop()]+=prices[i%n];
            }
            // 必须有这个 否则就会多加一次
            if(i<n)
            linkedList.push(i);
        }
//        Arrays.stream(res).forEach(i-> System.out.print(i+" "));
        StringJoiner sj = new StringJoiner(" ");
        for (int num : res) {
            sj.add(num + "");
        }
        System.out.println(sj);

    }
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] prices = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = prices.length;

        // 记录题解
        int[] res = Arrays.copyOf(prices, n);

        // 单调栈，栈底到栈顶单调递增，压栈元素是栈顶元素在nums顺序后面的值
        // 每当压栈时，比较栈顶元素 > 压栈元素？若是，则说明找到了栈顶元素的最近的下一个更小值，此时弹栈，压栈元素继续和新栈顶元素比较大小，直到栈顶元素 <= 压栈元素，则停止比较，执行压栈
        LinkedList<Integer> stack = new LinkedList<>(); // 栈中记录是prices元素的索引

        // 这里循环两轮，因为一轮循环可能无法确保所有值都能找到下一个更小值
        for (int j = 0; j < n * 2; j++) {
            // prices_j 是压栈(索引对应的)元素
            int prices_j = prices[j % n]; // 索引 j % n 是为了让第二轮遍历时，继续从prices的0索引开始

            while (stack.size() > 0) {
                // prices[i] 是栈顶(索引对应的)元素
                int i = stack.getLast();

                if (prices[i] > prices_j) {
                    // 如果栈顶元素 > 压栈元素，则说明找到了栈顶元素的下一个更小值，此时栈顶元素弹出,压栈元素继续和新的栈顶元素比较
                    stack.removeLast();
                    // 题目要统计当前元素和其下一个更小值元素之和
                    res[i] += prices_j;
                } else {
                    break;
                }
            }

            // 只有第一轮遍历时，才允许压栈，第二轮遍历时，只进行比较
            if (j < n) {
                stack.add(j);
            }
        }

        StringJoiner sj = new StringJoiner(" ");
        for (int num : res) {
            sj.add(num + "");
        }
        System.out.println(sj);
    }
}
