import com.sun.javafx.image.IntPixelGetter;
import javafx.util.Pair;

import java.util.*;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/16 11:19
 */
public class My604BinaryTreeLevelOrderTraversal {
    public static List<List<Integer>> levelOrderTraversal(TreeNode root){
        //返回结果中不同层的节点在不同的list中 所以res是ArrayList<List<>>
        List<List<Integer>> res=new ArrayList<>();
        if(root==null) //必须要有 否则当root为空时 会报错
            return res;
        LinkedList<Pair<TreeNode,Integer>> queue=new LinkedList<>();
        //访问节点是在该节点出队的时候 在循环前就要将根节点入队 然后循环里就可以按照将节点出队访问即保存值到res中
        // 将左孩子右孩子入队的顺序循环下去
        queue.addLast(new Pair(root,0));
        while(!queue.isEmpty()){
            Pair<TreeNode,Integer> front=queue.removeFirst();
            int level=front.getValue();
            TreeNode node=front.getKey();

            //判断当前节点的层是否是res中已包含的层 相等就说明当前节点肯定在新的层res中还没有 所以要add new
            if(level==res.size())
                res.add(new ArrayList<>());
            res.get(level).add(node.val);

            if(node.left!=null)
                queue.addLast(new Pair<>(node.left,level+1));
            if(node.right!=null)
                queue.addLast(new Pair<>(node.right,level+1));

        }
        return res;
    }

    /*BFS遍历的过程就是一个父亲节点出队 其子节点入队 所以在遍历的过程中常出现队列里有上下两层的节点都在的情况 普通的
    BFS遍历就是在while循环里队头出队一个节点 让其子节点入队这样循环下去处理 所以针对层序遍历的改进就是在while循环里再
    设一个循环 一次性处理掉一层的节点 将其val放在一个list里 如何一次性处理一层 方法就是在每一层第一个节点出队之前
    记录队伍中节点的数量n 此时的n就是所在层的节点数量 之后一次性处理这n个节点 将其子节点入队 直到该层最后一个节点出队
    处理完 此时 队伍中就是下一层的所有节点 这样不断维护
    在每一层遍历开始前，先记录队列中的结点数量 n（也就是这一层的结点数量），然后一口气处理完这一层的 n 个结点。
    * 即在 while 循环的每一轮中，都是将当前层的所有结点出队列，再将下一层的所有结点入队列，这样就实现了层序遍历。*/
    public static List<List<Integer>> levelT(TreeNode root){
        List<List<Integer>> res=new ArrayList<>();
        if(root==null)
            return res;
        LinkedList<TreeNode> queue=new LinkedList<>();

        queue.addLast(root);
        while(!queue.isEmpty()){
            int n=queue.size();
            List<Integer> sameLevelNodes=new ArrayList<>();
            for(int i=0;i<n;i++){
                TreeNode node=queue.removeFirst();
                sameLevelNodes.add(node.val);
                if(node.left!=null)
                    queue.addLast(node.left);
                if(node.right!=null)
                    queue.addLast(node.right);
            }
            res.add(sameLevelNodes);

        }

        return res;

    }
}
