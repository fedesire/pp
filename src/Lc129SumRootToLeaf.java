import java.util.ArrayList;
import java.util.List;

/**
 *给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和
 * @date 2023/12/21 19:32
 */
public class Lc129SumRootToLeaf {
    //看sumNumbers3是1.17更好的写法 方便记忆
    private static List<String> paths=new ArrayList<>();
    private static StringBuilder curPath=new StringBuilder();
    public static void getPath(TreeNode root){
        if(root==null)
            return ;
        curPath.append(root.val);
        if(root.left==null&&root.right==null){
            paths.add(curPath.toString());
            curPath.deleteCharAt(curPath.length()-1);
            return ;
        }
        getPath(root.left);//不能和801问题算法一样 在这后面写delete 因为这个问题并不是每次递归结束就
        //一定是得到一个解了可以delete保留的结果了 得到解必须是要到叶子节点 而递归出口还有一个是遇到空
        //节点 比如root是[1,null,2]这种情况 1的左孩子为空 这时候递归结束回到1 但是1不是叶子节点 所以1还不能删
        //801那个问题其实主要也不是树 不是通过.left.right往下遍历 它是直接通过index+1往下遍历的
        //只有一个递归出口 就是index=length 本题有两个递归出口
        getPath(root.right);
        curPath.deleteCharAt(curPath.length()-1);
    }
    public static int sumNumbers(TreeNode root){
        getPath(root);
        int sum=0;
        for(String path:paths){
            sum+=Integer.parseInt(path);
        }
        return sum;
    }

    public static int sumNumbers2(TreeNode root){
        return getSum(root,0);
    }

    //计算当前节点对应的数字 最终的结果通过返回值是int且在return里就做加法完成 不用再额外设立一个list保存所有sum
    public static int getSum(TreeNode root,int preSum){
        if(root==null)
            return 0;
        int sum=preSum*10+root.val;
        if(root.left==null&&root.right==null)
            return sum;
        else
            return getSum(root.left,sum)+getSum(root.right,sum);

    }

    int sum=0;
    StringBuilder path=new StringBuilder();
    public void dfs(TreeNode root){
        if(root==null)
            return ;
        int size=path.length();
        path.append(root.val);
        if(root.left==null&&root.right==null){
            sum+=Integer.parseInt(path.toString());//把valueOf改成parseInt直接从20%提升到100%了
        }
        dfs(root.left);
        dfs(root.right);
        path.delete(size,path.length());
    }
    public void dfs1(TreeNode root,StringBuilder path){
        if(root==null)
            return ;
        int size=path.length();
        path.append(root.val);
        if(root.left==null&&root.right==null){
            sum+=Integer.parseInt(path.toString());//把valueOf改成parseInt直接从20%提升到100%了
        }
        dfs1(root.left,path);
        dfs1(root.right,path);
        path.delete(size, path.length());
    }
    public void dfs2(TreeNode root,String path){
        if(root==null)
            return ;
        StringBuilder sb=new StringBuilder(path);
        sb.append(root.val);
        if(root.left==null&&root.right==null){
            sum+=Integer.parseInt(sb.toString());//把valueOf改成parseInt直接从20%提升到100%了
        }
        dfs2(root.left,sb.toString());
        dfs2(root.right,sb.toString());
    }
    public int sumNumbers3(TreeNode root) {
        dfs(root);
        // 这样也行
//        StringBuilder path=new StringBuilder();
//        dfs1(root,path);

//        dfs2(root,"");
        return sum;
    }

    public static void main(String[] args) {
        Integer[] arr={1,2,3,null,5}; //java数组里可以存放null int数组不行
        TreeNode root=BuildTree.buildTree(arr);
        System.out.println(sumNumbers(root));
    }
}
