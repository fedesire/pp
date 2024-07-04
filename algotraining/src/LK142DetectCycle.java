import java.util.HashSet;

/**
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * @date 2024/5/9 11:13
 */

public class LK142DetectCycle {
    // https://leetcode.cn/problems/linked-list-cycle-ii/solutions/12616/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/?envType=study-plan-v2&envId=top-100-liked
    // 链表入环前有a个节点 环里有b个节点 使用快慢指针在包含环的链表中走就是先走a 之后就一直在环里转圈 快慢指针相遇说明此时快指针
    // 走的步数一定比慢指针走的多n个环 即f=s+nb 由于快慢指针也是一个走两步 一个走一步 所以f=2s 所以 s=nb f=2nb
    // 说明快慢指针相遇时慢指针走了n个环的周长 快指针走了2n个环的周长
    // 所有走到链表入口节点时的步数k=a+nb 现在慢指针已经走了nb了 只要让慢指针再走a就行 所以再用一个指针指向链表头节点 和慢指针一起走知道相遇即可
    public ListNode detectCycle(ListNode head) {
        // 这里fast和slow是同时指向head 不是fast指向head.next了
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    public ListNode detectCycle1(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode p=head;
        while(p!=null){
            if(set.contains(p))
                return p;
            set.add(p);
            p=p.next;
        }
        return null;
    }
}
