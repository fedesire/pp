import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 输入一个由随机数组成的数列（数列中每个数均是大于 0 的整数，长度已知），和初始计数值 m。
 * 从数列首位置开始计数，计数到 m 后，将数列该位置数值替换计数值 m，
 * 并将数列该位置数值出列，然后从下一位置从新开始计数，直到数列所有数值出列为止。
 * 如果计数到达数列尾段，则返回数列首位置继续计数。
 * 请编程实现上述计数过程，同时输出数值出列的顺序。
 * 比如：输入的随机数列为：3,1,2,4，初始计数值 m=7，从数列首位置开始计数（数值 3 所在位置）
 * 第一轮计数出列数字为 2，计数值更新 m=2，出列后数列为 3,1,4，从数值 4 所在位置从新开始计数
 * 第二轮计数出列数字为 3，计数值更新 m=3，出列后数列为 1,4，从数值 1 所在位置开始计数
 * 第三轮计数出列数字为 1，计数值更新 m=1，出列后数列为 4，从数值 4 所在位置开始计数
 * 最后一轮计数出列数字为 4，计数过程完成。输出数值出列顺序为：2,3,1,4。
 * 要求实现函数：
 * void array_iterate(int len, int input_array[], int m, int output_array[])
 * 输入描述
 * int len：输入数列的长度； int intput_array[]：输入的初始数列 int m：初始计数值
 * 输出描述
 * int output_array[]：输出的数值出列顺序
 *
 * 约瑟夫环的解题有多种方式，比较容易理解和实现的可以使用双端队列。
 * 即intput_array当成双端队列，从队头取出元素，判断此时计数是否为m：
 * 若是，则将取出的元素加入output_arr，并将取出的元素的值赋值给m，然后len--，计数重置为1
 * 若不是，则将取出的元素塞回intput_array的队尾，仅计数++
 * @date 2024/4/14 10:11
 */
public class LinkedListNumber {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Integer[] nums = Arrays.stream(scanner.nextLine().split(",")).map(Integer::parseInt).toArray(Integer[]:: new);
        int len=scanner.nextInt();
        int m=scanner.nextInt();

        LinkedList<Integer> linkedList=new LinkedList<>(Arrays.asList(nums));
        int i=1;
        StringJoiner joiner=new StringJoiner(",");
        // 法1：用这种从头部取出元素 不符合条件就加入尾部的方式 就能很方便的每次从头部取出元素就是按照顺序的方式
//        while(len>0){
//            Integer out = linkedList.removeFirst();
//            if(i==m){
//                joiner.add(out+"");
//                m=out;
//                len--;
//                i=1;
//            }
//            else{
//                linkedList.addLast(out);
//                i++;
//            }
//        }

        // 法二 更加高效 不用一个一个找 直接+m-1定位到需要删除的元素
        int start=0;
        while(linkedList.size()>0){
            start+=m-1;
            start=start%linkedList.size();
            m=linkedList.get(start);
            joiner.add(m+"");
            linkedList.remove(start);
        }
        System.out.println(joiner);

    }
}
