import java.util.LinkedList;

/**
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 * @date 2024/5/12 8:59
 */
public class DBFS994OrangesRotting {
    public int orangesRotting(int[][] grid) {
        int m=grid.length,n=grid[0].length;
        int total=m*n,count=0;
        LinkedList<int[]> queue=new LinkedList<>();
        boolean[][] visited=new boolean[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==2){
                    queue.addLast(new int[]{i,j});
                    count++;
                    visited[i][j]=true;
                }
                else if(grid[i][j]==0)
                    total-=1;
            }
        }
        if(total==count) return 0;

        int[][] dirs={{0,1},{0,-1},{1,0},{-1,0}};
        int round=0;
        while(!queue.isEmpty()){
            int s=queue.size();
            // 这里必须先把queue.size()保存下来 否则在循环中改变queue.size()会导致循环次数错误
            for (int i = 0; i < s; i++) {
                int[] ele = queue.removeFirst();
                for (int j = 0; j < 4; j++) {
                    int newx=ele[0]+dirs[j][0],newy=ele[1]+dirs[j][1];
                    if(newx>=0&&newx<m&&newy>=0&&newy<n&&!visited[newx][newy]&&grid[newx][newy]==1){
                        queue.addLast(new int[]{newx,newy});
                        visited[newx][newy]=true;
                        count++;
                    }
                }
            }
            round++;
            // 必须在这里就判断 否则的话最后一个橘子腐烂后还会再进入一次循环 round就会多加了一次1
            if(count==total)
                return round;
        }
        return count<total?-1:round;
    }
    public static void main(String[] args)
    {
        int[][] grid={{2,1,1},{1,1,0},{0,1,1}};
        DBFS994OrangesRotting test=new DBFS994OrangesRotting();
        System.out.println(test.orangesRotting(grid));
    }
}
