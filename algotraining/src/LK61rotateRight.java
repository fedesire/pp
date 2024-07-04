/**
 * @author xqi
 * @version 1.0
 * @description: 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * @date 2024/1/23 11:33
 */
public class LK61rotateRight {
    public ListNode rotateRight(ListNode head, int k) {
        if(k==0||head==null||head.next==null)
            return head;
        ListNode dh=new ListNode(0);
        dh.next=head;
        ListNode cur=head;
        int count=0;
        while(cur!=null){
            count++;
            cur=cur.next;
        }
        if(k>=count)
            k=k%count;
        if(k==0)
            return head;
        cur=dh;
        for(int i=0;i<k+1;i++)
            cur=cur.next;
        ListNode p=dh;
        while(cur.next!=null){
            cur=cur.next;
            p=p.next;
        }
        p=p.next;//此时p是倒数k+1个节点 p.next是倒数k节点即旋转后的头结点
        dh.next=p.next;
        p.next=null;
        cur.next=head;
        return dh.next;
    }
}
