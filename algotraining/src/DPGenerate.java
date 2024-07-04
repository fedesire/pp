import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 * 输入: numRows = 5
 * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * @date 2024/5/8 9:59
 */
public class DPGenerate {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res=new ArrayList<>();
        res.add(Arrays.asList(1));
        if(numRows==1)
            return res;
        for(int i=1;i<numRows;i++){
            List<Integer> temp=new ArrayList();
            temp.add(1);
            List<Integer> last=res.get(res.size()-1);
            int n=last.size();
            for(int j=0;j<n-1;j++){
                temp.add(last.get(j)+last.get(j+1));
            }
            temp.add(1);
            res.add(temp);
        }
        return res;

    }
}
