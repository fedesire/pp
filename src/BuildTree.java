import java.util.*;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/20 16:46
 */

public class BuildTree {
    /*每个节点入队的时候就是他自身的值已经确定了构建好了 出队的时候就是来确定他左右孩子的情况的 for循环在从前往后遍历
    数组的时候遇到每个非空值 都构建节点 并且将其赋给其他节点作为其孩子节点 队列就是用来快速找到要赋给谁作为孩子节点
    只要依次入队出队 每遍历到一个数构建一个节点 从队列头取出一个节点肯定就是要找的他的父节点*/
    public static TreeNode buildTree(Integer[] arr){
        if(arr.length==1) return new TreeNode(arr[0]);
        Queue<TreeNode> queue=new LinkedList<>();
        TreeNode root=new TreeNode(arr[0]);
        queue.add(root);
        //和层序遍历的逻辑差不多 都是先将根节点入队 之后在循环里先将每一个节点出队然后将其左右孩子入队
        for(int left=1;left<arr.length;left+=2){
            TreeNode poll = queue.remove();
            if(arr[left]!=null){
                poll.left=new TreeNode(arr[left]);
                queue.offer(poll.left);
            }
            if(left+1<arr.length){
                if(arr[left+1]!=null){
                    poll.right=new TreeNode(arr[left+1]);
                    queue.add(poll.right);
                }
            }
        }
        //输出的结果里不会有null 因为在构建二叉树的时候null就代表空节点 节点不存在也不会存储在二叉树中
//        System.out.println(My604BinaryTreeLevelOrderTraversal.levelOrderTraversal(root));
        return root;
    }

    public static void main(String[] args) {
        //java引用类型数组里可以存放null int数组不行
        Integer[] arr={1,2,2,3,null,4,4,3};
        buildTree(arr);
    }
}
