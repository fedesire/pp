import java.util.HashMap;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/16 10:42
 */
public class LBT105PreInBuildTree {
    //存储中序序列里每个元素的索引位置 方便后期查找前序序列里某个元素在中序序列里的位置
    HashMap<Integer,Integer> inOrderIndex=new HashMap<>();
    public TreeNode buildTree(int[] preOrder,int[] inOrder){
        int n=inOrder.length;
        for (int i = 0; i < n; i++) {
            inOrderIndex.put(inOrder[i],i);
        }
        return buildTreeInRange(preOrder,0,n-1,inOrder,0,n-1);


    }
    public TreeNode buildTreeInRange(int[] preOrder,int preLeft,int preRight,int[] inOder,int inLeft,int inRight){
        if(preLeft>preRight||inLeft>inRight)
            return null;
        int val=preOrder[preLeft];
        TreeNode root=new TreeNode(val);
        int pivotIndex=inOrderIndex.get(val);//当前根节点在中序序列中的位置
        /*temp是当前根节点的左子树的在前序遍历中的最后一个节点的位置 因为根节点在中序序列中的位置i已经知道了 那i前面的就是左子树
        * 的节点 i后面的就是右子树的节点 左右子树节点的个数都知道后 那同样的就能知道前序序列中左右子树节点是从哪开始哪结束
        * temp不需要记 记也记不住 遇到了画个图*/
        int temp=pivotIndex-inLeft+preLeft;

        root.left=buildTreeInRange(preOrder,preLeft+1,temp,inOder,inLeft,pivotIndex-1);
        root.right=buildTreeInRange(preOrder,temp+1,preRight,inOder,pivotIndex+1,inRight);
        return root;

    }
}
