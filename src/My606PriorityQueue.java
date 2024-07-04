import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/16 14:56
 */
public class My606PriorityQueue {
    public static void priorityQueue(){
        PriorityQueue<Integer> queue=new PriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            int num=(int)(Math.random()*100);
            queue.add(num);
        }
        while(!queue.isEmpty()){
            System.out.println(queue.poll());
        }
        // 使用lambda表达式，创建底层是最大堆的PriorityQueue
        PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(10, (a, b) -> (b - a));//b-a不加括号也行

        PriorityQueue<Integer> pq3=new PriorityQueue<>(10, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) { //先比较个位数 个位数小的在前面 个位数相等 十位数小的在前面
                if(o1%10!=o2%10)
                    return o1%10-o2%10;
                else
                    return o1-o2;
            }
        });

        for (int i = 0; i < 10; i++) {
            int num=(int)(Math.random()*100);
            pq3.add(num);
        }
        while(!pq3.isEmpty()){
            System.out.print(pq3.poll()+" ");
        }

    }

    public static void main(String[] args) {
        priorityQueue();
    }
}
