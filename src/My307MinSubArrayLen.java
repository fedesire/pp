/**
 * @author xqi
 * @version 1.0
 * @description: 给定一个整型数组和数字s 找到数组中最短的连续子数组 使得连续子数组的数字之和>=s 返回最短子数组的长度值
 * @date 2023/12/12 16:53
 */
public class My307MinSubArrayLen {
    public static int minSubArrayLen(int[] nums,int s){
        int res=nums.length+1;
        int l=0,r=-1;// nums[l...r]为我们的滑动窗口
        int sum=0;
        // 窗口的左边界在数组范围内,则循环继续 窗口右边界的限制条件在下面if里 因为r先加到头之后循环还要继续 所以外循环的条件是控制l
        while(l<nums.length){
            if(r+1<nums.length&&sum<s)//注意是<而不是<= 因为=时右边界就不应该向右拓展 该是左边界向右拓展了
                // 一旦有数组用[]取值时就要小心是否会出现数组越界问题 所以if的判断里有r+1<length
                sum+=nums[++r];
            else // r已经到头 或者 sum >= s
                sum-=nums[l++];
            if(sum>=s)
                res=Math.min(res,r-l+1);

        }
        if(res==nums.length+1)
            return 0;

        return res;
    }

    public static int minSubArrayLen1(int[] nums,int s){
        int res=nums.length+1;
        int l=0,r=0;//nums[l...r)为我们的滑动窗口
        int sum=0,n=nums.length;
        while(l<n){
            if(r<n&&sum<s)
                sum+=nums[r++];
            else{
                sum-=nums[l++];
            }
            if(sum>=s)
                res=Math.min(res,r-l);
        }
        if(res==nums.length+1)
            return 0;
        return res;
    }
}
