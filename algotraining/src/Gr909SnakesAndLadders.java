import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/2/13 9:21
 */
//https://leetcode.cn/problems/snakes-and-ladders/solutions/846328/she-ti-qi-by-leetcode-solution-w0vl/?envType=study-plan-v2&envId=top-interview-150
    //使用BFS 我们可以将棋盘抽象成一个包含 N2个节点的有向图，对于每个节点 x，若 x+i (1≤i≤6)上没有蛇或梯子，则连一条从x
// 到 x+i的有向边；否则记蛇梯的目的地为 y，连一条从 x 到 y 的有向边。如此转换后，原问题等价于在这张有向图上求出从 1到 N2
// 的最短路长度。对于该问题，我们可以使用广度优先搜索。将节点编号和到达该节点的移动次数作为搜索状态，顺着该节点的出边扩展
// 新状态，直至到达终点 N2 返回此时的移动次数。若无法到达终点则返回 −1
    //真正在代码实现时 是无需建立图的 图在入队出队的过程中自动完成建立以及广度优先搜索
public class Gr909SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int n=board.length;
        boolean[] visited=new boolean[n*n+1];//visited[i]代表编号为i的格子是否访问过

        Queue<int[]> queue=new LinkedList<>();
        queue.add(new int[]{1, 0});
        while(!queue.isEmpty()){
            int[] p=queue.remove();
            for(int i=1;i<=6;i++){
                int next=p[0]+i;
                if(next>n*n) break;
                int []nextp=id2rc(next,n); //得到下一步的行列
                if(board[nextp[0]][nextp[1]]>0){ // 存在蛇或梯子
                    next=board[nextp[0]][nextp[1]];
                }
                if(next==n*n)
                    return p[1]+1;
                if(!visited[next]){
                    visited[next]=true;
                    queue.add(new int[]{next,p[1]+1}); // 扩展新状态
                }
            }
        }
        return -1;

    }
    public int[] id2rc(int id,int n){
        int r=(id-1)/n,c=(id-1)%n;
        if(r%2==1)
            c=n-1-c;
        return new int[]{n-1-r,c}; //设编号为 id,由于每行有 n个数字，其位于棋盘从下往上数的第(id-1)/n行 所以从上往下数
        //就是第n-1-r行

    }
}
