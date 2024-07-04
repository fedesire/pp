import java.util.ArrayList;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/21 20:11
 */

import java.util.ArrayList;
import java.util.Scanner;
/*
* 输入描述
输入分3部分：
第一行表示注册的App数量 N（N ≤ 100）
第二部分包括 N 行，每行表示一条App注册数据
最后一行输入一个时间点，程序即返回该时间点使用的App
2
App1 1 09:00 10:00
App2 2 11:00 11:30
09:30
数据说明如下：
N行注册数据以空格分隔，四项数依次表示：App名称、优先级、起始时间、结束时间
优先级1~5，数字越大，优先级越高
时间格式 HH:MM，小时和分钟都是两位，不足两位前面补0
起始时间需小于结束时间，否则注册不上
注册信息中的时间段包含起始时间点，不包含结束时间点
输出描述
输出一个字符串，表示App名称，或NA表示空闲时间
备注
用例保证时间都介于 00:00 - 24:00 之间；
用例保证数据格式都是正确的，不用考虑数据输入行数不够、注册信息不完整、字符串非法、优先级超限、时间格式不正确的问题；
用例
输入 1
App1 1 09:00 10:00
09:30
输出 App1
说明 App1注册在9点到10点间，9点半可用的应用名是App1
输入 2
App1 1 09:00 10:00
App2 2 09:10 09:30
09:20
输出 App2
说明 App1和App2的时段有冲突，App2优先级比App1高，注册App2后，系统将App1的注册信息自动注销后，09:20时刻可用应用名是App2
输入 2
App1 1 09:00 10:00
App2 2 09:10 09:30
09:50
输出 NA
说明 App1被注销后，09:50时刻没有应用注册，因此输出NA*/
/*
* 将输入的App按照优先级降序（对于优先级相同的App，保持输入顺序，即稳定排序），此时我们会先注册高优先级的App，这样的话，
* 后注册的App只要和前面注册的App发生冲突，则必然无法注册，因为前面已注册App的优先级必然更高。*/
class App {
    String name;
    int priority;
    int startTime;
    int endTime;

    public App(String name, int priority, int startTime, int endTime) {
        this.name = name;
        this.priority = priority;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

public class LogicAppRegister {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        // 需要注册的App
        ArrayList<App> apps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            apps.add(new App(sc.next(), sc.nextInt(), convert(sc.next()), convert(sc.next())));
        }

        // 需要查询的时间点
        int queryTime = convert(sc.next());

        System.out.println(getResult(apps, queryTime));
    }

    public static String getResult(ArrayList<App> apps, int queryTime) {
        // 记录已注册的App
        ArrayList<App> registereds = new ArrayList<>();

        // 按照注册优先级降序
        apps.sort((a, b) -> b.priority - a.priority);

        for (App app : apps) {
            // 起始时间>=结束时间，则注册不上
            if (app.startTime >= app.endTime) continue;
            boolean intersect_flag=false;
            for (App registered : registereds) {
                // 如果和前面注册的App注册时间有交集（冲突），由于已经按照优先级排序，因此后注册的优先级肯定不比前面高，因此发生冲突时，后注册的无法成功
                if (hasInterSection(app, registered)) {
                    intersect_flag=true;
                    break;
                }
            }

            // 如果和前面高优先级的App无注册时间冲突，则可以注册
            if(!intersect_flag)
                registereds.add(app);
        }

        String ans = "NA";

        for (App app : registereds) {
            if (queryTime >= app.startTime && queryTime < app.endTime) {
                ans = app.name;
                // 注册成功的App时段之间互不冲突，因此queryTime只会对应一个App
                break;
            }
        }

        return ans;
    }

    public static int convert(String time) {
        // 时间格式 HH:MM，小时和分钟都是两位，不足两位前面补0
        String[] tmp = time.split(":");

        String hours = tmp[0];
        String minutes = tmp[1];
        return Integer.parseInt(hours) * 60 + Integer.parseInt(minutes);
    }

    // 判断两个范围是否有交集
    private static boolean hasInterSection(App app1, App app2) {
        int s1 = app1.startTime, e1 = app1.endTime;
        int s2 = app2.startTime, e2 = app2.endTime;

        if (s1 >= s2 && s1 < e2) {
            return true;
        }

        if (s2 >= s1 && s2 < e1) {
            return true;
        }

        return false;
    }
}