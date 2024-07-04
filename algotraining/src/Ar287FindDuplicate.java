/**
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 *
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * @date 2024/5/10 9:57
 */
/*二分查找除了对索引二分，还有值域二分
数组元素是 1 - n 中的某一个，出现的位置不确定，但值域是确定的。

对索引二分，一般用于有序数组中找元素，因为索引的大小可以反映值的大小，因此对索引二分即可。
对值域二分。重复数落在 [1, n] ，可以对 [1, n] 这个值域二分查找。
mid = (1 + n) / 2，重复数要么落在[1, mid]，要么落在[mid + 1, n]。

遍历原数组，统计 <= mid 的元素个数，记为 k。

如果k > mid，说明有超过 mid 个数落在[1, mid]，但该区间只有 mid 个“坑”，说明重复的数落在[1, mid]。

相反，如果k <= mid，则说明重复数落在[mid + 1, n]。

对重复数所在的区间继续二分，直到区间闭合，重复数就找到了。

时间复杂度：二分法O(logN)O(logN)O(logN)，但二分法内部遍历了一次数组O(N)O(N)O(N)，综合为O(NlogN)O(NlogN)O(NlogN)
空间复杂度：O(1)O(1)O(1)
*/
class Ar287FindDuplicate {
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                // 下一轮搜索的区间 [mid+1...r]
                l = mid + 1;
            } else {
                // 下一轮搜索的区间 [l..mid-1]
                r = mid - 1;
            }
        }
        return l;
    }
}
