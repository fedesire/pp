import java.util.HashMap;
import java.util.Scanner;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/16 9:36
 */
/*
* 从前有个村庄，村民们喜欢在各种田地上插上小旗子，旗子上标识了各种不同的数字。
某天集体村民决定将覆盖相同数字的最小矩阵形的土地分配给村里做出巨大贡献的村民，请问此次分配土地，做出贡献的村民种最大会分配多大面积?
输入描述
第一行输入 m 和 n，
m 代表村子的土地的长
n 代表土地的宽
第二行开始输入地图上的具体标识
输出描述
此次分配土地，做出贡献的村民种最大会分配多大面积
备注
旗子上的数字为1~500，土地边长不超过500
未插旗子的土地用0标识
用例
输入 3 3
1 0 1
0 0 0
0 1 0
输出 9
说明 土地上的旗子为1，其坐标分别为(0,0)，(2,1)以及(0,2)，为了覆盖所有旗子，矩阵需要覆盖的横坐标为0和2，纵坐标为0和2，所以面积为9，即（2-0+1）*（2-0+1）= 9
输入 3 3
1 0 2
0 0 0
0 3 4
输出 1
说明 由于不存在成对的小旗子，故而返回1，即一块土地的面积。*/
public class MathLand {
   static class Rect{
        public int maxx=Integer.MIN_VALUE;
        public int maxy=Integer.MIN_VALUE;
        public int minx=Integer.MAX_VALUE;
        public int miny=Integer.MAX_VALUE;
        void setRow(int x){
            maxx=Math.max(x,maxx);
            minx=Math.min(minx,x);
        }
        void setCol(int y){
            maxy=Math.max(maxy,y);
            miny=Math.min(miny,y);
        }
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        HashMap<Integer,Rect> numberRect=new HashMap<Integer, Rect>();
        // 关于这里矩形的长是行数而不是列 这是考友实际考试测试出来的，这个没啥好研究的，实际考试可以都试试
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int key=scanner.nextInt();
                if(key>0){
                    numberRect.putIfAbsent(key,new Rect());
                    numberRect.get(key).setRow(i);
                    numberRect.get(key).setCol(j);

                }
            }
        }
        int res=0;
        for (Rect value : numberRect.values()) {
            res=Math.max(res,(value.maxx-value.minx+1)*(value.maxy-value.miny+1));
        }
        System.out.println(res);
    }
}
