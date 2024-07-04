/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 请你将两个数相加，并以相同形式返回一个表示和的链表。你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 每个链表中的节点数在范围 [1, 100] 内
 * @date 2024/1/6 13:40
 */
public class LK2addTwo {

    // 为什么数字按照逆序方式存储 就是因为本身正常计算两数相加的时候也是从后面个位 十位往前计算的 所以这样逆序存储 刚好个位数在前面
    // 就可以直接按照位计算了
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dh=new ListNode(0);
        ListNode p=dh;
        int carry=0;
        while(l1!=null||l2!=null){
            int v1=0,v2=0;
            if(l1!=null){
                v1=l1.val;
                l1=l1.next;

            }
            if(l2!=null){
                v2=l2.val;
                l2=l2.next;

            }
            int x=v1+v2+carry;
            carry=x/10;
            p.next=new ListNode(x%10);
            p=p.next;
        }
        //最后循环结束后 可能还会有进位(比如99+1生成的新链表就有3个节点）所以还要再多生成一位 这里很容易忘
        if(carry!=0){
            p.next=new ListNode(carry);
        }
        return dh.next;

    }

    //lc有一半的测试案例没通过 因为整形溢出了 这道题能表示的数的范围特别大
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        int k=1,s1=0,s2=0,s=0;
        while(l1!=null){
            s1+=l1.val*k;
            k=k*10;
            l1=l1.next;
        }
        k=1;
        while(l2!=null){
            s2+=l2.val*k;
            k=k*10;
            l2=l2.next;
        }
        s=s1+s2;
        if(s==0)
            return new ListNode(0);

        ListNode pre=new ListNode(0);
        ListNode dummyHead,cur=new ListNode(0);
        dummyHead=pre;
        while(s!=0){
            cur=new ListNode(s%10);
            s=s/10;
            pre.next=cur;
            pre=cur;
        }
        cur.next=null;
        return dummyHead.next;

    }


    }
