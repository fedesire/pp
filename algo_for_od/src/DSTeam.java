import java.util.Arrays;
import java.util.Scanner;

/**
 * 总共有 n 个人在机房，每个人有一个标号（1<=标号<=n），他们分成了多个团队，需要你根据收到的 m 条消息判定指定的两个人是否在一个团队中，具体的：
 *
 * 消息构成为 a b c，整数 a、b 分别代表两个人的标号，整数 c 代表指令
 * c == 0 代表 a 和 b 在一个团队内
 * c == 1 代表需要判定 a 和 b 的关系，如果 a 和 b 是一个团队，输出一行’we are a team’,如果不是，输出一行’we are not a team’
 * c 为其他值，或当前行 a 或 b 超出 1~n 的范围，输出‘da pian zi’
 * 输入描述
 * 第一行包含两个整数 n，m(1<=n,m<100000),分别表示有 n 个人和 m 条消息
 * 随后的 m 行，每行一条消息，消息格式为：a b c(1<=a,b<=n,0<=c<=1)
 * 输出描述
 * c ==1,根据 a 和 b 是否在一个团队中输出一行字符串，在一个团队中输出‘we are a team‘,不在一个团队中输出’we are not a team’
 * c 为其他值，或当前行 a 或 b 的标号小于 1 或者大于 n 时，输出字符串‘da pian zi‘
 * 如果第一行 n 和 m 的值超出约定的范围时，输出字符串”NULL“。
 * 用例
 * 输入	5 7
 * 1 2 0
 * 4 5 0
 * 2 3 0
 * 1 2 1
 * 2 3 1
 * 4 5 1
 * 1 5 1
 * 输出	we are a team
 * we are a team
 * we are a team
 * we are not a team
 * @date 2024/4/13 9:14
 */
public class DSTeam {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt(),m=scanner.nextInt();
        int[][] messages=new int[m][3];
        for (int i = 0; i < m; i++) {
            messages[i][0]=scanner.nextInt();
            messages[i][1]=scanner.nextInt();
            messages[i][2]=scanner.nextInt();
        }

        if(n<1||n>=100000||m<1||m>=100000){
            System.out.println("NULL");
            return ;
        }
        UnionFindSet uf=new UnionFindSet(n+1);
        for (int i = 0; i < m; i++) {
            int a=messages[i][0];
            int b=messages[i][1];
            int c=messages[i][2];
            if (a < 1 || a > n || b < 1 || b > n) {
                // 当前行 a 或 b 的标号小于 1 或者大于 n 时，输出字符串‘da pian zi‘
                System.out.println("da pian zi");
                continue;
            }
            if(c==0)
                uf.union(a,b);
            else if(c==1)
                System.out.println(uf.find(a)==uf.find(b)?"we are a team":"we are not a team");
            else
                System.out.println("da pian zi");
        }

    }

    /*
    * 本题c == 1判断a,b是否处于一个团队时，本题判断范围存在歧义，当前有两种思路：

1、仅限判断前面行中建立的关系中，a,b是否处于同一团队

2、不限于前面行，而是等所有行输入完后，才判断a,b是否处于同一团队*/
    public static void getResult(int[][] msgs, int n, int m) {
        // 如果第一行 n 和 m 的值超出约定的范围时，输出字符串”Null“。
        // 1<=n,m<100000
        if (n < 1 || n >= 100000 || m < 1 || m >= 100000) {
            System.out.println("NULL");
            return;
        }

        UnionFindSet ufs = new UnionFindSet(n + 1);

        // 过滤出 c == 0 的，且非异常的行，提前构建出所有关系
        Arrays.stream(msgs)
                .filter(msg -> msg[2] == 0 && msg[0] >= 1 && msg[0] <= n && msg[1] >= 1 && msg[1] <= n)
                .forEach(msg -> ufs.union(msg[0], msg[1]));

        // 处理 c 不为0的其他情况
        for (int[] msg : msgs) {
            int a = msg[0], b = msg[1], c = msg[2];

            if (a < 1 || a > n || b < 1 || b > n) {
                // 当前行 a 或 b 的标号小于 1 或者大于 n 时，输出字符串‘da pian zi‘
                System.out.println("da pian zi");
                continue;
            }

            if (c == 0) {
            } else if (c == 1) {
                // c == 1 代表需要判定 a 和 b 的关系，如果 a 和 b 是一个团队，输出一行’we are a team’,如果不是，输出一行’we are not a team’
                System.out.println(ufs.find(a) == ufs.find(b) ? "we are a team" : "we are not a team");
            } else {
                // c 为其他值，输出字符串‘da pian zi‘
                System.out.println("da pian zi");
            }
        }
    }


    static class UnionFindSet{
        int[] findArr;
        UnionFindSet(int n){
            findArr=new int[n];
            for (int i = 0; i < n; i++) {
                findArr[i]=i;
            }
        }
        // 查x站点对应的顶级祖先站点
        public int find(int x){
            if(findArr[x]!=x){
                findArr[x]=find(findArr[x]);
                return findArr[x];
            }
            return x;
        }

        // 合并两个站点，其实就是合并两个站点对应的顶级祖先节点
        public void union(int x,int y){
            int x_fa=findArr[x];
            int y_fa=findArr[y];
            if(x_fa!=y_fa){
                findArr[y_fa]=x_fa;
            }
        }
    }
}
