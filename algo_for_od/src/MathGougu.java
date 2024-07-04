/**
 * 如果3个正整数(a,b,c)满足a^2 + b^2 = c^2的关系，则称(a,b,c)为勾股数（著名的勾三股四弦五），
 * 为了探索勾股数的规律，我们定义如果勾股数(a,b,c)之间两两互质（即a与b，a与c，b与c之间均互质，没有公约数），则其为勾股数元组
 * （例如(3,4,5)是勾股数元组，(6,8,10)则不是勾股数元组）。
 * 请求出给定范围[N,M]内，所有的勾股数元组。
 * 输入描述
 * 起始范围N，1 <= N <= 10000
 * 结束范围M，N < M <= 10000
 * 输出描述
 * 1. a,b,c请保证a < b < c,输出格式：a b c；
 * 2. 多组勾股数元组请按照a升序，b升序，最后c升序的方式排序输出；
 * 3. 给定范围中如果找不到勾股数元组时，输出”NA“。
 * @date 2024/4/14 12:30
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
// 因此本题难点有二：1、如何找出所有勾股数； 2、如何判断两个数互质
//关于1，我们可以先求出区间[n,m]的所有数的平方，缓存到一个数组arr中，然后对该数组进行双重for遍历，外层遍历所有元素arr[i]，
// 内层遍历i之后的每一个元素arr[j]，我们求arr[i]+arr[j]的和sum，看arr中是否包含sum元素，若是，则就得到一组勾股数sqrt(arr[i])、
// sqrt(arr[j])、sqrt(sum)。
//按照上面逻辑求得所有勾股数。
//之后，我们可以根据辗转相除法判断两个数是否互质，比如求9和12是否互质，以及求47和18是否互质。
//我们只需要用
//a % b 得到一个 mod
//然后将
//a <= b
//b <= mod
//如果进行到b===0时，则看此时a的值，若a===1，则说明初始时的a,b互质，否则就有最大公约数结束时的a。
public class MathGougu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        getResult(n, m);
    }

    public static void getResult(int n, int m) {
        ArrayList<Integer> arr = new ArrayList<>();

        for (int i = n; i <= m; i++) {
            arr.add(i * i);
        }

        HashSet<Integer> set = new HashSet<>(arr);
        ArrayList<Integer[]> res = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                // 判断勾股数 a^2 + b^2 = c^2
                int sum = arr.get(i) + arr.get(j);
                if (set.contains(sum)) {
                    res.add(
                            new Integer[] {
                                    (int) Math.sqrt(arr.get(i)), (int) Math.sqrt(arr.get(j)), (int) Math.sqrt(sum)
                            });
                }
            }
        }

        List<Integer[]> collect =
                res.stream()
                        .filter(
                                g ->
                                        isRelativePrime(g[0], g[1])
                                                && isRelativePrime(g[0], g[2])
                                                && isRelativePrime(g[1], g[2]))
                        .collect(Collectors.toList());

        if (collect.size() == 0) {
            System.out.println("NA");
        } else {
            for (Integer[] g : collect) {
                System.out.println(g[0] + " " + g[1] + " " + g[2]);
            }
        }
    }

    // 判断两个数是否互质，辗转相除
    public static boolean isRelativePrime(int x, int y) {
        while (y > 0) {
            int mod = x % y;
            x = y;
            y = mod;
        }

        return x == 1;
    }
}
