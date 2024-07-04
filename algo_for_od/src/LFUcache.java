import java.util.*;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 16:01
 */
/*
* 请设计一个文件缓存系统，该文件缓存系统可以指定缓存的最大值（单位为字节）。

文件缓存系统有两种操作：

存储文件（put）
读取文件（get）
操作命令为：

put fileName fileSize
get fileName
存储文件是把文件放入文件缓存系统中；

读取文件是从文件缓存系统中访问已存在，如果文件不存在，则不作任何操作。

当缓存空间不足以存放新的文件时，根据规则删除文件，直到剩余空间满足新的文件大小位置，再存放新文件。

具体的删除规则为：

文件访问过后，会更新文件的最近访问时间和总的访问次数，当缓存不够时，按照第一优先顺序为访问次数从少到多，第二顺序为时间从老到新的方式来删除文件。

输入描述
第一行为缓存最大值 m（整数，取值范围为 0 < m ≤ 52428800）

第二行为文件操作序列个数 n（0 ≤ n ≤ 300000）

从第三行起为文件操作序列，每个序列单独一行，文件操作定义为：

op  file_name  file_size

file_name 是文件名，file_size 是文件大小

输出描述
输出当前文件缓存中的文件名列表，文件名用英文逗号分隔，按字典顺序排序，如：

a,c

如果文件缓存中没有文件，则输出NONE

备注
如果新文件的文件名和文件缓存中已有的文件名相同，则不会放在缓存中
新的文件第一次存入到文件缓存中时，文件的总访问次数不会变化，文件的最近访问时间会更新到最新时间
每次文件访问后，总访问次数加1，最近访问时间更新到最新时间
任何两个文件的最近访问时间不会重复
文件名不会为空，均为小写字母，最大长度为10
缓存空间不足时，不能存放新文件
每个文件大小都是大于 0 的整数
用例
输入	50
6
put a 10
put b 20
get a
get a
get b
put c 30
输出	a,c
说明	无
输入	50
1
get file
输出	NONE
说明	无
*/
import java.util.HashMap;
import java.util.StringJoiner;

public class LFUcache {
    /** 双向链表节点 */
    static class Node {
        /** 记录本题的文件名 */
        String key;
        /** 记录本题的文件大小 */
        int val;
        /** 记录该文件的访问次数 */
        int freq;
        /** 当前节点的前一个节点 */
        Node prev;
        /** 当前节点的后一个节点 */
        Node next;

        public Node(String key, int val, int freq) {
            this.key = key;
            this.val = val;
            this.freq = freq;
            this.prev = null;
            this.next = null;
        }
    }

    /** 双向链表 中存的是同一种访问次数的所有节点 且链表尾部是最近访问的，链表头部是最远访问的*/
    static class Link {
        /** 链表节点数 */
        int size;
        /** 链表头节点 */
        Node head;
        /** 链表尾节点 */
        Node tail;

        /**
         * 尾插
         *
         * @param node 要插入的节点
         */
        public void addLast(Node node) {
            if (this.size == 0) {
                this.head = node;
                this.tail = node;
            } else {
                this.tail.next = node;
                node.prev = this.tail;
                this.tail = node;
            }

            this.size++;
        }

        /**
         * 删除指定节点
         *
         * @param node 指定删除的节点
         */
        public void remove(Node node) {
            // 如果是空链表，则没法删除
            if (this.size == 0) return;

            if (this.size == 1) {
                // 如果是单节点链表，则删除完，链表为空
                this.head = null;
                this.tail = null;
            } else if (node == this.head) {
                // 被删除节点是链表头节点
                this.head = this.head.next;
                this.head.prev = null;
            } else if (node == this.tail) {
                // 被删除节点是链表尾节点
                this.tail = this.tail.prev;
                this.tail.next = null;
            } else {
                // 被删除节点是链表中间节点
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }

            this.size--;
        }
    }

    static class LFUCache {
        /** key是文件名，value是文件信息（key.value本质是freqMap.value对应的链表中节点Node的对象地址） */
        HashMap<String, Node> keyMap;

        /** key是访问次数，value是访问次数位key的文件（Node）组成的链表（Link） */
        HashMap<Integer, Link> freqMap;

        /** 文件系统总容量 */
        int capacity;

        /** 最少访问次数（记录自各个文件访问次数中最少的那个） */
        int minFreq;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.minFreq = 0;
            this.keyMap = new HashMap<>();
            this.freqMap = new HashMap<>();
        }

