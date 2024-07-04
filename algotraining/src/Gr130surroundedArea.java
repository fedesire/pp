import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O'
 * 用 'X' 填充。
 * @date 2024/2/6 11:23
 */
public class Gr130surroundedArea {
    int m,n;
    public void solve(char[][] board) {
        //以每个边界上的O为起点 在dfs的过程中标记所有与之直接或间接相连的字母O 这里的是字母O 不是数字0
        //dfs结束后 判断每个节点是否标记过 如果标记过说明他并不是被 'X' 围绕的区域 将其还原成O 如果没有
        //标记过说明他是被 'X' 围绕的区域 将其修改为X
        m=board.length;
        n=board[0].length;
        for(int i=0;i<m;i++){
            dfs(board,i,0);
            dfs(board,i,n-1);
        }
        for(int i=1;i<n-1;i++){
            dfs(board,0,i);
            dfs(board,m-1,i);
        }
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='A')
                    board[i][j]='O';
                else if(board[i][j]=='O')
                    board[i][j]='X';
            }
        }
    }
    public void dfs(char[][] board,int i,int j){
        //递归边界是！='O' 或者写成board[i][j]=='X'||board[i][j]=='A'
        if(i<0||i>=m||j<0||j>=n||board[i][j]!='O')
            return ;
        board[i][j]='A';
        dfs(board,i+1,j);
        dfs(board,i-1,j);
        dfs(board,i,j+1);
        dfs(board,i,j-1);
    }

    //bfs实现标记
    int[][] move={{0,1},{0,-1},{1,0},{-1,0}};
    public void solve1(char[][] board){
        m=board.length;
        n=board[0].length;
        Queue<int[]> queue=new LinkedList<>();
        for(int i=0;i<m;i++){
            if(board[i][0]=='O'){
                queue.add(new int[]{i,0});
                board[i][0]='A';
            }
            if(board[i][n-1]=='O'){
                queue.add(new int[]{i,n-1});
                board[i][n-1]='A';
            }
        }
        for(int i=1;i<n-1;i++){
            if(board[0][i]=='O'){
                queue.add(new int[]{0,i});
                board[0][i]='A';
            }
            if(board[m-1][i]=='O'){
                queue.add(new int[]{m-1,i});
                board[m-1][i]='A';
            }
        }

        while(!queue.isEmpty()){
            int[] cell = queue.remove();
            int x=cell[0],y=cell[1];
            for(int i=0;i<4;i++){
                int newx=x+move[i][0];
                int newy=y+move[i][1];
                if(newx<0||newx>=m||newy<0||newy>=n||board[newx][newy]!='O') continue;
                queue.add(new int[]{newx,newy});
                board[newx][newy]='A';
            }
        }

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]=='A')
                    board[i][j]='O';
                else if(board[i][j]=='O')
                    board[i][j]='X';
            }
        }
    }
}
