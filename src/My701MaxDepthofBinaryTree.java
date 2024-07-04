/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/17 13:44
 */
public class My701MaxDepthofBinaryTree {
    public int maxDepthOfBinaryTree(TreeNode root){
        if(root==null)
            return 0;
        return Math.max(maxDepthOfBinaryTree(root.left),maxDepthOfBinaryTree(root.right))+1;
    }
    public static int minDepth(TreeNode root){
        //错误的解法 不能由maxDepth照推 因为最小的深度必须是根节点到达叶子节点的深度 这种写法比如当根节点的
        //左节点为空右节点不为空时 就会返回结果1 但它不是叶子节点
       /* if(root==null)
            return 0;
        return Math.min(minDepth(root.left),minDepth(root.right))+1;*/
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) { //左右孩子都为空 说明是叶子节点
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }
        return min_depth + 1;
    }


    public static int minDepth1(TreeNode root){
        if (root == null) {
            return 0;
        }
        //这道题递归条件里分为三种情况
        //1.左孩子和有孩子都为空的情况，说明到达了叶子节点，直接返回1即可
        if (root.left == null && root.right == null) { //左右孩子都为空 说明是叶子节点
            return 1;
        }
        //2.如果左孩子和由孩子其中一个为空，那么需要返回比较大的那个孩子的深度
        int m1=minDepth(root.left);
        int m2=minDepth(root.right);
        //这里其中一个节点为空，说明m1和m2有一个必然为0，所以可以返回m1 + m2 + 1;
        if(root.left==null||root.right==null)
            return m1+m2+1;
        //3.最后一种情况，也就是左右孩子都不为空，返回最小深度+1即可
        return Math.min(m1,m2)+1;

    }
    public static void main(String[] args) {

    }

}
