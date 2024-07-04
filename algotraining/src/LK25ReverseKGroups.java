/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/5/10 10:46
 */
public class LK25ReverseKGroups {
    // 本来题解方法的含义是把head节点开始的链表k个一组反转 现在用递归求解就转变成含义是从head开始 反转k个节点
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null||head.next==null)
            return head;
        ListNode tail=head;
        // for循环结束后 tail就是此次要反转的尾节点的后一个节点 也是下一轮要反转的起始节点
        for (int i = 0; i < k; i++) {
            //剩余数量小于k的话，则不需要反转
            if(tail==null)
                return head;
            tail=tail.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(head, tail);
        //下一轮的开始的地方就是tail head是反转前的头节点即反转后的尾节点
        head.next = reverseKGroup(tail, k);

        return newHead;
    }

    /*
     左闭又开区间
      */
    private ListNode reverse(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode next = null;
        while (head != tail) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;

    }
    public static void main(String[] args)
    {
        ListNode head=new ListNode(1);
        head.next=new ListNode(2);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(4);
        head.next.next.next.next=new ListNode(5);
        LK25ReverseKGroups lk25ReverseKGroups=new LK25ReverseKGroups();
        ListNode newHead=lk25ReverseKGroups.reverseKGroup(head,2);
        while(newHead!=null){
            System.out.print(newHead.val+" ");
            newHead=newHead.next;
        }
    }
}
