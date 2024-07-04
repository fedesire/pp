/**
 *给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 *不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成
 * @date 2023/12/29 10:27
 */
public class Ar80removeDupII {
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        // nums中, [0...k]的元素为满足最多出现2次重复的序列 k指向当前结果的最后一个 freq代表最后一个元素出现的次数
        // 所以刚开始就是1 不能是0
        int k = 0, freq = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[k]) {
                nums[++k] = nums[i];
                freq = 1;
            }
            else {
                if (freq == 1) {
                    nums[++k] = nums[i];
                    freq = 2;
                }
            }

        }
        return k + 1;
    }

    //双指针解决的时候 指针如果是从数组开始扫描 也不一定每次都必须指向第一个元素 具体情况讨论 像这一题就可以直接指向第三个元素
    public static int remove1(int[] nums){
        if(nums.length<=2)
            return nums.length;
        int slow=2,fast=2;//slow指向当前可加入的位置 fast指向当前要处理的元素 因为重复元素可出现两次 所以只要将新扫描的元素
        // 和倒数第二个加入结果集中的元素比较就行
        while(fast<nums.length){
            if(nums[fast]!=nums[slow-2]){
                nums[slow++]=nums[fast];
            }
            fast++;
        }
        return slow;

 /*       int slow=1,fast=2;//slow指向当前加入了结果序列的最后一个 fast指向当前要处理的元素
        while(fast<nums.length){
            if(nums[fast]!=nums[slow-1]){
                nums[++slow]=nums[fast];
            }
            fast++;
        }
        return slow+1;*/


    }
}
