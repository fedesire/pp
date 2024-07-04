import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/12 10:33
 */
public class  MinStack {
    private final int INIT_SIZE=100;
    private int[] elementData;
    private int size;
    private int minElement;
    private int minCount;//记录当前栈里面和最小值minElement相等的数有多少个 主要是为了pop的时候更新minELement

    public MinStack() {
        elementData=new int[INIT_SIZE];
        minElement=Integer.MAX_VALUE;
    }
    //扩容检测
    public void ensureCapacity(){
        //size当前栈里的元素个数 length数组的长度 不能是和initsize比 因为扩容了一次之后 intisize就没有比较意义了
        //和length-1比 所以是这次push完后数组就满了 就执行扩容 如果是和length比 那就是满了之后下次再有数
        //push 进来之前扩容
        if(size>=elementData.length-1)
            elementData= Arrays.copyOf(elementData,elementData.length*2);
        //用arraycopy虽然可以复制 但是不能扩容 length比原数组长能自动填充0 这里长了就报indexoutofbounds异常
//        System.arraycopy(elementData,0,elementData,0,elementData.length*2);
    }

    public void push(int val) {
        ensureCapacity();
        elementData[size++]=val;
        if(val<minElement){
            minElement=val;
            minCount=1;
        }
        else if(val==minElement)
            minCount++;
    }

    public void pop() {
        int popNum=elementData[--size];
        if(popNum==minElement&&--minCount==0){
            minElement=Integer.MAX_VALUE;
            for (int e : elementData) {
                minElement=Math.min(minElement,e);
            }
            minCount=1;
        }
    }

    public int top() {
        return elementData[size-1];
    }

    public int getMin() {
        return minElement;
    }
}
/*只需要设计一个数据结构，使得每个元素 a 与其相应的最小值 m 时刻保持一一对应。因此我们可以使用一个辅助栈，
与元素栈同步插入与删除，用于存储与每个元素对应的最小值。*/
class MinStack1{
    Stack<Integer> eleStack;
    Stack<Integer> eleMinStack;
    MinStack1(){
        eleStack=new Stack<Integer>();
        eleMinStack=new Stack<Integer>();
        eleMinStack.push(Integer.MAX_VALUE);
    }
    public void push(int x){
        eleStack.push(x);
        eleMinStack.push(Math.min(x,eleMinStack.peek()));
    }
    public void pop(){
        eleStack.pop();
        eleMinStack.pop();
    }
    public int top(){
        return eleStack.peek();
    }
    public int getMin(){
        return eleMinStack.peek();
    }

}
