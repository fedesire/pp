import com.sun.corba.se.impl.orbutil.DenseIntMapImpl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * @date 2024/2/3 10:37
 */
//不知道哪里错了 有几个测试案例没通过
class LRUCache{
    Map<Integer,Integer> hm;
    LinkedList<Integer> linkedList;
    int size;
    int capacity;
    public LRUCache(int capacity){
        hm=new HashMap<>();
        linkedList=new LinkedList<>();
        size=0;
        this.capacity=capacity;
    }
    public int get(int key){
        if(hm.containsKey(key)){
            linkedList.remove(new Integer(key));
            linkedList.addFirst(key);
            return hm.get(key);
        }
        return -1;
    }
    public void put(int key,int value){
        if(!hm.containsKey(key)){
            if(size==capacity){
                int removeEle=linkedList.removeLast();
                hm.put(removeEle,-1);
                size--;
            }
            linkedList.remove(new Integer(key));

        }
        hm.put(key,value);

        linkedList.addFirst(key);
        size++;

    }
}

/*哈希表+双向链表 head tail是虚拟的头结点和尾节点 也叫伪头部 伪尾部 所以在构造函数中就可以new一个节点了
使用虚拟头结点和尾节点的好处是添加和删除时就不需要检查相邻的节点是否存在了 比如一般添加节点是将节点加入
最后一个节点的后面 即将最后一个节点的next指向新生成的节点 如果没有虚拟尾节点 加入第一个节点的时候就要判断了
因为还没有最后一个节点 有了虚拟尾节点之后 添加新节点只需要将新节点插到虚拟尾节点之前 即将新节点指向虚拟尾节点
这里的插入是插在虚拟头结点的后面 比如head<->a->b->tail 将新节点插在head之后 即head<->newNode<->a->b->tail 其实就是
动pre next pre next4个指针参考addToHead方法 当第一次插入节点前head<->tail 所以来一个新节点不需要判断是否是第一个
* 用“哈希表”和“双向链表”实现一个 LRU 缓存。
哈希表：用于存储 key 和对应的节点位置。双向链表：用于存储节点数据，按照访问时间排序。
当访问一个节点时，如果节点存在，我们将其从原来的位置删除，并重新插入到链表头部。这样就能保证链表尾部存储的就是
* 最近最久未使用的节点，当节点数量大于缓存最大空间时就淘汰链表尾部的节点。当插入一个节点时，如果节点存在，我们
* 将其从原来的位置删除，并重新插入到链表头部。如果不存在，我们首先检查缓存是否已满，如果已满，则删除链表尾部的
* 节点，将新的节点插入链表头部。
这里的实现是头部作为新数据 尾部是旧数据 Linkedhashmap是头部是旧数据

有一个node链表就能实现有先后顺序的存储 但是只有一个node链表 无法快速的根据key获取值 也无法快速移动
所以为了实现通过key就能找到node 加入hashmap key对应的value里存的就是node
*/
class LRUcache{
    class DeNode{
        int key;
        int value;
        DeNode pre;
        DeNode next;
        DeNode(int key,int value){
            this.key=key;
            this.value=value;
        }
        DeNode(){}
    }
    DeNode head;
    DeNode tail;
    Map<Integer,DeNode> cache;
    int capacity;
    LRUcache(int capacity){
        head=new DeNode();
        tail=new DeNode();
        head.next=tail;
        tail.pre=head;
        cache=new HashMap<>();
        this.capacity=capacity;
    }
    // 已存在的元素就是moveToHead 新添加的元素就是addTohead
    int get(int key){
        if(cache.containsKey(key)){
            DeNode node=cache.get(key);
            moveToHead(node);
            return node.value;
        }
        return -1;
    }
    void put(int key,int value){
        if(cache.containsKey(key)){
            DeNode node=cache.get(key);
            node.value=value;
            moveToHead(node);
        }
        else{
            DeNode newNode=new DeNode(key,value);
            addToHead(newNode);
            cache.put(key,newNode);
            if(cache.size()>capacity){
                DeNode delNode=tail.pre;
                delNode.pre.next=tail;
                tail.pre=delNode.pre;
                cache.remove(delNode.key);

            }
        }
    }

    void moveToHead(DeNode node){
        removeNode(node);
        addToHead(node);
    }
    void removeNode(DeNode node){
        node.pre.next=node.next;
        node.next.pre=node.pre;
    }
    void addToHead(DeNode node){
        node.next=head.next;
        head.next.pre=node;
        head.next=node;
        node.pre=head;
    }
}
public class LK146LRUcache
{
    public static void main(String[] args) {
        LRUcache lruCache = new LRUcache(2);
        lruCache.put(1,6);
        lruCache.put(2,9);
        lruCache.get(1);
        lruCache.put(3,4);
        System.out.println(lruCache.get(2));


    }

}
