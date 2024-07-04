import java.util.Arrays;

/**
 *给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * @date 2024/1/23 10:49
 */
public class Ar238productExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        //left[i] 为索引 i 左侧所有元素的乘积 right[i] 为索引 i 右侧所有元素的乘积
        int[] left=new int[n];
        int[] right=new int[n];
        left[0]=1;
        right[n-1]=1;
        for(int i=1;i<n;i++){
            left[i]=left[i-1]*nums[i-1];
        }
        for(int i=n-2;i>=0;i--){
            right[i]=right[i+1]*nums[i+1];
        }
        int[] res=new int[n];
        for(int i=0;i<n;i++){
            res[i]=left[i]*right[i];
        }
        return res;
    }

    //维护两个变量，beforeSum表示前缀和，afterSum表示后缀和
    public int[] productExceptSelf2(int[] nums) {
        int n=nums.length;
        int[] ans=new int[n];
        Arrays.fill(ans,1);
        int beforeSum=1;
        int afterSum=1;
//        for(int i=0,j=n-1;i<n;i++,j--){
//            ans[i]*=beforeSum;
//            ans[j]*=afterSum;
//            beforeSum*=nums[i];
//            afterSum*=nums[j];
//        }
        // 拆成两个for也可以
        for (int i = 0; i < n; i++) {
            ans[i]*=beforeSum;
            beforeSum*=nums[i];
        }
        for(int i=n-1;i>=0;i--){
            ans[i]*=afterSum;
            afterSum*=nums[i];
        }
        return ans;
    }

    //要求在O(1)的空间复杂度内完成 由于输出数组不算在空间复杂度内，那么我们可以将 L 或 R 数组用输出数组来计算。
    // 先把输出数组当作 L 数组来计算，然后再动态构造 R 数组得到结果。
    public int[] productExceptSelf1(int[] nums) {
        int n=nums.length;
        int[] res=new int[n];
        res[0]=1;
        for(int i=1;i<n;i++){
            res[i]=res[i-1]*nums[i-1];
        }
        int r=1;
        for(int i=n-1;i>=0;i--){
            res[i]=res[i]*r;
            r=r*nums[i];
        }

        return res;
    }
}
