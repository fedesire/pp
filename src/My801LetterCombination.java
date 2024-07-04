import java.util.ArrayList;
import java.util.List;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/18 9:52
 */
public class My801LetterCombination { //看一下bobo实现里的注释
    private static List<String> res;
    //0表示空格 1什么也不是 所以传入的数字不能有1
    private static String[] letterMap={" ","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    //fc1 fc2在lc上结果的速度比lc快很多 将多次递归中都要用到的中间结果设为类成员变量就不用额外设置一个
    //参数每次都传入递归函数中 写参数这种虽然慢 但比较好理解 而且不容易写错 因为不需要考虑何时delete
    //上面的结论不对 并不是中间结果作为递归函数参数 函数里面就不需要delete remove 本题fc函数里不用delete
    //是因为参数类型是String不可变类 每调用一次会有不同的副本 fc1 fc2因为用stringbuilder所以会一直是一个SB
    //所以需要delete lc113pathsum中间结果作为函数参数和类成员变量两种写法 dfs实现时都需要递归结束后remove
    //因为此时中间结果是一个list
    private static StringBuilder curString=new StringBuilder();
    public static List<String> letterCombination(String numbers){ //查找一串数字能代表的所有可能的字符串
        res=new ArrayList<>();
        if(numbers.equals("")) //最好不用==
            return res;
//        findCombination(numbers,0,"");
//        findCombination1(numbers,0);
        findCombination3(numbers,0);


        return res;
    }
    // s中保存了此时从numbers[0...index-1]翻译得到的一个字母字符串
    // 寻找和numbers[index]匹配的字母, 获得numbers[0...index]翻译得到的解
    public static void findCombination(String numbers,int index,String s){
        if(index==numbers.length())
        {
            res.add(s);
            return ; //必须要有return 没有的话就无法递归终止 还得往下执行
        }
        Character digit=numbers.charAt(index); //当前处理的index位上的数字
        assert digit.compareTo('0')>=0 &&digit.compareTo('9')<=0&&digit.compareTo('1')!=0;
        String letters=letterMap[digit-'0'];
        for(int i=0;i<letters.length();i++){
            char c=letters.charAt(i);
            findCombination(numbers,index+1,s+c);
            //c不加toString也能自动转为String 因为此时+是连接运算符 每个参数都能自动转为String 常用的sout里str+int就是

        }
        return ;
    }

    //感觉这种写法只是最终的结果是对的 但思路不对或者说不好 还是记fc2
    public static void findCombination1(String numbers,int index){
        if(index==numbers.length())
        {
            res.add(curString.toString());
            curString.deleteCharAt(curString.length()-1);
            System.out.println(curString);
            return ; //必须要有return 没有的话就无法递归终止 还没往下执行
        }
        Character digit=numbers.charAt(index); //当前处理的index位上的数字
        assert digit.compareTo('0')>=0 &&digit.compareTo('9')<=0&&digit.compareTo('1')!=0;
        String letters=letterMap[digit-'0'];
        for(int i=0;i<letters.length();i++){
            char c=letters.charAt(i);
            curString.append(c);
            System.out.println(curString);
            findCombination1(numbers,index+1);
        }
        //如果不加这个判断 在递归全部结束 回到第一次调用fc1这儿 for循环结束后curString会变成空
        if(curString.length()>=1)
            curString.deleteCharAt(curString.length()-1);
        System.out.println(curString);

        return ;
    }

    //修改fc1中deletecharat的位置 更简洁
    public static void findCombination2(String numbers,int index){
        if(index==numbers.length())
        {
            res.add(curString.toString());
            System.out.println(curString);
            return ;
        }
        Character digit=numbers.charAt(index);
        assert digit.compareTo('0')>=0 &&digit.compareTo('9')<=0&&digit.compareTo('1')!=0;
        String letters=letterMap[digit-'0'];
        for(int i=0;i<letters.length();i++){
            char c=letters.charAt(i);
            curString.append(c);
            System.out.println(curString);
            findCombination2(numbers,index+1);
            curString.deleteCharAt(curString.length()-1);
            System.out.println(curString);

        }
        System.out.println("ghrohgir");
        System.out.println(curString);

        return ;
    }

    public static void findCombination3(String numbers,int index){
        if(index==numbers.length())
        {
            res.add(curString.toString());
            return ;
        }
        Character digit=numbers.charAt(index);
        assert digit.compareTo('0')>=0 &&digit.compareTo('9')<=0&&digit.compareTo('1')!=0;
        String letters=letterMap[digit-'0'];

        for(int i=0;i<letters.length();i++){
            char c=letters.charAt(i);
            // int size=curString.length();
            curString.append(c);
            findCombination3(numbers,index+1);
            // curString.delete(size,curString.length());
            curString.deleteCharAt(curString.length()-1);
        }
        return ;
    }
    public static void main(String[] args) {
        System.out.println(letterCombination("23"));
    }
}
