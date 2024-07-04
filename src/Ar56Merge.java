import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 合并区间 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，
 * 并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * @date 2024/2/8 12:19
 */
public class Ar56Merge {
    // 按照区间的左端点排序，那么在排完序的列表中，可以合并的区间一定是连续的
    public int[][] merge(int[][] intervals) {
        int n=intervals.length;
        Arrays.sort(intervals,(o1, o2)->(o1[0]-o2[0]));
        List<int[]> list=new ArrayList<>();
        // 遍历区间 如果当前遍历到的区间的左端点大于结果数组中最后区间的右端点，说明可以直接将当前区间加入结果数组中 否则就说明需要
        // 合并 即将结果数组里最后区间的右端点更新为最大值
        for(int[] interval:intervals){
            if(list.isEmpty() ||interval[0]>list.get(list.size()-1)[1]){
                list.add(interval);
            }
            else{
                list.get(list.size()-1)[1]=Math.max(list.get(list.size()-1)[1],interval[1]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
class Solution {
    public int[][] merge(int[][] intervals) {
        // 先按照区间起始位置排序
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        // 遍历区间
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval: intervals) {
            // 如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，
            // 则不合并，直接将当前区间加入结果数组。
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                // 反之将当前区间合并至结果数组的最后区间
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }
}