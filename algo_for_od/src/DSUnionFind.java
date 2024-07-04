import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/16 17:41
 */
/*
* 为了达到新冠疫情精准防控的需要，为了避免全员核酸检测带来的浪费，需要精准圈定可能被感染的人群。
现在根据传染病流调以及大数据分析，得到了每个人之间在时间、空间上是否存在轨迹交叉。
现在给定一组确诊人员编号（X1,X2,X3,...,Xn），在所有人当中，找出哪些人需要进行核酸检测，输出需要进行核酸检测的人数。（注意：确诊病例自身不需要再做核酸检测）
需要进行核酸检测的人，是病毒传播链条上的所有人员，即有可能通过确诊病例所能传播到的所有人。
例如：A是确诊病例，A和B有接触、B和C有接触、C和D有接触、D和E有接触，那么B\C\D\E都是需要进行核酸检测的人。
输入描述
第一行为总人数 N
第二行为确认病例人员编号（确诊病例人员数量 < N），用逗号分割
第三行开始，为一个 N * N 的矩阵，表示每个人员之间是否有接触，0表示没有接触，1表示有接触。
输出描述
整数：需要做核酸检测的人数
备注
人员编号从0开始
0 < N < 100
用例
输入 5
1,2
1,1,0,1,0
1,1,0,0,0
0,0,1,0,1
1,0,0,1,0
0,0,1,0,1
输出 3
说明
编号为1、2号的人员，为确诊病例。
1号和0号有接触，0号和3号有接触。
2号和4号有接触。
所以，需要做核酸检测的人是0号、3号、4号，总计3人需要进行核酸检测。
* */
public class DSUnionFind {
    // 遍历第一行 1指向6和7 6 7的父节点就是1 便利到第二行 2也指向6 7 就将1的父节点变为2 此时1 6 7调用find方法得到的结果就是2
    // 便利到第三行 3也指向6 7 就将2的父节点变为3 此时1 2 6 7调用find方法得到的结果就是3
    // 可以看成是下面行的优先级比较高
    static class UnionFindSet{
        // father里存的可以看作是每个节点的第一个父节点
        int[] father;
        public UnionFindSet(int n){
            father=new int[n];
            for (int i = 0; i < n; i++) {
                father[i]=i;
            }
        }

        // find是查找节点当前阶段能找到的最上级的父节点
        int find(int x){
            if(x!=father[x]){
                father[x]=find(father[x]);
                return father[x];
            }
            return x;
        }
        void union(int x,int y){
            // find是查找节点当前阶段能找到的最上级的父节点
            int x_fa=find(x);
            int y_fa=find(y);
            if(x_fa!=y_fa)
                father[y_fa]=x_fa;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        int[] confirmed = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();

        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(sc.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        }

        UnionFindSet ufs=new UnionFindSet(n);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (matrix[i][j] == 1) {
                    // 有过接触的人进行合并
                    ufs.union(i, j);
                }
            }
        }
        // 统计每个接触群体（连通分量）中的人数 有可能多个确诊病人在同一个连通分量重，此时需要注意避免重复统计。
        int[] cnts = new int[n];
        for (int i = 0; i < n; i++) {
            int fa = ufs.find(i);
            cnts[fa]++;
        }
        // 不能直接这样计算ans 应该从每个确诊病人出发统计他们的连通分量 直接这样算 可能确诊病人所属的连通分量就他自己 此时应该是0而不是有数字结果
//        int  ans=Arrays.stream(cnts).reduce(Integer::sum).getAsInt();

        // 记录已统计过的感染群体
        HashSet<Integer> confirmed_fa = new HashSet<>();

        // 将有感染者的接触群体的人数统计出来
        int ans = 0;
        for (int i : confirmed) {
            int fa = ufs.find(i);

            // 如果该感染群体已统计过，则不再统计
            if (confirmed_fa.contains(fa)) continue;
            confirmed_fa.add(fa);

            ans += cnts[fa];
        }

        // 最终需要做核酸的人数，不包括已感染的人
        System.out.println(ans - confirmed.length);
    }
}
