import java.util.*;
import java.util.stream.Collectors;

/**
 * 某个产品当前迭代周期内有 N 个特性（F1,F2,......FN）需要进行覆盖测试，每个特性都被评估了对应的优先级，特性使用其 ID 作为下标进行标识。
 * 设计了 M 个测试用例（T1,T2,......,TM），每个测试用例对应一个覆盖特性的集合，测试用例使用其 ID 作为下标进行标识，测试用例的优先级
 * 定义为其覆盖的特性的优先级之和。
 * 在开展测试之前，需要制定测试用例的执行顺序，规则为：优先级大的用例先执行，如果存在优先级相同的用例，用例 ID 小的先执行。
 *
 * 输入描述
 * 第一行输入为 N 和 M，
 * N 表示特性的数量，0 < N ≤ 100
 * M 表示测试用例的数量，0 < M ≤ 100
 * 之后 N 行表示特性 ID=1 到特性 ID=N 的优先级，
 * 再接下来 M 行表示测试用例 ID=1 到测试用例 ID=M 关联的特性的 ID 的列表。
 *
 * 输出描述
 * 按照执行顺序（优先级从大到小）输出测试用例的 ID，每行一个ID
 * 测试用例覆盖的 ID 不重复。
 * 5 4
 * 1
 * 1
 * 2
 * 3
 * 5
 * 1 2 3
 * 1 4
 * 3 4 5
 * 2 3 4
 * 输出 3
 * 4
 * 1
 * 2
 * 说明
 * 测试用例的优先级计算如下：
 * T1 = Pf1 + Pf2 + Pf3 = 1 + 1 + 2 = 4
 * T2 = Pf1 + Pf4 = 1 + 3 = 4
 * T3 = Pf3 + Pf4 + Pf5 = 2 + 3 + 5 = 10
 * T4 = Pf2 + Pf3 + Pf4 = 1 + 2 + 3 = 6
 * 按照优先级从小到大，以及相同优先级，ID小的先执行的规则，执行顺序为T3,T4,T1,T2
 *
 */
public class SACTestPlan {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt(),m=scanner.nextInt();
        Integer[] featurePriority=new Integer[n];

        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            featurePriority[i]=Integer.valueOf(scanner.nextLine());
        }

        Map<Integer,Integer> testCasePriority=new HashMap<>();
        for (int i = 1; i <=m; i++) {
            String[] split = scanner.nextLine().split(" ");
            // 注意这里要-1 因为f[0]代表id为1的特性的优先级 也可以上面数组大小改为n+1 循环从i=1开始
            Integer sum = Arrays.stream(split).map(s -> featurePriority[Integer.parseInt(s)-1]).reduce(Integer::sum).orElse(0);
            testCasePriority.put(i,sum);
        }

        testCasePriority.keySet().stream().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int p1 = testCasePriority.get(o1);
                int p2 = testCasePriority.get(o2);
                if (p1 != p2)
                    return p2 - p1;
                return o1 - o2;
            }
        }).forEach(System.out::println);
    }
}
