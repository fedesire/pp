import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/10 10:22
 */
public class Stk71simplifyPath {
    public static String simplifyPath(String path) {
        Stack<String> stack=new Stack<>();
        StringBuilder res=new StringBuilder();
        String[] strs = path.split("/");
//        String[] strs = path.split("/+");//这样能达到//中间的""不会保留在strs中 第一个/之前还是会有一个""这是避免不了的
        //注意写path.split("//\/")也能达到效果 但是path.split("/\//")出来的效果//中间的""就会保留在strs中 因为会先匹配/而不是//
        for (String str : strs) {
            System.out.println(str);
            if(str.equals(".")||str.equals(""))
                continue;
            else if(str.equals("..")){
                if(!stack.isEmpty())
                    stack.pop();
            }
            else
                stack.push(str);
        }
        if(stack.isEmpty())
            res.append("/");
        while(!stack.isEmpty()){
            res.insert(0,"/"+stack.pop());
        }
        return res.toString();
    }

    //和我的方法思路其实一样 只不过for循环if else顺序改了一下 而且栈改为用双向队列 因为最终需要得到的结果是从前往后
    // 遍历的序列结果 我是利用stringbuilder的insert方法
    public static String simplifyPath1(String path) {
        //变量名字叫栈 变量的类型是双端队列 因为程序里先把它用作栈 执行在栈顶压入弹出addLast removeLast的操作
        // 后来要得到返回结果又将其作为队列 从队列头开始遍历输出removeFirst
        Deque<String> stack=new ArrayDeque<>(); //用LinkedList也行
        StringBuilder res=new StringBuilder();
        String[] strs = path.split("/");
        for (String str : strs) {
            if(str.equals("..")){
                if(!stack.isEmpty())
                    stack.removeLast();
            }
            else if(str.length()>0&&!str.equals("."))
                stack.addLast(str);
        }
        if(stack.isEmpty())
            res.append("/");
        else{
            while(!stack.isEmpty()){
                res.append("/");
                res.append(stack.removeFirst());
//                res.append("/"+stack.removeFirst());//改成上面的就能提升1ms 拼接其实虽然代码少了一行 但耗时久
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(simplifyPath("/home//foo/"));
    }
}
