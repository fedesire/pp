import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *有n个人围成一圈，顺序排号为1-n。
 * 从第一个人开始报数(从1到3报数)，凡报到3的人退出圈子，问最后留下的是原来第几号的那位。
 * 输入描述
 * 输入人数n（n < 1000）
 * 输出描述
 * 输出最后留下的是原来第几号
 * @date 2024/4/14 10:58
 */
public class LinkedListYosef {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n = scanner.nextInt();
        LinkedList<Integer> linkedList=new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            linkedList.add(i);
        }
        int cur=1;
        while(linkedList.size()>1){
            Integer out = linkedList.removeFirst();
            if(cur!=3){
                linkedList.addLast(out);
                cur++;
            }
            else{
                // cur=3的时候报到3的人退出圈子即上面的remove操作生效 因为这一次不会再加入尾部了 cur=1意思是下一个人报数报1
                cur=1;
            }
        }
        System.out.println(linkedList.get(0));
    }
}
