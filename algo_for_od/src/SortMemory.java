import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/16 11:38
 */
/*
* 现代计算机系统中通常存在多级的存储设备，针对海量 workload 的优化的一种思路是将热点内存页优先放到快速存储层级，这就需要对内存页进行冷热标记。
一种典型的方案是基于内存页的访问频次进行标记，如果统计窗口内访问次数大于等于设定阈值，则认为是热内存页，否则是冷内存页。
对于统计窗口内跟踪到的访存序列和阈值，现在需要实现基于频次的冷热标记。内存页使用页框号作为标识。
输入描述
第一行输入为 N，表示访存序列的记录条数，0 < N ≤ 10000。
第二行为访存序列，空格分隔的 N 个内存页框号，页面号范围 0 ~ 65535，同一个页框号可能重复出现，出现的次数即为对应框号的频次。
第三行为热内存的频次阈值 T，正整数范围 1 ≤ T ≤ 10000。
输出描述
第一行输出标记为热内存的内存页个数，如果没有被标记的热内存页，则输出 0 。
如果第一行 > 0，则接下来按照访问频次降序输出内存页框号，一行一个，频次一样的页框号，页框号小的排前面。
用例
输入 10
1 2 1 2 1 2 1 2 1 2
5
输出 2
1
2
说明 内存页1和内存页2均被访问了5次，达到了阈值5，因此热内存页有2个。内存页1和内存页2的访问频次相等，页框号小的排前面。
输入 5
1 2 3 4 5
3
输出 0
说明 访存跟踪里面访存频次没有超过3的，因此热内存个数为0。*/
public class SortMemory {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n = scanner.nextInt();
        HashMap<Integer,Integer> page=new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x=scanner.nextInt();
            page.put(x,page.getOrDefault(x,0)+1);
        }

        int target=scanner.nextInt();
        List<Map.Entry<Integer, Integer>> list = page.entrySet().stream().filter(e -> e.getValue() >= target).collect(Collectors.toList());
        System.out.println(list.size());
        list.stream().sorted((o1, o2) -> {
            int freq1=o1.getValue();
            int freq2=o2.getValue();
            if(freq1==freq2)
                return o1.getKey()-o2.getKey();
            return freq2-freq1;
        }).forEach(e -> System.out.println(e.getKey()));


        //法二： keyset是和map绑定的 对keyset的修改会反映到map里 反之也是
        page.keySet().removeIf(k->page.get(k)<target);
        System.out.println(page.size());
        page.entrySet().stream().sorted((o1, o2) -> {
            int freq1=o1.getValue();
            int freq2=o2.getValue();
            if(freq1==freq2)
                return o1.getKey()-o2.getKey();
            return freq2-freq1;
        }).forEach(e -> System.out.println(e.getKey()));
    }
}
