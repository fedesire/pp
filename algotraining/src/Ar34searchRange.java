/**
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * @date 2024/2/18 9:22
 */
public class Ar34searchRange {
    //两次二分查找，分开查找第一个和最后一个
    public int[] search(int[] nums,int target){
        int l=0,r=nums.length-1;
        //first是第一个等于target的位置 last是最后一个等于target的位置
        int first=-1,last=-1;
        while(l<=r){
            int mid=l+(r-l)/2;
            // 相等的时候不退出 而是收缩右边界 更新first 这样循环结束时就能得到first即第一个等于target的位置
            // 同理 相等的时候不退出 而是收缩左边界 更新last 这样循环结束时就能得到last即最后一个等于target的位置
            if(nums[mid]==target){
                first=mid;
                r=mid-1;
            }
            else if(nums[mid]<target)
                l=mid+1;
            else r=mid-1;
        }
        if(first==-1)
            return new int[]{-1,-1};
        l=first;
        r=nums.length-1;
        while(l<=r){
            int mid=l+(r-l)/2;
            if(nums[mid]==target){
                last=mid;
                l=mid+1;
            }
            else if(nums[mid]<target)
                l=mid+1;
            else r=mid-1;
        }
        return new int[]{first,last};
    }

    public static void main(String[] args) {
        new Ar34searchRange().search(new int[]{5,7,7,8,8,8,10},8);
    }
}
