import java.util.*;
import java.util.function.Consumer;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/16 9:58
 */
/*小明来到某学校当老师，需要将学生按考试总分或单科分数进行排名，你能帮帮他吗？
输入描述
第 1 行输入两个整数，学生人数 n 和科目数量 m。
0 < n < 100
0 < m < 10
第 2 行输入 m 个科目名称，彼此之间用空格隔开。
科目名称只由英文字母构成，单个长度不超过10个字符。
科目的出现顺序和后续输入的学生成绩一一对应。
不会出现重复的科目名称。
第 3 行开始的 n 行，每行包含一个学生的姓名和该生 m 个科目的成绩（空格隔开）
学生不会重名。
学生姓名只由英文字母构成，长度不超过10个字符。
成绩是0~100的整数，依次对应第2行种输入的科目。
第n+2行，输入用作排名的科目名称。若科目不存在，则按总分进行排序。
输出描述
输出一行，按成绩排序后的学生名字，空格隔开。成绩相同的按照学生姓名字典顺序排序。
用例
输入 3 2
yuwen shuxue
fangfang 95 90
xiaohua 88 95
minmin 100 82
shuxue
输出 xiaohua fangfang minmin
说明 按shuxue成绩排名，依次是xiaohua、fangfang、minmin
输入 3 2
yuwen shuxue
fangfang 95 90
xiaohua 88 95
minmin 90 95
zongfen
输出 fangfang minmin xiaohua
说明 排序科目不存在，按总分排序，fangfang和minmin总分相同，按姓名的字典顺序，fangfang排在前面*/
public class SortScore {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        HashMap<String, HashMap<String,Integer>> stuScores=new HashMap<>();
        String[] lessons = scanner.nextLine().split(" ");
     /*   ArrayList<String> lessions=new ArrayList<>();
        for (int i = 0; i < m; i++) {
            lessions.add(scanner.next());
        }*/
        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().split(" ");
            HashMap<String,Integer> scores=new HashMap<>();
            for (int j = 0; j < m; j++) {
                scores.put(lessons[j],Integer.valueOf(parts[j+1]));
            }
                stuScores.put(parts[0],scores);
        }
        String target=scanner.nextLine();
        boolean single=false;
        for (int i = 0; i < m; i++) {
            if(lessons[i].equals(target)){
                single=true;
                break;
            }
        }
        if (single) {
            stuScores.entrySet().stream().sorted((o1, o2) -> {
                int s1=o1.getValue().get(target);
                int s2=o2.getValue().get(target);
                if(s1==s2)
                    return o1.getKey().compareTo(o2.getKey());
                return s2-s1;
            }).forEach(e -> System.out.print(e.getKey()+" "));
        }
        else{
            stuScores.entrySet().stream().sorted((o1, o2) -> {
                int sum1=o1.getValue().values().stream().reduce(Integer::sum).orElse(0);
                int sum2=o2.getValue().values().stream().reduce(Integer::sum).orElse(0);
                if(sum2==sum1)
                    return o1.getKey().compareTo(o2.getKey());
                return sum2-sum1;
            }).forEach(e -> System.out.print(e.getKey()+" "));
        }

    }
}
