/**
 *给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 * @date 2024/1/5 11:42
 */
public class Ar55canJump {
    public static boolean getTo(int[] nums,int n){
        if(n==0)
            return true;
        if(nums[n-1]>0)
            return getTo(nums,n-1);
        else{
            int i=2;
            while(n-i>=0&&nums[n-i]<i)
                i++; //i++不是i--
            if(n-i<0)
                return false;
            return getTo(nums,n-i);
        }

    }
    public static boolean canJump(int[] nums){
        return getTo(nums, nums.length-1);
    }

    public boolean canJump1(int[] nums) {
        int maxPos=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                maxPos=Math.max(maxPos,nums[i]+i);
            }
            if(maxPos>=nums.length-1)
                return true;
            else if(maxPos<=i)
                return false;
        }
        return true; //return true false都对 但是没有就会编译报错

    }
    public boolean canJump2(int[] nums) {
        int maxPos=0;
        if(nums==null||nums.length<=1)
            return true;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                maxPos=Math.max(maxPos,nums[i]+i);
            }
            else{
                if(maxPos<=i)
                    return false;
            }
            if(maxPos>=nums.length-1)
                return true;
        }
        return true;
    }

    public boolean canJump3(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        int coverRange = 0;
        for (int i = 0; i <= coverRange; i++) { //这里i不是<length
            coverRange = Math.max(coverRange, i + nums[i]);
            if (coverRange >= nums.length - 1){
                return true;
            }
        }
        return false;
    }
    public boolean canJump4(int[] nums) {
        int max = 0;
        for(int i=0; i<nums.length; i++){
            if (i > max){ //i>max是为了判断当前是否能到达i这一步
                return false;
            }
            max = Math.max(max, i + nums[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums={3,2,1,0,4};
        System.out.println(canJump(nums));
    }
}
