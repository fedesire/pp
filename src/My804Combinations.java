import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 你可以按 任何顺序 返回答案。
 * @date 2023/12/18 15:34
 */
public class My804Combinations {
    private static List<List<Integer>> res;
    public static void getCombination(int n, int k, int start, LinkedList<Integer> c){
        if(c.size()==k){
            res.add((LinkedList<Integer>)c.clone());
            return ;
        }
        for (int i = start; i <= n; i++) {
            if(n-i+1<k-c.size()) //剪枝
                break;
            c.addLast(i);
            getCombination(n,k,i+1,c);//不是start+1
            c.removeLast();

        }
        return;

    }
    public static List<List<Integer>> combine(int n,int k){
        res=new ArrayList<List<Integer>>();
        if(n<=0||k<=0||k>n)
            return res;
        LinkedList<Integer> c=new LinkedList<>();
        getCombination(n,k,1,c);
        return res;

    }
    private static void printList(List<Integer> list){
        for(Integer e: list)
            System.out.print(e + " ");
        System.out.println();
    }

    public static void main(String[] args) {

        List<List<Integer>> res = combine(4, 2);
        for(List<Integer> list: res)
            printList(list);
    }
}
