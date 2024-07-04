/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/21 20:48
 */
public class Lc98ValidateBST {
    //逻辑错误 每个节点不是只额外和父节点的父节点比较就行 是有上下界的
    public static boolean isBST(TreeNode root){
        if(root==null)
            return false;
        if(root.left==null&&root.right==null)
            return true;
        if((root.left!=null&&root.left.val>=root.val)||(root.right!=null&&root.right.val<=root.val))
            return false;
        return validate(root.left,root.val,true)&&validate(root.right,root.val,false);
    }
    public static boolean validate(TreeNode root,int preRootVal,boolean isLeft){
        if(root.left==null&&root.right==null)
            return true;
        if(root.left!=null){
            if(root.left.val>=root.val||(!isLeft&&root.left.val<=preRootVal))
                return false;
        }
        if(root.right!=null){
            if(root.right.val<=root.val||(isLeft&&root.right.val>=preRootVal))
                return false;
        }
        return validate(root.left,root.val,true)&&validate(root.right,root.val,false);

    }

    public static boolean isValidBST(TreeNode root){
        if(root==null)
            return true;//试验过在lc上这里写true false都AC了 这一句可以不写 所以空树到底是不是BST不重要
        return isValid(root,Long.MIN_VALUE,Long.MAX_VALUE);

    }
    public static boolean isValid(TreeNode root,long lower,long upper){
        if(root==null)
            return true;
        if(root.val>=upper||root.val<=lower)
            return false;
        //调用左子树 那左子树的节点就有上界 调用右子树 右子树的节点就有下界 不要写反了
        return isValid(root.left,lower,root.val)&&isValid(root.right,root.val,upper);
    }
    /*二叉搜索树的中序遍历序列是一个升序序列 所以用中序遍历一遍 判断是否遍历到的每个节点的值都比上一个节点值大*/
    public  boolean isValidBST1(TreeNode root){
        if(root==null)
            return true;
        return dfs(root);

    }
     long last=Long.MIN_VALUE;
    //这里的last不能像之前有些dfs中要获取路径path用stringbuilder保存直接设在方法形参上 ，因为获取路径是不断往sb里添加
    //节点 sb也是可变的 能一直添加 是有效的 这里last就是一个基本类型变量 如果带在方法参数上 进入递归方法里改了之后
    // 出来进入下一轮的递归函数里是见不到的
    public  boolean dfs(TreeNode root){
        if(root==null)
            return true;
        boolean f1=dfs(root.left);
        if (!f1 ||root.val <= last)
            return false;
        last=root.val;
        return dfs(root.right);
    }

    public static void main(String[] args) {
        System.out.println(new Lc98ValidateBST().isValidBST1(new TreeNode(0)));
    }
}
