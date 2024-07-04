import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target
 * 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * candidates 的所有元素 互不相同
 * 1 <= target <= 40
 * @date 2024/1/19 10:24
 */
public class Lc39combinationSum {
    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates); //其实不排序也可以 下面的递归条件里就不需要candidates[index]>target
        LinkedList<Integer> temp=new LinkedList<>();
        getCombination(candidates,0,target,temp);
        return res;
    }
    // 选或不选解法
    private void getCombination(int[] candidates, int index, int target,LinkedList<Integer> temp) {
        /*递归出口 一个是得到结果解的出口 一个是说明这一轮已经越界了或者肯定找不到解的出口 不要忘记*/
        if(target==0){
            res.add(new LinkedList<>(temp));
        }
        else if(index>=candidates.length||candidates[index]>target)
            return ;
        else{
            // 选择跳过不用第index个数
            getCombination(candidates,index+1,target,temp);
            //选择使用第index个数，同时因为每个数字可以重复使用 所以下次还是从index开始搜索
            if(target-candidates[index]>=0){
                temp.add(candidates[index]);
                getCombination(candidates,index,target-candidates[index],temp);
                temp.removeLast();
            }
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> state = new ArrayList<>(); // 状态（子集）
        Arrays.sort(candidates); // 对 candidates 进行排序
        List<List<Integer>> res = new ArrayList<>(); // 结果列表（子集列表）
        backtrack(state, target, candidates, 0, res);
        return res;
    }
    // 枚举选哪个解法
    void backtrack(List<Integer> state, int target, int[] choices, int start, List<List<Integer>> res) {
        // 子集和等于 target 时，记录解
        if (target == 0) {
            res.add(new ArrayList<>(state));
            return;
        }
        // 遍历所有选择 剪枝二：从 start 开始遍历，避免生成重复子集
        for (int i = start; i < choices.length; i++) {
            // 剪枝一：若子集和超过 target ，则直接结束循环 这是因为数组已排序，后边元素更大，子集和一定超过 target
            if (target - choices[i] < 0) {
                break;
            }
            // 尝试：做出选择，更新 target, start
            state.add(choices[i]);
            // 进行下一轮选择 因为可以有重复 所以下一轮start还是i 如果不可以有重复 下一轮start就是i+1
            backtrack(state, target - choices[i], choices, i, res);
            // 回退：撤销选择，恢复到之前的状态
            state.remove(state.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target =8;
        System.out.println(new Lc39combinationSum().combinationSum(candidates,target));

    }
}

