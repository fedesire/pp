/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/8 9:09
 */
public class LK21mergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead=new ListNode(0);
        ListNode cur=dummyHead;

        while(list1!=null&&list2!=null){
            if(list1.val<=list2.val){
                cur.next=new ListNode(list1.val);
                list1=list1.next;
            }
            else{
                cur.next=new ListNode(list2.val);
                list2=list2.next;
            }
            cur=cur.next;
        }
        cur.next=list1==null?list2:list1;
        return dummyHead.next;

    }

    //用递归的方式完成合并
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if(list1==null)
            return list2;
        else if(list2==null)
           return list1;
        // 如果list1.val<=list2.val 说明list1 list2合并的结果应该是list1当前的节点指向list1.next和list2合并后的链表
        else if(list1.val<=list2.val){
            list1.next=mergeTwoLists2(list1.next,list2);
            return list1;
        }
        else{
            list2.next=mergeTwoLists2(list1,list2.next);
            return list2;
        }
    }

    }
