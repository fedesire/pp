import java.util.ArrayList;
import java.util.List;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/17 11:15
 */

public class LBT236lowestAncestor {
    /*boolean dfs(TreeNode cur, TreeNode t, List<TreeNode> path)：其中 cur 为当前处理到的节点，t 为需要找到的目的节点，path 为从根节点到当前节点 cur 所经过的路径。若能够以当前节点 cur 为根的子树包含目标节点 t，函数返回 true，否则返回 false。

调用函数分别传入 p 和 q 作为目标节点，从而得到从根节点到 p 和 q 的路径列表，遍历路径找到最后一个相同的节点即是答案。
*/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> a = new ArrayList<>(), b = new ArrayList<>();
        dfs(root, p, a); dfs(root, q, b);
        TreeNode ans = null;
        for (int i = 0; i < Math.min(a.size(), b.size()) && a.get(i) == b.get(i); i++) ans = a.get(i);
        return ans;
    }
    boolean dfs(TreeNode cur, TreeNode t, List<TreeNode> path) {
        if (cur == null) return false;
        path.add(cur);
        if (cur == t || dfs(cur.left, t, path) || dfs(cur.right, t, path)) {
            return true;
        } else {
            path.remove(path.size() - 1);
            return false;
        }
    }


    public static void main(String[] args) {
        TreeNode node = BuildTree.buildTree(new Integer[]{-1,0,3,-2,4,null,null,8});
        TreeNode p=new TreeNode(3);
        TreeNode q=new TreeNode(8);

        System.out.println(new LBT236lowestAncestor().lowestCommonAncestor(node,p,q).val);
    }
}
