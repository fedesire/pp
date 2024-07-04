import java.util.PriorityQueue;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/5/12 10:45
 */
public class DS295FindMedian {
}
class MedianFinder {
    PriorityQueue<Integer> l=new PriorityQueue<>((a,b)->b-a);
    PriorityQueue<Integer> r=new PriorityQueue<>((a,b)->a-b);
    public MedianFinder() {

    }

    // 每次添加元素后都能满足l rsize相等或者l比r刚好只多了一个元素 且l维持最大堆 r维持最小堆
    public void addNum(int num) {
        int lSize=l.size(),rSize=r.size();
        // 此时必须使得进入if操作完成后lsize+1 如果num能直接加入l是最好的 如果不行 说明num要加入r 那就先从r中移除一个加入l
        if(lSize==rSize){
            if(rSize==0||num<=r.peek()){
                l.offer(num);
            }
            else{
                l.offer(r.poll());
                r.offer(num);
            }
        }
        // 到这儿说明lsize=rsize+1 所以l肯定不是空
        else{
            if(num>=l.peek())
                r.offer(num);
            else{
                r.offer(l.poll());
                l.offer(num);
            }
        }

    }

    public double findMedian() {
        int s1 = l.size(), s2 = r.size();
        if (s1 == s2) {
            return (l.peek() + r.peek()) / 2.0;
        } else {
            return l.peek();
        }
    }
}