import java.util.*;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/12 9:17
 */
public class LBT199rightview {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        dfs(root,0,res);// 从根节点开始访问，根节点深度是0
        return res;
    }
    public void dfs(TreeNode root,int depth,List<Integer> res){
        if(root==null)
            return ;
        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
        if(depth==res.size()){
            res.add(root.val);
        }
        dfs(root.right,depth+1,res);
        dfs(root.left,depth+1,res);
    }

/*    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> res=new ArrayList<>();
        Stack<TreeNode> stack=new Stack<>();

    }*/


    //BFS层次遍历 记录下每层的最后一个元素
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {  //将当前层的最后一个节点放入结果列表
                    res.add(node.val);
                }
            }
        }
        return res;
    }
    }


