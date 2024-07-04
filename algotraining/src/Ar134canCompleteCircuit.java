/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/30 10:07
 */
public class Ar134canCompleteCircuit {
    //这种方案有测试案例超出时间限制了 太多重复计算了
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n=gas.length;
        //找到第一个gs[i]>=cost[i]的下标
        int i=0;
        while(i<n&&gas[i]<cost[i]) i++;
        for(;i<n;i++){
            if(check(i,n,gas,cost))
                return i;
        }
        return -1;
    }

    public boolean check(int start,int n,int gas[],int cost[]){
        int cap=gas[start];
        for(int i=start+1;i<n;i++){
            cap-=cost[i-1]; //必须先减后就判断 不能-cost+gas后再判断 因为可能减之后就小于0 说明已经到达不了了
            if(cap<0)
                return false;
            cap+=gas[i];
        }
        cap-=cost[n-1];
        if(cap<0)
            return false;
        cap+=gas[0];
        for(int i=1;i<=start;i++){
            cap-=cost[i-1];
            if(cap<0)
                return false;
            cap+=gas[i];
        }
        return true;

    }

    //首先检查第 0个加油站，并试图判断能否环绕一周；如果不能，就从第一个无法到达的加油站开始继续检查。
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int n=gas.length;
        int i=0;
        while(i<n){
            int sumOfGas=0,sumOfCost=0;
            int count=0;
            while(count<n){ //必须是<n 这样count=n的时候就能退出循环了
                int j=(i+count)%n;
                sumOfGas+=gas[j];
                sumOfCost+=cost[j];
                if(sumOfCost>sumOfGas)
                    break;
                count++;
            }
            if(count==n)
                return i;
            i=i+count+1;//下一次循环从上一次的第一个无法到达的加油站开始继续检查
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] gas={1,2,3,4,5};
        int[] cost={3,4,5,1,2};
        System.out.println(new Ar134canCompleteCircuit().canCompleteCircuit(gas, cost));
    }
}
