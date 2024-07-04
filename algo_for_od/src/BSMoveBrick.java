import java.util.Arrays;
import java.util.Scanner;

/**
 *机器人搬砖，一共有 N 堆砖存放在 N 个不同的仓库中，第 i 堆砖中有 bricks[i] 块砖头，要求在 8 小时内搬完。
 *
 * 机器人每小时能搬砖的数量取决于有多少能量格，机器人一个小时中只能在一个仓库中搬砖，机器人的能量格只在这一个小时有效，为使得机器人损耗最小化，
 * 应尽量减小每次补充的能量格数。
 *
 * 为了保障在 8 小时内能完成搬砖任务，请计算每小时给机器人充能的最小能量格数。
 *
 * 无需考虑机器人补充能力格的耗时；
 * 无需考虑机器人搬砖的耗时；
 * 机器人每小时补充能量格只在这一个小时中有效；
 * 输入描述
 * 第一行为一行数字，空格分隔
 *
 * 输出描述
 * 机器人每小时最少需要充的能量格，若无法完成任务，输出 -1
 * @date 2024/4/12 13:53
 *
 * 如果只有一个仓库一堆砖，那么这堆砖就可以平分到8小时内搬，这样才能保证每小时搬最少的砖，消耗最少的能量，即每小时可以充最少的能量。
 * 如果这堆砖头足够少，比如只有1块，那么此时机器人每小时只需要充1块能量即可。因此 k 的最小值取 1。
 * 如果有8个仓库，那么机器人每小时的能量格数至少就是 max(bricks)，这样才能保证一个小时干完砖头数量最多的那个仓库。因此 k 的最大值取max(bricks)。
 * 求出 k 的取值范围后，我们可以通过二分取中值的方式，不停尝试可能解mid：
 * 如果mid能量块可以满足8小时内搬完所有仓库，那么mid就是一个可能解，但不一定是最优解，此时我们应该尝试充更少的能量，即缩小k的右边界范围到 = mid - 1
 * 如果mid能量块不能满足8小时内搬完所有仓库，那么说明每小时充mid能力太少了，我们应该尝试充更多能量，即增大k的左边界范围到 = mid + 1
 *
 * 获得一个范围后就可以二分查找了 而不是根据数字再找规律
 */
public class BSMoveBrick {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int[] bricks = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n=bricks.length;
        if(n>8){
            System.out.println("-1");
            return ;
        }
        int right=Arrays.stream(bricks).max().getAsInt();
        int left=1;
        int res=right;
        int time=8;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(validate(mid,bricks,time)){
                res=mid;
                right--;
            }
            else
                left++;
        }
        System.out.println(res);

    }
    private static boolean validate(int target,int[] bricks,int time){
        // 总共花费的时间
        int sum=0;
        for (int brick : bricks) {
            int costHour=(int)Math.ceil(brick*1.0/target);
            sum+=costHour;
            if(sum>time)
                return false;
        }
        return true;
    }
}
