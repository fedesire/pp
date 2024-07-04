/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/16 16:30
 */
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;
/*
* XX市机场停放了多架飞机，每架飞机都有自己的航班号CA3385，CZ6678，SC6508等，航班号的前2个大写字母（或数字）代表航空公司的缩写，后面4个数字代表航班信息。

但是XX市机场只有一条起飞跑道，调度人员需要安排目前停留在机场的航班有序起飞。

为保障航班的有序起飞，调度员首先按照航空公司的缩写（航班号前2个字母）对所有航班进行排序，同一航空公司的航班再按照航班号的后4个数字进行排序，最终获得安排好的航班的起飞顺序。

请编写一段代码根据输入的航班号信息帮助调度员输出航班的起飞顺序。

说明：
ascii码表里 36 38 42 数字0是48 A是65 所以下面的sorted里不用切割分开两段排序 放一起用按字典顺序排序也可以
航空公司缩写排序按照从特殊符号$ & *，0~9，A~Z排序；
*/
public class SortFlight {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] flights = sc.nextLine().split(",");

        StringJoiner sj = new StringJoiner(",");
        Arrays.stream(flights)
                .sorted(
                        (a, b) -> {
                            String abbr1 = a.substring(0, 2);
                            String num1 = a.substring(2);

                            String abbr2 = b.substring(0, 2);
                            String num2 = b.substring(2);

                            if (abbr1.equals(abbr2)) {
                                return num1.compareTo(num2);
                            } else {
                                return abbr1.compareTo(abbr2);
                            }
                        })
                .forEach(sj::add);

        System.out.println(sj);
    }
}
