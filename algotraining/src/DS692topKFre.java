import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/26 11:15
 */
public class DS692topKFre {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<String, Integer>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        return cnt.keySet().stream().sorted((new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int c1 = cnt.get(o1);
                int c2 = cnt.get(o2);
                return c1==c2?o1.compareTo(o2):c2-c1;
            }
        })).collect(Collectors.toList()).subList(0,k);
    }
    /*对于前 kkk 大或前 kkk 小这类问题，有一个通用的解法：优先队列。优先队列可以在 O(log⁡n)O(\log n)O(logn) 的时间内完成插入或删除元素的操作（其中 nnn 为优先队列的大小），并可以 O(1)O(1)O(1) 地查询优先队列顶端元素。
在本题中，我们可以创建一个小根优先队列（顾名思义，就是优先队列顶端元素是最小元素的优先队列）。我们将每一个字符串插入到优先队列中，如果优先队列的大小超过了 kkk，那么我们就将优先队列顶端元素弹出。这样最终优先队列中剩下的 kkk 个元素就是前 kkk 个出现次数最多的单词。
*/
    public List<String> topKFrequent1(String[] words, int k){
        Map<String, Integer> cnt = new HashMap<String, Integer>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        // 小跟堆 次数小的在前面 最终要得到的是字典序前面的 所以排序的时候字典序后面的元素在前面 这样移除出去的时候才会先移除出去
        PriorityQueue<Map.Entry<String,Integer>> pq=new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                int c1=o1.getValue();
                int c2=o2.getValue();
                return c1==c2?o2.getKey().compareTo(o1.getKey()):c1-c2;
            }
        });
        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            // 不能是这样 必须是先加入 再删除 不然每次先删除的话每次新加入的元素没有比较就必定会加入
         /*   if(pq.size()==k)
                pq.poll();
            pq.offer(entry);*/
            pq.offer(entry);
            if(pq.size()>k)
                pq.poll();
        }
        List<String> res=new ArrayList<>();
        while(!pq.isEmpty()){
            res.add(pq.poll().getKey());
        }
        // 这里很容易忘 因为此时的顺序是次数小的和字典序后面的元素在前面
        Collections.reverse(res);
        return res;
    }
}
