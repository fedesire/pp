import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author xqi
 * @version 1.0
 * @description: 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 * @date 2024/1/4 11:04
 */
public class Ts128LonestCons {
    //可以通过 但是treeset插入删除查找的时间复杂度都是ologn 所以将nums中数全部插入treeset的时间复杂度就不是on了 不符合要求
    //HashSet查找contains的时间复杂度是o1
    public static int longestConsecutive(int[] nums) {
        if(nums==null||nums.length==0)
            return 0;
        TreeSet<Integer> record=new TreeSet<>();
        for(int num:nums){
            record.add(num);
        }
        System.out.println(record);
        int res=0;
        int last=record.first();
        int count=1;
        for(int num:record){
            if(num-last==1){
                count++;
                //下面的res更新很好理解 这里res也更新是因为当输入案例是01234这种一直满足num-last=1 一直走if分支的 如果res
                //不更新 最后res就是1 其实这里如果res不更新 最后循环结束后就要return res>count?res:count count代表最后一次的
                res=Math.max(res,count);
            }
            else{
                res=Math.max(res,count);
                count=1;
            }
            last=num;
        }
        return res;
    }

    //外层循环需要 O(n) 的时间复杂度，只有当一个数是连续序列的第一个数的情况下才会进入内层循环，然后在
    // 内层循环中匹配连续序列中的数，因此数组中的每个数只会进入内层循环一次。根据上述分析可知，总时间复杂度为 O(n)
    public static int longestConsecutive1(int[] nums) {
        if(nums==null||nums.length==0)
            return 0;
        HashSet<Integer> record=new HashSet<>();
        for (int num : nums) {
            record.add(num);
        }
        int res=0;
        //对每一个数字找从他开始的连续序列 所以先将数组中所有元素放入hs 之后查找每个元素都是o1
        //同时存在可以剪枝的情况 即当num存在比他小一的数时 此时就不用找了 因为找到了也不会比num-1得到的结果大
        for (int num : nums) {
            int count=1;
            if(!record.contains(num-1)){
                while(record.contains(num+1)){
                    count++;
                    num++;
                }
                res=Math.max(res,count);

            }
        }
        return res;

    }

        public static void main(String[] args) {
        int[] nums={0,3,7,2,5,8,4,6,0,1};
        System.out.println(longestConsecutive(nums));
    }
}
