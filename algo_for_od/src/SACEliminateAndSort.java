import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/10 20:57
 */
/*
* 给定一个乱序的数组，删除所有的重复元素，使得每个元素只出现一次，并且按照出现的次数从高到低进行排序，相同出现次数按照第一次出现顺序进行
* 先后排序。

输入描述
一个数组

输出描述
去重排序后的数组

用例
输入	1,3,3,3,2,4,4,4,5
输出	3,4,1,2,5
备注	数组大小不超过100 数组元素值大小不超过100。
*/
public class SACEliminateAndSort {
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        String line = scanner.nextLine();
        String[] numbers = line.split(",");
        HashMap<String,Integer> count=new HashMap<>();
        HashMap<String,Integer> firstPosition=new HashMap<>();
        for (int i=0;i<numbers.length;i++) {
            count.put(numbers[i],count.getOrDefault(numbers[i],0)+1);
            firstPosition.putIfAbsent(numbers[i],i);
//            if(!firstPosition.containsKey(numbers[i]))
//                firstPosition.put(numbers[i],i);
        }
        StringJoiner joiner=new StringJoiner(",");
         count.keySet().stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (!count.get(o1).equals(count.get(o2)) )
                    return count.get(o2) - count.get(o1);
                return firstPosition.get(o1) - firstPosition.get(o2);
            }
        }).forEach(s->joiner.add(s));
        System.out.println(joiner.toString());
    }
}
