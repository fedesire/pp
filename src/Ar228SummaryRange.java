import java.util.ArrayList;
import java.util.List;

/**
 * 汇总区间 给定一个  无重复元素 的 有序 整数数组 nums 。返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表 。也就是说，nums
 * 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x
 * 输入：nums = [0,1,2,4,5,7]
 * 输出：["0->2","4->5","7"]
 * @date 2024/2/8 11:10
 */
public class Ar228SummaryRange {
    public List<String> summaryRanges(int[] nums) {
        List<String> res=new ArrayList<>();
        if(nums==null||nums.length==0) return res;
        StringBuilder temp=new StringBuilder();
        temp.append(nums[0]);
        int start=nums[0],last=start;
        if(nums.length==1) {
            res.add(temp.toString());
            return res;
        }
        for(int i=1;i<nums.length;i++){
            if(nums[i]==last+1){
                last=nums[i];
            }
            else{
                if(last!=start)
                    temp.append("->").append(last);
                res.add(temp.toString());
                start=nums[i];
                last=nums[i];
                temp.delete(0,temp.length());
                temp.append(nums[i]);
            }
        }
        if(last!=start)
            temp.append("->").append(last);
        res.add(temp.toString());
        return res;
    }

    public List<String> summaryRanges1(int[] nums){
        List<String> res=new ArrayList<>();
        int i=0,n=nums.length;
        while (i<n){
            int low=i;
            while(i+1<n&&nums[i]+1==nums[i+1]) i++;
            int high=i;
            StringBuilder temp=new StringBuilder(Integer.toString(nums[low]));
            if(low<high) {
                temp.append("->").append(nums[high]);
            }
            /*if(low==high)
                res.add(String.valueOf(nums[low]));
            else {
                res.add(String.format("%d->%d",nums[low],nums[high]));
            }*/
            res.add(temp.toString());
            i++;//容易漏写
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums={0,1,2,4,5,7};
        System.out.println(new Ar228SummaryRange().summaryRanges1(nums));
    }
}
