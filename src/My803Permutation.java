import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/18 14:24
 */
public class My803Permutation {
    private static List<List<Integer>> res;
    private  boolean[] used;
    // p中保存了一个有index-1个元素的排列。只有一个
    // 向这个排列的末尾添加第index个元素, 获得一个有index个元素的排列
    //方法形参定义的就是LinkedList不是List 是因为List只能调用remove(object) remove(index) 写LinkedList才能removeLast
    //LinkedList可以直接调用remove() 但此时remove的是head元素
    //这里p也可以是List 然后传进来的实参p是ArrayList 最后的代码改成p.remove(p.size()-1)
    public  void getPermutation(int[] nums, int index, LinkedList<Integer> p){
        if(index==nums.length){
            res.add((LinkedList<Integer>)p.clone());
            return ;
        }
        for (int i = 0; i < nums.length; i++) {
            if(!used[i]){
                used[i]=true;
                p.addLast(nums[i]);
                getPermutation(nums,index+1,p);
                used[i]=false;
                p.removeLast();
            }

        }
        return ;
    }
    public  List<List<Integer>> permute(int[] nums){
        res= new ArrayList<>();
        if(nums==null||nums.length==0)
            return res;
        used=new boolean[nums.length]; //boolean数组默认初始值为false
        LinkedList<Integer> p=new LinkedList<>();
        getPermutation(nums,0,p);
        return res;

    }

    private static void printList(List<Integer> list){
        for(Integer e: list)
            System.out.print(e + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        int[] nums = {1, 1,2};
        List<List<Integer>> res = (new My803Permutation()).permuteUnique(nums);
        for(List<Integer> list: res)
            printList(list);
    }

    // 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列
    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] used = new boolean[nums.length];
        LinkedList<Integer> path = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();

        // 新增：排序是为了让相同元素相邻，方便后面树层去重
        Arrays.sort(nums);
        dfs(nums, used, path, res);

        return res;
    }

    public  void dfs(
            int[] nums, boolean[] used, LinkedList<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) {
            res.add(new LinkedList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            // 新增：树层去重 i>0是因为有i-1
            // 加入 !used[i-1] 判断条件，表示当前分支前面层级并没有用过nums[i-1]，因此当前nums[i] 对比的 nums[i - 1] 只能是
            // 其他轮次的，而不是本轮次的 https://blog.csdn.net/qfc_128220/article/details/127292846?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522171401259616800188535890%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fblog.%2522%257D&request_id=171401259616800188535890&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~first_rank_ecpm_v1~rank_v31_ecpm-1-127292846-null-null.nonecase&utm_term=%E5%85%A8%E6%8E%92%E5%88%97&spm=1018.2226.3001.4450
            if(i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;

            used[i] = true;
            path.add(nums[i]);

            dfs(nums, used, path, res);

            used[i] = false;
            path.removeLast();
        }
    }
}
