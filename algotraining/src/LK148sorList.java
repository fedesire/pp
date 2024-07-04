/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 * @date 2024/2/14 12:06
 */

public class LK148sorList {
    // 归并排序（递归法）
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // fast要先走一步
        ListNode fast = head.next, slow = head;
        // while循环结束后 如果总共是奇数个节点此时slow到达中点，如果总共是偶数个节点此时slow到达中心左边的节点。
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        // 把左右两边切断
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }


    //自顶向下递归的归并排序
    public ListNode sortList(ListNode head) {
        return sortList(head,null);
    }
    //这里最初传入的tail是空节点 并不是链表的最后一个有值的节点 所以这里的排序是包头不包尾 即只对head到tail前一个的所有
    //节点排序 递归的出口是当链表为空或者链表只包含 1个节点时，不需要对链表进行拆分和排序。注意链表只包含一个节点并不是
    //head=tail而是head.next=tail
    public ListNode sortList(ListNode head,ListNode tail){
        if(head==null) return head;
        if(head.next==tail){
            head.next=null;
            return head;
        }
        ListNode fast=head,slow=head;
        while(fast!=tail){
            fast=fast.next;
            slow=slow.next;
            if(fast!=tail)
                fast=fast.next;
        }
        ListNode mid=slow;
        ListNode left=sortList(head,mid);
        ListNode right=sortList(mid,tail);//注意是从mid开始 而不是mid+1
        return merge(left,right);
    }
    public ListNode merge(ListNode left,ListNode right){
        ListNode dh=new ListNode(0);
        ListNode cur=dh,p1=left,p2=right;
        while(p1!=null&&p2!=null){
            if(p1.val<=p2.val){
                cur.next=p1;
                p1=p1.next;
            }
            else{
                cur.next=p2;
                p2=p2.next;
            }
            cur=cur.next;
        }
        if(p1!=null)
            cur.next=p1;
        else if(p2!=null)
            cur.next=p2;
        return dh.next;
    }

    //自底向上迭代的递归排序
    ListNode sortList1(ListNode head){
        if(head==null||head.next==null)
            return head;
        int length=0;
        ListNode node=head;
        while(node!=null){
            length++;
            node=node.next;
        }
        ListNode dummyHead=new ListNode(0);
        dummyHead.next=head;
        /*将 subLength的值加倍，重复第 2 步，对更长的有序子链表进行合并操作，直到有序子链表的长度大于或等于 length
        整个链表排序完毕。*/
        for(int subLength=1;subLength<length;subLength<<=1){
            //找到所有相邻的两个长度为sublength的两个子链表a和b进行合并
            ListNode pre=dummyHead,cur=dummyHead.next;
            while(cur!=null){
                ListNode head1=cur;
                //找到a的尾节点 这样才能继续找b
                for(int i=1;i<subLength&&cur.next!=null;i++){
                    cur=cur.next;
                }
                ListNode head2=cur.next;
                cur.next=null;//确定了b的头结点之后 就要把a尾节点指向空 这样才截断了
                cur=head2;
                if(cur!=null){ //必须先判断 否则下面要访问cur.next可能报空指针异常 这里也可以写在下面 for循环条件里
                    //变成cur!=null&&cur.next!=null cur!=null必须写在前面
                    for(int i=1;i<subLength&&cur.next!=null;i++){
                        cur=cur.next;
                    }
                }

                ListNode next=null;
                if(cur!=null){
                    next=cur.next; //next此时就是下一轮a的起始节点
                    cur.next=null;

                }
                ListNode merged=merge(head1,head2);
                pre.next=merged;
                //循环结束后pre变成当前ab合并后的尾节点 目的是为了下一轮的ab合并之后上一行代码将pre指向合并后的头结点 这样
                //才能连起来
                while(pre.next!=null){
                    pre=pre.next;
                }
                cur=next;
            }
        }
        return dummyHead.next;


    }
}
