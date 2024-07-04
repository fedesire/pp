import java.util.HashSet;

/**
 * @author xqi
 * @version 1.0
 * @description 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
 * @date 2024/5/8 8:19
 */
public class LK160GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> hs=new HashSet<>();
        while(headA!=null){
            hs.add(headA);
            headA=headA.next;
        }
        while(headB!=null&&!hs.contains(headB)){
            headB=headB.next;
        }
        return headB;

    }
    // 将2个链表在末尾加上对方，碰到第一个相同的点，要么是交点，要么是末尾null
    // 比如链表1 链表2相交后有c个共同的节点 在相交前1有a个节点 2有b个节点 所以1是走a c b 2是走b c a 此时走到相同的位置
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode p1=headA;
        ListNode p2=headB;
        while(p1!=p2){
            p1=p1==null?headB:p1.next;
            p2=p2==null?headA:p2.next;
        }
        return p1;
    }
}
