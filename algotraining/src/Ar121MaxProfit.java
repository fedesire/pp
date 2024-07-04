import java.util.TreeSet;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。你只能选择 某一天 买入这只股票，
 * 并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * @date 2024/1/5 10:29
 */
public class Ar121MaxProfit {
    public int maxProfit1(int[] prices) {
        TreeSet<Integer> record=new TreeSet<>();
        int res=0;
        record.add(prices[0]);
        for(int i=1;i<prices.length;i++){
            int minPrice=record.first();
            if(minPrice<prices[i])
                res=Math.max(res,prices[i]-minPrice);

            record.add(prices[i]);
        }
        return res;

    }
    //也可以从0的位置开始遍历 此时最初的minPrice设为Integer.MAX_VALUE
    //遍历价格数组一遍，记录历史最低点
    //上面的解法是用Treeset来找某一个元素前面的最小值 这里直接用一个变量来记录就可以
    public int maxProfit(int[] prices) {
        int res=0;
        int minPrice=prices[0];
        for(int i=1;i<prices.length;i++){
            if(prices[i]>minPrice){
                int curProfit=prices[i]-minPrice;
                res=Math.max(res,curProfit);
            }
            else{
                minPrice=prices[i];
            }
        }
        return res;
    }
    public int maxProfit2(int[] prices) {
        int res=0;
        int minPrice=prices[0],n=prices.length;//竟然先保存n 就能让运行时间从原本的击败43%到100%
        for(int i=1;i<n;i++){
            if(prices[i]<minPrice){
                minPrice=prices[i];
            }
            else if(prices[i]-minPrice>res){
                res=prices[i]-minPrice;
            }
        }
        return res;
    }
}
