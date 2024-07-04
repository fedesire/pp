/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/15 13:49
 */
public class Lc27RemoveElement {
    public static int removeElement(int[] nums,int val){
        int index=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=val)
                nums[index++]=val;
        }
        return index;
    }
}
