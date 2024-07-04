import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/14 8:20
 */
/*
* 现有若干个会议，所有会议共享一个会议室，用数组表示各个会议的开始时间和结束时间，格式为：
[[会议1开始时间, 会议1结束时间], [会议2开始时间, 会议2结束时间]]
请计算会议室占用时间段。
输入描述
第一行输入一个整数 n，表示会议数量
之后输入n行，每行两个整数，以空格分隔，分别表示会议开始时间，会议结束时间
输出描述
输出多行，每个两个整数，以空格分隔，分别表示会议室占用时间段开始和结束
备注
会议室个数范围：[1, 100]
会议室时间段：[1, 24]
用例
输入 4
1 4
2 5
7 9
14 18
输出 1 5
7 9
14 18
说明
输入：[[1,4],[2,5],[7,9],[14,18]]
输出：[[1,5],[7,9],[14,18]]
说明：时间段[1,4]和[2,5]重叠，合并为[1,5]
输入 2
1 4
4 5
输出 1 5
说明
输入：[[1,4],[4,5]]
输出：[[1,5]]
说明：时间段[1,4]和[4,5]连续*/
public class IntervalConference {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n= scanner.nextInt();
        int[][] timeRecord=new int[n][2];
        for (int i = 0; i < n; i++) {
            timeRecord[i][0]=scanner.nextInt();
            timeRecord[i][1]=scanner.nextInt();
        }
        Arrays.sort(timeRecord, (o1, o2) -> o1[0]-o2[0]);
        int pre=0,cur=1;
        for (int i = 1; i < n; i++) {
            if(timeRecord[i][0]<=timeRecord[pre][1]){
                // 当前会议开始时间 <= 上一个会议结束时间，则两个会议时间重叠，可以合并
                // 注意合并时，结束时间取两个时间段中较大的结束时间
                timeRecord[pre][1]=Math.max(timeRecord[i][1],timeRecord[pre][1]);
            }
            else{
                System.out.println(timeRecord[pre][0]+" "+timeRecord[pre][1]);
                pre=i;
            }
        }
        // 最后一个会议的时间还需要在循环外输出
        System.out.println(timeRecord[pre][0]+" "+timeRecord[pre][1]);
    }
}
