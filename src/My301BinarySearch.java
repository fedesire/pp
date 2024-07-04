import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/12 8:50
 */
public class My301BinarySearch {
    //加static是因为不加的话 那就必须要new一个my301对象才能调用方法 加了static就不需要new对象
    // 而且是在本类中所以My301BinarySearch.binarySearch或者直接用binarySearch都行
    //如果是private static也可以在本类中调用方法
    public static int binarySearch(int[] arr,int n,int target){
        int l=0,r=n-1;// 在[l...r]的范围里寻找target
        while(l<=r){// 当 l == r时,区间[l...r]依然是有效的
//            int mid=(l+r)/2; 写加法容易产生整型溢出问题 所以最好改用减法
            int mid=l+(r-l)/2;
            if(arr[mid]==target)
                return mid;
            else if(target>arr[mid])
                l=mid+1;
            else r=mid-1;
        }

        return -1;
    }


    public static void main(String[] args) {
        int n=(int)Math.pow(10,7);
        Integer[] array = MyUtil.generateOrderedArray(n);
        int[] arr= Arrays.stream(array).mapToInt(Integer::intValue).toArray();
        long startTime=System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            if(i!=binarySearch(arr,n,i))
                throw new IllegalStateException("find i failed");
        }
        long endTime=System.currentTimeMillis();
        System.out.println("time cost"+(endTime-startTime)+"ms");//(endTime-startTime)不加括号会报错
    }
}
