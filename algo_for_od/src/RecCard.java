import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/19 13:45
 */
/*
* 有这么一款单人卡牌游戏，牌面由颜色和数字组成，颜色为红、黄、蓝、绿中的一种，数字为0-9中的一个。游戏开始时玩家从手牌中选取一张卡牌打出，
* 接下来如果玩家手中有和他上一次打出的手牌颜色或者数字相同的手牌，他可以继续将该手牌打出，直至手牌打光或者没有符合条件可以继续打出的手牌。

现给定一副手牌，请找到最优的出牌策略，使打出的手牌最多。

输入描述
输入为两行

第一行是每张手牌的数字，数字由空格分隔，
第二行为对应的每张手牌的颜色，用r y b g这4个字母分别代表4种颜色，字母也由空格分隔。
手牌数量不超过10。

输出描述
输出一个数字，即最多能打出的手牌的数量。

用例
输入
1 4 3 4 5
r y b b r
输出	3
说明
如果打（1, r）-> (5, r)，那么能打两张。

如果打（4，y) -> (4, b) -> (3, b)，那么能打三张。

输入	1 2 3 4
r y b l
输出	1
说明	没有能够连续出牌的组合，只能在开始时打出一张手牌，故输出1
*/
import java.util.Arrays;
import java.util.Scanner;

public class RecCard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[] colors = sc.nextLine().split(" ");

        System.out.println(getResult(nums, colors));
    }

    static class Card {
        int num;
        char color;

        public Card(int num, String color) {
            this.num = num;
            this.color = color.charAt(0);
        }
    }

    public static int getResult(int[] nums, String[] colors) {
        int n = nums.length;
        Card[] cards = new Card[n];
        for (int i = 0; i < n; i++) cards[i] = new Card(nums[i], colors[i]);

        int[] ans = {0};
        boolean[] used = new boolean[n];
        dfs(cards, used, null, 0);
        return res;
    }


    static int res=0;
    public static void dfs(Card[] cards, boolean[] used, Card last, int count) {
//        ans[0] = Math.max(ans[0], count);
        res=Math.max(res,count);

        for (int i = 0; i < cards.length; i++) {
            if (used[i]) continue;

            Card cur = cards[i];
            if (last != null && last.num != cur.num && last.color != cur.color) continue;
            // 有和他上一次打出的手牌颜色或者数字相同的手牌，他可以继续将该手牌打出
            used[i] = true;
            dfs(cards, used, cur, count + 1);
            used[i] = false;
        }
    }
}