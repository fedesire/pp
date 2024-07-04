package code;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xqi
 * @version 1.0
 * @description 假设现在有10个任务，要求同时处理，并且必须所有任务全部完成才返回结果
 * @date 2024/5/3 20:43
 */

public class MultiTask {

    private static AtomicInteger count = new AtomicInteger(10);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 准备10个线程
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 收集每个任务的结果
        List<Future<Integer>> resultList = new ArrayList<>();

        long start = System.currentTimeMillis();

        // 并行处理10个任务
        for (int i = 0; i < 4; i++) {
            // 准备任务
            Callable<Integer> task = () -> {
                // 模拟任务耗时 0~4秒
                int seconds = ThreadLocalRandom.current().nextInt(5);
                TimeUnit.SECONDS.sleep(seconds);
                System.out.println("task is completed! cost:" + seconds + "s left: " + count.decrementAndGet());
                // 模拟返回结果
                return 1;
            };
            // 提交任务
            Future<Integer> partResult = executorService.submit(task);
            // 收集结果
            resultList.add(partResult);
        }

        int result = 0;

        // 阻塞获取并累加结果
        for (Future<Integer> future : resultList) {
            result += future.get();
        }

        // 最终全部任务完成，总耗时取决于最耗时的任务时长
        System.out.println("all task is completed! result=" + result + " cost: " + (System.currentTimeMillis() - start) + "ms");

    }

    private static void myMultiTask() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        List<Future<Integer>> resultList2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Callable task=()->{
                // Thread.sleep()单位是ms
                int seconds= new Random().nextInt(10000);
                Thread.sleep(seconds);
                System.out.println("task is completed! cost:" + seconds + "ms left: " + count.decrementAndGet());
                // 模拟返回结果
                return 1;
            };
            Future<Integer> futureResult = threadPoolExecutor.submit(task);
            resultList2.add(futureResult);
        }
        int res=0;
        for (Future<Integer> future : resultList2) {
            res+=future.get();
        }
        System.out.println(res);
        // 用了线程池，所以程序会一直活着。用shutdown就能关闭线程池
        threadPoolExecutor.shutdownNow();
    }
}
// 使用CompletableFuture
/*supplyAsync 方法用于异步地执行一个返回值的操作，并返回一个 CompletableFuture<U>，其中 U 是该操作的返回类型。这个方法接收一个
Supplier<U> 参数，即一个无参数且返回 U 类型结果的函数式接口。
runAsync 方法用于异步地执行一个无返回值（即 void）的操作，并返回一个 CompletableFuture<Void>。这个方法接收一个 Runnable 参数，
即一个无参数且没有返回值的函数式接口。*/
/**
 * @author qiyu
 * @date 2020-09-07
 */
 class CompletableFutureDemo {

    private static AtomicInteger count = new AtomicInteger(10);

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 准备10个线程
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 收集结果
        List<CompletableFuture<Integer>> resultList = new ArrayList<>();

        long start = System.currentTimeMillis();

        // 任务并行
        for (int i = 0; i < 10; i++) {
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                // 模拟任务耗时 0~4秒
                try {
                    int seconds = ThreadLocalRandom.current().nextInt(5);
                    TimeUnit.SECONDS.sleep(seconds);
                    System.out.println("task is completed! cost:" + seconds + "s left: " + count.decrementAndGet());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 模拟返回结果
                return 1;
            }, executorService);
            resultList.add(completableFuture);
        }

        // 处理结果
        int result = 0;
        for (CompletableFuture<Integer> completableFuture : resultList) {
            result += completableFuture.get();
        }

        // 最终全部任务完成，总耗时取决于最耗时的任务时长
        System.out.println("all task is completed! result=" + result + " cost: " + (System.currentTimeMillis() - start) + "ms");
    }
}