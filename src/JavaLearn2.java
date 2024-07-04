import com.sun.deploy.util.StringUtils;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import javafx.util.Pair;
import org.omg.CORBA.INTERNAL;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author xqi
 * @version 1.0
 * @description: TODO
 * @date 2023/12/13 10:21
 */
public class JavaLearn2 {
    public static void aboutContainer() {
        /*List*/

        //ArrayList能够快速获取元素的索引 第一次出现的索引位置indexOf 最后一次出现的索引位置lastIndexOf
        List<String> arrayList = new ArrayList<>(50);
        arrayList.add("ghiregh");

        System.out.println(arrayList.indexOf("ghiregh"));
        //用arrayList.for就可以生成
        for (String s : arrayList) {

        }

        //数组只能通过下标得知到具体的元素内容 无法通过值快速获取其下标索引 所以当有需要快速获取
        //元素下标索引的需求时 就要考虑将数组转化为列表
        String s = "ghir grhio oir ghrio";
        List<String> list = Arrays.asList(s.split(" "));
        //asList返回的是List 直接传给ArrayList会报错 必须像下面这种作为构造函数参数传入才行
        // 直接强转一下赋值也不行 编译不报错 运行时会报ClassCastException
        //其实asList()方法里面真正的实现写的是return new ArrayList(a) 只是方法声明的返回类型是接口List
        //java中接口可以作为方法的返回值进行传递：只是必须方法里实现时写的是返回一个接口的实现类的对象。
        ArrayList<String> list1 = new ArrayList<String>(Arrays.asList(s.split(" ")));
        list.indexOf("hroeo");

        /*使用Arrays.asList方法生成的List，是Arrays类的内部类，并非java.util包下我们
        常用的ArrayList  且生成的是一个固定长度的List，它的长度与原始数组的长度相同。
        这意味着我们无法通过添加或删除元素来改变List的长度，否则会抛出UnsupportedOperationException异常。
        如果需要对List进行增删操作，可以考虑创建一个新的ArrayList并将转换后的List作为构造函数的参数。
         */
        List<List<Integer>> res = new ArrayList<>();
        Integer[] arr = {1, 3, 4};
        res.add(new ArrayList<Integer>(Arrays.asList(arr)));

        Integer[] ar = {45, 34, 67, 77};
        Arrays.asList(ar).set(0, 23);
        System.out.println(ar[0]);//变成23 说明对asList返回的列表修改会直接写入原数组
        List<Integer> lis = Arrays.asList(ar);
        lis.set(1, 22);
        System.out.println(ar[1]);//22 因为list对象是指向了asList返回的列表 并不是新new的一个list
        List<Integer> list2 = new ArrayList(Arrays.asList(ar));
        list2.set(2, 98);
        System.out.println(ar[2]);//67 是数组原本的值
        Arrays.asList(ar).add(55);//不能add 会报错

        //toArray返回的是object[] 可以强转为Integer[] 但Integer[]转为int[] 不简洁 要使用Stream流
        //不行 虽然强转不报错 但运行时还是会报类型转换错误 可以直接toArray()方法里传入参数 但是只能传Integer[] 不能传int[]
        //HashSet里的toArray()方法也是同样的情况
        List<Integer> result = new ArrayList<>();
//        Integer[] ret=(Integer[])result.toArray();
        Integer[] aa = new Integer[result.size()];
        result.toArray(aa);
        result.toArray(new Integer[0]);
//        result.toArray(new int[0]); //直接转为int[]不行 会报错
        // 想要转换成int[]类型，就得先转成IntStream。
// 这里就通过mapToInt()把Stream<Integer>调用Integer::valueOf来转成IntStream
// 而IntStream中默认toArray()是转成int[]。
        int[] array1 = result.stream().mapToInt(Integer::valueOf).toArray();
        //用这种写法
        ArrayList<String> als = new ArrayList<>();
        als.add("Apple");
        als.add("Banana");
        als.add("Orange");
        /*传递了一个空的字符串数组 new String[0] 作为参数给 toArray() 方法。这是因为如果传递的数组的长度
        小于 ArrayList 的大小，toArray() 方法会创建一个新的数组并返回；如果传递的数组的长度大于或等于
        ArrayList 的大小，toArray() 方法会使用传递的数组来存储元素并返回。*/
        String[] array = als.toArray(new String[0]);

        //ArrayList()构造函数里不能直接传单个的这种元素 ArrayList有三种构造函数 空 int数字代表size 最后一种就是
        //ArrayList(Collection<? extends E>
//        List<String> l=new ArrayList<>("gheio");
        List<String> lll = new ArrayList<>();
        lll.add("grhihgr");


        //list用foreach循环遍历时可以修改数据 但不能remove删除数据 而且List<T>T是String Integer就算遍历的时候重新
        // 赋值 List里面的数据不会变 但如果T是一个新的自定义的有成员属性的类或者T也是一个list修改数据后原list里面会变
        List<Integer> as = new ArrayList<>();
        as.add(3);
        as.add(4);
        for (Integer a : as) {
            a = 34;
        }
        System.out.println(as); //还是[3, 4]
        List<List<String>> arrayList2 = new ArrayList<>();
        List<String> lq = new ArrayList<>();
        lq.add("ghori");
        arrayList2.add(lq);
        lq.add("gh");
        arrayList2.add(lq);
        System.out.println(arrayList2);
        for (List<String> listq : arrayList2) {
            listq.add("gho");
        }
        System.out.println(arrayList2);


        //当int[]型数组直接调用Arrays.asList()想Array转化成List 生成的List里面的泛型是int[] 不是想要的int 必须是
        //Integer[]数组调用才对
        /*在Arrays.asList中，该方法接受一个变长参数，一般可看做数组参数，但是因为int[] 本身就是一个类型，所以a变量作为
        参数传递时，编译器认为只传了一个变量，这个变量的类型是int数组，所以size为1，相当于是List中数组的个数。基本类型
        是不能作为泛型的参数，按道理应该使用包装类型，但这里缺没有报错，因为数组是可以泛型化的，所以转换后在list中就有
        一个类型为int的数组*/
        int[] p = {1, 2};
//        List<int[]> ints = Arrays.asList(p);
        List<Integer> listt = Arrays.asList(1, 2);
        Integer[] a = {3, 4};
        List<Integer> list11 = Arrays.asList(a);

        List rawType=new ArrayList();
        rawType.add("ghi");
        //原始类型 List 使用List里的元素时需要进行显示的类型转换 否则报错 因为rawType.get(0)是Object
        String ele= (String) rawType.get(0);


        /*Map*/

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        System.out.println(treeMap.get(4)); //返回null 说明treemap中元素如果不存在时是没有默认值的
        treeMap.put(1, 3);

        //Map的put操作如果新增的k是未出现过的 则返回null 如果新增的k之前出现过 则将其对应的v
        //改为新的v put方法返回值是之前的v
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("ghri", 2);

        int v2 = hm.put("ghri", 4);
        System.out.println(v2);//2
        hm.put("oirghri", 3);
        //Map插入元素的另一种写法 不需要写先判断containsKey
        int[] nums = {3, 45, 76};
        for (int num : nums) {
            treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
        }

        //hashmap的values()类型是Collection 可以作为构造函数的参数传入hashset arraylist这种
        //hashmap只提供方法get()通过key获取value 没有提供方法能通过value得到key
        HashMap<Character, Character> s2t = new HashMap<>();
        HashSet<Character> hs1 = new HashSet<>(s2t.values());
        ArrayList<Character> la = new ArrayList<>(s2t.values());

        //hm的get返回的是V类型 如果V是Integer 在没判断containsKey(k) 直接用get(k)方法获取值就必须赋给Integer对象 否则
        //当k不存在时 get(k)返回的是null 赋给一个int对象 编译不报错 运行时会报空指针异常 如果已经提前判断k是存在的
        //那就可以直接用int对象接收 因为会自动拆箱
        HashMap<Integer, Integer> record = new HashMap<Integer, Integer>();
        record.put(3, 4);
        int n = record.get(1);
        System.out.println(n);

        //hm用foreach循环遍历
        for (Map.Entry<Integer, Integer> e : record.entrySet()) {
            e.getKey();
            e.getValue();
        }

        //这种写法new后面的泛型里的参数必须要填 不填会报错
        /*这种用双花括号其实是创建了一个匿名内部类对象 外层的括号是定义了一个匿名内部类 内层的花括号包含的代码是实例初始化块
        在匿名内部类构造时执行*/
        Map<String, String> ham = new HashMap<String, String>() {{
            put("grhi", "grhio");
            put("gheoi", "ghrio");
        }};

        LinkedHashMap<String,String> map=new LinkedHashMap<>(16, 0.75F,true);
        map.put("aa","bb");
        map.put("ge","bge");
        map.put("gr","bhh");
        map.put("agr","bgr");
        map.get("gr");
        map.get("aa");
        // {ge=bge, agr=bgr, gr=bhh, aa=bb} 说明尾部是最新的数据 所以用linkedhashmap实现lru缓存时淘汰的是头部元素
        System.out.println(map);


        // 测试父类数组里是可以存放子类的 所以hashmap里的table数组类型是Node 里面也可以存放树节点
        // 因为TreeNode是Node的子类
        Number[] numsk = new Number[3];
        numsk[0]=new Integer(1);
        numsk[1]=new Long(2);
        System.out.println(Arrays.toString(numsk));


        /*Set*/
        //HashSet不重复是当add加入的是重复元素时 保持set不变返回false罢了
        HashSet<Integer> hs = new HashSet<>();
        hs.add(34);
        hs.add(30);
        hs.add(2);
        hs.add(6);
        hs.add(15);
        //能够直接print出来 是因为默认调用了toString() 进源码查看就能发现hs父类的父类abstractcollection里面实现了toString()
        //所以直接传入变量名就能打印出来 都是因为上面的某个父类实现了toString()方法
        //打印出来的结果和插入的顺序不一样 说明hs不保证插入顺序和遍历顺序一致 所以hs没有提供根据下标获取元素的方法 因为
        // 顺序是不确定的 获取到的值是随机的不确定的 不能提前预判 那写程序的时候就没法利用 这种方法提供了也没用
        //所有set都没有根据下标获取值的方法 treeSet有first() last()方法获取集合中的最小最大值 遍历set可以用foreach循环
        //所有List都有get(int index)方法 因为list的定义就是存放多个元素的容器 允许重复 并且保证元素的先后顺序
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(34);
        ts.add(12);
        ts.add(89);
        ts.first();//treeset当数组为空时 不能调用first() 因为会抛出异常 而不是返回null
        ts.last();
        System.out.println(hs);


        ListNode h1 = new ListNode(1);
        ListNode h2 = new ListNode(2);
        h1.next = h2;
        ListNode head = h1;
        HashSet<ListNode> hss = new HashSet<>();
        hss.add(head);
        head.val = 3; //head.val改变会导致hs中加入的node的val也变了 但head指向head.next不会导致hs中节点变 不论是head指向head.next
        //还是head指向一个新的节点 都是将head变量指向新的一个地址 而hs中加入的元素还指向原来的地址
        head = new ListNode(9);
//        head=head.next;
        hss.add(head);
        System.out.println(hss);

        /*工具类相关*/

        int[] arrx = {0, 1, 0, 3, 12};
        int[] newArr = Arrays.copyOf(arrx, 3);//用Arrays工具类复制一个数组的内容到新数组

        ArrayList<String> l = new ArrayList<>();
        l.add("ghrigh");
        l.add("jigrig");
        //注意reverse方法直接对传入的list进行反转 调用reverse后不用把它赋值回去 因为reverse方法返回的就是空
        Collections.reverse(l);
        System.out.println(l);

        //根据HashMap的value来排序
        //HashMap不属于list子类，所以无法使用Collections.sort方法来进行排序，所以我们将hashmap中
        // 的entryset取出放入一个ArrayList中，来对ArrayList中的entryset进行排序（根据entryset中value的值），达到我们对hashmap的值进行排序的效果。

        HashMap<String, Integer> hm1 = new HashMap<>();
        hm.put("ghri", 2);
        hm.put("ghryyi", 4);
        hm.put("oirghri", 3);

        List<Map.Entry<String, Integer>> entryList1 = new ArrayList<>(hm1.entrySet());
//        List<Map.Entry<String,Integer>> entryList1=new ArrayList<>();
//        entryList1.addAll(hm1.entrySet());

        //sort里面接收的参数类型就是list 所以set不行 要是接收的类型是Collection 那set就也可以
        Collections.sort(entryList1, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
        Iterator<Map.Entry<String, Integer>> ite1 = entryList1.iterator();
        while (ite1.hasNext()) {
            Map.Entry<String, Integer> item = ite1.next();
            String key = item.getKey();
            int value = item.getValue();
            System.out.println("键" + key + "值" + value);

        }

        int[] arr1 = new int[8];
        Arrays.fill(arr1, 34);
//      使用Arrays类提供的stream()方法将数组转成一个流，然后使用max()方法获取最大值，最后返回结果。
        int maxValue = Arrays.stream(arr1).max().getAsInt();

    }

    public static void aboutStackQueue() {
        // 我们使用LinkedList来做为我们的先入先出的队列
        //linkdedlist继承list接口 deque接口 deque是double ended queue双端队列 即两端都可以插入删除的队列
        //普通的队列只能是一端插入另一端删除 stack的插入删除是push pop queue插入是add offer删除是remove poll
        //deque分为addFirst offerFirst addLast offerLast removeFirst pollFirst removeLast removeFirst
        //queue的add相当于deque的addLast remove相当于removeFirst
        //LinkedList也可以作为栈使用 提供了push pop
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<Pair<TreeNode, Integer>>();

        //用lambda表达式的方式构建comparator e2-e1所以这里是大根堆
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((e1, e2) -> (e2.getValue() - e2.getValue()));

        //q2的add方法里有addFirst addLast q1的只有add
        Queue<TreeNode> q1 = new LinkedList<>();
        Deque<TreeNode> q2 = new LinkedList<>();

        //当Queue接口引用变量指向实现类对象时  变量只能调用add offer remove poll方法 不能调用addLast这种
        //因为父类/接口引用指向子类对象 只能调用父类中已有的方法 Queue里面
        //没有addLast这种 queue就是队列 所以里面只有add 而且add的意思就是addLast
        Queue<Integer> queue1 = new ArrayDeque<>();
        queue1.add(3);
        //这种情况才是add addLast addFirst都能调用
        ArrayDeque<Integer> queue2 = new ArrayDeque<>();
        queue2.addLast(4);

        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(34);
        linkedList.add(11);
        linkedList.remove(); //remove的是head元素
        Queue<Integer> q = new LinkedList<>();
        q.add(22);
        q.remove(); //remove的是头元素

    }

    public static void aboutString() {
        StringBuilder s = new StringBuilder();
        int val = 34;
        s.append(val); //不加Integer.toString(val)直接写val也可以 因为append是支持char int char[]等很多参数的

        //String是不可变类 所以根本没提供reverse这类的修改方法 StringBuilder提供了
        //必须要加In.toString或者String.valueof 虽然StringBuilder构造函数也包含可以传入int类型 但此时该int指的是capacity
        StringBuilder sr = new StringBuilder(String.valueOf(335));
        System.out.println(sr.reverse());

        String s1 = "ghighr";
        System.out.println(s1.equals("ghroih"));//养成习惯用equals 减少用==
        s1.subSequence(0,3);


        //StringBuilder删除方法是delete不是remove delete(start,end)是删除[start,end-1】区间的元素即[s,e)左闭包含 右开不包含
        sr.deleteCharAt(0);
        sr.delete(0, 2);

        //string不能用foreach循环 遍历可以用charAt() 或通过toCharArray()转为char数组再遍历
        String st = "ghrigh";
        for (int i = 0; i < st.length(); i++) {
            st.charAt(i);
        }
//        for (String s:st) {
//            System.out.println(s); //编译错误
//        }

        char[] a = {'a', 'b', 'c'};
        System.out.println(a.toString());//返回的是[C@4554617c 并不是abc
        System.out.println(Arrays.toString(a)); //返回的shi[a,b,c]
        System.out.println(new String(a));//返回的是abc

        //String()构造函数里可以接受int数组 但必须是三个参数 String(codePoints,int offset,int count) 返回的是每个数字
        //对应的字符所构成的字符串 offset起始位置 count总数
        int[] aa = {97, 98, 99};
        String ss = new String(aa, 0, 2);
        System.out.println(ss);

        //用变量名 数组直接打印不行 arraylist可以 因为arraylist上面的某个父类实现了toString()方法 数组无论是基本类型
        //还是引用类型数组都不行
        Integer[] ab = {4, 5, 6, 7};
        ArrayList<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        System.out.println(ab);//[Ljava.lang.Integer;@4554617c
        System.out.println(l); //[1, 2]


        StringBuilder sb = new StringBuilder("ghro");
        sb.reverse();//sb的reverse()方法返回的就是一个sb类型 所以赋不赋值都行 Collections.reverse(list)返回的是空
        sb = sb.reverse();
        System.out.println(sb);

        char[] chararr = {'a', 'v', 'd'};
        System.out.println(new String(chararr)); //avd
        System.out.println(chararr.toString()); //[C@4554617c 字符数组直接toString()得到的是地址值
        System.out.println(Arrays.toString(chararr)); //[a, v, d]

        s1.substring(0,3);//substring都是小写 参数是beginindex endindex 并且是左闭右开

        //以若干个空格分隔字符串 如果原字符串开头有空格 返回的结果数组里第一个元素会是空字符串
        //字符串末尾有空格 不会使返回的结果数组里最后一个元素是空字符串
        String[] arr = ss.split(" {1,}");


        System.out.println(String.join(" ", "rhhr","hihg"));//join第二个参数是...elements可变参数
        List<String> ints = Arrays.asList("3","4");
        System.out.println(StringUtils.join(ints, "-"));//StringUtils的join方法第一个参数必须是一个集合且集合里装的是
        //字符串 第二个参数是分隔符


        String szs="ab好c";
        byte[] bytes = szs.getBytes();
        System.out.println(Arrays.toString(bytes));//[97, 98, -27, -91, -67, 99] 采用的是utf-8编码 一个英文占一个字节
        //一个中文占三个字节


    }

    //    基本类型的封装类的相关操作用法
    public static void aboutIntegerCharacter() {

        /*为啥要加Character. 感觉直接s.append(num % 10);确实可以  Character.getNumericValue('a')是10是什么原理
         说明这个方法的功能就不是转换成ascii码 而是仅适用于表示数字的字符，例如数字'0'-'9'返回0-9 为方便起见，
         它还将ASCII字母视为基数为36的数字系统中的数字（因此'A'-'Z'('a'-'z')也是 返回10-35*/
        System.out.println(Character.getNumericValue('0')); //0


        //        JDK8之前可以使用Apache Commons Lang3包中的工具类进行转换
/*
ArrayUtils是org.apache.commons.lang3.jar中lang3自带的数组操作工具类，当我们需要使用时
在maven中添加对应的依赖即可
		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-lang3</artifactId>
			<version>3.5</version>
		</dependency>

        //int[]-->Integer[]
        Integer[] integers = ArrayUtils.toObject(arr);
        // Integer[] --> int[]
        int[] ints = ArrayUtils.toPrimitive(integers);
*/

//JDK8可以使用Stream流来实现互相转化
        // int[] --> Integer[]
        int[] arr = {1, 2, 3, 4, 5};
        Integer[] integers = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        // Integer[] --> int[]
        int[] ints = Arrays.stream(integers).mapToInt(Integer::valueOf).toArray();

        //String.valueOf返回String Integer.valueOf（String s)返回Integer Integer.parseInt(Str)返回int
        Integer.valueOf("34"); //如果传入的字符串不是数字会抛出NumberFormatException

        Double s = 0.0;//定义的是Double类型 就必须初始化为0.0 0会报错
        double s1 = 0;

        String a="11001",b="110000";
        //Integer.parseInt(String,radix)将字符串转换为指定进制下的整数
        /*如果字符串超过 33位，不能转化为 Integer
如果字符串超过 65 位，不能转化为 Long
如果字符串超过 500000001位，不能转化为 BigInteger*/
        int sum=Integer.parseInt(a,2)+Integer.parseInt(b,2);
        //返回一个整数的二进制字符串形式
        Integer.toBinaryString(sum);



    }

    public static void intToInteger() {
        Integer[] obj = new Integer[]{null, 1, 2, 3};
        int[] newObj = Arrays.stream(obj).mapToInt(i -> i == null ? 0 : i.intValue()).toArray();
        for (int i = 0; i < newObj.length; i++) {
            System.out.println(newObj[i]);
        }
        //Integer[] obj = new Integer[] {1, 2, 3};
        //int[] newObj = Arrays.stream(obj).mapToInt(Integer::valueOf).toArray();

        int[] obj2 = new int[]{4, 5, 6};
        Integer[] newObj2 = Arrays.stream(obj2).boxed().toArray(Integer[]::new);
        Integer[] newObj3 = IntStream.of(obj2).boxed().toArray(Integer[]::new);
    }

    public static void aboutPrimitiveType() {
        char a = 'a';
        int n = a;
        System.out.println((int) a);//两种写法都可以 说明强制类型转换一般是用于大转小 小转大直接赋值就能自动转换 但就强制类型的方式(type)也可以

        int num = 65;
        char c = ("" + num).charAt(0);
        System.out.println(c); // 输出6
        System.out.println('0' + 1);//char型在算数表达中自动转换成int型即Ascii
        System.out.println("2343" + 4);//string型 此时加号是连接符

        int[] arr = new int[4]; //基本类型的数组只要new了就会给变量分配内存空间 所以有默认的初始值是0 boolean数组默认初始值是false;
        //引用类型的数组如Integer new了之后 初始值是null
        System.out.println(arr[2]);

        int[] nums = {5, 6, 7, 4, 5};
        testArray(nums);
        for (int i = 0; i < 5; i++) {
            System.out.println(nums[i]);
        }//没有变 还是5 6 7 4 5 因为方法调用的时候 刚开始形参指向原本实参数组的位置 copyof那一句形参又指向了一个新的位置
        // 在方法外面实参nums依旧是原来的数组 数组作为方法参数传递时，传递的是数组在内存中的地址，但是在方法里直接给数组
        // 的引用赋值是无效的，需要分别给每个元素赋值

        BigDecimal bigDecimal=new BigDecimal(5);
        System.out.println(bigDecimal.divide(new BigDecimal(14),3,BigDecimal.ROUND_UP));

        //编译报错 变量未初始化 Java中只有类成员变量才会默认初始化值。Int类型默认值为0，你定义的是方法内的局部变量，需要初始化才能进行运算。
        // Integer m;
        // int n;
        //  System.out.println(m==n);

        Integer i=199;
        int j=199;
        // 返回的是true 基本数据类型与引用数据类型进行==比较时，引用数据类型会进行拆箱，然后与基本数据类型进行值的比较
        System.out.println(i==j);
        // 返回的是true 引用数据类型与基本数据类型进行equals比较，基本数据类型会进行自动装箱，与引用数据类型进行比较，
        // Object中的equals方法比较的是地址，但是Integer类已经重写了equals方法，只要两个对象的值相同就相同
        System.out.println(i.equals(j));

        // Integer a = 100;其实在内部做了自动装箱Interger a = Integer.valueOf(100); 进入Integer的valueOf方法会看到
        // 将整数类型装箱时，首先会进行判断，如果i >= -128且 i <= 127，那么会返回以 i + 128为下标到数组中取出该值并将其返回。所以对于
        // 任意的在[-128, 127]之间的数进行==比较时，如果是两个一样的数，他们的内存地址是相同的，所以返回的是ture，不在此范围的话，
        // 会new 一个新的integer，所以尽管两个数值相同，但地址不同，所以返回false
        Integer aa = 100;
        Integer b = 100;
        System.out.println(aa == b); // true
        Integer cc = 1000;
        Integer d = 1000;
        System.out.println(cc == d); //false

    }

    public static void aboutClassObject() {
        Comparable[] arr2 = {12, 33, 34, 56, 76, 77}; //多态 定义一个接口类型的引用变量来引用具体类对象的实例
        Comparable a = 54;//这也行
        System.out.println(a);

    }

    public static void aboutAlgoLogicDetail() {

        int rangeL = 3, rangeR = 10;
        int randNum;
        //生成一个[l,r]范围内的随机数是*(rangeR-rangeL+1) 生成一个[l,r）范围内的随机数是*(rangeR-rangeL)
        randNum = (int) (Math.random() * (rangeR - rangeL + 1)) + rangeL;

        new Random().nextInt(8);


    }

    public static void learnIDEA() {

        //idea学习
        System.out.println("ghrighi"); //生成打印日志：sout
        for (int k = 0; k < 4; k++) {
            System.out.println("fori生成for循环");
        }

        int num = 0;
        //输入num==2.if自动出来下面的if语句
        if (num == 2) {

        }
        //输num.fori自动出来下面的for语句
        for (int i1 = 0; i1 < num; i1++) {

        }

        //选中要抽取成一个方法的代码->右键Refactor->Extract抽取
        System.out.println("grhighr");
        System.out.println("grhighr");
        System.out.println("grihgro");
        //extractedfunc();

        //ctrl alt v自动生成变量
        LinkedList<Object> ff = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();

        //ctrl+左键跳到类或方法的定义上 Ctrl+Alt+方向键左跳回跳转前的位置
        //三击选中一行
        /*ctrl+r替换 选中单词按ctrl+r就会跳出窗口能填写想要替换成的内容 针对当前文件做替换
        按ctrl shift r跳出的面板是针对整个项目进行替换
        ctrl f在文件总查找
        在整个项目中根据名称进行类或文件的搜索 ctrl n
        /**再按tab生成方法的注释说明
        ctrl+enter 在当前行上面插入一行空行 必须是在行首使用该快捷键才行
        ctrl alt enter 在当前行上面插入一行空行光标到上一行 不需要在行首使用
         */


    }

    public static void toBeExplored() {
        /*
ArrayUtils是org.apache.commons.lang3.jar中lang3自带的数组操作工具类，当我们需要使用时
在maven中添加对应的依赖即可
		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-lang3</artifactId>
			<version>3.5</version>
		</dependency>

        //int[]-->Integer[]
        Integer[] integers = ArrayUtils.toObject(arr);
        // Integer[] --> int[]
        int[] ints = ArrayUtils.toPrimitive(integers);
*/
    }

    public static void testString(String s, StringBuilder sb) {
        s = "ghroih";
        sb.setCharAt(0, 'i');
        s.isEmpty();
        s.indexOf("ghie");

    }

    public static void aboutGeneric() {
        //ArrayList<Number> list01 = new ArrayList<Integer>();// 编译错误

        ArrayList<? extends Number> list02 = new ArrayList<Integer>();// 编译正确
//        fillObjList(new ArrayList<Integer> ());//报错

        List listOfRawTypes = new ArrayList();
        listOfRawTypes.add("abc");
        listOfRawTypes.add(123);
        listOfRawTypes = new ArrayList<Integer>();

        String item = (String) listOfRawTypes.get(0);// 获取元素时需要显式的类型转换
// 编译器不报错，但运行时会产生 ClassCastException异常，因为 Integer不能被转换为 String
        item = (String) listOfRawTypes.get(1);

        List<String> listOfString = new ArrayList();
        listOfString.add("abcd");
//        listOfString.add(1234);// 编译器直接报错
        item = listOfString.get(0); // 不需要显式的类型转换，编译器会自动转换

        List<?> listOfAnyType;
        List<Object> listOfObject = new ArrayList<Object>();
        List<String> listOfString1 = new ArrayList<String>();
        List<Integer> listOfInteger = new ArrayList<Integer>();

        listOfAnyType = listOfString;// 编译正确
        listOfAnyType = listOfInteger;// 编译正确
//        listOfObjectType =  listOfString;// 编译错误

//        listOfAnyType.add(43);//错误 只能加null

        //当使用列表保存列表时，如果使用下面第一种格式，编译器会报类型不匹配错误 原因是泛型必须完全匹配。后两种就可以
        //List<List<String>> res= new ArrayList<ArrayList<String>>();
        List<List<String>> res1 = new ArrayList<List<String>>();
        List<ArrayList<String>> res2 = new ArrayList<ArrayList<String>>();

        //双重列表 声明的变量要写两个list new的对象直接写一个ArrayList就可以
        List<List<String>> list = new ArrayList<>();

    }

    public static void fillObjList(ArrayList<Object> list) {
        System.out.println("grhih");
    }

    public static void testArray(int[] nums) {
        int[] a = {34, 5, 2, 2, 56};
        nums = Arrays.copyOf(a, 5);


        int[][] memo = new int[2][3];
        Arrays.fill(memo, -1);//运行时报错了 所以Arrays.fill只能针对一维数组
        System.out.println(Arrays.toString(memo));
        //可以这样做
        for (int i = 0; i < 2; i++)
            Arrays.fill(memo[i], -1);

        Arrays.stream(a).max().getAsInt();

        StringBuilder[] arr=new StringBuilder[3];

        //在增强for循环里如果要给数组中的每个元素初始化是行不通的 sb本来是指向arr中的元素 但是new对象赋值之后sb就指向别的了
        for (StringBuilder stringBuilder : arr) {
            stringBuilder=new StringBuilder();
        }
        System.out.println(Arrays.toString(arr));//[null, null, null]
        for(int i=0;i<3;i++){
            arr[i]=new StringBuilder();
        }
        System.out.println(Arrays.toString(arr)); //[, , ]

        arr[0].append("aaa");
        arr[1].append("aaa");
        arr[2].append("aaa");
        //在增强for循环里修改这种可变变量是可以的 只要不指向别的元素就行
        for (StringBuilder stringBuilder : arr) {
            stringBuilder.append("bb");
        }
        System.out.println(Arrays.toString(arr)); //[aaabb, aaabb, aaabb]

        String[] as={"aa","bb","cc"};
        for (String s : as) {
            s="geg";
        }
        System.out.println(Arrays.toString(as)); //[aa, bb, cc]

    }

    public void aboutFunction(){
        BigDecimal bigDecimal=new BigDecimal("34.89");
        System.out.println(bigDecimal.setScale(1, RoundingMode.CEILING));

        int count = Runtime.getRuntime().availableProcessors();
        System.out.println(count);

        Random rdm = new Random();
        //nextInt()方法生成的随机数取值范围是[0, 2^31 - 1]）
        int x=rdm.nextInt();
        System.out.println(x);
        String hash1 = Integer.toHexString(x);
        System.out.println(hash1);

//        Math.pow()方法传入的参数类型均为double 返回值也是double
        System.out.println(Math.pow(2, 1.5));

        int r=(int)(Math.random()*10000);
        System.out.println(r);

        System.arraycopy(new int[]{1,2,3},0,new int[]{1,2,3},0,3);
        Arrays.copyOf(new int[]{1,2,3},3);

        long sum=0;
        long l = System.currentTimeMillis();
        for(int i=0;i<=100000000;i++){
            sum=sum+i;
        }
        System.out.println(sum);
        long l1=System.currentTimeMillis();
        System.out.println("程序运行时间: " + (l1-l) + " 毫秒");
    }

    public void aboutThread(){
        ThreadLocal<String> t1=new ThreadLocal<>();
        ThreadLocal<String> t2=new ThreadLocal<>();
        t1.set("abc");
        t2.set("def");
        Thread thread=Thread.currentThread();
        HashMap<String,String> map=new HashMap<>();
        map.put("aa","bb");
    }

    public static void main(String[] args) {
//        aboutContainer();
//        intToInteger();
//        String s="gori";
//        System.out.println(s.indexOf('i'));
//        System.out.println(Integer.valueOf("34"));
//        StringBuilder sb=new StringBuilder("ab");
//        testString(s,sb);
//        System.out.println(s+" "+sb);

//        ArrayDeque<Integer> stack=new ArrayDeque<>();
//        stack.push(1);
//        System.out.println(stack);
//
//        System.out.println(calculateSize(31));
//        System.out.println(-1&7);

//        int hashcode = testHashCode("abc".toCharArray());
//        System.out.println(hashcode);

//        testTableSizeFor(20);
//        System.out.println((char)255);
//        ArrayList<ArrayList<Integer>> dd=new ArrayList<>();
//        dd.add(new ArrayList<>(Arrays.asList(3,4,9,9)));
//        dd.add(new ArrayList<>(Arrays.asList(4,9,9)));
//        dd.add(new ArrayList<>(Arrays.asList(3,9)));
//        Collections.reverse(dd);
//        System.out.println(dd);

//         Pattern p = Pattern.compile("(<.*>)"); // 01<D>23<B>45<C>6,B,C,D
//        Matcher matcher=p.matcher("01<D>23<B>45<C>6");
//        matcher.find();
//        System.out.println(matcher.group());

//        System.out.println(Runtime.getRuntime().availableProcessors());
//
//        Scanner scanner=new Scanner(System.in);
//        int n = scanner.nextInt();

//        String uuid = UUID.randomUUID().toString().replaceAll("-","");
//        System.out.println(uuid);

//         ArrayList<Integer> aa=null;
//         for (Integer i : aa) {
//             System.out.println(i);
//         }
        Long id=3L;
         // 当这个Long对象传递给接受基本类型long的testTrans方法时，Java会自动进行拆箱操作，将Long对象转换为基本类型的long值，这一过程是自动进行的，无需显式转换。
        testTrans(id);
        Integer page=1,limit=10;
        int pageNo=page==null?1:page;
        int size=limit==null?20:limit;
        int offset=(pageNo-1)*size;
        
    }

    static void testTrans(long id){
        System.out.println(id);
    }
    public static int testHashCode(char[] value){
        int hash=0;
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }

    //这是hashmap计算初始容量的方法 向右移位1、2、4、8、16再或运算，这主要是为了把二进制的各个位置都填上1，
    // 当二进制的各个位置都是1以后，就是一个标准的2的幂次方减1了，最后把结果加1再返回
    //假如一个数的二进制位里面只有一个1 右移一位再位或运算 就能影响它右边的位也变成1 于是变成了这个数有两个1了
    //再右移两位再位或运算 又能影响其右边的两位 就能让这个二进制数里从两个1变成4个1 最终结果就是即使原本的二进制位
    // 里面只有一个1，也会被影响成其最高位的1后面都是1 更不用说如果原本的二进制数里不止一个1 也可以达到效果
    //获取比初始值大的最小的2的幂次方
    public static final int testTableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1  : n + 1;
    }

    private static int calculateSize(int numElements) {
        int initialCapacity = 16;
        // Find the best power of two to hold elements.
        // Tests "<=" because arrays aren't kept full.
        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>>  1);
            initialCapacity |= (initialCapacity >>>  2);
            initialCapacity |= (initialCapacity >>>  4);
            initialCapacity |= (initialCapacity >>>  8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;
            if (initialCapacity < 0)   // Too many elements, must back off
                initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 element
        }
        return initialCapacity;
    }


}









