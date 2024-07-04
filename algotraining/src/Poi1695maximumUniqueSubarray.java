import java.util.HashSet;

/**
 * 删除子数组的最大得分
 * 已解答
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。删除子数组的 得分 就是子数组各元素之 和 。
 * 返回 只删除一个 子数组可获得的 最大得分 。
 * 如果数组 b 是数组 a 的一个连续子序列，即如果它等于 a[l],a[l+1],...,a[r] ，那么它就是 a 的一个子数组。
 * 示例 1：
 * 输入：nums = [4,2,4,5,6]
 * 输出：17
 * 解释：最优子数组是 [2,4,5,6]
 * @date 2024/5/2 8:05
 */
public class Poi1695maximumUniqueSubarray {
    public int maximumUniqueSubarray(int[] nums) {
        int l=0,r=0,ans=0,temp=0;
        HashSet<Integer> hs=new HashSet<>();
        while(r<nums.length){
            // 如果哈希表中已经有了即将加入滑动窗口的元素
            while(hs.contains(nums[r])){
                // 需要不断的把窗口的左边元素移除窗口
                // 元素和需要减去移除的值
                temp-=nums[l];
                hs.remove(nums[l++]);
            }
            // 此时滑动窗口可以接纳nums[r]2
            hs.add(nums[r]);
            temp+=nums[r];
            ans=Math.max(ans,temp);
            r++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums={4,2,4,5,6};
        System.out.println(new Poi1695maximumUniqueSubarray().maximumUniqueSubarray(nums));
    }
}
