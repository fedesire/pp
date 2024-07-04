package code;

import java.util.concurrent.*;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/15 21:54
 */
public class ThreadTest {
    // Thread类也是实现了Runnable接口的
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run(){
                System.out.println("runnable");
            }
        });
        thread.start();

        // 必须用FutureTask包装callable对象后才能传递给Thread 不能像上面那样写一个Callable
        // 的匿名内部类对象传给Thread 因为Thread的构造函数没有能接收Callable的
        // 用FutureTask获取返回结果（异步执行的返回结果）FutureTask是实现Runnable Future接口的类
        /*FutureTask实现了RunnableFuture接口，而RunnableFuture接口则继承了Runnable接口和Future接口，这使得FutureTask
        既可以作为一个任务提交给线程池执行，也可以获取异步计算的结果。*/
        MyCallable callable=new MyCallable();
        FutureTask<String> ft=new FutureTask<>(callable);
        Thread thread1 = new Thread(ft);
        thread1.start();
        // 调用get方法获取执行结果
        String result = ft.get();
        System.out.println(result);

        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(12,20,
                1,TimeUnit.MINUTES,new LinkedBlockingDeque<>(),handler );
        // 使用线程池可以直接提交一个callable 不用包装成futuretask
        Future<Object> res = threadPoolExecutor.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "grhih";
            }
        });
        System.out.println(res.get());
    }

   static class MyCallable implements Callable<String>{

        // 重写Runnable接口的run方法不能抛出异常 只能内部捕捉 重写callable接口的call方法可以向上抛出异常
       //因为run()方法是Runnable接口里面的方法,而Runnable接口在定义run()方法的时候没有抛出任何异常,所以子类在重写run()
        // 方法的时候要小于或等于父类(Runnable)的run()方法的异常,所以父类没有抛出异常,子类不能抛出异常
       // callable里的 call方法有throw exception
        @Override
        public String call() throws Exception {
            System.out.println("callable");
            return "callable result";
        }
    }

    public void test(){
        // 方式1：重写Thread#run() 这是匿名内部类继承Thread类重写run方法 生成一个对象
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "========>正在执行");
            }
        };
        thread.start();
    }
}
