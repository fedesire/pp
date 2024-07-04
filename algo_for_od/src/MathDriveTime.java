/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 22:59
 */

import java.text.NumberFormat;
import java.util.Scanner;
/*
* M（1 ≤ M ≤ 20）辆车需要在一条不能超车的单行道到达终点，起点到终点的距离为 N（1 ≤ N ≤ 400）。

速度快的车追上前车后，只能以前车的速度继续行驶，求最后一辆车到达目的地花费的时间。

注：每辆车固定间隔 1 小时出发，比如第一辆车 0 时出发，第二辆车 1 时出发，依次类推

输入描述
第一行两个数字：M N，分别代表车辆数和到终点的距离，以空格分隔

接下来 M 行，每行一个数字 S，代表每辆车的速度。0 < S < 30

输出描述
最后一辆车到达目的地花费的时间

用例
输入	2 11
3
2
输出	5.5
说明	2辆车，距离11，0时出发的车速度快，1时出发的车，到达目的地花费5.5
*/
public class MathDriveTime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();

        // 记录前车到达终点的时刻，本题后车不可能比前车更早到达，因此如果后车到达时刻 < 前车到达时刻arrived，则后车也是按照前车arrived时刻达到
        double arrived = 0;

        for (int i = 0; i < m; i++) {
            // 当前车的速度
            double speed = sc.nextDouble();
            // 当前车到达终点的时刻
            // * 当前车如果比前车更早到达，则被前车阻碍，按前车到达时间算
            // * 当前车如果比前车更晚到达，则不被前车阻碍，按后车到达时间算
            arrived = Math.max(arrived, n / speed + i); // n*1.0/speed是行驶花费时间； i是第i辆车的出发时间
        }

        // 到达时刻 - 最后一辆车的出发时刻 = 路上花费的时间
        double cost = arrived - (m - 1);

        System.out.println(cost);// 实际考试没有精度问题，可以直接输出cost，可以满分

        // 我这边OJ用例设计时有小数位则至多保留3位
        // 格式化打印小数
//        NumberFormat nf = NumberFormat.getInstance();
//        nf.setMinimumFractionDigits(0); // 没有小数位则不保留
//        nf.setMaximumFractionDigits(3); // 有小数位则至多保留3位
//
//        System.out.println(nf.format(cost));
    }
}