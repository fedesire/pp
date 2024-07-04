import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
* 将一个 csv 格式的数据文件中包含有单元格引用的内容替换为对应单元格内容的实际值。
comma separated values(CSV) 逗号分隔值，csv 格式的数据文件使用逗号 "," 作为分隔符将各单元的内容进行分隔。
输入描述
输入只有一行数据，用逗号分隔每个单元格，行尾没有逗号。最多26个单元格，对应编号A~Z。
每个单元格的内容包含字母和数字，以及使用 '<>' 分隔的单元格引用，例如：<A>表示引用第一个单元的值。
每个单元格的内容，在替换前和替换后均不超过100个字符。
引用单元格的位置不受限制，允许排在后面的单元格被排在前面的单元格引用。
不存在循环引用的情况，比如下面这种场景是不存在的：
A单元恪：aCd<B>8U
B单元格：KAy<A>uZq0
不存在多重 '<>' 的情况，一个单元只能引用一个其他单元格。比如下面这种场景是不存在的：
A单元格：aCdOu
B单元格：kAydzco
C单元格：y<<A><B>>d
输出描述
输出替换后的结果
用例
输入 1,2<A>00
输出 1,2100
说明 第二个单元中有对A单元的引用，A单元格的值为1，替换时，将A单元的内容替代<A>的位置，并和其他内容合并。
输入 1<B>2,1
输出 112,1
说明 第一个单元中有对B单元的引用，B单元格的值为1，耆换时，将第二个数据第单元的内容替代<B>的位置，并和其他内容合并
输入 <B<12,1
输出 -1
说明 第一个单元中有错误的单元格引用方式，输出字符串"-1"表示错误*/

// 这题题目输入描述里有的条件在代码里还做了判断 有的好像有没有
// aCd<B>8U,KAy<A>uZq0 aCdOu,kAydzco,y<<A><B>>d 比如第一个循环引用 代码还是会报错栈溢出
public class RecCellReplace {
    static String[] cells;
    //
    //当?紧随任何其他限定符（*、+、?、{n}、{n,}、{n,m}）之后时，匹配模式是"非贪心的"。"非贪心的"模式匹配搜索到的、尽可能短的字符串，
    // 而默认的"贪心的"模式匹配搜索到的、尽可能长的字符串。例如，在字符串"oooo"中，"o+?"只匹配单个"o"，而"o+"匹配所有"o"。
    static Pattern p = Pattern.compile("(<.*?>)"); // 01<D>23<B>45<C>6,B,C,D 白天的时候怎么也想不明白为什么加? 就能
    // 匹配到<D> 不加就会匹配到<D>23<B>45<C> 睡前再回想一下 突然就想通了 因为后面的也可以看做是一个<>里面的字符是D23<B>45<C
    static Pattern p1=Pattern.compile("(<.*?>)");


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        cells = sc.nextLine().split(",");
        System.out.println(getResult());
//        System.out.println(solve());
    }

    public static String getResult() {
        if (cells.length > 26) {
            // 最多26个单元格，对应编号A~Z
            return "-1";
        }

        StringJoiner sj = new StringJoiner(",");

        for (int i = 0; i < cells.length; i++) {
            // 替换单元格中的引用
            if (!changeCell(i)) {
                // 替换失败，则返回-1
                return "-1";
            }

            if (cells[i].length() > 100) {
                // 每个单元格的内容，在替换前和替换后均不超过100个字符
                return "-1";
            }

            if (!cells[i].matches("^[a-zA-Z0-9]+$")) {
                // 每个单元格的内容包含字母和数字
                return "-1";
            }

            // 替换成功，则记录单元格内容
            sj.add(cells[i]);
        }

        return sj.toString();
    }

    public static boolean changeCell(int index) {
        // 通过正则匹配出单元格内容中"引用字符串"
        Matcher m = p.matcher(cells[index]);

        while (m.find()) {
            // reference记录引用字符串 还有一个特殊的组（group(0)），它总是代表整个表达式。该组不包括在 groupCount 的返回值中。
            String reference = m.group(0);

            // 引用单元格编号只能是A~Z的字母，即引用引用字符串长度只能是3，比如"<A>"
            if (reference.length() != 3) {
                return false;
            }

            // 引用单元格的编号
            char reference_cellNum = reference.charAt(1);
            // 当前单元格的编号
            char self_cellNum = (char) ('A' + index);

            // 引用单元格编号只能是A~Z的字母，且不能自引用
            if (reference_cellNum < 'A' || reference_cellNum > 'Z' || reference_cellNum == self_cellNum) {
                return false;
            }

            // 引用单元格的数组索引， 'A' -> 0  ... 'Z' -> 25
            int reference_index = reference_cellNum - 'A';

            // 引用单元格编号不存在
            if (reference_index >= cells.length) {
                return false;
            }

            if (!changeCell(reference_index)) return false;

            // 将单元格内容中的引用部分，替换为被引用的单元格的内容 replaceAll方法支持正则匹配 replace不支持
            cells[index] = cells[index].replaceAll(reference, cells[reference_index]);
            // 重新正则匹配
            m = p.matcher(cells[index]);
        }

        return true;
    }

    public static String solve(){
        StringJoiner joiner=new StringJoiner(",");
        if(cells.length>26)
            return "-1";
        for (int i = 0; i < cells.length; i++) {
           if(!changeCell1(i))
               return "-1";
           if(cells[i].length()>100)
               return "-1";
           if(!cells[i].matches("[A-Za-z0-9]+"))
               return "-1";
           joiner.add(cells[i]);

        }
        return joiner.toString();
    }

    public static boolean changeCell1(int index){
        String s=cells[index];
        Matcher m=p.matcher(s);
        while(m.find()){
            String matchedStr=m.group(0);
            if(matchedStr.length()!=3||!Character.isUpperCase(matchedStr.charAt(1)))
                return false;
            int matchedIndex=matchedStr.charAt(1)-'A';
            if(matchedIndex==index||matchedIndex>=cells.length)
                return false;
            if (!changeCell1(matchedIndex)) {
                return false;
            }
            cells[index]=cells[index].replaceAll(matchedStr,cells[matchedIndex]);
            m=p.matcher(cells[index]);
        }
        return true;
    }
}