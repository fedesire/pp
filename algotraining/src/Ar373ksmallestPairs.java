import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/13 11:18
 */
public class Ar373ksmallestPairs {
    //用大根堆的方法逻辑也对 但最终有些测试案例会超时 因为这种没有利用到有序数组的特性 这题还是用小跟堆

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> maxHeap=new PriorityQueue<>(k,(e1,e2)->(e2.get(0)+e2.get(1)-e1.get(0)-e1.get(1)));
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                Integer[] pair={nums1[i],nums2[j]};
                List<Integer> top = maxHeap.peek();
                if(maxHeap.size()<k)
                    maxHeap.add(Arrays.asList(pair));
                else if(nums1[i]+nums2[j]<top.get(0)+top.get(1)){
                    maxHeap.remove();
                    maxHeap.add(Arrays.asList(pair));
                }
            }
        }
        List<List<Integer>> res=new ArrayList<>();
        for (List<Integer> list : maxHeap) {
            res.add(list);
        }
        return res;
    }

    //minheap里的数据存的是索引 并不是真正的数 https://leetcode.cn/problems/find-k-pairs-with-smallest-sums/solutions/2286318/jiang-qing-chu-wei-shi-yao-yi-kai-shi-ya-i0dj/?envType=study-plan-v2&envId=top-interview-150
    public List<List<Integer>> kSmallestPairs1(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> minHeap=new PriorityQueue<>(k,(e1,e2)->nums1[e1[0]]+nums2[e1[1]]-nums1[e2[0]]-nums2[e2[1]]);
        List<List<Integer>> res=new ArrayList<>();

        // 把 nums1 的所有索引入队，nums2 的索引初始时都是 0
        // 优化：最多入队 k 个就可以了，因为提示中 k 的范围较小，这样可以提高效率
        for(int i=0;i<Math.min(nums1.length,k);i++){
            minHeap.add(new int[]{i, 0});

        }
        while(k-->0&&!minHeap.isEmpty()){
            int[] index = minHeap.remove();
            res.add(Arrays.asList(nums1[index[0]],nums2[index[1]]));
            if(++index[1]<nums2.length)
                minHeap.add(index);

        }
        return res;
    }
    }
