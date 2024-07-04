import java.util.ArrayList;
import java.util.List;

/**
 * 给定一颗二叉树 返回所有从根节点到叶子节点路径和为sum的路径
 * @description: TODO
 * @date 2023/12/21 17:32
 */
public class Lc113PathSumII {
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs1(root, sum, new ArrayList<>(), result);
//        dfs2(root,sum);
        return result;
    }
    public static void dfs(TreeNode root, int sum, List<Integer> list,
                           List<List<Integer>> result) {
        //如果节点为空直接返回
        if (root == null)
            return;
        //因为list是引用传递，为了防止递归的时候分支污染，我们要在每个路径
        //中都要新建一个subList
        List<Integer> subList = new ArrayList<>(list);
        subList.add(new Integer(root.val));
        System.out.println(subList);
        if (root.left == null && root.right == null) {
            if (sum == root.val)
                result.add(subList);
            return;
        }
        //如果没到达叶子节点，就继续从他的左右两个子节点往下找，注意到
        //下一步的时候，sum值要减去当前节点的值
        dfs(root.left, sum - root.val, subList, result);
        dfs(root.right, sum - root.val, subList, result);
    }

    public static void dfs1(TreeNode root, int sum, List<Integer> list,
                    List<List<Integer>> result) {
        if (root == null)
            return;
        list.add(new Integer(root.val));
        System.out.println(list);
        if (root.left == null && root.right == null) {
            if (sum == root.val)
                result.add(new ArrayList(list));//必须要new新的list 不然结果集里的list就会随着后面的操作一直变
            // 如果这里不写remove 就不能return 然后走到最底下的remove也行
            list.remove(list.size() - 1);
            System.out.println(list);
            return;
        }
        //如果没到达叶子节点，就继续从他的左右两个子节点往下找
        dfs1(root.left, sum - root.val, list, result);
        dfs1(root.right, sum - root.val, list, result);

        //上面left和right的递归都结束后 说明左右节点的操作已经完成 且之前递归结束的时候已经把这个左右
        // 节点在list中remove了 左右都结束了 现在该节点相关的操作都完成了 所以要把该节点也从list中remove
        //不清楚的话再运行一次看输出的list变化就很清楚了
        list.remove(list.size() - 1);
        System.out.println(list);
    }

    //dfs1改一下 将list和result都作为类成员变量来实现
    static List<Integer> list=new ArrayList<>();
    static List<List<Integer>> result=new ArrayList<>();
    public static void dfs2(TreeNode root, int sum) {
        if (root == null)
            return;

        list.add(new Integer(root.val));
        System.out.println(list);
        if (root.left == null && root.right == null) {

            if (sum == root.val)
                result.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            System.out.println(list);

            return;
        }
        dfs2(root.left, sum - root.val);
        dfs2(root.right, sum - root.val);

        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        Integer[] arr={1,2,3,7,5,8,9,3,4,9,9}; //java数组里可以存放null int数组不行
        TreeNode root=BuildTree.buildTree(arr);
        System.out.println(pathSum(root,14));
    }

}
