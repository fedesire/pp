import java.util.ArrayList;
import java.util.List;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/19 11:14
 */
public class Lc22generateParen {
    List<String> res=new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        StringBuilder temp = new StringBuilder();
        dfs(n,0,0,temp);
        return res;

    }

    private void dfs(int n, int leftCount, int rightCount, StringBuilder temp) {
        if(leftCount==n&&rightCount==n){
            res.add(temp.toString());
            return ;
        }
        /*如果左括号数量不大于n，我们可以放一个左括号。只有在右括号数量小于左括号的数量时，才可以放一个右括号。*/
        if(leftCount<n){
            temp.append("(");
            dfs(n,leftCount+1,rightCount,temp);
            temp.deleteCharAt(temp.length()-1);

        }
        if(leftCount>rightCount) {
            temp.append(")");
            dfs(n, leftCount, rightCount + 1, temp);
            temp.deleteCharAt(temp.length() - 1);
        }


}

    public static void main(String[] args) {
        System.out.println(new Lc22generateParen().generateParenthesis(3));
    }

}
