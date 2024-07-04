import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 21:31
 */
/*
* 一个应用启动时，会有多个初始化任务需要执行，并且任务之间有依赖关系，例如A任务依赖B任务，那么必须在B任务执行完成之后，才能开始执行A任务。

现在给出多条任务依赖关系的规则，请输入任务的顺序执行序列，规则采用贪婪策略，即一个任务如果没有依赖的任务，则立刻开始执行，如果同时有多个任务要执行
* ，则根据任务名称字母顺序排序。

例如：B任务依赖A任务，C任务依赖A任务，D任务依赖B任务和C任务，同时，D任务还依赖E任务。那么执行任务的顺序由先到后是：

A任务，E任务，B任务，C任务，D任务

这里A和E任务都是没有依赖的，立即执行。

输入描述
输入参数每个元素都表示任意两个任务之间的依赖关系，输入参数中符号"->"表示依赖方向，例如：

A->B：表示A依赖B

多个依赖之间用单个空格分隔

输出描述
输出排序后的启动任务列表，多个任务之间用单个空格分隔

用例
输入	A->B C->B
输出	B A C
说明	无
题目解析
*/
import java.util.*;

public class GraphTaskSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[][] relations =
                Arrays.stream(sc.nextLine().split(" ")).map(s -> s.split("->")).toArray(String[][]::new);

        HashMap<String, Integer> inDegree = new HashMap<>();
        HashMap<String, ArrayList<String>> next = new HashMap<>();

        for (String[] relation : relations) {
            // a依赖b, 即b执行完，才能执行a
            String a = relation[0];
            String b = relation[1];

            // b的入度不变
            inDegree.put(b, inDegree.getOrDefault(b, 0));
            // a的入度+1
            inDegree.put(a, inDegree.getOrDefault(a, 0) + 1);

            // b的后继节点集合添加a
            next.putIfAbsent(b, new ArrayList<>());
            next.get(b).add(a);
            // a的后继节点集合不变
            next.putIfAbsent(a, new ArrayList<>());
        }

        // queue收集第一层入度为0的点
        ArrayList<String> queue = new ArrayList<>();
        for (String task : inDegree.keySet()) {
            if (inDegree.get(task) == 0) {
                queue.add(task);
            }
        }

        // 记录任务执行的顺序
        StringJoiner ans = new StringJoiner(" ");

        while (queue.size() > 0) {
            // 如果同时有多个任务要执行，则根据任务名称字母顺序排序
            queue.sort(String::compareTo);

            // newQueue用于记录下一层入度为0的点
            ArrayList<String> newQueue = new ArrayList<>();

            for (String fa : queue) {
                // fa执行，则加入已完成的任务列表
                ans.add(fa);

                for (String ch : next.get(fa)) {
                    // fa是父任务，ch是子任务, 即fa执行完，才能执行ch
                    // fa执行完，则对应ch的入度-1
                    inDegree.put(ch, inDegree.get(ch) - 1);

                    // 如果ch的入度变为0，则加入新一层入度0的点集
                    if (inDegree.get(ch) == 0) {
                        newQueue.add(ch);
                    }
                }
            }

            queue = newQueue;
        }

        System.out.println(ans);
    }
}