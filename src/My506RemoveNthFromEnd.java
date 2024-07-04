/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/14 21:20
 */
    public class My506RemoveNthFromEnd {
        public static ListNode removeNthFromEnd(ListNode head,int n){
            ListNode dummyHead=new ListNode(0);
            dummyHead.next=head;
            ListNode p=dummyHead,q=dummyHead;
            for (int i = 0; i < n + 1; i++) {
                assert q != null;
                q=q.next;
            }
            while(q!=null){
                p=p.next;
                q=q.next;
            }
            p.next=p.next.next;
            return dummyHead.next;
        }
}
