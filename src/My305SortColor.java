/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/15 14:54
 */
public class My305SortColor {
    public static void sortColors(int[] nums){
        //[0...zero) zero的意思可看作是能立马存储0的位置 (two...n-1] two是能立马存储2的位置
        //因为[two+1..n-1]的位置才是确定了的已经存储了2的位置 two的位置上的数还不确定 所以循环的条件是i<=two
        int zero=0,two=nums.length-1;
        for (int i = 0; i <= two; ) {  //注意这里是i<=two 第一次写的时候写错了 和bobo算法里边界不同
            if(nums[i]==1)
                i++;
            else if(nums[i]==0){  //注意这里i++ 下面不
                swap(nums,i++,zero++);
            }
            else
                swap(nums,i,two--);
        }
    }
    public static void swap(int[] nums,int i,int j){
        int t=nums[i];
        nums[i]=nums[j];
        nums[j]=t;
    }

    //非常精妙简洁的双指针算法 一次遍历 无需交换
    /*首先，所有数都≤2，那么索性把所有数组置为2，然后遇到所有≤1的，就再全部置为1，，覆盖了错误的2，
    这时候所有2的位置已经正确，最后所有≤0的全部置为0，也就覆盖了一些错误的1，这时候，0和1的位置
    都正确。最重要的就是需要两个指针指向下一个1或0的位置
    效果就是假设有9个数 2个0 3个1 4个2 那么这九个位置都赋值过2 前5个位置都赋值过1 前两个位置赋值过0
    */
    public void sortColors2(int[] nums) {
        int n0 = 0, n1 = 0;
        for(int i = 0; i < nums.length; i++){
            int num = nums[i];
            nums[i] = 2;
            if(num < 2){
                nums[n1++] = 1;
            }
            if(num < 1){
                nums[n0++] = 0;
            }
        }
    }

    public static void printArr(int[] nums){
        for(int num: nums)
            System.out.print(num + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        int[] nums = {2, 2, 2, 1, 1, 0};
        sortColors(nums);
        printArr(nums);
    }

}
