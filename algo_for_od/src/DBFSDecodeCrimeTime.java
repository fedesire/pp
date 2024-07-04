import java.util.*;
import java.util.regex.Pattern;

/**
 * 警察在侦破一个案件时，得到了线人给出的可能犯罪时间，形如 “HH:MM” 表示的时刻。
 * 根据警察和线人的约定，为了隐蔽，该时间是修改过的，
 * 解密规则为：利用当前出现过的数字，构造下一个距离当前时间最近的时刻，则该时间为可能的犯罪时间。
 * 每个出现数字都可以被无限次使用。
 * 输入描述
 * 形如HH:SS字符串，表示原始输入。
 * 输出描述
 * 形如HH:SS的字符串，表示推理处理的犯罪时间。
 * 备注
 * 1.可以保证现任给定的字符串一定是合法的。
 * 例如，“01:35”和“11:08”是合法的，“1:35”和“11:8”是不合法的。
 * 2.最近的时刻可能在第二天。
 * 用例
 * 输入 输出
 * 20:12 20:20
 * 23:59 22:22
 * 12:58 15:11
 * 18:52 18:55
 * 23:52 23:53
 * 09:17 09:19
 * 07:08 08:00
 * 看起来很复杂 根本找不到答案的规律 可能解决方法就会用dfs遍历所有可能性
 * 用dfs找全排列 在加入res之前要先判断是否是合法的时间格式字符串 dfs结束后对list里的元素按照字典顺序排序 之后就能找到输入的时间的后面一个
 * @date 2024/4/12 16:00
 */
public class DBFSDecodeCrimeTime {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String[] time = scanner.nextLine().split(":");

        String hour=time[0];
        String minute=time[1];
        HashSet<Character> set=new HashSet<>();
        set.add(hour.charAt(0));
        set.add(hour.charAt(1));
        set.add(minute.charAt(0));
        set.add(minute.charAt(1));

        Character[] array = set.toArray(new Character[0]);
        ArrayList<String> res=new ArrayList<>();
        dfs(array,new LinkedList<>(),res);

        // 按照字典顺序排序 0034 0039 0040这样
//        res.sort((o1, o2) -> o1.compareTo(o2));
        res.sort(String::compareTo);
        String inputTime=hour+minute;
        int i = res.indexOf(inputTime);
        int ansIndex=0;
        if(i!=res.size()-1)
            ansIndex=i+1;

        String ansTime = res.get(ansIndex);
        StringJoiner joiner=new StringJoiner(":");
        joiner.add(ansTime.substring(0,2));
        joiner.add(ansTime.substring(2,4));
        System.out.println(joiner.toString());

    }

//    static Pattern pattern= Pattern.compile("(([01][0-9])|([2][0-3]))[0-5][0-9]");
    static Pattern pattern= Pattern.compile("([01][0-9]|[2][0-3])[0-5][0-9]");
    public static void dfs(Character[] arr,LinkedList<Character> path,ArrayList<String> res){
        if(path.size()==4){
            StringBuilder sb=new StringBuilder();
            for (Character c : path) {
                sb.append(c);
            }
            String time=sb.toString();
            if(pattern.matcher(time).find())
                res.add(time);
            return ;
        }
        for (Character c : arr) {
            path.add(c);
            dfs(arr,path,res);
            path.removeLast();
        }
    }
}
