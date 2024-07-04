import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2024/1/20 11:33
 */
public class Ar380RandomizedSet {
    public HashMap<Integer,Integer> hm;
    public ArrayList<Integer> list;
    public Random random;
    public int i; //测试全局变量及成员变量 没初始化也是有默认值的
    public Ar380RandomizedSet(){
        hm=new HashMap<>();
        list=new ArrayList<>();
        random=new Random();
    }
    public boolean insert(int val){
        if(hm.containsKey(val))
            return false;
        hm.put(val,list.size());
        list.add(val);
        return true;
    }
    //用和最后一个数交换 之后删除最后一个数来达到删除的目的
    public boolean remove(int val){
        if(!hm.containsKey(val))
            return false;
        int valIndex=hm.get(val);
        int lastVal=list.get(list.size()-1);
        list.set(valIndex,lastVal);
        hm.put(lastVal,valIndex);
        list.remove(list.size()-1);
        hm.remove(val);
        return true;
    }
    public int getRandom(){
        return list.get(random.nextInt(list.size()));
    }

    public static void main(String[] args) {
        int j;
//        System.out.println(j); 局部变量不初始化没有默认值 会报错
        System.out.println(new Ar380RandomizedSet().i);
    }
}
