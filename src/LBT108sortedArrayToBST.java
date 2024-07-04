/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/2/4 11:57
 */
/*BST 的中序遍历是升序的，因此本题等同于根据中序遍历的序列恢复二叉搜索树。因此我们可以以升序序列中的任一个元素
作为根节点，以该元素左边的升序序列构建左子树，以该元素右边的升序序列构建右子树，这样得到的树就是一棵二叉搜索树
啦～ 又因为本题要求高度平衡，因此我们需要选择升序序列的中间元素作为根节点奥*/

/*二叉搜索树的中序遍历是升序序列，题目给定的数组是按照升序排序的有序数组，因此可以确保数组是二叉搜索树的中序
遍历序列。选择中间位置左边或右边的数字作为根节点 可以保证左右子树节点的个数最大不超过1 并且mid前面的数字一定
比mid小 所以前面的数字作为左子树的节点也满足二叉搜索树的要求*/
public class LBT108sortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root=helper(nums,0,nums.length-1);
        return root;
    }
    public TreeNode helper(int[] nums,int left,int right){
        if(left>right)
            return null;
        // 总是选择中间位置左边的数字作为根节点
        int mid=(left+right)/2;

      /*  // 总是选择中间位置右边的数字作为根节点
        int mid = (left + right + 1) / 2;*/

        TreeNode node=new TreeNode(nums[mid]);
        node.left=helper(nums,left,mid-1);
        node.right=helper(nums,mid+1,right);
        return node;
    }

}
