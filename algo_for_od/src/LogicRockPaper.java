import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 14:23
 */
/*
* 石头剪刀布游戏有 3 种出拳形状：石头、剪刀、布。分别用字母A、B、C表示。

游戏规则：

出拳形状之间的胜负规则如下：

A > B；
B > C；
C > A；

">" 左边一个字母，表示相对优势形状。右边一个字母，表示相对劣势形状。

当本场次中有且仅有一种出拳形状优于其他出拳形状，则该形状的玩家是胜利者。否则认为是平局。

例如1：三个玩家出拳分别是A，B，C。由于三方优势循环（即没有任何一方优于其他出拳者），判断为平局。

例如2：三个玩家出拳分别是A，B，B。出拳A的获胜。

例如3：三个玩家出拳全部是A。判为平局。

当发生平局，没有赢家。有多个胜利者时，同为赢家。
输入描述
在一场游戏中，每个玩家的信息为一行。玩家数量不超过1000。每个玩家信息有2个字段，用空格隔开；

玩家ID：一个仅由英文字母和数字组成的字符串
出拳形状：以英文大写字母表示，A、B、C形状。
出拳时间：正整数，越小表示时间越早
例如：

abc1 A
xyz B

解释：玩家abc1出拳为石头（A）。玩家xyz出拳为剪刀（B）

输出描述
输出为赢家的玩家ID列表（一个或多个），每个ID一行，按字符串升序排列。如果没有赢家，输出为”NULL“字符串。

例如：

abc1

用例
输入	abc1 A
xyz B
输出	abc1
说明	A比B有优势，abc1胜出
输入	abc1 A
xyz A
输出	NULL
说明	没有优胜的出拳形状，平局
输入	abc1 A
def A
alic A
xyz B
输出	abc1
alic
def
说明	A为优胜方，有三个赢家
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class LogicRockPaper {
    // 输入输出处理
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HashMap<Character, ArrayList<String>> map = new HashMap<>();

        while (sc.hasNextLine()) {
            String[] s = sc.nextLine().split(" ");
            String player = s[0];
            char gesture = s[1].charAt(0);

            // 如果有人不按套路出，则此局作废
            if (gesture < 'A' || gesture > 'C') {
                System.out.println("NULL");
                return;
            }

            // 统计各个手势的出派人
            map.putIfAbsent(gesture, new ArrayList<>());
            map.get(gesture).add(player);
        }

        switch (map.size()) {
            case 1:
            case 3:
                // 只有一种手势，或者三种手势都有，则平局
                System.out.println("NULL");
                break;
            case 2:
                ArrayList<String> ans;

                if (!map.containsKey('A')) {
                    // 没有A手势，只有B、C手势，则B赢
                    ans = map.get('B');
                } else if (!map.containsKey('B')) {
                    // 没有B手势，只有A、C手势，则C赢
                    ans = map.get('C');
                } else {
                    // 没有C手势，只有A、B手势，则A赢
                    ans = map.get('A');
                }

                ans.sort(String::compareTo);
                ans.forEach(System.out::println);

                break;
        }
    }
}