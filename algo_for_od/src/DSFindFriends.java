/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/15 17:12
 */
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;
/*
* 在学校中，N个小朋友站成一队， 第i个小朋友的身高为height[i]，

第i个小朋友可以看到的第一个比自己身高更高的小朋友j，那么j是i的好朋友(要求j > i)。

请重新生成一个列表，对应位置的输出是每个小朋友的好朋友位置，如果没有看到好朋友，请在该位置用0代替。

小朋友人数范围是 [0, 40000]。

输入描述
第一行输入N，N表示有N个小朋友

第二行输入N个小朋友的身高height[i]，都是整数

输出描述
输出N个小朋友的好朋友的位置

用例
输入	2
100 95
输出	0 0
说明
第一个小朋友身高100，站在队尾位置，向队首看，没有比他身高高的小朋友，所以输出第一个值为0。

第二个小朋友站在队首，前面也没有比他身高高的小朋友，所以输出第二个值为0。

输入
8
123 124 125 121 119 122 126 123
输出	1 2 6 5 5 6 0 0
说明	123的好朋友是1位置上的124
124的好朋友是2位置上的125
125的好朋友是6位置上的126
以此类推
*/
public class DSFindFriends {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        System.out.println(getResult(arr));
    }

    public static String getResult(int[] arr) {
        LinkedList<int[]> stack = new LinkedList<>();

        int len = arr.length;
        int[] res = new int[len];

        for (int i = 0; i < len; i++) {
            int ele = arr[i];

            // 这里的add 是addLast 所以这里addLast removeLast可以看做是在栈顶入栈 出栈 反正只要add remove后面是一样的 就是栈的操作 不一定
            // 非得是addFirst removeFirst
            // 当前遍历到的数组元素ele如果比栈顶元素小可以直接入栈进入下一轮 如果比栈顶元素大 说明栈里有部分元素就找到结果了 可以出栈了
            // 所以在while循环里的if就是在寻找

        /*    while (true) {
                if (stack.size() == 0) {
                    stack.add(new int[] {ele, i});
                    break;
                }
                int[] peek = stack.getLast();
                int peekEle = peek[0];
                int peekIndex = peek[1];

                if (ele > peekEle) {
                    res[peekIndex] = i;
                    stack.removeLast();
                } else {
                    stack.add(new int[] {ele, i});
                    break;
                }
            }*/

            // 这就是一个单调递减栈  寻找下一个最近的更大值的问题 再次说明单调栈里必须要存元素的索引 元素值其实可以没有
            while(!stack.isEmpty()&&stack.getLast()[0]<ele){
                res[stack.removeLast()[1]] = i;
            }
            stack.add(new int[] {ele, i});

        }

        StringJoiner sj = new StringJoiner(" ");
        for (int v : res) {
            sj.add(v + "");
        }

        return sj.toString();
    }


    // 栈里只保存索引下标也可以
    public static String getResult1(int[] arr) {
        LinkedList<Integer> stack = new LinkedList<>();

        int len = arr.length;
        int[] res = new int[len];

        for (int i = 0; i < len; i++) {
            int ele = arr[i];

            while(!stack.isEmpty()&&arr[stack.getLast()]<ele){
                res[stack.removeLast()] = i;
            }
            stack.add(i);

        }

        StringJoiner sj = new StringJoiner(" ");
        for (int v : res) {
            sj.add(v + "");
        }

        return sj.toString();
    }
}