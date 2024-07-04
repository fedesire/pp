import java.util.Arrays;

/**
 * @author xqi
 * @version 1.0
 * @description: 冒泡 选择 插入 快排 归并
 * @date 2024/1/22 10:44
 */
public class FiveSortAlgo {
    public static void bubbleSort(int[] arr){
        int n=arr.length;
        //外循环：表示要执行多少轮 n个数据要执行n-1轮 内循环：用来在符合条件时交换相邻两个数使得内循环结束后最大的数到达最右边
        //-1是为了防止索引越界 -i是为了提高效率 每一轮执行的次数可以比上一轮少一 因为后面的数是已经确定了的 如果不写-i不会错 只是慢了
        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-1-i;j++){
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }

    public static void selectSort(int[] arr){
        int n=arr.length;
        //外循环表示循环了几轮 同时i也表示每一轮要拿着i索引处的元素和其后面的所有元素进行比较交换
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(arr[j]<arr[i]){
                    int temp=arr[j];
                    arr[j]=arr[i];
                    arr[i]=temp;
                }
            }
        }
    }

    //插入排序是将数组0索引处到i索引处看成是有序序列 i+1索引到最后一个数看成无序序列
    public static void insertSort(int[] arr){
        int n=arr.length;
        //也是要循环n-1轮 上面的冒泡选择i是[0,n-1） 这里i是[1,n)
        for(int i=1;i<n;i++){
            /*for(int j=i;j>0;j--){
                if(arr[j]<arr[j-1]){
                    int temp=arr[j];
                    arr[j]=arr[j-1];
                    arr[j-1]=temp;
                }
                else break;
            }*/
            //当前待插入的元素是arr[i] 但是i不能改变 所以用j记录 通过不断交换最终就能将arr[i]插入到前面有序序列里正确的位置
            int j=i;
            while(j>0&&arr[j]<arr[j-1]){
                int temp=arr[j];
                arr[j]=arr[j-1];
                arr[j-1]=temp;
                j--;
            }
        }
    }
    //这种比上面的方法好 因为上面每次交换是涉及三步操作 这里只需要一步操作向后移
    public static void insertSort2(int[] arr){
        int n=arr.length;
        //也是要循环n-1轮 上面的冒泡选择i是[0,n-1） 这里i是[1,n)
        for(int i=1;i<n;i++){
            //arr[i]是这一轮待插入的目标元素 因为下面的比较过程是需要将元素后移的 所以需要提前保存arr[i]
            int temp=arr[i];
            int j=i-1;
            //当遇到temp>=arr[j]时就找到了temp该处的位置 即j+1处
            while(j>=0&&temp<arr[j]) {
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=temp;
        }
    }
    public static void quickSort(int[] arr){
        quickSort2(arr,0,arr.length-1);
    }

    private static void quickSort(int[] arr, int i, int j) {
        if(i>=j) return;
        int pivot=arr[i];
        int start=i,end=j; //start不能是i+1 因为只有两个数的情况下 如果从i+1开始直接就不会进入后面的while循环了
        System.out.println(Arrays.toString(arr));
        System.out.println(i+" "+j);
        //end从后面开始找比标定点小的元素 start从前面开始找比标定点大的元素 找到了就相互交换 最终循环结束时start=end
        // 该位置处即是标定点最终的正确位置 将标定点移到这儿 该位置的元素移到标定点原本的位置 然后进入左右两边子数组的递归
        //而且必须是先end从后面开始寻找 再是start从前面开始 不能先start后end
        while(start<end){ //这里不能有= 否则会进入死循环 因为这里如果start=end可以进入循环 下面两个循环就不会进入 s e就不会变就会一直死循环了
            while(start<end&&arr[end]>=pivot) //这里和下面必须是有=（下面必须有= 这里=没有可以） 否则会进入死循环 因为
                // start就是从i开始的 如果是arr[start]<pivot
                //下面里面不成立了 bobo算法start是从i+1开始 这里while循环必须是没有等于号(效率才更快）
                end--;
            while(start<end&&arr[start]<=pivot)
                start++;
            //里面不能写end-- start++ 因为如果end是5 start是4 能进入if里面交换后 如果end-- star t++就end变成4 start变成5了
            //本来应该是进入下一轮end变成4 start就不会+1 就直接退出while循环了
            //这里只是起到一个交换的作用 前面找到比标定点大的 后面找到比标定点小的 所以要交换一下 和end-- start++ 无关
            // 到这里下面之后就要退出while循环 然后将标定点和i位置也是j位置处的值交换一下 就能将标定点移动正确位置
            // 不用if判断也行 用了if判断可以减少一次索引相同时不必要的交换
            if(end!=start){
                int temp=arr[end];
                arr[end]=arr[start];
                arr[start]=temp;

            }
        }
        int temp=arr[i];
        arr[i]=arr[end];
        arr[end]=temp;
        quickSort(arr,i,end-1);
        quickSort(arr,end+1,j);

    }

    static void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    //单边循环快排 选择最右元素作为基准点元素 j指针负责找到比pv小的元素 一旦找到与i进行交换
    //i指针负责维护小于pv元素的边界，也是每次交换的目标索引
    //最后基准点与i交换，i即为分区位置

    /*单边循环快排选择最右元素作为基准元素 因为是从前往后遍历 这样就很方便 基准点不会占用位置 只要从前往后逐渐遍历基准点前面的
    所有元素即可 当达到条件时交换 每次找到a[j]<pv时将i j位置的元素互换这样就能使得最终小的在前 大的在后  j是比基准点小的元素
    移到前面这很容易理解 i位置的元素移到后面因为i位置的元素一定是比基准点大的 之所以i位置的元素一定是比基准点大的是因为i指针
    从头到尾遵循的定义就是维护小于基准点元素的边界*/
    public static void quickSort1(int [] nums,int l,int r){
        if(l>=r)
            return ;

        int pivot=nums[r];
        int i=l;
        for(int j=l;j<r;j++){
            if(nums[j]<pivot){
                swap(nums,i++,j); //可以优化一下 先判断i和j不相等再swap
            }
        }
        swap(nums,i,r);

        quickSort1(nums,l,i-1);
        quickSort1(nums,i+1,r);

    }

    //双边循环快排 和quickSort解法是一样的
    //选择最左元素作为基准点元素 j指针负责从右向左找比pv小的元素 i指针负责从左向右找比基准点大的元素 一旦找到二者
    // 进行交换 直到i,j相交 最后基准点与i(此时i j相等）交换 i即为分区位置
    public static void quickSort2(int[] nums,int l,int r){
        if(l>=r)
            return ;

        int pivot=nums[l];
        int i=l,j=r;
        while(i<j){
            while(i<j&&nums[j]>pivot)
                j--;
            //a[i]<=pv必须有=是因为刚开始i和pv都是在l位置 a[i]是=pv的 此时必须要让循环能继续往后i++在左边继续寻找比pv大的数 如果这里没有=就直接跳出循环了
            while(i<j&&nums[i]<=pivot)
                i++;
            swap(nums,i,j);
        }
        swap(nums,l,i);

        quickSort2(nums,l,i-1);
        quickSort2(nums,i+1,r);

    }
    public static void main(String[] args) {
        int[] arr={45,34,45,23,14,42,14};
//        bubbleSort(arr);
//        selectSort(arr);
        insertSort2(arr);
//        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
