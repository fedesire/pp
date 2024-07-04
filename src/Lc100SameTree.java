/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/17 9:35
 */
public class Lc100SameTree {
    public boolean sameTree(TreeNode root1,TreeNode root2){
        if(root1==null&&root2==null)
            return true;
        if(root1==null&&root2!=null||root1!=null&&root2==null)
            return false;
        if(!sameTree(root1.left,root2.left) ||!sameTree(root1.right,root2.right))
            return false;
        if(root1.val!=root2.val)
            return false;
        return true;//必须要有
    }
    //和我的一样的逻辑 顺序不一样 更准确更好
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
}
