/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/29 9:48
 */
public class ArL26removeDupli {
    //
    public static int removeDuplicates(int[] nums){
        //必须要有 否则数组为空时也会返回1 nums=null是传入的数组就是null length=0是传入的数组是[]
        if(nums==null||nums.length==0)
            return 0;
        // nums中, [0...k]的元素均为不重复元素 k指向不重复元素的最后一个 每次numsi numsk不等时 即遇到新的元素可以加入前面的不重复序列了
        int k=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=nums[k])
                nums[++k]=nums[i];

        }

        //优化一下 i可以从1开始比较 当数组中全部都是非重复元素时 上面的代码会每次都要赋值 加一个判断条件 k是已经确定的
        // 不重复元素的最后一个 k+1就是下一个可存放的位置 所以只要确认i是不是在k+1后面就行 只有在后面的时候才赋值
        for(int i=1;i<nums.length;i++){
            if(nums[i]!=nums[k]) {
                if (i - k > 1)
                    nums[k + 1] = nums[i];
                k++;
            }

        }
        return k+1;


    }
}
