import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/10 22:28
 */
/*
* 给定一个数组，编写一个函数来计算它的最大N个数与最小N个数的和。你需要对数组进行去重。

说明：

数组中数字范围[0, 1000]
最大N个数与最小N个数不能有重叠，如有重叠，输入非法返回-1
输入非法返回-1
输入描述
第一行输入M， M标识数组大小
第二行输入M个数，标识数组内容
第三行输入N，N表达需要计算的最大、最小N个数
输出描述
输出最大N个数与最小N个数的和

用例
输入
5
95 88 83 64 100
2

输出	342
说明	最大2个数[100,95],最小2个数[83,64], 输出为342。
输入
5
3 2 3 4 2
2

输出	-1
说明	最大2个数[4,3],最小2个数[3,2], 有重叠输出为-1。
*/
public class SACMaxNAndMinN {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = Integer.parseInt(scanner.nextLine());
        String[] nums = scanner.nextLine().split(" ");
        int n = scanner.nextInt();

        System.out.println(getResult(nums, n));
    }

    private static int getResult(String[] nums, int n) {
        if (n <= 0)
            return -1;

        Integer[] array = Arrays.stream(nums).distinct().map(Integer::valueOf).toArray(Integer[]::new);
        Integer[] array1 = Arrays.stream(array).filter(integer -> integer > 1000 || integer < 0).toArray(Integer[]::new);
        if (array1.length > 0)
            return -1;
        if (2 * n > array.length) {
            return -1;
        }
        Integer minN = Arrays.stream(array).sorted().limit(n).reduce(Integer::sum).orElse(0);
        Integer maxN = Arrays.stream(array).sorted((o1, o2) -> o2 - o1).limit(n).reduce(Integer::sum).orElse(0);
        // 会自动拆箱 用+等价与Integer.valueOf(a.intValue() + b.intValue());
        return minN + maxN;
    }

    public static void main2() {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = sc.nextInt();
        }
        int n = sc.nextInt();
        System.out.println(getResult2(arr, n));
    }

    private static int getResult2(int[] arr, int n) {
        if (n <= 0)
            return -1;
        HashSet<Integer> set = new HashSet<>();
        for (int val : arr) {
            if (val < 0 || val > 1000) return -1;
            set.add(val);
        }
        if (set.size() < 2 * n)
            return -1;
        Integer[] distinct_arr = set.toArray(new Integer[0]);
        Arrays.sort(distinct_arr, (a, b) -> a - b);
        int l = 0;
        int r = distinct_arr.length - 1;
        int ans = 0;
        while (n > 0) {
            ans += distinct_arr[l] + distinct_arr[r];
            l++;
            r--;
            n--;
        }
        return ans;
    }
}

