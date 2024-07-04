import sun.reflect.generics.tree.Tree;

import java.util.ArrayDeque;
import java.util.LinkedList;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/21 14:17
 */
public class Lc101Symmetric {
    //比较根节点的左孩子和有孩子是否镜像对称 递归下去就是比较左孩子的左孩子与右孩子的右孩子是否相等
    // 左孩子的右孩子与右孩子的左孩子是否相等
    public static boolean isSymmetric(TreeNode root){
        if(root==null)
            return true;
        return dfs(root.left,root.right);
    }
    public static boolean dfs(TreeNode left,TreeNode right){
        if(left==null&&right==null)
            return true;
        if(left==null||right==null)
            return false;
        if(left.val!=right.val)
            return false;
        return dfs(left.left,right.right) && dfs(left.right,right.left);
    }

    //迭代的方式通过队列实现
    public static boolean isSymmetric1(TreeNode root){
        if(root==null||(root.left==null&&root.right==null))
            return true;
        if(root.left==null||root.right==null)
            return false;
        LinkedList<TreeNode> queue=new LinkedList<>();//这里queue的实现类不能用ArrayDeque 因为ArrayDeque不能入队null
        queue.add(root.left);
        queue.add(root.right);
        while(!queue.isEmpty()){
            TreeNode node1=queue.removeFirst();
            TreeNode node2=queue.removeFirst();
            if(node1==null&&node2==null)
                continue;
            if(node1==null||node2==null)
                return false;
            if(node1.val!= node2.val)
                return false;
            //将左节点的左孩子， 右节点的右孩子放入队列
            queue.add(node1.left);
            queue.add(node2.right);
            //将左节点的右孩子，右节点的左孩子放入队列
            queue.add(node1.right);
            queue.add(node2.left);
        }
        return true;
    }
}
