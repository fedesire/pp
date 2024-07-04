import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/16 13:51
 */
/*篮球(5V5)比赛中，每个球员拥有一个战斗力，每个队伍的所有球员战斗力之和为该队伍的总体战斗力。
现有10个球员准备分为两队进行训练赛，教练希望2个队伍的战斗力差值能够尽可能的小，以达到最佳训练效果。
给出10个球员的战斗力，如果你是教练，你该如何分队，才能达到最佳训练效果?请说出该分队方案下的最小战斗力差值。
输入描述
10个篮球队员的战斗力(整数，范围[1,10000]),战斗力之间用空格分隔，如:10987654321
不需要考虑异常输入的场景。
输出描述
最小的战斗力差值，如:1
用例
输入 10 9 8 7 6 5 4 3 2 1
输出 1
说明 1 2 5 9 10分为一队，3 4 6 7 8分为一队，两队战斗力之差最小，输出差值1。备注：球员分队方案不唯一，但最小战斗力差值固定是1*/
public class DBFSGame {
    public static void main(String[] args) {
        int[] score=new int[10];
        Scanner scanner=new Scanner(System.in);
        int sum=0;
        for (int i = 0; i < 10; i++) {
            score[i]= scanner.nextInt();
            sum+=score[i];
        }
        Arrays.sort(score);
        ArrayList<Integer> groupScores=new ArrayList<>();
        // // dfs求10选5的去重组合，并将组合之和记录进res中，即res中记录的是所有可能性的5人小队实力值之和
        dfs(score,0,0,0,groupScores);
        // 下面map里用sum提示说必须是final的 所以新建了一个变量
        int finalSum = sum;
        int res= groupScores.stream().map(subSum -> Math.abs(finalSum - 2 * subSum)).min((a, b) -> a - b).orElse(0);
        System.out.println(res);
    }

    private static void dfs(int[] score, int index, int level, int sum, ArrayList<Integer> groupScores) {
        if(level==5){
            groupScores.add(sum);
            return ;
        }
        // 当前level层的数从数组index及其之后位置里的数选 进入下一层level+1
        for (int i = index; i < 10; i++) {
            // 没看出来这里的作用
            if (i > index && score[i] == score[i - 1]) continue; // arr已经升序，这里进行树层去重
            /*if(5-level>10-index)
                continue;*/
            dfs(score,i+1,level+1,sum+score[i],groupScores);
        }
    }
}