        /**
         * @param key 对应本题的文件名
         */
        public void get(String key) {
            // 如果文件不存在，则不作任何操作。
            if (!this.keyMap.containsKey(key)) return;

            Node node = this.keyMap.get(key);
            // 每次文件访问后，总访问次数加1，最近访问时间更新到最新时间
            incNodeFreq(node);
        }

        /**
         * @param key 对应本题的文件名
         * @param val 对应本题的文件大小
         */
        public void put(String key, int val) {
            // 如果新文件的文件名和文件缓存中已有的文件名相同，则不会放在缓存中
            if (this.keyMap.containsKey(key)) return;

            // 当缓存空间不足以存放新的文件时，根据规则删除文件，直到剩余空间满足新的文件大小位置，再存放新文件。
            while (this.capacity < val) {
                if (this.minFreq == 0) {
                    // 文件系统空了，也放不下该文件，则不放入
                    return;
                }

                // 找出最少访问次数对应的链表
                Link minFreqLink = this.freqMap.get(this.minFreq);

                // 链表头部节点是最少访问次数中，最远访问的文件，我们需要删除它
                Node removeNode = minFreqLink.head;
                // 删除对应文件后，文件系统容量新增
                this.capacity += removeNode.val;

                // 执行删除操作，freqMap和keyMap都要删除掉对应文件的记录
                minFreqLink.remove(removeNode);
                this.keyMap.remove(removeNode.key);

                // 如果删除后，最少访问次数的链表空了，则需要找到下一个最少访问次数的链表
                if (minFreqLink.size == 0) {
                    // 最少访问次数没有对应文件（链表为空），则删除最少访问次数的记录（freqMap的key）
                    this.freqMap.remove(this.minFreq);

                    if (this.freqMap.size() > 0) {
                        this.minFreq = this.freqMap.keySet().stream().min((a, b) -> a - b).get();
                    } else {
                        // 文件系统没有缓存文件了，则最少次数为0，表示文件系统空了
                        this.minFreq = 0;
                    }
                }
            }

            // 新增文件，则文件系统容量减少
            this.capacity -= val;
            // 新增文件的访问次数为1，因此最少访问次数变为了1
            this.minFreq = 1;
            Node node = new Node(key, val, this.minFreq);
            // 执行新增操作，freqMap和keyMap都要新增对应文件的记录
            this.freqMap.putIfAbsent(this.minFreq, new Link());
            this.freqMap.get(this.minFreq).addLast(node);
            this.keyMap.put(key, node);
        }

        public void incNodeFreq(Node node) {
            Link link = this.freqMap.get(node.freq);

            // 由于要更新文件的访问次数，因此需要将node从当前访问次数的链表中删除
            link.remove(node);
            // 如果当前访问次数链表只有当前node，则继续看当前链表对应的访问次数是否位最少访问次数，若是，则说明删除当前节点后，最少访问次数对应的文件没了
            if (link.size == 0) {
                // 当前访问次数没有对应文件（链表为空），则删除当前访问次数的记录（freqMap的key）
                this.freqMap.remove(node.freq);

                if (node.freq == this.minFreq) {
                    // 此时我们应该更新最少访问次数
                    this.minFreq++;
                }
            }

            node.freq++; // 总访问次数加1
            this.freqMap.putIfAbsent(node.freq, new Link());
            // 将node插入到对应freq的链表中
            // 尾插 是为了实现：最近访问时间更新到最新时间，即这里认为：链表尾部是最近的，链表头部是最远的
            this.freqMap.get(node.freq).addLast(node);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = Integer.parseInt(sc.nextLine());
        LFUCache lfuCache = new LFUCache(m);
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String[] operation = sc.nextLine().split(" ");

            String op = operation[0];
            String fileName = operation[1];

            if ("put".equals(op)) {
                int fileSize = Integer.parseInt(operation[2]);
                lfuCache.put(fileName, fileSize);
            } else {
                lfuCache.get(fileName);
            }
        }

        if (lfuCache.capacity == m) {
            // 如果文件系统容量没有减少，则没有文件被缓存
            System.out.println("NONE");
        } else {
            // 否则取出文件系统中的文件名，按照字典序输出
            StringJoiner sj = new StringJoiner(",");
            lfuCache.keyMap.keySet().stream().sorted().forEach(sj::add);
            System.out.println(sj);
        }
    }
}