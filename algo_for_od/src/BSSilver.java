import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 有 N 块二手市场收集的银饰，每块银饰的重量都是正整数，收集到的银饰会被熔化用于打造新的饰品。
 * 每一回合，从中选出三块最重的银饰，然后一起熔掉。
 * 假设银饰的重量分别为 x 、y和z，且 x ≤ y ≤ z。那么熔掉的可能结果如下：
 * 如果 x == y == z，那么三块银饰都会被完全熔掉；
 * 如果 x == y 且 y != z，会剩余重量为 z - y 的银块无法被熔掉；
 * 如果 x != y 且 y == z，会剩余重量为 y - x 的银块无法被熔掉；
 * 如果 x != y 且 y != z，会剩余重量为 z - y 与 y - x 差值 的银块无法被熔掉。
 * 最后，
 * 如果剩余两块，返回较大的重量（若两块重量相同，返回任意一块皆可）
 * 如果只剩下一块，返回该块的重量
 * 如果没有剩下，就返回 0
 *
 * @date 2024/4/12 14:17
 */
public class BSSilver {
    // 升序 或降序都可以 降序只要传入Comparator Collections.binarySearch()返回的结果也是要插入的位置
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n = scanner.nextInt();
        LinkedList<Integer> weights=new LinkedList<>();
        for (int i = 0; i < n; i++) {
            weights.add(scanner.nextInt());
        }
//        weights.sort((e1,e2)->e1-e2);
        weights.sort((e1,e2)->e2-e1);
        while(weights.size()>=3){
//            int z=weights.removeLast();
//            int y=weights.removeLast();
//            int x=weights.removeLast();
            int z=weights.removeFirst();
            int y=weights.removeFirst();
            int x=weights.removeFirst();
            int leftWeight=Math.abs((z-y)-(y-x));
            if(leftWeight>0){
//                int index = Collections.binarySearch(weights, leftWeight);
                int index = Collections.binarySearch(weights, leftWeight,(e1,e2)->e2-e1);
                if(index<0)
                    index=-index-1;
                weights.add(index,leftWeight);
            }
        }
        int res=0;
//        if(weights.size()==2)
//            res=weights.get(1);
//        else if(weights.size()==1)
//            res=weights.get(0);
//        else res=0;
        if(weights.size()>0)
            res=weights.get(0);
        System.out.println(res);
    }
}

