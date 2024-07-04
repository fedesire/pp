/**
 * 两两交换链表中的节点  给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下
 * 完成本题（即，只能进行节点交换）。
 * @date 2024/1/7 10:52
 */
public class My504swapNodes {
    public ListNode swapNodes(ListNode head){
        if(head==null||head.next==null)
            return head;
        ListNode n1=head;
        ListNode n2=n1.next;
        //必须先这一步 不能先写下一步 否则先n2.next改了再进入递归就重复卡死了 不过如果先把n2.next保存起来就可以
        n1.next=swapNodes(n2.next);
        n2.next=n1;
        return n2;
    }
    public ListNode swapNodes2(ListNode head) {
        if(head==null||head.next==null)
            return head;
        ListNode dummyHead=new ListNode(0);
        dummyHead.next=head;
        ListNode pre=dummyHead;
        while(pre.next!=null&&pre.next.next!=null){
            ListNode n1=pre.next;
            ListNode n2=n1.next;
            ListNode n=n2.next;
            n2.next=n1;
            n1.next=n;//n1.next指向n其实最终n1.next并不是指向n 这一步只是为了后面的pre变成n1后开启下一循环 链表能够连下去不会断掉
            //n1.next会在下一轮循环经由pre最终指向正确的节点
            pre.next=n2;
            pre=n1;
        }
        return dummyHead.next;
    }
    //上面的解法是用pre移动来遍历链表 这里是用first second移动
    public ListNode swapNodes3(ListNode head) {
        if(head==null||head.next==null)
            return head;
        ListNode first=head,second=head.next;
        ListNode h=second;
        //在循环体里已经把两种情况都考虑过然后return了 一种是总共有偶数个数 所以next置为null 一种是总共有奇数个数 本来first.next
        // 指向nextFirst.next 此时nextFirst.next=null 所以直接first.next指向nextFirst就可以结束循环了
        //如果在while条件里写判断 反而容易出错
        while(true){
            ListNode nextFirst=second.next;
            second.next=first;
            if(nextFirst==null){
                first.next=null;
                return h;
            }
            else if(nextFirst.next==null){
                first.next=nextFirst;
                return h;
            }
            else{
                first.next=nextFirst.next;
                first=nextFirst;
                second=nextFirst.next;
            }
        }
    }
    }
