import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * 最长和谐子序列
 * 已解答
 * 简单
 * 相关标签
 * 相关企业
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
 *
 * 现在，给你一个整数数组 nums ，请你在所有可能的子序列中找到最长的和谐子序列的长度。
 *
 * 数组的子序列是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,3,2,2,5,2,3,7]
 * 输出：5
 * 解释：最长的和谐子序列是 [3,2,2,2,3]
 * @date 2024/4/26 10:15
 */
public class Ar594findLHS {
    public int findLHS(int[] nums) {
        HashMap<Integer,Integer> hm=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            hm.put(nums[i],hm.getOrDefault(nums[i],0)+1);
        }
        int ans=0;
        for (Integer i : hm.keySet()) {
            if(hm.containsKey(i+1))
                ans=Math.max(ans,hm.get(i)+hm.get(i+1));
        }
        return ans;
    }

    // 排序+双指针（滑动窗口）
    public int findLHS1(int[] nums) {
        Arrays.sort(nums);
        int l=0,r=0;
        int ans=0;
        // 每次都会移动右指针 即判断当前的r和l 如果超了就移动左指针 移动完再判断nums[r]-nums[l]==1 两个if位置不能颠倒
        while(r<nums.length){
            while(nums[r]-nums[l]>1){
                l++;
            }
            if(nums[r]-nums[l]==1)
                ans=Math.max(ans,r-l+1);

            r++;
        }
        return ans;
    }

}
