/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/31 21:55
 */
public class ArK169majorityEle {
    public static int majorityElement(int [] nums){
        int candidate=nums[0];
        int count=1;
        for (int i = 1; i < nums.length; i++) {
            if(count==0)
                candidate=nums[i];
            count+=(nums[i]==candidate)?1:-1;
        }
        return candidate;
    }
}
