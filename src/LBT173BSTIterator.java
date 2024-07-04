import java.util.ArrayList;
import java.util.List;

/**
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 *
 * @date 2024/1/23 11:56
 */
public class LBT173BSTIterator {
    TreeNode root;
    List<Integer> res;
    int index;
    private void inorder(TreeNode root){
        if(root!=null){
            inorder(root.left);
            res.add(root.val);
            inorder(root.right);
        }

    }

    public LBT173BSTIterator(TreeNode root) {
        this.root=root;
        res=new ArrayList<>();
        inorder(this.root);
        index=-1;
    }

    public int next() {
        return res.get(++index);
    }

    public boolean hasNext() {
        if(index+1<res.size())
            return true;
        return false;
    }
}
