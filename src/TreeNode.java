/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/12 9:05
 */
public class TreeNode{
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(){}
    public TreeNode(int x){
        val=x;
    }
    public TreeNode(int x, TreeNode left, TreeNode right){
        this.val=x;
        this.left=left;
        this.right=right;
    }
}
