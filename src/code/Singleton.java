package code;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/5/19 21:30
 */
public class Singleton {
    // instance必须要是static 因为下面的getInstance方法是static的 里面使用了instance instance如果不是static 就会报错 无法从
    // static上下文引用非static字段
    // volatile禁止指令重排
    private static volatile Singleton instance;
    private Singleton(){}
    public static Singleton getInstance() {
        if(instance==null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance=new Singleton();
                }
            }
        }
        return instance;
    }
}
// 使用类的静态内部类实现的单例模式，既保证了线程安全有保证了懒加载，同时不会因为加锁的方式耗费性能。
// 其实有点类似饿汉模式里的instance定义时就直接赋值 只不过这里instance是在内部类里定义的 所以是懒加载模式
class Singleton1 {
    private Singleton1() {
    }

    private static class SingletonHolder {
        private static Singleton1 instance = new Singleton1();
    }

    public Singleton1 getInstance() {
        return SingletonHolder.instance;
    }
}