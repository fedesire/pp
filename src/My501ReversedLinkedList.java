/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/14 14:02
 */
 class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int[] nums) {
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("arrays can't be empty");
        this.val = nums[0];
        ListNode cur = this;
        for (int i = 1; i < nums.length; i++) {
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
//            this=this.next; //编译报错 this.val可以赋值 this不行 不是变量

        }
    }

    @Override
    public String toString() {
        StringBuilder s=new StringBuilder(""); //构造函数里是空或者""都行
        ListNode cur=this;
        while(cur!=null){
            s.append(Integer.toString(cur.val)); //不加直接写val也可以 因为append是支持char int char[]等很多参数的
            s.append("-->");
            cur=cur.next;
        }
        s.append("Null");
        return s.toString();
    }
}
public class My501ReversedLinkedList {
    //反转链表分为4步 最核心的就是将当前节点cur的next指针指向前一个节点 所以在这之前要先保存cur.next避免让后面的节点断了
    // 指针反转之后的两步就是更新pre节点 next节点
    //反转链表算法里的两个要点 一是next节点如果定义在一开始是不能直接给他赋值为cur.next 要先判断是否为空
    //二是最终返还的是pre而不是cur

    public static ListNode reverseLinkdedList(ListNode head){
        ListNode pre=null,cur=head;
        while(cur!=null){
            ListNode next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
    }

//    对于递归算法，最重要的就是明确递归函数的定义。具体来说，我们的 reverse 函数定义是这样的：
//    输入一个节点 head，将「以 head 为起点」的链表反转，并返回反转之后的头结点。
    public static ListNode reverseByRecursive(ListNode head){
        if(head==null||head.next==null)
            return head;
        //以head.next为起点的链表已经反转了 下面的操作就是处理当前head节点
        ListNode reverseHead=reverseByRecursive(head.next);
        head.next.next=head;//经过上一步head.next变成了反转后的链表的尾节点 所以将他的next指向head head就加入反转队伍
        // 变成了尾节点 下一步再将head.nex置为空 注意返回的是last
        head.next=null;
        return reverseHead;
    }
    public static void main(String[] args) {
        int[] arr={3,6,6,4,75};
        ListNode head=new ListNode(arr);
        System.out.println(head);
        ListNode head2=reverseLinkdedList(head); //一定要保存调用方法后返回的ListNode 因为原本的head现在只剩一个节点
        System.out.println(head2);
    }

}
