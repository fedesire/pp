import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * 有一种特殊的加密算法，明文为一段数字串，经过密码本查找转换，生成另一段密文数字串。
 * 规则如下：
 * 明文为一段数字串由 0~9 组成
 * 密码本为数字 0~9 组成的二维数组
 * 需要按明文串的数字顺序在密码本里找到同样的数字串，密码本里的数字串是由相邻的单元格数字组成，上下和左右是相邻的，注意：对角线不相邻，
 * 同一个单元格的数字不能重复使用。
 * 每一位明文对应密文即为密码本中找到的单元格所在的行和列序号（序号从0开始）组成的两个数宇。
 * 如明文第 i 位 Data[i] 对应密码本单元格为 Book[x][y]，则明文第 i 位对应的密文为X Y，X和Y之间用空格隔开。
 * 如果有多条密文，返回字符序最小的密文。
 * 如果密码本无法匹配，返回"error"。
 * 请你设计这个加密程序。
 * 示例1：
 * 输入描述
 * 第一行输入 1 个正整数 N，代表明文的长度（1 ≤ N ≤ 200）
 *
 * 第二行输入 N 个明文组成的序列 Data[i]（0 ≤ Data[i] ≤ 9）
 *
 * 第三行输入 1 个正整数 M，代表密文的长度
 *
 * 接下来 M 行，每行 M 个数，代表密文矩阵
 *
 * 输出描述
 * 输出字典序最小密文，如果无法匹配，输出"error"
 *
 * 2
 * 0 3
 * 3
 * 0 0 2
 * 1 3 4
 * 6 6 4
 * 输出 0 1 1 1
 * @date 2024/4/12 16:59
 */
public class DBFSEnrypt {
    static boolean[][] used;
    static int n;
    static int m;
    static int[] raw;
    static int[][] matrix;
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        n = scanner.nextInt();
        raw=new int[n];
        for (int i = 0; i < n; i++) {
            raw[i]=scanner.nextInt();
        }
        m=scanner.nextInt();
        matrix=new int[m][m];
        // 找出第一个明文字符在矩阵中的所有位置
        ArrayList<Integer> startPos=new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j]=scanner.nextInt();
                if(matrix[i][j]==raw[0])
                    startPos.add(i*m+j);
            }
        }

        // 从每个起始位置开始检查是否能找到目标密文
        for (int pos : startPos) {
            int x=pos/m;
            int y=pos%m;

            // used[i][j]用于记录密码本(i,j)元素是否已使用 注意每一次都要重新初始化
            boolean[][] used=new boolean[m][m];
            used[x][y]=true;
            LinkedList<String> path=new LinkedList<>();
            path.add(x+" "+y);

            if (dfs(x,y,1,path)) {
                StringJoiner joiner=new StringJoiner(" ");
                for (String s : path) {
                    joiner.add(s);
                }
                System.out.println(joiner.toString());
                return ;
            }

        }
        System.out.println("error");

    }

    // 上，左，右，下偏移量，注意这里的顺序是有影响的，即下一步偏移后产生的密文的字符序必然是：上 < 左 < 右 < 下
    // 注意我第一次就写错了 最后两个搞反了
    static int[][] moveDirection={{-1,0},{0,-1},{0,1},{1,0}};

    // 将要匹配raw[index]处的字符和当前xy坐标附近4个方向的元素 这里dfs没有用到res保存结果 因为是要输出字典序最小密文 所以是
    // 碰到第一个就返回 直接用path里的结果即可
    private static boolean dfs(int x, int y, int index, LinkedList<String> path) {
        if(index==n)
            return true;
        for (int i = 0; i < 4; i++) {
            int newX=x+moveDirection[i][0];
            int newY=y+moveDirection[i][1];
            if(newX<0||newX>=m||newY<0||newY>=m||used[newX][newY]||matrix[newX][newY]!=raw[index])
                continue;
            // 递归进入新位置
            path.add(newX+" "+newY);
            used[newX][newY]=true;
            if (dfs(newX,newY,index+1,path)) {
                return true;
            }
            used[newX][newY]=false;
            path.removeLast();
        }
        return false;
    }
}
