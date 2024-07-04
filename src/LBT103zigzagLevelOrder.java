import java.util.*;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，
 * 以此类推，层与层之间交替进行）。
 * @date 2024/1/15 10:48
 */
public class LBT103zigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        if(root==null)
            return res;//必须要有 否则队列里也会出队一个null赋给TreeNode node然后取val就会报空指针异常
        queue.add(root);
        int layer=0;
        while(!queue.isEmpty()){
            int count=queue.size();
            List<Integer> record=new ArrayList<>();
            for(int i=0;i<count;i++){
                TreeNode node=queue.remove();
                record.add(node.val);
                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
            }
            if(res.size()%2==0) //直接不用layer记录 用res.size()判断也可以
//            if(layer%2==0)
                res.add(record);
            else{
                Collections.reverse(record);
                res.add(record);

            }
            layer++;
        }
        return res;

    }

    //不是在最后要加入res时才判断然后反转 而是把保留中间每一层结果的list设为两端都能插入的Linkedlist
    // 利用addLast addFirst达到效果
    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> res=new ArrayList<>();
        Queue<TreeNode> queue=new LinkedList<>();
        if(root==null)
            return res;//必须要有 否则队列里也会出队一个null赋给TreeNode node然后取val就会报空指针异常
        queue.add(root);
        while(!queue.isEmpty()){
            int count=queue.size();
            int layer=res.size();
            LinkedList<Integer> record=new LinkedList<>();
            for(int i=0;i<count;i++){
                TreeNode node=queue.remove();
                if(layer%2==0)
                    record.addLast(node.val);
                else
                    record.addFirst(node.val);
                if(node.left!=null)
                    queue.add(node.left);
                if(node.right!=null)
                    queue.add(node.right);
            }
            res.add(record);
        }
        return res;

    }
}
