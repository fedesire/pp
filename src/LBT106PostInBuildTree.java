import java.util.HashMap;
import java.util.Map;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/18 10:59
 */
public class LBT106PostInBuildTree {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer,Integer> hm=new HashMap<>();
        int m=inorder.length,n=postorder.length;
        for(int i=0;i<m;i++){
            hm.put(inorder[i],i);
        }
        return buildTreeInRange(inorder,0,m-1,postorder,0,n-1,hm);

    }
    public TreeNode buildTreeInRange(int[] inorder,int inLeft,int inRight,int[] postorder,int postLeft,int postRight,Map<Integer,Integer> hm){
        if(inLeft>inRight||postLeft>postRight)
            return null;

        int rootVal=postorder[postRight];
        TreeNode root=new TreeNode(rootVal);
        int index=hm.get(rootVal);
        int x=postRight-inRight+index;//x是右子树在中序序列中的第一个节点的下标 newpostleft
        root.left=buildTreeInRange(inorder,inLeft,index-1,postorder,postLeft,x-1,hm);
        root.right=buildTreeInRange(inorder,index+1,inRight,postorder,x,postRight-1,hm);
        return root;
    }
}
