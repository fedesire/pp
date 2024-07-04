/**
 * @author xqi
 * @version 1.0
 * @description: 搜索旋转排序数组 nums 中的每个值都 独一无二
 * @date 2024/2/2 10:37
 */
/*将数组从中间分开成左右两部分的时候，一定有一部分的数组是有序的。这启示我们可以在常规二分查找的时候查看当前 mid 为分割
位置分割出来的两个部分 [l, mid] 和 [mid + 1, r] 哪个部分是有序的，并根据有序的那个部分确定我们该如何改变二分查找的上下界，
因为我们能够根据有序的那部分判断出 target 在不在这个部分：*/

/* 旋转排序数组可分为N1 + N2两个部分，如：[4,5,6,7,1,2,3]，N1为[4,5,6,7]，N2为[1,2,3]
 * 必然满足以下两个条件：
 * 1. N1和N2都是分别递增的；
 * 2. N1中的所有元素大于N2中的所有元素;
 * 以上两个条件可推出：nums[0]是N1中最小的数，即nums[0] > N2中的所有元素
 * 而mid不是在N1内就是在N2内
 * 所以：如果nums[0] <= nums[mid]，即mid落在了N1内，则[0, mid]肯定是有序的
 *       否则mid落在了N2内，则[mid, n)肯定是有序的*/
public class _Ar33search {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l=0,r=nums.length-1;
        // 先根据 nums[mid] 与 nums[l] 的关系判断 mid 是在左段还是右段，接下来再判断 target 是在 mid 的左边还是右边，
        // 从而来调整左右边界 l 和 r。
        while(l<=r){
            int mid=l+(r-l)/2;
            if(target==nums[mid]) return mid;
            //如果前半部分有序 必须有= 否则[3,1]寻找1就找不到 因为此时l=mid=0 r=1 进入下面else循环就是认为[mid，r]是有序
            // 即nums[0],nums[1]有序 肯定不对
            //[l, mid]是有序数组，[mid+1, r]是旋转数组
            if(nums[l]<=nums[mid]){
                // target在l到mid之间 所以r移动到mid-1
                if(nums[l]<=target&&target<nums[mid]){
                    r=mid-1;
                }
                // 可以确定target不在l到mid之间 所以l移动到mi+1
                else{
                    l=mid+1;
                }
            }
            //[l,mid]不是有序的 则[mid, r]肯定是有序数组
            else{
                if(nums[mid]<target&&target<=nums[r]){
                    l=mid+1;
                }
                //写成这种也可以
               /* if(nums[mid+1]<=target&&target<=nums[r]){
                    l=mid+1;
                }*/
                else{
                    r=mid-1;
                }
            }
        }
        return -1;
    }

    // 和上面的解法是同样的思想 也是先判断 mid 是在左段还是右段，接下来再判断 target 是在 mid 的左边还是右边，从而来调整左右边界l和r。
    // 只不过这里是根据 nums[mid] 与 nums[r] 的关系来判断 mid 是在左段还是右段
    public int search1(int[] nums, int target) {
        int l=0,r=nums.length-1;
        while(l<=r){
            int mid=l+(r-l)/2;
            if(nums[mid]==target)
                return mid;
            // 这里不需要加= 是因为在只剩两个数的时候 mid也是等于l而不是等于r 只剩一个数上面就会return了 上面的解法需要nums[l]<=nums[mid]
            if(nums[mid]<nums[r]){
                if(nums[mid]<target&&target<=nums[r]){
                    l=mid+1;
                }
                else
                    r=mid-1;
            }
            else{
                if(nums[l]<=target&&target<nums[mid]){
                    r=mid-1;
                }
                else
                    l=mid+1;
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        System.out.println(new _Ar33search().search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    }
}
