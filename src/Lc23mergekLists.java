import java.util.List;
import java.util.PriorityQueue;

/**
 * @author xqi
 * @version 1.0
 * @description: 给你一个链表数组，每个链表都已经按升序排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 要从所有链表节点中找出最小的那个 并且移除了一个链表节点后 对剩余的所有链表节点还要能快速的找到最小的那个 所以使用优先队列
 * 元素删掉之后 还会自动排序
 * @date 2023/12/20 22:14
 */
public class Lc23mergekLists {
    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status status2) {
            return this.val - status2.val;
        }
    }
    PriorityQueue<Status> queue = new PriorityQueue<Status>();
    PriorityQueue<ListNode> queue1 = new PriorityQueue<ListNode>((e1,e2)->(e1.val-e2.val));
    public ListNode mergeKLists(ListNode[] lists) {
        for (ListNode node: lists) {
            if (node != null) { //因为传入的lists可能是[]所以要判断
                queue1.offer(node);
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue1.isEmpty()) {
            ListNode f = queue1.poll();
            tail.next = f;
            tail = tail.next;
            if (f.next != null) {
                queue1.offer(f.next);
            }
        }
        return head.next;
     /*   for (ListNode node: lists) {
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }*/

    }

    public static void main(String[] args) {

    }
}
