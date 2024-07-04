/**
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 * @date 2024/2/5 11:47
 */
public class LBT530getMinimumDifference {
    int preVal=-1;
    int res=Integer.MAX_VALUE;
    public void inorder(TreeNode root){
        if(root!=null){
            inorder(root.left);
            if(preVal!=-1){
                res=Math.min(res,Math.abs(root.val-preVal));
            }
            preVal=root.val;
            inorder(root.right);
        }
    }
    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return res;
    }
}
