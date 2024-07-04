/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/28 10:18
 */
public class ArL27removeEles {
    /*双指针法 右指针指向当前要处理的元素 左指针指向下一个将要赋值的位置 最坏的情况下左右指针各遍历了数组一次
    * 双指针解法并不一定是要两个指针一首一尾 这种l r开始都指向开头也行 一个指针是作为循环变量 每一轮都会+1右移
    *  另一个指针先初始化 后面只有当满足某个条件时才会右移 但是该方法会导致很多重复赋值操作*/
    public static int removeElements(int[] nums,int val){
        int left=0;
        for(int right=0;right<nums.length;right++){
            if(nums[right]!=val)
                nums[left++]=nums[right];
        }
        return left;//注意返回不是left+1 每次赋值完后left都右移指向后一位置 所以遍历完后left就刚好代表移除val后数组的新长度
    }

    /*上一种方法当数组开头元素为val时会需要后面的所有元素都要向前移 优化一下 只将数组末位的元素移到开头 就不需要其他元素移动了
    * 所以此时的双指针就是开始时指向一首一尾 向中间移动遍历数组 以left指针为主导 当left处的元素为val时就将right处的元素复制
    * 过来到left处 然后right左移一位 当left处的元素不为val时 只需left右移一位继续扫描即可 当left right重合时 左右指针
    * 遍历完数组所有元素 如果最后left=right的时候 left处元素等于val 则只做一次无意义的赋值操作后right左移 left不变
    * 此时left指向的元素(现在是val)已不是所需的目标 如果最后left=right的时候 left处元素不等于val 则left右移一位到了之前
    * right已经扫描过的元素(已经经历过复制到左边去 已经是处理过的元素) 已不是所需的目标
    *  这两种情况下 当前left索引前面的所有位置此刻的元素就是全部非val元素了 所以return left都能得到结果*/
    public static int removeElements1(int[] nums,int val){
        int left=0,right=nums.length-1;
        while(left<=right){
            if(nums[left]==val){
                nums[left]=nums[right--];
            }
            else{
                left++;
            }
        }
        return left;
    }

    //上述移除元素的代码稍微改一下就能解决把所有val元素移到数组末尾的问题
    public static void moveElements(int[] nums,int val){
        int left=0;
        for(int right=0;right<nums.length;right++){
            if(nums[right]!=val){
                int t=nums[left];
                nums[left++]=nums[right];
                nums[right]=t;
            }
        }
    }

    //也可以移动 但这样没有保持数组非val元素的相对顺序 在lc movezeroes不能通过
    public static void moveElements1(int[] nums,int val){
        int left=0,right=nums.length-1;
        while(left<=right){
            if(nums[left]==val){
                int t=nums[left];
                nums[left]=nums[right];
                nums[right--]=t;

            }
            else left++;
        }
    }

}
