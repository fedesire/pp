import java.util.*;

/**
 * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。与实际答案相差 10-5 以内的答案可以被接受。
 * @date 2024/1/15 10:17
 */
public class LBT637averageOfLevels {
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        List<Double> list=new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int count=queue.size();
            double sum=0;//如果sum定义的是Double类型 就必须初始化为0.0 而且程序运行时间就会慢1ms
            for(int i=0;i<count;i++){
                TreeNode node=queue.remove();
                sum+=node.val;
                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
            }
            list.add(sum/count);

        }
        return list;

    }
}
