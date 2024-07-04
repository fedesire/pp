/**
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表
 * [1,2,3,3,4,4,5]返回的是[1,2,5]
 * @date 2024/1/18 11:52
 */
public class LK82removeDupl { //注意所有重复都要删 不是让剩下的节点没有重复就行
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null||head.next==null)
            return head;
        ListNode dh=new ListNode(0);
        dh.next=head;
        ListNode cur=dh,p1,p2;
        while(cur.next!=null&&cur.next.next!=null){
            p1=cur.next;
            p2=p1.next;
            if(p1.val==p2.val){
                while(p2!=null&&p1.val==p2.val){
                    p1.next=p2.next;
                    p2=p1.next;
                }
                cur.next=p2;
            }
            else{
                cur=cur.next;
            }
        }
        return dh.next;
    }
    //和我的思路其实一样 只是更精简 少了一些中间变量 还是就按我自己的来 他这个少写变量 把我都绕懵了
    public ListNode removeDup1(ListNode head){
        if(head==null)
            return head;
        ListNode dh=new ListNode(0);
        dh.next=head;
        ListNode cur=dh;
        while(cur.next!=null&&cur.next.next!=null){
            if(cur.next.val==cur.next.next.val){
                int x=cur.next.val;
                while(cur.next!=null&&cur.next.val==x){
                    cur.next=cur.next.next;
                }

            }
            else
                cur=cur.next;
        }
        return dh.next;

    }

    public static void main(String[] args) {
        System.out.println();
    }
}
