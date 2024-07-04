import java.util.Arrays;
import java.util.List;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/13 17:21
 */
public class My306TwoSum {
    //我想的算法 在lc上可以通过 就是耗时久
    public static int[] twoSum(int[] arr, int target) {
        Integer[] ins = Arrays.stream(arr).boxed().toArray(Integer[]::new);

        List<Integer> list = Arrays.asList(ins);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != target - arr[i]){
                /*刚开始写的时候 下面的if后面没加{}导致出bug了
                当if嵌套if时不加花括号 则后面else与最近的if对应，会导致其对应的else被内部嵌套的if占有*/
                if (list.contains(target - arr[i])) {
                    int[] res = {i, list.indexOf(target - arr[i])};
                    return res;
                }
            }
            else if(list.indexOf(arr[i])!=list.lastIndexOf(arr[i])){
                    int[] res = {i, list.lastIndexOf(target - arr[i])};
                    return res;
                }
        }
        throw new IllegalStateException("the input has no solution");
        //return new int[]{-1,-1}; //throw和return必须二选一 否则编译不通过
    }

    public static void main(String[] args) {
        int[] arr={3,3};
        int[] res=twoSum(arr,6);
        System.out.println(res[0]+" "+res[1]);
    }
}