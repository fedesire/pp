import java.util.Scanner;
/*总结一下，Scanner是一个扫描器，它扫描数据都是去内存中一块缓冲区中进行扫描并读入数据的，而我们在控制台中输入的数据也都是被先
存入缓冲区中等待扫描器的扫描读取。这个扫描器在扫描过程中判断停止的依据就是“结束符”，空格，回车，tab 都算做是结束符
而坑点在于 next 系列的，也就是下面这些函数：
next
nextInt
nextDouble
nextFloat
这些函数与 nextLine 连用都会有坑

坑点就是 next 系列的函数返回了数据后，会把回车符留在缓冲区，因此我们下一次使用 nextLine 的时候会碰到读取空字符串的情况

解决方案：
输入都用 nextLine ，做格式转换
输入 next 系列函数调用后，中间调用一次 nextLine 调用去掉了回车符后，再调用一次 nextLine 调用真正输入我们的数据*/
public class Main {
    // 遍历第一行 1指向6和7 6 7的父节点就是1 便利到第二行 2也指向6 7 就将1的父节点变为2 此时1 6 7调用find方法得到的结果就是2
    // 便利到第三行 3也指向6 7 就将2的父节点变为3 此时1 2 6 7调用find方法得到的结果就是3
    // 可以看成是下面行的优先级比较高
    static class UnionFindSet{
        // father里存的可以看作是每个节点的第一个父节点 也不是 调用find方法会让father[x]里存的是最上级的父节点
        int[] father;
        public UnionFindSet(int n){
            father=new int[n];
            for (int i = 0; i < n; i++) {
                father[i]=i;
            }
        }

        // find是查找节点当前阶段能找到的最上级的父节点
        int find(int x){
            if(x!=father[x]){
                father[x]=find(father[x]);
                return father[x];
            }
            return x;
        }
        void union(int x,int y){
            // find是查找节点当前阶段能找到的最上级的父节点
            int x_fa=find(x);
            int y_fa=find(y);
            if(x_fa!=y_fa)
                father[y_fa]=x_fa;
        }
    }
    public static void testUfs(){
        UnionFindSet ufs=new UnionFindSet(8);
        ufs.union(1,6);
        ufs.union(1,7);
        ufs.union(2,6);
        ufs.union(2,7);
        int a=ufs.find(1);
        a= ufs.find(6);
        a=ufs.find(7);

        ufs.union(3,6);
        ufs.union(3,7);
        a=ufs.find(1);
        a=ufs.find(6);
        a=ufs.find(7);

    }

    public void testScanner(){
        Scanner scanner=new Scanner(System.in);
  /*      String next = scanner.next();
        System.out.println(next);
        // 以回车( "\r" )作为结束符，也就是 nextLine 返回回车( \r )之前的所以字符。
        String s = scanner.nextLine();
        s.substring(0,2);
        System.out.println(s);*/
// ctrl+d可结束输入
        while(scanner.hasNextLine()){

            try{
                String next1 = scanner.next();
                System.out.println(next1);
            }catch(Exception e){
                break;
            }

        }


    }
    public static void main(String[] args) {
        testUfs();
        int n=6;
        int[] arr={1,2,3,4,5,6};
        int[] preSum=new int[n+1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i]=preSum[i-1]+arr[i-1];
        }

    }
}