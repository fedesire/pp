import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 最小基因变化
 * @date 2024/2/13 10:02
 */
public class Gr433MinMutation {
    public int minMutation(String startGene, String endGene, String[] bank) {
        if(endGene.equals(startGene)) return 0;
        HashMap<String,Boolean> map=new HashMap<>(); //key存储基因库bank中的所有有效基因 value表示key 在起始基因startGene
        //向endGene变化的过程中基因key是否出现过
        for(String gene:bank){
            map.put(gene,false);
        }
        Queue<Pair<String,Integer>> queue=new LinkedList<>();
        queue.add(new Pair<>(startGene,0));
        while(!queue.isEmpty()){
            Pair<String,Integer> p=queue.remove();
            for (String s : map.keySet()) {
                if(!map.get(s) &&validChange(p.getKey(),s)){
                    if(s.equals(endGene))
                        return p.getValue()+1;
                    queue.add(new Pair<>(s,p.getValue()+1));
                    map.put(s,true);
                }
            }
        }
        return -1;
    }

    public boolean validChange(String original,String object){
        int count=0;
        for (int i = 0; i < 8; i++) {
            if(original.charAt(i)!=object.charAt(i)){
                count++;
                if(count>1) return false;
            }

        }
        return true;
    }
}
