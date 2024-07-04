/**
 * 寻找旋转排序数组中的最小值
 * @date 2024/2/18 10:09
 */
public class Ar153findMin {
    //这道题mid和right比较细节会少一点 用mid和left比较细节会更多
    public int findMin(int[] nums) {
        int l=0,r=nums.length-1;
        while(l<=r) { //有没有等于都行
            //必须要先判断提前返回 否则当数组是有序的情况下 最小值是nums[0] 下面第一次判断后l=m+1就会错过
            //下面的解法当数组是有序的情况下会是high一直往左收缩 直到到达nums[0] 所以没有判断也行
            if( nums[l] <= nums[r])
                return nums[l];//左边已经满足单调
            int m = ((r-l)>>1) + l;
            if(nums[l] <= nums[m]) l = m + 1;//mid和left之间单调递增说明必不在这里 所以移动left指针 因为寻找的是最小值
            //这种情况nums[l]<nums[m] 说明m处的数已经比一个数大了 不可能是最小值了 所以left移动的时候移动到m+1
            //下面的情况是nums[m]<nums[l] m处的数就无法排除出去 所以right移动的时候就移动到m
            else r = m;//最小值肯定在mid left之间 所以移动right
        }
        return nums[l];

    }

    public int findMin1(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        //在左闭右闭区间寻找 循环条件也可以是< 这里不是寻找一个确定的target 而是不断逼近区间直到low=high只剩一个数就是结果
        //这里必须是low<high 不能有=
        while (low < high) {
            //可以加上这句提前结束循环 没有也行
            if(nums[low]<=nums[high]) return nums[low];

            int pivot = low + (high - low) / 2;
            //pivot中点因为是向下取整 所以在只剩两个数的时候pivot也是等于low而不是等于
            //high 所以这里可以没有等于号 在只剩一个数的时候循环已经退出了 上面nums[l]和nums[m]比较要有等于 因为l可能等于m
            if (nums[pivot] < nums[high]) {
                //pivot比high小 说明最小值不是在右半段而是在左边 但pivot此时可能就是最小值 所以high移到pivot 而不是 pivot-1
                high = pivot;
            }
            // nums[pivot] > nums[high] 所以nums[pivot]不可能是最小值 所以low直接到pivot+1
            else {
                low = pivot + 1;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        System.out.println(new Ar153findMin().findMin1(new int[]{5,6,7,8}));
    }
}
