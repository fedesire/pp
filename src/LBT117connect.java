import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/2/4 10:45
 */
public class LBT117connect {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public Node connect(Node root) {
        if(root==null)
            return root;
        Queue<Node> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            Node pre=null;
            for(int i=0;i<size;i++){
                Node node=queue.remove();
                node.next=pre;
                if(node.right!=null)
                    queue.add(node.right);
                if(node.left!=null)
                    queue.add(node.left);
                pre=node;
            }
        }
        return root;
    }

    /*利用已建立好的next指针 如果第i层的next指针已建立好 那么遍历第i层的节点的时候就不需要用队列了 直接用
    * next指针就能找到所有 同时还能通过left right访问下一层的节点 因此在遍历第i层节点的过程中就可以按顺序
    * 为第 i+1层节点建立 next指针。*/
    public Node connect1(Node root){
        if(root==null)
            return null;
        //cur可以看作是每一层的链表
        Node cur=root;
        while(cur!=null){
            //遍历当前层的时候 为了方便操作在下一层前面添加一个哑巴节点（注意这里是访问当前层即cur层的节点
            //同时将下一层的节点串起来） 为下一层节点依次建立next指针 即把下一层连起来是要让下一层的每个节点的
            // next指针指向其下一个 这不好操作 因为下一个节点是谁不确定
            //因此可以转变思路变成保存上一个节点 每次将上一个节点的next指向当前节点 所以设哑巴节点 下一层的
            //第一个节点的上一个节点就是哑巴节点
            Node dummy=new Node(0);
            //pre表示下一层节点的前一个节点
            Node pre=dummy;
            //开始遍历当前层的链表
            while(cur!=null){
                if(cur.left!=null){
                    pre.next=cur.left;
                    pre=pre.next;
                }
                if(cur.right!=null){
                    pre.next=cur.right;
                    pre=pre.next;
                }
                cur=cur.next;
            }
            //上面的while循环结束后就完成了把下一层串联成一个链表 更新后cur变成下一层的第一个节点 这样继续循环
            cur=dummy.next;
        }
        return root;
    }

}
