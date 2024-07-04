/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/21 15:48
 */
public class Lc404SumofLeftLeaves {
    public static int sumofLeftLeaves(TreeNode root){
        if(root==null)
            return 0;

        return getSum(root.left,true)+getSum(root.right,false);
    }
    public static int getSum(TreeNode root,boolean isLeftChild){
        if(root==null)
            return 0;
        if(root.left==null&&root.right==null){
            if(isLeftChild)
                return root.val;
            else return 0;
        }
        int s1=0,s2=0;
        if(root.left!=null)
            s1=getSum(root.left,true);
        if(root.right!=null)
            s2=getSum(root.right,false);
        return s1+s2;
    }
}
