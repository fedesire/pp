import java.util.PriorityQueue;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * @date 2024/1/13 10:59
 */
/*这道题也可以用快排的方法 当完成partition操作后得到的pivot的位置刚好是n-k时就提前返回 此时pivot就是要找的元素*/
public class Ar214findKlargest {
    //维护一个包含k个元素的最小堆 当堆满的时候 就将要进来的元素num和堆顶的元素比较 只有当num大于堆顶元素时才入堆
    //这样当数组遍历完后 堆里包含的就是整个数组前k大的元素 并且堆顶的元素就是前k大里最小的 即第k大的元素
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq=new PriorityQueue<>(k);
        for(int num:nums){
            if(pq.size()<k)
                pq.add(num);
            else if(num>pq.peek()){
                pq.remove();
                pq.add(num);
            }
        }
        return pq.peek();

    }
}
