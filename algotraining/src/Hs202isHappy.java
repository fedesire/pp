import java.util.HashSet;

/**
 * @author xqi
 * @version 1.0
 * @description: 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」 定义为：
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果这个过程 结果为 1，那么这个数就是快乐数。
 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
 * @date 2024/1/4 10:38
 */
public class Hs202isHappy {
    public int getNext(int n){
        int s=0;
        while(n>0){
            int t=n%10;
            s+=t*t;
            n/=10;
        }
        return s;
    }
    public boolean isHappy(int n) {
        int t=getNext(n);
        HashSet<Integer> record=new HashSet<>();
        while(t!=1&&!record.contains(t)){
            record.add(t);
            t=getNext(t);
        }
        return t==1;
    }
    //用快慢指针的方法 快指针每次前进两步 慢指针每次前进一步 直到快指针到达了1或者快慢指针重合
    public boolean isHappy1(int n){
        int slow=n,fast=getNext(n);
        while(fast!=1&&slow!=fast){
            slow=getNext(slow);
            fast=getNext(getNext(fast));
        }
        return fast==1;

    }
}
