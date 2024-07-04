/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/21 20:41
 */
/*
* 为了充分发挥GPU算力，需要尽可能多的将任务交给GPU执行，现在有一个任务数组，数组元素表示在这1秒内新增的任务个数且每秒都有新增任务。
假设GPU最多一次执行n个任务，一次执行耗时1秒，在保证GPU不空闲情况下，最少需要多长时间执行完成。
输入描述
第一个参数为GPU一次最多执行的任务个数，取值范围[1, 10000]
第二个参数为任务数组长度，取值范围[1, 10000]
第三个参数为任务数组，数字范围[1, 10000]
输出描述
执行完所有任务最少需要多少秒。
用例
输入
3
5
1 2 3 4 5
输出 6
说明 一次最多执行3个任务，最少耗时6s
输入
4
5
5 4 1 1 1
输出 5
说明 一次最多执行4个任务，最少耗时5s*/
import java.util.Scanner;

public class LogicTaskTime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int maxCount = sc.nextInt();

        int n = sc.nextInt();
        int[] tasks = new int[n];
        for (int i = 0; i < n; i++) tasks[i] = sc.nextInt();

        System.out.println(getResult(maxCount, tasks));
    }

    public static int getResult(int maxCount, int[] tasks) {
        int time = 0;
        int remain = 0;

        for (int task : tasks) {
            if (task + remain > maxCount) {
                remain = task + remain - maxCount;
            } else {
                remain = 0;
            }
            time++;
        }

        while (remain > 0) {
            remain -= maxCount;
            time++;
        }

        return time;
    }
}