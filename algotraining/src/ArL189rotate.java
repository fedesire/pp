import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description: 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * @date 2024/1/1 11:22
 */
public class ArL189rotate {
    //我写的使用辅助数组的方法
    public static void rotate(int[] nums,int k){
        k=k%nums.length;
        int[] aux=new int[nums.length];
        for(int i=0;i<nums.length;i++)
            aux[i]=nums[i];
        for(int i=0;i<k;i++)
            nums[i]=aux[i-k+nums.length];
        for(int i=k;i<nums.length;i++)
            nums[i]=aux[i-k];

    }

    public static void rotate1(int[] nums,int k){
        int[] aux=new int[nums.length];
        for(int i=0;i<nums.length;i++)
            aux[(i+k)%nums.length]=nums[i];
//        nums=Arrays.copyOf(aux,nums.length);//这样不行 nums是指向了一个新的数组对象 原有的数组里面的元素值不会变
        for(int i=0;i<nums.length;i++)
            nums[i]=aux[i];
//        System.arraycopy(aux,0,nums,0,nums.length);// 用systemarraycopy可以

    }

    public static void reverse(int[] nums,int l,int r){
        while (l<r){
            if(nums[l]!=nums[r]){
                int t=nums[l];
                nums[l++]=nums[r];
                nums[r--]=t;
            }
        }
    }

    //当我们将数组的元素向右移动 k次后，尾部 kmodn 个元素会移动至数组头部，其余元素向后移动 kmodn 个位置
    // 所以可以先将所有元素翻转，这样尾部的 kmodn个元素就被移至数组头部，然后我们再翻转前k个元素即[0,k-1]
    //  区间的元素和 [kmodn,n−1] 区间的元素即能得到最后的答案。
    public static void rotate2(int[] nums,int k){
        k=k%nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);


    }
}
