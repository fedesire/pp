/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/8 10:55
 */
public class LK92reverseLK {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left==right)
            return head;
        ListNode dummyHead=new ListNode(0);
        dummyHead.next=head;
        int index=1;
        ListNode cur=head;
        ListNode preCur=dummyHead;
        while (index<left){
            cur=cur.next;
            preCur=preCur.next;
            index++;
        }//结束后cur来到了left位置 preCur一直是curd前一个节点
        ListNode preLeft=preCur;//记录precur 即left前一个节点
        while (index<=right){
            ListNode next=cur.next;
            cur.next=preCur;
            preCur=cur;
            cur=next;
            index++;
        }//循环里完成left到right的反转 循环结束后cur来到了right后一个位置 preCur来到了right位置
        ListNode nodeAtLeft=preLeft.next;//preLeft.next是原本待反转区域的头结点即left位置的节点 现在是待反转区域完成反转后的尾结点
        nodeAtLeft.next=cur;
        preLeft.next=preCur;//preCur现在是待反转区域完成反转后的头结点 所以将left前一个节点的next指向它
        return dummyHead.next;
    }

    public ListNode reverseBetween1(ListNode head,int left,int right){
        if(left==right)
            return head;
        ListNode dummyHead=new ListNode(0);
        dummyHead.next=head;
        ListNode cur=head,preCur=dummyHead;
        int index=1;
        while(index<left){
            cur=cur.next;
            preCur=preCur.next;
            index++;
        }
        ListNode leftNode=cur,preLeft=preCur;
        while(cur!=null&&index<=right){
            ListNode next=cur.next;
            cur.next=preCur;
            preCur=cur;
            cur=next;
            index++;
        }// cur来到了right后一个位置 preCur来到了right位置
        preLeft.next=preCur;
        leftNode.next=cur;
        return dummyHead.next;
    }
}
