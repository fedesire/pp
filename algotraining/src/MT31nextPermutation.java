/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/5/10 9:16
 */
public class MT31nextPermutation {
    /*例如 2, 6, 3, 5, 4, 1 这个排列， 我们想要找到下一个刚好比他大的排列，于是可以从后往前看 我们先看后两位 4, 1
    能否组成更大的排列，答案是不可以，同理 5, 4, 1也不可以 直到3, 5, 4, 1这个排列，因为 3 < 5， 我们可以通过重新排列这一段数字
    ，来得到下一个排列 因为我们需要使得新的排列尽量小，所以我们从后往前找第一个比3更大的数字，发现是4 然后，我们调换3和4的位置，
    得到4, 5, 3, 1这个数列 因为我们需要使得新生成的数列尽量小，于是我们可以对5, 3, 1进行排序，可以发现在这个算法中，我们得到的
    末尾数字一定是倒序排列的，于是我们只需要把它反转即可 最终，我们得到了4, 1, 3, 5这个数列 完整的数列则是2, 6, 4, 1, 3, 5。*/

    // 从后往前找第一个[i -1] < [i]，然后再从后往前找首个大于[i-1]的进行交换，再把[i]到[end]倒序。
    public void nextPermutation(int[] nums)
    {
        boolean flag = false;
        for (int i =nums.length-1; i >= 1;  i--) {
            if(nums[i-1]<nums[i]){
                int index=i-1;
                for (int j = nums.length-1; j>index; j--) {
                    if(nums[j]>nums[index]){
                        int temp = nums[index];
                        nums[index] = nums[j];
                        nums[j] = temp;
                        reverse(nums,index+1);
                        flag=true;
                        break;
                    }
                }
                // 这里必须要有break
                break;
            }

        }
        if(!flag) reverse(nums,0);

    }

    private void reverse(int[] nums, int i) {
        for (int j = i,k=nums.length-1; j<k; j++,k--) {
            int temp = nums[j];
            nums[j] = nums[k];
            nums[k] = temp;
        }
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        new MT31nextPermutation().nextPermutation(nums);
        for (int i : nums) {
            System.out.print(i+" ");
        }
    }
}
