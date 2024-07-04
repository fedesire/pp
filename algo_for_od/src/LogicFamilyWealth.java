import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/16 8:21
 */
/*
* 题目描述
在一颗树中，每个节点代表一个家庭成员，节点的数字表示其个人的财富值，一个节点及其直接相连的子节点被定义为一个小家庭。
现给你一颗树，请计算出最富裕的小家庭的财富和。
输入描述
第一行为一个数 N，表示成员总数，成员编号 1~N。1 ≤ N ≤ 1000
第二行为 N 个空格分隔的数，表示编号 1~N 的成员的财富值。0 ≤ 财富值 ≤ 1000000
接下来 N -1 行，每行两个空格分隔的整数（N1, N2），表示 N1 是 N2 的父节点。
输出描述
最富裕的小家庭的财富和
用例
输入 4
100 200 300 500
1 2
1 3
2 4
输出 700
说明
成员1，2，3 组成的小家庭财富值为600
成员2，4 组成的小家庭财富值为700
* */
public class LogicFamilyWealth {

    /*如果只用一个wealth数组辅助 就会出现在下面这种情况下
    5
100 200 300 400 500
3 4
3 5
1 3
1 2
3是1的子节点，但是3的家庭关系先被定义出来了，因此如果将家庭的财富都汇总到父节点身上，上面逻辑会将节点3的财富值变为1200，
从而影响后面父节点为1的家庭财富计算。*/
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n = scanner.nextInt();
        int[] wealth=new int[n+1];
        int[] family=new int[n+1];
        for (int i = 1; i <= n; i++) {
            wealth[i]=scanner.nextInt();
            family[i]=wealth[i];
        }
        for (int i = 0; i < n-1; i++) {
            int fa = scanner.nextInt();
            int ch=scanner.nextInt();
            family[fa]+=wealth[ch];
        }
        System.out.println(Arrays.stream(family).max().orElse(0));
        // 这种方法也不行 即使先排序了 也会出现把间接相连的节点的财富值也算进去的情况 还是必须要多一个数组出来记录原始的wealth
     /*   for (int i = 1; i <= n; i++) {
            wealth[i]=scanner.nextInt();
        }
        int[][] relation=new int[n-1][2];
        for (int i = 0; i < n-1; i++) {
            int fa = scanner.nextInt();
            int ch=scanner.nextInt();
            relation[i][0]=fa;
            relation[i][1]=ch;
        }
        Arrays.sort(relation, (o1, o2) -> o1[0]-o2[0]);
        for (int i = 0; i < n-1; i++) {
            wealth[relation[i][0]]+=wealth[relation[i][1]];
        }
        System.out.println(Arrays.stream(wealth).max().orElse(0));
*/

    }
}
