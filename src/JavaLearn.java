import com.sun.deploy.util.ArrayUtil;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/11 17:08
 */
public class JavaLearn {
    private static String intToString(int num){

        StringBuilder s = new StringBuilder("");
        String sign = "+";
        if(num < 0){
            num = -num;
            sign = "-";
        }

        //为啥要加Character. 感觉直接s.append(num % 10);也行 Character.getNumericValue('a')是10是什么原理
        while(num != 0){
            s.append( Character.getNumericValue('0') +num % 10);
            num /= 10;
        }
        if(s.length() == 0)
            s.append('0');

        s.reverse();
        if(sign == "-")
            return sign + s.toString();
        else
            return s.toString();
    }
    public static void funcAboutInteger(){


    }


    public static void main(String[] args) {







    }
}
/**
 * Created by liuyubobobo.
 */
 class MyUtil { //不能用public 因为一个java文件只能有一个public类 不写可以就是默认的default通常称为“默认访问模式”，在该模式下，这个类只能被同一个包中的类访问或引用，这一访问特性又称包访问性。
//不能用private private一般不修饰类，但是可以修饰内部类 protected用在这也不行
//    有一种是工具类, 所有方法都是静态的.构造函数设为私有, 就是这个类不需要实例.
//还有的是类本身很大, 构造多个可能爆内存, 还可能数据不一致. 就单例, 构造函数设为私有.

    private MyUtil(){}

    public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {

        assert n > 0 && rangeL <= rangeR;

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++)
            arr[i] = (int)(Math.random() * (rangeR - rangeL + 1)) + rangeL;
        return arr;
    }

    public static Integer[] generateOrderedArray(int n) {

        assert n > 0;

        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++)
            arr[i] = i;
        return arr;
    }
}
