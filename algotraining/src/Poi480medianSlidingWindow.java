import java.util.*;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/5/2 11:34
 */
public class Poi480medianSlidingWindow {
    // 还是会有案例超时 这道题是要用两个优先队列做
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n=nums.length;
        double[] res=new double[n-k+1];
        int l=0,r=k-1,idx=0;
        while(r<n){
            int[] cur = Arrays.copyOfRange(nums, l, r + 1);
            Arrays.sort(cur);
            if(k%2==0)
                res[idx++]=((double)cur[k/2]+(double)cur[k/2-1])/2.0;
            else
                res[idx++]=cur[k/2];
            l++;
            r++;
        }
        return res;

    }
    /*定义大顶堆maxHeap负责维护所有元素中较小的那一半（这样堆顶的元素就是中间位置的元素），小顶堆minHeap负责维护所有元素中较大的那一半，
    以及它们的大小maxsize, minsize 始终保持minsize - maxsize = 1(奇数) or 0(偶数)
用HashMap标记待删除的元素，只有当它出现在堆顶，才将它删除。平衡时需要将堆顶元素在两个堆之间迁移，堆顶变化，同样要检查新堆顶需要删除否。
*/
    class Solution {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        Map<Integer, Integer> markedMap = new HashMap<>();
        int minsize = 0, maxsize = 0;
        public double[] medianSlidingWindow(int[] nums, int k) {
            int i = 0;
            int n = nums.length - k + 1;
            double[] ans = new double[n];
            for (; i < k; i++) { // 初始化窗口
                addHeaps(nums[i]);
            }
            balanceHeaps();
            ans[0] = findMedian();
            for (int j = 0; j < n - 1; i++, j ++) {
                addHeaps(nums[i]);
                markNumberAsDeleted(nums[j]); //如果先删后加，minHeap为空的话，直接加进去，顺序就乱了
                balanceHeaps();
                ans[j + 1] = findMedian();
            }
            return ans;
        }

        public void addHeaps(int num) {
            if (minHeap.isEmpty() || num >= minHeap.peek()) {
                minHeap.offer(num);
                minsize ++;
            } else {
                maxHeap.offer(num);
                maxsize ++;
            }
        }
        //检查堆顶元素，无论两堆哪个堆顶变化，都运行这个方法
        public void deleteMarked(PriorityQueue<Integer> Heap) {
            while (!Heap.isEmpty() && markedMap.containsKey(Heap.peek())) {
                int del = Heap.poll();
                if (markedMap.get(del) > 1) {
                    markedMap.put(del, markedMap.get(del) - 1);
                } else {
                    markedMap.remove(del);
                }
            }
        }
        public void markNumberAsDeleted(int num) {
            markedMap.put(num, markedMap.getOrDefault(num, 0) + 1);
            if(num >= minHeap.peek()) {
                minsize --;
                deleteMarked(minHeap);
            }
            else {
                maxsize --;
                deleteMarked(maxHeap);
            }
        }
        public void balanceHeaps() {
            while(maxsize > minsize) {
                minHeap.offer(maxHeap.poll());
                maxsize --;
                minsize ++;
                deleteMarked(maxHeap);
            }
            while(minsize - maxsize > 1) {
                maxHeap.offer(minHeap.poll());
                minsize --;
                maxsize ++;
                deleteMarked(minHeap);
            }
        }
        public double findMedian() {
            if (((maxsize + minsize) & 1) == 0) {
                return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
            }
            return minHeap.peek() * 1.0;
        }
    }

    public static void main(String[] args)
    {
        int[] nums={2147483647,2147483647};
        int k=2;
        Poi480medianSlidingWindow p=new Poi480medianSlidingWindow();
        double[] res=p.medianSlidingWindow(nums,k);
        for(double i:res)
            System.out.println(i);
    }
}
