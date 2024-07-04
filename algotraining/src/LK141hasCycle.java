import java.util.HashSet;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/6 10:27
 */
public class LK141hasCycle {
    public boolean hasCycle(ListNode head) {
        if(head==null||head.next==null)
            return false;
        // 规定初始时慢指针在位置 head，快指针在位置 head.next，而不是两个指针都在位置 head是因为循环条件有slow!=fast
        // 如果初始时fast也指向head 就不会进入循环体了
        ListNode slow=head,fast=head.next;
        while(fast!=null&&fast.next!=null&&slow!=fast){
            //注意这里必须是fast和fast.next都要判断 但是二者的原因不同 因为循环里用到了fast.next.next 要想能够访问
            // fast.next.next 所以必须先保证fast.next!=null 而判断fast!=null是因为当fast=null结果就出来了 循环就可以结束了
            //fast每次走两步 有些情况下fast是先走到尾节点 fast.next=null循环就可以结束了 因为一次走两步有些情况下
            // 不会走到尾节点 而是fast走到倒数第二个节点(经过循环体更新后下次就直接走到null了) 此时可以进入循环 但经过这一轮
            // fast=fast.next.next后fast就是空 此时就不能继续进行下一次循环了 所以就不能只有fast.next判空 必须先判断
            // fast是否为null 通常情况下是每次只走一步先到倒数第二节点 再是尾节点 然后到null循环结束
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow==fast;
    }

    public boolean hasCycle1(ListNode head){
        if(head==null||head.next==null)
            return false;
        ListNode slow=head,fast=head.next;
        while(slow!=fast){
            if(fast==null||fast.next==null)
                return false;
            slow=slow.next;
            fast=fast.next.next;
        }
        return true;
    }
    public boolean hasCycle2(ListNode head){
        if(head==null||head.next==null)
            return false;
        HashSet<ListNode> record=new HashSet<>();
        while(head!=null){
            if(record.contains(head))
                return true;
            record.add(head);
            head=head.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode h1=new ListNode(1);
        ListNode h2=new ListNode(2);
        h1.next=h2;
        ListNode head=h1;
        HashSet<ListNode> hs=new HashSet<>();
        hs.add(head);
        head.val=3; //head.val改变会导致hs中加入的node的val也变了 但head指向head.next不会导致hs中节点变 不论是head指向head.next
        //还是head指向一个新的节点 都是将head变量指向新的一个地址 而hs中加入的元素还指向原来的地址
        head=new ListNode(9);
//        head=head.next;
        hs.add(head);
        System.out.println(hs);
    }
}
