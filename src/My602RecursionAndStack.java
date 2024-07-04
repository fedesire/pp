import java.util.ArrayList;
import java.util.List;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/16 8:46
 */

public class My602RecursionAndStack {
    //因为lc上要求的是传入参数只有root节点 返回结果集 所以要再写一个函数 如果要求的是直接打印
    // 就可以直接一个函数就搞定 更新：一个函数也能搞定 只是要把结果集定义成全局变量
    public static List<Integer> inorderTravesal(TreeNode root){
        List<Integer> res=new ArrayList<>();
        inorderTravesal(root,res);
        return res;
    }
    public static void inorderTravesal(TreeNode node,List<Integer> list){
        if(node!=null){
            inorderTravesal(node.left,list);
            list.add(node.val);
            inorderTravesal(node.right,list);
        }
    }

    public static List<Integer> res=new ArrayList<>();
    public static List<Integer> inorderTravesal1(TreeNode root){
        if(root!=null){
            inorderTravesal1(root.left);
            res.add(root.val);
            inorderTravesal1(root.right);
        }
        return res;
    }
    public static void main(String[] args) {
        TreeNode root=new TreeNode(1,new TreeNode(2,new TreeNode(4),new TreeNode(5)),new TreeNode(3,new TreeNode(6),new TreeNode(7)));
        List<Integer> list=new ArrayList<>();
//        inorderTravesal(root,list);
        list=inorderTravesal1(root);
        System.out.println(list);
    }

}
