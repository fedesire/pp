import java.util.HashMap;
import java.util.Map;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/2/11 12:00
 */
public class LBT230KthSmallest {
    int count=0;
    int res=-1;
    public int kthSmallest(TreeNode root, int k) {
        inorder(root,k);
        return res;

    }
    public void inorder(TreeNode root,int k){
        if(root!=null){
            inorder(root.left,k);
            count++;
            if(count==k){
                res=root.val;
                return ;
            }
            inorder(root.right,k);
        }
    }

    /*如果需要频繁地查找第 k小的值，你将如何优化算法 在方法一中，我们之所以需要中序遍历前 k个元素，是因为我们不知道
    子树的结点数量，不得不通过遍历子树的方式来获知。因此，我们可以记录下以每个结点为根结点的子树的结点数，并在查找第k小
    的值时，根据当前根节点的左节点的子树的节点数量确定是在左子树右子树继续搜索还是直接已经获得答案了*/
    class MyBST{
        TreeNode root;
        Map<TreeNode,Integer> nodeNum;
        public MyBST(TreeNode root){
            this.root=root;
            nodeNum=new HashMap<>();
            countNodeNum(root);
        }

        // 统计以node为根结点的子树的结点数(节点数包括node节点）
        int countNodeNum(TreeNode node){
            if(node==null) return 0;
            nodeNum.put(node,1+countNodeNum(node.left)+countNodeNum(node.right));
            return nodeNum.get(node);
        }
    }
    public int kthSmallest1(TreeNode root,int k){
        MyBST bst=new MyBST(root);
        while(root!=null){
            //因为上面创建BST时通过countNodeNum统计每个节点子树节点数量时 如果左右子树是null 就不会加入map里了
            //这里从map里取值时是有可能节点为null的 所以用getOrDefault
            int leftNum=bst.nodeNum.getOrDefault(root.left,0);
            if(leftNum==k-1) break;
            else if(leftNum<k-1){
                root=root.right;//在右子树中查找 查找的元素数要减去左子树数量leftNum和一个根节点
                k=k-leftNum-1;
            }
            else{
                root=root.left;//在左子树中继续查找
            }
        }
        return root.val;

    }
}
