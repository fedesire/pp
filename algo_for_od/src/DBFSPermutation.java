import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 22:32
 */

class DBFSPermutation {
    public static void main(String[] args) {
        int[] nums={1,2,3};
        System.out.println(new DBFSPermutation().permute(nums));
    }
    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        LinkedList<Integer> path = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();

        dfs(nums, used, path, res);

        return res;
    }

    public static void dfs(
            int[] nums, boolean[] used, LinkedList<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new LinkedList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            used[i] = true;
            path.add(nums[i]);

            dfs(nums, used, path, res);

            used[i] = false;
            path.removeLast();
        }
    }

}