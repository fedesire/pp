/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为
 * 回文链表
 * 。如果是，返回 true ；否则，返回 false
 * @date 2024/5/8 8:43
 */

class _LK234PalindromeLink {
    public boolean isPalindrome1(ListNode head) {
        ListNode slow=head,fast=head;
        ListNode pre=null,next;
        // 主要是链表反转的代码 但和平常的链表反转不一样 这里只反转前半部分所以循环条件不一样 用快慢指针的方式找到中点
        while(fast!=null&&fast.next!=null){
            // 必须先写fast=fast.next.next; 不能把这句写在反转逻辑的后面 否则反转后节点变了就没用了
            fast=fast.next.next;

            next=slow.next;
            slow.next=pre;
            pre=slow;
            slow=next;
        }
        // 链表反转结束后pre是前半部分反转后的头结点 通常反转后slow会是空 但这里不是反转全部 所以slow还是指向后面的节点
        // 此时如果本来链表节点个数是偶数slow就是后半部分的头节点 如果本来链表节点个数是奇数 此时slow就是那个中间节点
        // 用案例1221 和121试一下 当121的时候此时fast到最后的1的时候循环就结束了 slow指向2 此时fast！=null
        // 所以下面就是判断fast!=null的时候slow=slow.next
        if(fast!=null)
            slow=slow.next;

        while(slow!=null){
            if(slow.val!=pre.val)
                return false;
            slow=slow.next;
            pre=pre.next;
        }
        return true;

    }
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    // 最终返回的slow是前半部分链表的尾节点 当原本链表节点数是奇数时 此时slow就是123中的2 这个中间节点可以看作是前半部分的节点
    // 所以slow是前半部分链表的尾节点一直成立
    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        // 如果这里循环的条件是fast!=null&&fast.next != null 那么循环结束后slow就是后半部分链表的头节点 此时如果链表节点数是奇数 那么slow就是123中的3 这个中间节点
        // 123中的2就可以视作是后半部分的节点
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
