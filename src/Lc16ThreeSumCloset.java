import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/5/3 10:38
 */
public class Lc16ThreeSumCloset {

    // 不对
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int l=0,r=2,ans=nums[0]+nums[1]+nums[2];
        int sum=ans;
        while(r+1<nums.length){
            if(sum<target){
                sum+=nums[++r];
                sum-=nums[l++];
                ans=sum;
            }
            else{
                if(sum-target<target-ans)
                    ans=sum;
                break;
            }
        }
        return ans;
    }

    // 排序 一层循环枚举 里面双指针枚举
    public int threeSumClosest1(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0, n = nums.length;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            int x = nums[i];
            if (i > 0 && x == nums[i - 1]) {
                continue;
            }
                int s = x + nums[i + 1] + nums[i + 2];
                if (s > target) { // 后面无论怎么选，选出的三个数的和不会比 s 还小
                    if (s - target < minDiff) {
                        ans = s; // 由于下面直接 break，这里无需更新 minDiff
                    }
                    break;
                }

                s = x + nums[n - 2] + nums[n - 1];
                if (s < target) { // x 加上后面任意两个数都不超过 s，所以下面的双指针就不需要跑了
                    if (target - s < minDiff) {
                        minDiff = target - s;
                        ans = s;
                    }
                    continue;
                }

                // 双指针
                int j = i + 1, k = n - 1;
                while (j < k) {
                    s = x + nums[j] + nums[k];
                    if (s == target) {
                        return target;
                    }
                    if (s > target) {
                        if (s - target < minDiff) { // s 与 target 更近
                            minDiff = s - target;
                            ans = s;
                        }
                        k--;
                    } else { // s < target
                        if (target - s < minDiff) { // s 与 target 更近
                            minDiff = target - s;
                            ans = s;
                        }
                        j++;
                    }
                }
            }
        return ans;
    }
    public static void main(String[] args)
    {
        int[] nums={-1,2,1,-4};
        int target=1;
        Lc16ThreeSumCloset lc16ThreeSumCloset=new Lc16ThreeSumCloset();
        System.out.println(lc16ThreeSumCloset.threeSumClosest(nums,target));
    }
}




