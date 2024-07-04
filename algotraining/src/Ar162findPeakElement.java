/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/31 11:36
 */
public class Ar162findPeakElement {
    public int findPeakElement(int[] nums) {
        int n=nums.length;
        int l=0,r=n-1;
        //这里是n-1是为了防止下面取nums[mid+1]越界
        while(l<r){
            int mid=l+(r-l)/2;
            if(nums[mid]>nums[mid+1])
                r=mid;
            else
                l=mid+1;
        }
        return l;
    }
}
