import java.util.ArrayList;
import java.util.List;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/17 11:14
 */
public class My704BinaryTreePaths {

    public static List<String> binaryTreePaths3(TreeNode root) {
        ArrayList<String> res = new ArrayList<String>();
        constructPaths(root,"",res);
        return res;

    }
    //这种方法定义时就用string 而不是stringbuilder就能避免回溯时还要delete
    public static void constructPaths(TreeNode root,String path,List<String> res){
        if(root!=null){
            StringBuilder sb=new StringBuilder(path);
            sb.append(root.val);
            if(root.left==null&&root.right==null){
                //这里不写return可以 因为下面写了else就不会走到下面分支了（其实走到了也没事 因为函数调用时传递的是string
                res.add(sb.toString());
            }
            else {
                sb.append("->");
                constructPaths(root.left,sb.toString(),res);
                constructPaths(root.right,sb.toString(),res);
            }
        }
/*        if(root!=null){
            path=path+root.val;
            if(root.left==null&&root.right==null){
                res.add(path);
            }
            else {
                path=path+"->";
                constructPaths(root.left,path,res);
                constructPaths(root.right,path,res);
            }

        }*/
    }
    // 只用一个递归方法来解决 和平常用的那种方法里面调用一个dfs递归方法不同 这里的递归方法是有返回值的 平常的dfs方法
    // 返回值是空 这里就是通过这个递归方法返回值来传递中间结果 所以这里方法的入参不用传递中间结果 也没有一个全局的res
    public static List<String> treePaths(TreeNode root){
        List<String> res=new ArrayList<>();
        if(root==null)
            return res;
        //必须{}里有return res 否则到这里递归就没有终止 还会往下执行遇到.left就会出错
        if(root.left==null&&root.right==null){
            res.add(Integer.toString(root.val));
            return res;
        }
        List<String> leftPaths=treePaths(root.left);
        for(String path:leftPaths){
            //必须要加In.toString 虽然StringBuilder构造函数也包含可以传入int类型 但此时该int指的是capacity
            StringBuilder s=new StringBuilder(Integer.toString(root.val));
            s.append("->");
            s.append(path);
            res.add(s.toString());
        }
        List<String> rightPaths=treePaths(root.right);
        for(String path:rightPaths){
            StringBuilder s=new StringBuilder().append(root.val);
            s.append("->");
            s.append(path);
            res.add(s.toString());
        }
        return res;
    }

    public static List<String> binaryTreePaths1(TreeNode root) {

        ArrayList<String> res = new ArrayList<String>();


        if (root == null)
            return res;

        if (root.left == null && root.right == null) {
            res.add(Integer.toString(root.val));
            return res;
        }
        StringBuilder record=new StringBuilder(Integer.toString(root.val));
        StringBuilder record1=new StringBuilder(Integer.toString(root.val));
        dfs(root.left,res,record);
        dfs(root.right,res,record1);
        return res;

    }

    public static void dfs(TreeNode root,ArrayList<String> res,StringBuilder record){
        if(root==null)
            return ;
        record.append("->");
        record.append(root.val);
        if(root.left==null&&root.right==null){
            res.add(record.toString());
            return ;
        }

        dfs(root.left,res,new StringBuilder(record));
        dfs(root.right,res,new StringBuilder(record));
    }

    static List<String> res = new ArrayList<String>();
    static StringBuilder sb=new StringBuilder();

    public static List<String> binaryTreePaths2(TreeNode root) {
        dfs1(root);
        return res;
    }

    public static void dfs1(TreeNode root) {
        if (root != null) {
            int size = sb.length();
            if (root.left == null && root.right == null) {
                res.add(String.valueOf(sb) + root.val);
                return;
            }
            sb.append(root.val).append("->");
            dfs1(root.left);
            dfs1(root.right);
            sb.delete(size, sb.length());

        }
    }
        public static void dfs2(TreeNode root){
            if(root!=null){
                int size=sb.length();
                sb.append(root.val);
                //上面先append 那这里就不能提前return了 必须还要经过下面的delete把root.val去掉才行
                if(root.left==null&&root.right==null){
                    res.add(sb.toString());
                }
                sb.append("->");
                dfs2(root.left);
                dfs2(root.right);
                sb.delete(size,sb.length());
            }
    }


        public static void main(String[] args) {
        Integer[] arr={1,2,3,7,5,null,null}; //java数组里可以存放null int数组不行
        TreeNode root=BuildTree.buildTree(arr);
        System.out.println(treePaths(root));
        System.out.println(binaryTreePaths3(root));
    }
}
