import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。
 * 滑动窗口每次只向右移动一位。返回滑动窗口中的最大值 。
 * @date 2024/5/7 11:42
 */
public class _DS239MaxSlidingWindow {
    // 使用优先队列的方法 求最大值很容易想到用大根堆保存滑动窗口里的元素 但如果用优先队列只保存nums[i] 那每次窗口右移添加一个元素add很方便
    // 但是窗口左边刚刚移除出去的元素要直接用remove(object)很耗时 优先队列只有poll即removeFirst是O(1)很快的 所以只能利用poll
    // 那要如何判断poll的元素是否是要移除的元素 所以优先队列刚开始就应该存储二元组(num,index)
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n=nums.length;
        // 为了方便判断堆顶元素与滑动窗口的位置关系，我们可以在优先队列中存储二元组 (num,index)，表示元素 num在数组中的下标为 index
        PriorityQueue<int[]> pq=new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] pair1,int[] pair2){
                return pair1[0]!=pair2[0]?pair2[0]-pair1[0]:pair1[1]-pair2[1];
            }
        });
        for(int i=0;i<k;i++){
            pq.offer(new int[]{nums[i],i});
        }
        int[] ans=new int[n-k+1];
        ans[0]=pq.peek()[0];
        // 这里并不是优先队列一直最多只保存k个数 而是只有当最大值不在滑动窗口内时才移除优先队列里的元素
        for(int i=k;i<n;i++){
            pq.offer(new int[]{nums[i],i});
            // 最大值不在滑动窗口内 目前的滑动窗口是[i-k+1,i]刚好k个数
            while(pq.peek()[1]<=i-k)
                pq.poll();
            // 用这种直接remove的方法不行 因为是引用类型参数数组 这样每次根本没有remove掉 如果pq改成PriorityQueue<Integer>就可以
            // 但这样也ac不了 有的测试案例超时了 因为remove方法是O(n)的复杂度 比较耗时
//            pq.remove(new int[]{nums[i-k],i-k});

            ans[i-k+1]=pq.peek()[0];
        }
        return ans;
    }

    // 使用单调队列的方法 https://leetcode.cn/problems/sliding-window-maximum/solutions/2361228/239-hua-dong-chuang-kou-zui-da-zhi-dan-d-u6h0/?envType=study-plan-v2&envId=top-100-liked
    public int[] maxSlidingWindow1(int[] nums, int k) {
        if(nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        // 未形成窗口
        for(int i = 0; i < k; i++) {
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
        }
        res[0] = deque.peekFirst();
        // 形成窗口后
        for(int i = k; i < nums.length; i++) {
            // 加入nums[i] 就要删除nums[i-k]但有可能此时deque里已经没有nums[i-k]了 因为deque是按照单调递减存的元素 每次加入元素时
            // 都要把队尾所有比它小的元素删除掉 所以可能nums[i-k]在之前就已经删掉了
            if(deque.peekFirst() == nums[i - k])
                deque.removeFirst();
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums={1,3,-1,-3,5,3,6,7};
        int k=3;
        _DS239MaxSlidingWindow ds239MaxSlidingWindow=new _DS239MaxSlidingWindow();
        int[] ans=ds239MaxSlidingWindow.maxSlidingWindow(nums,k);
        for(int i:ans)
            System.out.print(i+" ");
    }
}
