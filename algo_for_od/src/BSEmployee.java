import java.util.Arrays;
import java.util.Scanner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/15 15:39
 */
/*
* 部门在进行需求开发时需要进行人力安排。

当前部门需要完成 N 个需求，需求用 requirements 表述，requirements[i] 表示第 i 个需求的工作量大小，单位：人月。

这部分需求需要在 M 个月内完成开发，进行人力安排后每个月人力时固定的。

目前要求每个月最多有2个需求开发，并且每个月需要完成的需求不能超过部门人力。

请帮助部门评估在满足需求开发进度的情况下，每个月需要的最小人力是多少？

输入描述
输入为 M 和 requirements，M 表示需求开发时间要求，requirements 表示每个需求工作量大小，N 为 requirements长度，

1 ≤ N/2 ≤ M ≤ N ≤ 10000
1 ≤ requirements[i] ≤ 10^9
输出描述
对于每一组测试数据，输出部门需要人力需求，行末无多余的空格

用例
输入
3
3 5 3 4
输出	6
说明
输入数据两行，

第一行输入数据3表示开发时间要求，

第二行输入数据表示需求工作量大小，

输出数据一行，表示部门人力需求。

当选择人力为6时，2个需求量为3的工作可以在1个月里完成，其他2个工作各需要1个月完成。可以在3个月内完成所有需求。

当选择人力为5时，4个工作各需要1个月完成，一共需要4个月才能完成所有需求。

因此6是部门最小的人力需求。
*
m个月完成n份工作每个月需要的最小人力

* 二分法+双指针
*/
public class BSEmployee {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int m = scanner.nextInt();
        scanner.nextLine();
        int[] requirements = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n=requirements.length;

        Arrays.sort(requirements);
        int min=requirements[n-1];
        int max=requirements[n-1]+requirements[n-2];
        int res=min;
        while(min<=max){
            int mid=min+(max-min)/2;
            if(check(mid,m,requirements)){
                res=mid;
                max=mid-1;
            }
            else{
                min=mid+1;
            }
        }
        System.out.println(res);
    }

    // 每个月x人力 能否在m个月完成所有工作 一个月最多完成2个工作
    private static boolean check(int x, int m, int[] requirements) {
        int l=0;
        int r=requirements.length-1;
        int need=0;
        // 这里是在求所有工作都完成最少需要几个月 所以肯定是一个最大的和一个最小的搭配在一起 这样才平均 最后需要的时间才最少
        while(l<=r){
            // 如果最轻的人和最重的人可以共享一辆车，则l++,r--，
            // 否则最重的人只能单独坐一辆车，即仅r--
            if(requirements[r]+requirements[l]<=x){
                l++;
            }
            r--;
            need++;
        }
        return need<=m;
    }
}
