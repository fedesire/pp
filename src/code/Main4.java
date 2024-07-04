package code;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;
/*
* n 个学生排成一排，学生编号分别是 1 到 n，n 为 3 的整倍数。
老师随机抽签决定将所有学生分成 m 个 3 人的小组（n == 3 * m） ，
为了便于同组学生交流，老师决定将小组成员安排到一起，也就是同组成员彼此相连，同组任意两个成员之间无其它组的成员。
因此老师决定调整队伍，老师每次可以调整任何一名学生到队伍的任意位置，计为调整了一次， 请计算最少调整多少次可以达到目标。
注意：对于小组之间没有顺序要求，同组学生之间没有顺序要求。
输入描述
两行字符串，空格分隔表示不同的学生编号。
第一行是学生目前排队情况
第二行是随机抽签分组情况，从左开始每 3 个元素为一组
n 为学生的数量，n 的范围为 [3, 900]，n 一定为 3 的整数倍
第一行和第二行元素的个数一定相同
输出描述
老师调整学生达到同组彼此相连的最小调整次数
备注
同组相连：同组任意两个成员之间无其他组的成员，比如有两个小组 [4, 5, 6] 和 [1, 2, 3]，
以下结果都满足要求：
1,2,3,4,5,6；
1,3,2,4,5,6；
2,3,1,5,6,4；
5,6,4,1,2,3；
以下结果不满足要求：
1,2,4,3,5,6；（4与5之间存在其他组的成员3）
用例
输入 7 9 8 5 6 4 2 1 3
7 8 9 4 2 1 3 5 6
输出 1
说明
学生目前排队情况：7 9 8 5 6 4 2 1 3
学生分组情况：7 8 9 4 2 1 3 5 6
将3调整到4之前，队列调整为：7 9 8 5 6 3 4 2 1，那么三个小组成员均彼此相连 [7 9 8] [5 6 3] [4 2 1]
输出1
输入 8 9 7 5 6 3 2 1 4
7 8 9 4 2 1 3 5 6
输出 0
说明
学生目前排队情况：8 9 7 5 6 3 2 1 4
学生分组情况：7 8 9 4 2 1 3 5 6
无需调整，三个小组成员均彼此相连：
[7 8 9] [4 2 1] [3 5 6]
输出0*/
public class Main4 {
    // 分块（即连续的相同组的小朋友）
    static class Block {
        int groupId;
        int count;

        public Block(int groupId, int count) {
            this.groupId = groupId;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 初始小朋友（序号）排队顺序
        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = nums.length;

        // 序号->组号 映射关系
        int[] group = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            group[num] = i / 3;
        }

        // 相邻相同组号合并统计
        LinkedList<Block> queue = new LinkedList<>();
        for (int num : nums) {
            int groupId = group[num];

            if (queue.isEmpty() || queue.getLast().groupId != groupId) {
                queue.addLast(new Block(groupId, 1));
            } else {
                queue.getLast().count++;
            }
        }

        // 记录调整次数
        int moved_count = 0;
        while (!queue.isEmpty()) {
            Block first = queue.removeFirst();

            // 当first.count = 1 时，情况如下
            // 1 x 1 1 y z
            // 1 x 1 y 1 z
            if (first.count == 1) {
                Block x = queue.getFirst();

                // 判断x是否存在连续完整分组
                while (x.count == 3) {
                    queue.removeFirst();
                    x = queue.getFirst();
                }

                if (x.groupId == first.groupId && x.count == 2) {
                    // 情况：1 2 2 2 x[1 1] 到这里 前面的222会已经通过上面的removeFirst移除了
                    // 所以将开头1，移动进x中 再通过下面的removeFirst移除出去 不会对其他的有影响
                    moved_count++;
                    queue.removeFirst();
                } else {
                    // 情况如下：
                    // 1 x[2 2] 1 1
                    // 1 x[2] 1 2 1
                    // 将后面的两个1移动到开头
                    moved_count += 2;
                    queue = confirm(queue, first.groupId);
                }
            } else if (first.count == 2) {
                // 当first.count == 2 时，情况如下：
                // 1 1 x 1 y z 此时queue指向的是x 现在要将x后面的1移到前面去 所以movedcount+1 后面调用confirm的
                // 效果就是生成一个新的queue 在生成的过程中跳过当前的first.groupid 相当于移除出去了
                moved_count += 1;
                queue = confirm(queue, first.groupId);
            }
        }

        System.out.println(moved_count);
    }

    public static LinkedList<Block> confirm(LinkedList<Block> queue, int confirmed_groupId) {
        LinkedList<Block> back_queue = new LinkedList<>();

        while (!queue.isEmpty()) {
            Block first = queue.removeFirst();

            if (first.groupId == confirmed_groupId) {
                continue;
            }

            if (back_queue.isEmpty() || back_queue.getLast().groupId != first.groupId) {
                back_queue.addLast(new Block(first.groupId, first.count));
            } else {
                back_queue.getLast().count += first.count;
            }
        }

        return back_queue;
    }
}