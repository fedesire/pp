import java.util.Stack;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/20 9:14
 */
//本题还可以用数组模拟栈 详见官方题解
public class Lc150ReversePolish {
    private static int operation(String s1,String s2,String operator){
        int n1=Integer.parseInt(s1);
        int n2=Integer.parseInt(s2);
        if(operator.equals("+"))
            return n1+n2;
        else if(operator.equals("-"))
            return n1-n2;
        else if(operator.equals("*"))
            return n1*n2;
        else return n1/n2; //因为题目给定两个整数之间的除法总是 向零截断 所以可以用int表示
    }
    public static int evalRPN(String[] tokens){
        Stack<String> stack=new Stack<>();
        for(String token : tokens){
            if(token.matches("^-?\\d+$")){ //匹配正负整数 \\d用[0-9]代替也可以
                stack.push(token);
            }
            else {
                String agr2=stack.pop();
                String agr1=stack.pop();
                stack.push(String.valueOf(operation(agr1,agr2,token)));
            }
        }
        return Integer.parseInt(stack.pop());
    }

    //改进的我的版本 主要是Stack存放Integer 不要一上来原始数据是什么类型 stack里就存什么类型
    // 可以根据题意适当地转换数据类型存储 效率会更高
    public static int evalRPN2(String[] tokens){
        Stack<Integer> stack=new Stack<>();
        for(String token : tokens){
            if(token.matches("^-?\\d+$")){ //lc上百分比不高 好像是因为这里慢了
                stack.push(Integer.valueOf(token));
            }
            else {
                int agr2=stack.pop();
                int agr1=stack.pop();
                switch(token){
                    case "+":
                        stack.push(agr1+agr2);
                        break;
                    case "-":
                        stack.push(agr1-agr2);
                        break;
                    case "*":
                        stack.push(agr1*agr2);
                        break;
                    case "/":
                        stack.push(agr1/agr2);
                        break;
                    default:
                        stack.push(Integer.valueOf(token));
                        break;
                }
            }
        }
        return stack.pop();
    }

    public static int evalRPN1(String[] tokens){
        Stack<Integer> stack=new Stack<>();
        int agr1,agr2;
        for(String token: tokens){
            switch(token) {
                case "+":
                    agr2=stack.pop();
                    agr1=stack.pop();
                    stack.push(agr1+agr2);
                    break;
                case "-":
                    agr2=stack.pop();
                    agr1=stack.pop();
                    stack.push(agr1-agr2);
                    break;
                case "*":
                    agr2=stack.pop();
                    agr1=stack.pop();
                    stack.push(agr1*agr2);
                    break;
                case "/":
                    agr2=stack.pop();
                    agr1=stack.pop();
                    stack.push(agr1/agr2);
                    break;
                default:
                    stack.push(Integer.valueOf(token));
                    break;
            }
        }
        return stack.pop();
    }
    public static void main(String[] args) {
        System.out.println("-11".matches("^-?[0-9]+$"));
        String[] tokens={"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(evalRPN2(tokens));
    }
}
