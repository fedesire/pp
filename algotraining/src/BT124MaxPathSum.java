/**
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。
 * 该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * @date 2024/5/12 11:30
 */
class BT124MaxPathSum {
    int result = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return result;
    }
     // innerMax 是当前node作为root的时候的最大值，也就是说只有在当前节点是root的时候才能够同时选左边和右边；不然的话，
    // 作为路径上的一份子，只能选择左边或者右边。因为dfs的最顶层是二叉树的root，所以其实我们每个节点当root的可能性都考虑到了，
    // 所以选择在innermax那边更新。

    // 函数功能：返回当前节点能为父亲提供的贡献，需要结合上面的图来看！
    private int dfs(TreeNode root) {
        // 如果当前节点为叶子节点，那么对父亲贡献为 0
        if(root == null) return 0;
        // 如果不是叶子节点，计算当前节点的左右孩子对自身的贡献left和right
        int left = dfs(root.left);
        int right = dfs(root.right);

        int innerMaxSum = left + root.val + right; // 当前子树内部的最大路径和
        // 更新最大值，就是当前节点的val 加上左右节点的贡献。
        result = Math.max(result, innerMaxSum);
        // 计算当前节点能为父亲提供的最大贡献，必须是把 val 加上！
        int max = root.val+Math.max(0,Math.max(left,right));
        // 如果贡献小于0的话，直接返回0即可！
        return max < 0 ? 0 : max;
    }
}