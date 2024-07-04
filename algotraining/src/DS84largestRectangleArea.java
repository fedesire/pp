import java.util.Arrays;
import java.util.LinkedList;

/**
 * 柱状图中最大矩形
 *给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * @date 2024/5/9 8:48
 */
/*单调栈的经典应用场景是，在一维数组中，对每一个数字，找到前/后面第一个比自己大/小的元素。

该题的思路是：

对数组中的每个元素，若假定以它为高，能够展开的宽度越宽，那么以它为高的矩形面积就越大。
因此，思路就是找到每个元素左边第一个比它矮的矩形和右边第一个比它矮的矩形，在这中间的就是最大宽度
最后对每个元素遍历一遍找到最大值即可。*/
public class DS84largestRectangleArea {
    // 找左右两边第一个比自己小的 找右边第一个比自己小的 就是从左向右遍历用单调递增栈
    // 找左边第一个比自己小的就是从右向左遍历用单调递增栈


    // 其中 l[i] 代表位置 i左边最近一个比其小的位置（初始值为 −1），r[i]代表位置 i右边最近一个比其小的位置（初始值为 nnn），
    // 那么 r[i]−l[i]−1则是以 hs[i]作为矩形高度时所能取得的最大宽度。

    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int[] left = new int[len];
        int[] right = new int[len];
        // 初始值是-1 如果经过下面的处理后left[i]还是-1 代表i左边找不到比它小的 可以看成-1位置处有一个元素是0 这样就一定比i
        // 处的元素小 下标i的元素左右两边第一个比自己小的位置是left[i] right[i] 那计算宽度就是[left+1,right-1]
        // 所以填充-1的好处就是 -1+1=0 刚好符合此时计算宽度左边要从0开始
        // 同样填充n的好处就是 n-1=len 刚好符合此时计算宽度右边要从len开始
        Arrays.fill(left, -1);
        Arrays.fill(right, len);
        LinkedList<Integer> stack = new LinkedList<>();
        // 注意这里里面的while循环条件必须是> 不能有=
        for(int i=0;i<len;i++){
            while(!stack.isEmpty()&&heights[stack.peek()]>heights[i]){
                int index=stack.pop();
                right[index]=i;
            }
            stack.push(i);
        }
        stack.clear();
        for(int i=len-1;i>=0;i--){
            while(!stack.isEmpty()&&heights[stack.peek()]>heights[i]){
                int index=stack.pop();
                left[index]=i;
            }
            stack.push(i);
        }
        int max=0;
        for(int i=0;i<len;i++){
            int area=(right[i]-left[i]-1)*heights[i];
            max=Math.max(max,area);
        }
        return max;
    }
    public int largestRectangleArea1(int[] heights) {
        int len = heights.length;
        int[] left = new int[len];
        int[] right = new int[len];
        Arrays.fill(right, len);
        LinkedList<Integer> stack = new LinkedList<>();
        for(int i=0;i<len;i++){
            while(!stack.isEmpty()&&heights[stack.peek()]>=heights[i]){
                int index=stack.pop();
                right[index]=i;
            }
            left[i]=stack.isEmpty()?-1:stack.peek();
            stack.push(i);
        }
        int max=0;
        for(int i=0;i<len;i++){
            int area=(right[i]-left[i]-1)*heights[i];
            max=Math.max(max,area);
        }
        return max;
    }
    public static void main(String[] args) {
        int[] arr={1,1};
        DS84largestRectangleArea ds84largestRectangleArea=new DS84largestRectangleArea();
        System.out.println(ds84largestRectangleArea.largestRectangleArea(arr));
    }
}
