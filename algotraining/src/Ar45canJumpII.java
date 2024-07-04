import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/14 11:38
 */
public class Ar45canJumpII {
    public int jump(int[] nums) {
        int n=nums.length-1;
        if(n==0)
            return 0;
        else if(nums[0]>=n)
            return 1;
        //第一个int代表走到哪儿了 第二个int代表跳跃的次数
        Queue<Pair<Integer,Integer>> queue=new LinkedList<>();
        //不设visited记录有没有访问过 就会超时
        boolean[] visited=new boolean[n];
        visited[0]=true;
        queue.add(new Pair<Integer,Integer>(0,0));
        while(!queue.isEmpty()){
            Pair<Integer,Integer> pair=queue.remove();
            int index=pair.getKey();
            int step=pair.getValue();
            if(nums[index]+index>=n)
                return step+1;
            for(int i=nums[index];i>0;i--){
                if(!visited[index+i]){
                    queue.add(new Pair<Integer,Integer>(index+i,step+1));
                    visited[index+i]=true;
                }
            }
        }
        return -1;
    }

    public int canJump1(int[] nums){
        int step=0;
        int end=0; //上次跳跃可达范围右边界
        int maxPos=0;//目前能跳到的最远的位置
        for(int i=0;i<nums.length-1;i++){ //<length-1要注意
            maxPos=Math.max(maxPos,nums[i]+i);
            /*if(maxPos>=nums.length-1)
                return step+1;//这样提前return也行*/
            if(i==end){
                end=maxPos;
                //这里的step+1只代表跳了一步 但不一定是跳到更新的end那里 end之前的位置都有可能 具体是哪里也就是具体哪里
                // 是下次起跳位置要取决于后面的情况
                step++;
            }
        }
        return step;
    }
}
