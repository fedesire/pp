import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/3/21 23:27
 */
public class LK146LRUCache1 extends LinkedHashMap<Integer,Integer>{
    private int capacity;
    public LK146LRUCache1(int capacity) {
// 第三个参数true表示让LinkedHashMap按照访问顺序来进行排序，最近访问的放在尾部，最老访问的放在头部
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    //如果继承LinkedHashMap并重写removeEldestEntry方法始终返回true，那么你的LinkedHashMap将始终删除最老
    // 的条目（即最近最少使用的条目，如果你按照访问顺序配置了LinkedHashMap），导致它实际上变成一个只能存储
    // 一个条目的数据结构，因为每次添加新条目时都会删除最老的条目。

    // 这个方法是在hashmap的put方法里被调用的 本来hashmap put()里面最后会调用afterNodeInsertion方法，这个方法在hashmap里
    // 方法体是空 linkedhashmap重写了这个方法 里面就会调用removeEldestEntry方法，LinkedHashmap的removeEldestEntry方法，
    // 默认返回false，表示不删除最老的条目
    // 我这里再次重写removeEldestEntry这个方法 超过指定容量就会返回true，这样最终的效果就是容量超了就会删除最老的条目
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
// 当map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据
        return size()>capacity;
    }
    public int get(int key){
        return super.getOrDefault(key,-1);
    }
    public void put(int key,int value){
         super.put(key,value);
    }

    public static void main(String[] args) {
        LK146LRUCache1 cache = new LK146LRUCache1(3);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.get(1); // 最近访问了"1"
        cache.put(4,4); // 这时"2"会被淘汰，因为它是最近最少使用的
        System.out.println(cache.keySet()); // 输出：[3, 1, 4]
    }
}
