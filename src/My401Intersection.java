import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/13 9:37
 */
public class My401Intersection {
    public static int[] intersection(int[] arr1,int[] arr2){
        /*刚开始想的是直接把找到的重复的元素加到res中不用新建一个resultset 后来发现不可以
        * 因为直接添加的话可能会包含重复的元素 且res数组不知道它的size 无法给他初始化分配空间
        * 其实不知道size可以解决 先就设为其中一个数组的长度 最后返回时用Arrays.copyOf截断就可以*/
        TreeSet<Integer> record=new TreeSet<>();
        for (int value : arr1) {
            record.add(value);
        }
        TreeSet<Integer> resultSet=new TreeSet<>();
        for (int value: arr2){
            if(record.contains(value))
                resultSet.add(value);
        }
        int[] res=new int[resultSet.size()];
        int index=0;
        for (Integer num :resultSet) {
            res[index++]=num;
        }
        return res;
    }

    //排序+双指针解法
    public static int[] intersection1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i=0,j=0;
        int n1=nums1.length,n2=nums2.length;
        int[] res=new int[n1];
        int index=0;
        while(i<n1&&j<n2){
            if(nums1[i]==nums2[j]){
                if(index==0||nums1[i]!=res[index-1]){
                    res[index++]=nums1[i];
                }
                i++;
                j++;
            }
            else if(nums1[i]<nums2[j])
                i++;
            else j++;

        }
        return Arrays.copyOf(res,index);
    }
    private static void printArr(int[] arr){
        for(int e: arr)
            System.out.print(e + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        int[] res = intersection1(nums1, nums2);
        printArr(res);
    }
}
