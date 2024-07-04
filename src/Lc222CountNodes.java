/**
 * @author xqi
 * @version 1.0
 * @description: 求一颗完全二叉树的节点个数
 * @date 2023/12/21 14:56
 */
public class Lc222CountNodes {
    public static int countCompleteTreeNodes(TreeNode root){
        if(root==null)
            return 0;
        int leftLevel=countLevel(root.left);
        int rightLevel=countLevel(root.right);

        //一颗满二叉树的层数为h，则总节点数为：2^h - 1.
        /*left == right。这说明，左子树一定是满二叉树，因为节点已经填充到右子树了，左子树必定已经填满了。
        所以左子树的节点总数我们可以直接得到，是 2^left - 1，加上当前这个 root 节点，则正好是 2^left。
        再对右子树进行递归统计。left != right。说明此时最后一层不满，但倒数第二层已经满了，可以直接得到
        右子树的节点个数。同理，右子树节点 +root 节点，总数为 2^right。再对左子树进行递归查找。*/
        if(leftLevel==rightLevel)
            return (int)Math.pow(2,leftLevel)+countCompleteTreeNodes(root.right);
        else
            return (int)Math.pow(2,rightLevel)+countCompleteTreeNodes(root.left);

    }
    //因为是完全二叉树 所以可以沿着左边一直计算得到的层数就是本身的层数
    public static int countLevel(TreeNode root){
        int number=0;
        while(root!=null){
            number++;
            root=root.left;
        }
        return number;
    }
    //普适的解法 并没有用到完全二叉树的特性
    public static int countNodes(TreeNode root){
        if(root==null)
            return 0;
        return countNodes(root.left)+countNodes(root.right)+1;
    }
}
