import java.util.HashMap;

/**
 * @author xqi
 * @version 1.0
 * @description 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列。
 * @date 2024/5/6 17:46
 */
class DS560SubArraySum {
    // 可以ac 但是太慢了
    public int subarraySum1(int[] nums, int k) {
        int n=nums.length;
        int[] preSum=new int[n+1];
        for(int i=1;i<=n;i++){
            preSum[i]=preSum[i-1]+nums[i-1];
        }
        int res=0;
        for(int i=1;i<=n;i++){
            for(int j=0;j<i;j++){
                if(preSum[i]-preSum[j]==k)
                    res++;
            }
        }
        return res;

    }

    // 前缀和+哈希表
    public int subarraySum(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        //细节，这里需要预存前缀和为 0 的情况，会漏掉前几位就满足的情况
        //例如输入[1,1,0]，k = 2 如果没有这行代码，则会返回0,漏掉了1+1=2，和1+1+0=2的情况
        //输入：[3,1,1,0] k = 2时则不会漏掉
        //因为presum[3] - presum[0]表示前面 3 位的和，所以需要map.put(0,1),垫下底
        // 表示前缀和为0出现1次了 map中记录的是nums[0..i]0到每个i的和的次数 并不是任意两个下标之间的和的次数
        map.put(0, 1);
        int count = 0;
        int presum = 0;
        // 考虑以 i结尾的和为 k的连续子数组个数时只要统计有多少个前缀和为 pre[i]−k的 pre[j] 即可
        for (int x : nums) {
            presum += x;
            //当前前缀和已知，判断是否含有 presum - k的前缀和，那么我们就知道某一区间的和为 k 了。
            if (map.containsKey(presum - k)) {
                count += map.get(presum - k);//获取次数
            }
            //更新
            map.put(presum,map.getOrDefault(presum,0) + 1);
        }
        return count;
    }
}
