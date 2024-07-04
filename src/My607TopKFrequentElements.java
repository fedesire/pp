import com.sun.deploy.security.SelectableSecurityManager;
import javafx.util.Pair;

import java.util.*;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/16 16:26
 */
public class My607TopKFrequentElements {
    //用的是大根堆的方法 把所有节点都存进去队列 最后只读取前k个元素 bobo算法是用小跟堆 维护队列里只有k个元素
    //用最大堆时间复杂度就是nLogn 用最小堆时间复杂度是nlogk 最大堆开了n个空间 最小堆只需开k个空间
    /*O(Nlogk)，其中 N为数组的长度。我们首先遍历原数组，并使用哈希表记录出现次数，每个
    元素需要 O(1)的时间，共需 O(N) 的时间。随后，我们遍历「出现次数数组」，由于堆的大小至多为 k，因此每次
    堆操作需要 O(logk) 的时间 总共需要O(Nlogk) 的时间。二者之和为 O(Nlogk)
*/
    public static List<Integer> TopKFrequentElements(int[] nums, int k){
        List<Integer> res=new ArrayList<>();
        HashMap<Integer,Integer> hm=new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            if(hm.containsKey(nums[i]))
//                hm.put(nums[i],hm.get(nums[i])+1);
//            else hm.put(nums[i],1);
//        }

        //HashMap插入元素的另一种写法 不需要写先判断containsKey
        for(int num: nums){
            hm.put(num,hm.getOrDefault(num,0)+1);
        }


        PriorityQueue<Pair<Integer,Integer>> pq=new PriorityQueue<>(hm.size(), new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                if(o2.getValue()!=o1.getValue())
                    return o2.getValue()-o1.getValue();
                else
                    return o2.getKey()-o1.getKey();

            }
        });


        for (Integer key : hm.keySet()) {
            Pair<Integer,Integer> pair=new Pair<>(key,hm.get(key));
            pq.add(pair);
        }

//        for (int i = 0; i < k; i++) {
//            res.add(pq.poll().getKey());
//        }

        //这一段的算法逻辑与本题不符 我写的是实现 求出现频率前k高的元素 最终返回结果里包含的元素不一定就是只有k个 也可能更多的情况
        // 如1,2,2,3,3,4,4,4 k=2 这里的代码逻辑返回的结果就是2 3 4 本题的代码返回的就只有3 4
        int num=0;
        int curFreq=0;
        while(!pq.isEmpty()&&num<=k){ //循环结束的条件必须是<=k 如果是<k的话那当num第一次到达k的时候就会停止
            // 此时可能还会有其他元素的频率也是第k大的 必须等他们都加入res才算完成 所以res.add前还要有一个判断
            Pair<Integer,Integer> item=pq.poll();
            int freq=item.getValue();
            if(freq!=curFreq){
                num++;
                curFreq=freq;
            }
            if(num<=k)
                res.add( item.getKey());
        }

        int[] arr=new int[res.size()]; //这里是在lc上要用到的list转为数组
        for (int i = 0; i < arr.length; i++) {
            arr[i]=res.get(i);
        }



        return res;

    }
    //更简洁的写法
    public static int[] topkFrequent(int[] nums,int k){
        HashMap<Integer,Integer> hm=new HashMap<>();
        //HashMap插入元素的另一种写法 不需要写先判断containsKey
        for(int num: nums){
            hm.put(num,hm.getOrDefault(num,0)+1);
        }

        PriorityQueue<Map.Entry<Integer,Integer>> pq= new PriorityQueue<>(hm.size(),(e1,e2)->(e2.getValue()-e1.getValue()));
        pq.addAll(hm.entrySet());

        int[] res=new int[k];
        for (int i = 0; i < k&&!pq.isEmpty(); i++) {
            res[i]=pq.poll().getKey();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2,3,3,4,4,4};
        int k = 2;
        System.out.println(TopKFrequentElements(nums, k));
    }
}
