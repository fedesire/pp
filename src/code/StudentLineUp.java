package code;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 学生目前排队情况：8 9 7 5 6 3 2 1 4
 * 学生分组情况：7 8 9 4 2 1 3 5 6
 * @date 2024/4/24 9:04
 */
public class StudentLineUp {
     static class Block{
        int groupId;
        int count;
        Block(int groupId,int count){
            this.groupId=groupId;
            this.count=count;
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] cur = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n=cur.length;
        int[] group=new int[n+1];
        for (int i = 0; i < n; i++) {
            int num = scan.nextInt();
            group[num] = i/3;
        }
        // 相邻相同组号合并
        LinkedList<Block> queue = new LinkedList<>();
        for (int num : cur) {
            int groupId = group[num];
            if(queue.isEmpty()||queue.getLast().groupId!=groupId){
                Block block=new Block(groupId,1);
                queue.add(block);
            }
            else{
                queue.getLast().count++;
            }
        }
        int moved_count=0;
        while(!queue.isEmpty()){
            Block first=queue.removeFirst();
            //1 1 x 1 y z 后面的1移动到前面去移动次数少而且能保证这些特殊情况下 1 1 2 1 2 2 1移完了2也满足条件了
            if(first.count==2){
                moved_count+=1;
                queue=convert(queue,first.groupId);
            }
            //1 x 1 1 y z    1 x 1 y 1 z
            else if(first.count==1){
                Block temp = queue.getFirst();
                // 跳过中间连续3个的block
                while(temp.count==3){
                    queue.removeFirst();
                    temp=queue.getFirst();
                }
                //1 2 2 2 x[1 1] 这种情况直接将1移到后面是最优的
                if(temp.groupId==first.groupId&&temp.count==2){
                    moved_count++;
                    queue.removeFirst();
                }
                else{
                    moved_count+=2;
                    queue=convert(queue,first.groupId);
                }
            }
        }
        System.out.println(moved_count);
    }

    private static LinkedList<Block> convert(LinkedList<Block> queue, int groupId) {
         LinkedList<Block> newQueue=new LinkedList<>();
         while(!queue.isEmpty()){
             Block first = queue.removeFirst();
             if(first.groupId==groupId)
                 continue;
             if(newQueue.isEmpty()||newQueue.getLast().groupId!=first.groupId)
                 newQueue.add(new Block(first.groupId,first.count));
             else{
                 newQueue.getLast().count+=first.count;
             }
         }
         return newQueue;
    }
}
