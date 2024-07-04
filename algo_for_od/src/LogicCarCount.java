/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/21 20:52
 */
/*
* 特定大小的停车场，数组cars[]表示，其中1表示有车，0表示没车。

车辆大小不一，小车占一个车位（长度1），货车占两个车位（长度2），卡车占三个车位（长度3）。

统计停车场最少可以停多少辆车，返回具体的数目。

输入描述
整型字符串数组cars[]，其中1表示有车，0表示没车，数组长度小于1000。

输出描述
整型数字字符串，表示最少停车数目。

用例
输入	1,0,1
输出	2
说明
1个小车占第1个车位

第二个车位空

1个小车占第3个车位

最少有两辆车

输入	1,1,0,0,1,1,1,0,1
输出	3
说明
1个货车占第1、2个车位

第3、4个车位空

1个卡车占第5、6、7个车位

第8个车位空

1个小车占第9个车位

最少3辆车

题目解析
这道题的意思应该是：给定了车位占用情况，如 1,1,0,0,1,1,1,0,1，这种车位占用情况，可能停了6辆车，即每个1都停了一个小车，这是最多的情况，但是现在要求最少可能停几辆车。

解题思路也很简单，先把卡车，即111的停车情况先弄出来，再将火车，即11的停车情况弄出来，最后再弄小车1的情况。
*/
import java.util.Scanner;

public class LogicCarCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str =
                sc.nextLine()
                        .replaceAll(",", "")
                        .replaceAll("111", "x")
                        .replaceAll("11", "x")
                        .replaceAll("1", "x");

        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'x') {
                ans++;
            }
        }

        System.out.println(ans);
    }
}