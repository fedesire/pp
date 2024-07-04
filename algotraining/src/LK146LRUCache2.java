import java.util.HashMap;

/**
 * 实现过程记录：不用设置size 用map.size()即可 DlinkNode无参构造函数和有参构造函数都要有
 * 无参构造函数是因为LK146LRUCache2构造函数里head tail创建的时候就是没有key value 只设置指针
 * 有参构造函数是因为要put时需要new DlinkNode(key,value)
 * LK146LRUCache2构造函数里记得写cache的创建初始化
 * put get主要涉及到的辅助函数就是removeNode addToHead
 *
 * 有一个node链表就能实现有先后顺序的存储 但是只有一个node链表 无法快速的根据key获取值 也无法快速移动
 * 所以为了实现通过key就能找到node 加入hashmap key对应的value里存的就是node
 * @date 2024/4/9 8:19
 */
public class LK146LRUCache2 {
    class DlinkNode{
        int key;
        int value;
        DlinkNode pre;
        DlinkNode next;
        DlinkNode(int key,int value)
        {
            this.key=key;
            this.value=value;
        }
        DlinkNode(){}
    }
    private int capacity;
    // 维护虚拟头结点 尾节点 因为加入一个新节点或更改节点值都是要移到头部的 容量不足要删除是要删除尾部的
    private DlinkNode head;
    private DlinkNode tail;
    private HashMap<Integer,DlinkNode> cache;
    public LK146LRUCache2(int capacity){
        this.capacity=capacity;
        cache=new HashMap<>();
        head=new DlinkNode();
        tail=new DlinkNode();
        head.next=tail;
        tail.pre=head;
    }
    public void put(int key,int value)
    {
        if(cache.containsKey(key)){
            DlinkNode node = cache.get(key);
            node.value=value;
            removeNode(node);
            addToHead(node);
            return ;
        }
        DlinkNode newNode=new DlinkNode(key,value);
        cache.put(key,newNode);
        addToHead(newNode);
        if(cache.size()>capacity){
            DlinkNode delNode=tail.pre;
            cache.remove(delNode.key);
            removeNode(delNode);
        }

    }

    private void addToHead(DlinkNode node) {
        DlinkNode oldFirst=head.next;
        head.next=node;
        node.next=oldFirst;
        oldFirst.pre=node;
        node.pre=head;
    }

    private void removeNode(DlinkNode node) {
        DlinkNode pre=node.pre;
        DlinkNode next=node.next;
        pre.next=next;
        next.pre=pre;
    }

    public int get(int key){
        if(!cache.containsKey(key))
            return -1;
        DlinkNode node=cache.get(key);
        removeNode(node);
        addToHead(node);
        return node.value;
    }
    public static void main(String[] args){
        LK146LRUCache2 lruCache=new LK146LRUCache2(2);
        lruCache.put(1,6);
        lruCache.put(2,9);
        lruCache.get(1);
        lruCache.put(3,4);
        System.out.println(lruCache.get(1));
    }
}
