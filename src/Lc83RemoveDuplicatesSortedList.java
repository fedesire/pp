/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/14 16:23
 */
public class Lc83RemoveDuplicatesSortedList {
    public static ListNode removeDuplicte(ListNode head){
        ListNode cur=head;
        while(cur!=null){
            ListNode temp=cur;
            while(temp.next!=null && temp.next.val==temp.val) //注意这里容易忘记要先判断temp.next是否为null
                temp=temp.next;
            cur.next=temp.next;
            cur=cur.next;
        }
        return head;
    }
    //用一个while循环就可以了 无需当cur.next.val==cur.val时就立马用while循环找到下一个不相等的节点
    //只要这一轮做完了之后cur不变 下一轮循环还是比较当前的cur和上一轮过后新的cur.next
    public static ListNode remove2(ListNode head){
        ListNode cur=head;
        while(cur!=null&&cur.next!=null){ //这里只有cur.next!=null也可以 只需要先提前判断一下head是否为null 将等于null的情况先return
            if(cur.next.val==cur.val)
                cur.next=cur.next.next;
            else
                cur=cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] arr={1,2,2,2,3};
        ListNode head=new ListNode(arr);
        remove2(head);
        System.out.println(head);
    }
}
