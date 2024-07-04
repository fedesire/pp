import java.util.HashMap;

/**
 * @author xqi
 * @version 1.0
 * @description: 深拷贝带random指针的链表
 * @date 2024/1/8 9:28
 */
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
public class LK138randomCopy {
    //先第一遍遍历原链表 复制每个节点生成新链表的节点 通过hm建立新老链表每个节点之间的映射 第二遍遍历原链表 对新链表每个节点的
    //next random赋值 这种解法新链表明面上看都没有连起来 而是第一遍先一个一个存在hm的values里面 然后第二遍再给next random赋值连起来
    // 对比我自己的写法 这种要生成新链表的题目 上来就按照套路先生成新链表头结点的前虚拟头结点 然后一个一个生成用next连起来

    //难点就在于怎样想到是要建立新老链表每个节点之间的映射 我也想到用hm来解决 但我的hm存的确是新链表的每个节点的索引和节点的映射
    // 想要快速通过索引找到对应的节点 也有是受题目例题里出现的random_index的影响
    public Node copyRandomList(Node head){
        if(head==null)
            return null;
        HashMap<Node,Node> hm=new HashMap<>();
        Node cur=head;
        while(cur!=null){
            hm.put(cur,new Node(cur.val));
            cur=cur.next;
        }
        cur=head;
        while(cur!=null){
            // cur.next是原链表中的节点 hm.get(cur.next)是原链表对应的新链表的节点
            hm.get(cur).next=hm.get(cur.next);
            hm.get(cur).random=hm.get(cur.random);
            cur=cur.next;
        }
        return hm.get(head);

    }

    /*同样用HM辅助来存储新老链表节点之间的映射 只不过是用递归的写法 所以hm要写在函数外面作为全局变量
    * copyRandomList方法的作用可以看成是输入以head为头结点的链表 输出深拷贝后新链表与head对应的节点 即新链表的头结点
    * 因为头结点用next一个一个指向后面的节点 所以返回头结点也就是返回整个链表了
    * 递归的难点在于怎样想明白这个递归函数的作用 因为很容易陷入这个函数就是用来返回深拷贝后的新链表的 想不到他其实也可以
    * 用作返回新链表对应的节点 即输入是头结点 返回的就是新链表的头结点 也可以输入是原链表的第二个节点 输出就是深拷贝完成
    * 后新链表对应的第二个节点
    * */
    HashMap<Node,Node> hm=new HashMap<>();
    public Node copyRandomList1(Node head){
        if(head==null)
            return null;
        Node cur=head;
        if (!hm.containsKey(cur)) {
            Node node = new Node(cur.val);
            hm.put(cur, node);
            node.next = copyRandomList(cur.next);
            node.random = copyRandomList(cur.random);

        }
        return hm.get(head);
    }
    //虽然效率不高 但是也能ac
    public Node copyRandomList2(Node head) {
    Node preHead=new Node(0);
    Node cur=preHead,p=head;
    HashMap<Integer,Node> hm=new HashMap<>();
    int index=0;
    while(p!=null){
        Node node=new Node(p.val);
        hm.put(index++,node);
        cur.next=node;
        p=p.next;
        cur=cur.next;
    }
    cur.next=null;
    cur=preHead.next;
    p=head;
    while(p!=null){
        int pos=0;
        Node randomP=p.random;
        if(randomP==null)
            cur.random=null;
        else{
            while(randomP.next!=null){
                pos++;
                randomP=randomP.next;
            }
            int randomIndex=index-pos-1;
            cur.random=hm.get(randomIndex);
        }
        cur=cur.next;
        p=p.next;
    }
    return preHead.next;
    }

}
