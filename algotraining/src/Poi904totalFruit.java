import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
 * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
 * 你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。
 * 你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
 * 一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
 * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
 * 示例 1：
 * 输入：fruits = [1,2,1]
 * 输出：3
 * 解释：可以采摘全部 3 棵树。
 * 示例 2：
 * 输入：fruits = [0,1,2,2]
 * 输出：3
 * 解释：可以采摘 [1,2,2] 这三棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [0,1] 这两棵树。
 * 示例 3：
 * 输入：fruits = [1,2,3,2,2]
 * 输出：4
 * 解释：可以采摘 [2,3,2,2] 这四棵树。
 * 如果从第一棵树开始采摘，则只能采摘 [1,2] 这两棵树。
 * 示例 4：
 * 输入：fruits = [3,3,3,1,2,1,1,2,3,3,4]
 * 输出：5
 * 解释：可以采摘 [1,2,1,1,2] 这五棵树。
 * @date 2024/5/1 11:09
 */
public class Poi904totalFruit {
    /*使用滑动窗口解决本题，left\textit{left}left 和 right\textit{right}right 分别表示满足要求的窗口的左右边界，同时我们使用哈希表存储这个窗口内的数以及出现的次数。

我们每次将 right\textit{right}right 移动一个位置，并将 fruits[right]\textit{fruits}[\textit{right}]fruits[right] 加入哈希表。如果此时哈希表不满足要求（即哈希表中出现超过两个键值对），那么我们需要不断移动 left\textit{left}left，并将 fruits[left]\textit{fruits}[\textit{left}]fruits[left] 从哈希表中移除，直到哈希表满足要求为止。

需要注意的是，将 fruits[left]\textit{fruits}[\textit{left}]fruits[left] 从哈希表中移除后，如果 fruits[left]\textit{fruits}[\textit{left}]fruits[left] 在哈希表中的出现次数减少为 000，需要将对应的键值对从哈希表中移除。
*/
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();

        int left = 0, ans = 0;
        for (int right = 0; right < n; ++right) {
            // 每次都添加右指针上的元素 如果此时超过2个元素 就需要移动左指针
            cnt.put(fruits[right], cnt.getOrDefault(fruits[right], 0) + 1);
            while (cnt.size() > 2) {
                cnt.put(fruits[left], cnt.get(fruits[left]) - 1);
                if (cnt.get(fruits[left]) == 0) {
                    cnt.remove(fruits[left]);
                }
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }


    public int totalFruit1(int[] fruits) {
        int l=0,r=0;
        int ans=0;
        HashMap<Integer,Integer> hs=new HashMap<>();
        while(r<fruits.length){
            int target=fruits[r];
            if(hs.size()<2||hs.containsKey(target)){
                hs.put(target,hs.getOrDefault(target,0)+1);
                ans=Math.max(ans,r-l+1);
                r++;
            }
            else{
                while(l<fruits.length){
                    hs.put(fruits[l],hs.get(fruits[l])-1);
                    if(hs.get(fruits[l])==0){
                        hs.remove(fruits[l]);
                        l++;
                        break;
                    }
                    l++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] fruits={3,3,3,1,2,1,1,2,3,3,4};
        System.out.println(new Poi904totalFruit().totalFruit(fruits));
    }
}
