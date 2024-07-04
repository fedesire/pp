import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/16 16:09
 */
/*
* 给定一个二叉树，每个节点上站一个人，节点数字表示父节点到该节点传递悄悄话需要花费的时间。
初始时，根节点所在位置的人有一个悄悄话想要传递给其他人，求二叉树所有节点上的人都接收到悄悄话花费的时间。
输入描述
给定二叉树
0 9 20 -1 -1 15 7 -1 -1 -1 -1 3 2
输出	38
说明	无
注：-1表示空节点*/
    // 这道题我第一次看到这种求根节点到叶子节点的和的最大值 首先想到的就是用dfs 而本题使用bfs层序便利更方便
    // 因为树是以数组的形式存在的 父节点 子节点索引存在关系 用队列层序遍历很方便
public class TreeArrLevelTraversal {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int[] arr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 队列种存储的是节点的索引 而不是值
        LinkedList<Integer> queue=new LinkedList<>();
        queue.add(0);
        int res=0;
        while(!queue.isEmpty()){
            int father = queue.removeFirst();
            int child1=2*father+1;
            int child2=child1+1;
            boolean isLeftExists=false,isRightExists=false;
            if(child1<arr.length&&arr[child1]!=-1)
                isLeftExists=true;
            if(child2<arr.length&&arr[child2]!=-1)
                isRightExists=true;
            if(isLeftExists){
                queue.add(child1);
                arr[child1]+=arr[father];
            }
            if(isRightExists){
                queue.add(child2);
                arr[child2]+=arr[father];
            }
            if(!isRightExists&&!isRightExists){
                res=Math.max(res,arr[father]);
            }
        }
        System.out.println(res);
    }
}
