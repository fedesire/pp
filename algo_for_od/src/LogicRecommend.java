import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringJoiner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 19:43
 */
// https://fcqian.blog.csdn.net/article/details/135004447

/*
* 输入描述
第一行输入为 N，表示需要输出的窗口数量，取值范围 [1, 10]

第二行输入为 K，表示每个窗口需要的元素数量，取值范围 [1, 100]

之后的行数不定（行数取值范围 [1, 10]），表示每个列表输出的元素列表。元素之间以空格隔开，已经过排序处理，每个列表输出的元素数量取值范围 [1, 100]

输出描述
输出元素列表，元素数量 = 窗口数量 * 窗口大小，元素之间以空格分隔，多个窗口合并为一个列表输出，参考样例：

先输出窗口1的元素列表，再输出窗口2的元素列表，再输出窗口3的元素列表，最后输出窗口4的元素列表

备注
每个列表会保证元素数量满足窗口要求，不需要考虑元素不足情况
每个列表的元素已去重，不需要考虑元素重复情况
每个列表的元素列表均不为空，不需要考虑列表为空的情况
每个列表的元素列表已经过排序处理，输出结果要保证不改变同一个列表的元素顺序
每个列表的元素数量可能是不同的
用例
输入	4
7
0 1 2 3 4 5 6 7 8 9
10 11 12 13 14 15 16 17 18 19
20 21 22 23 24 25 26 27 28 29
输出	0 10 20 4 14 24 8 1 11 21 5 15 25 9 2 12 22 6 16 26 18 3 13 23 7 17 27 19
说明	无
题目解析*/
import java.util.*;

public class LogicRecommend {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int k = Integer.parseInt(sc.nextLine());

        ArrayList<LinkedList<Integer>> lists = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            // 本地测试，以空行作为输入截止条件
            if (line.length() == 0) break;

            Integer[] nums =
                    Arrays.stream(line.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

            lists.add(new LinkedList<>(Arrays.asList(nums)));
        }

        // 窗口矩阵，k行n列，每一列对应一个窗口，这里将二维矩阵一维化，方便后面赋值
        int[] windows = new int[k * n];
        // 窗口矩阵中正在赋值的索引位置
        int idx = 0;
        // 正在从第level个列表中取值
        int level = 0;

        // 当窗口矩阵填满后，结束循环
        while (idx < windows.length) {
            // 当前轮次是否发生了"借"动作
            boolean flag = false;

            // 从第level个列表中取前n个元素
            for (int i = 0; i < n; i++) {
                windows[idx++] = lists.get(level).removeFirst();

                // 如果第level个列表没有元素了，则继续切到下一个列表中"借"
                if (lists.get(level).size() == 0 && lists.size() > 1) {
                    lists.remove(level); // 删除空列表
                    level %= lists.size(); // 防止越界
                    flag = true; // 发生了"借"动作
                }
            }

            // 如果没有发生"借"动作，则需要切到下一行
            if (!flag) {
                level = (level + 1) % lists.size(); // 防止越界
            }
        }

        StringJoiner sj = new StringJoiner(" ");

        // 遍历窗口矩阵的每一列
        for (int j = 0; j < n; j++) { // 遍历列号
            for (int i = 0; i < k; i++) { // 遍历行号
                sj.add(windows[i * n + j] + ""); // 将每一列的元素进行拼接
            }
        }

        System.out.println(sj);
    }
}