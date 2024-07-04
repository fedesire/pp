package code;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xqi
 * @version 1.0
 * @description TODO
 * @date 2024/4/15 22:45
 */
public class ThreadPool3 {
    // 【新增】原子类，对线程池中的线程进行计数
    private final AtomicInteger ctl = new AtomicInteger(0);

    /**
     * 工作线程
     */
    private final List<Worker> workers = new ArrayList<>();
    /**
     * 任务队列
     */
    private final BlockingQueue<Runnable> workQueue;
    /**
     * 核心线程数
     */
    private final int corePoolSize;
    /**
     * 最大线程数
     */
    private final int maximumPoolSize;
    /**
     * 非核心线程最大空闲时间（否则销毁线程）
     */
    private final long keepAliveTime;

    public ThreadPool3(int corePoolSize,
                       int maximumPoolSize,
                       long keepAliveTime,
                       TimeUnit timeUnit,
                       BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = timeUnit.toNanos(keepAliveTime);
    }

    /**
     * 提交任务
     * - 线程数 < corePoolSize，创建线程处理任务（核心线程）
     * - 线程数 >= corePoolSize，新任务进入队列等待执行
     * - 任务队列爆满，继续创建线程处理任务（非核心线程）
     * - 线程数 >= maximumPoolSize，触发拒绝策略
     *
     * @param task 待执行任务
     */
    public void execute(Runnable task) {
//        Assert.notNull(task, "task is null");

        if (ctl.get() < corePoolSize) { // 从计数器获取count
            this.addWorker(task, true);
            return;
        }

        boolean enqueued = workQueue.offer(task);
        if (enqueued) {
            return;
        }
        // 加入工作队列失败 就创建非核心线程
        if (!this.addWorker(task, false)) {
            throw new RuntimeException("拒绝策略");
        }
    }

    private boolean addWorker(Runnable task, boolean core) {
        // 保证并发时workerCount正确计数，失败了要重试
        for (; ; ) {
            int wc = ctl.get(); // 从计数器获取count
            if (wc >= (core ? corePoolSize : maximumPoolSize)) {
                return false;
            }
            if (compareAndIncrementWorkerCount(wc)) { // 增加count
                break;
            }
        }

        Worker worker = new Worker(task);
        workers.add(worker);
        worker.getThread().start();
        return true;
    }

    private Runnable getTaskFromQueue() {
        boolean timedOut = false;

        // 循环获取任务
        for (; ; ) {

            // 从计数器获取count
            int c = ctl.get();
            boolean tryDestroy = c > corePoolSize;

            if (tryDestroy && timedOut) {
                // System.out.println(Thread.currentThread().getName() + "尝试销毁");
                if (compareAndDecrementWorkerCount(c)) {
                    return null;
                }
                // System.out.println(Thread.currentThread().getName() + "销毁失败，下次再试");
                continue;
            }

            try {
                Runnable r = tryDestroy ?
                        workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) : // 拉取任务，超时返回null
                        workQueue.take(); 									  // 拉取任务，持续阻塞直到获取任务
                if (r != null)
                    return r;
                timedOut = true;
            } catch (InterruptedException retry) {
                timedOut = false;
            }
        }
    }

    private class Worker implements Runnable {
        private Thread thread;
        private Runnable task;

        public Worker(Runnable task) {
            this.task = task;
            thread = new Thread(this);
        }

        @Override
        public void run() {
            try {
                // 循环处理任务 task刚开始是创建Worker时传递进来的任务 任务执行完后 就会从队列里拉取排队的任务执行
                while (task != null || (task = getTaskFromQueue()) != null) {
                    task.run();
                    task = null;
                }
            } finally {
                // 实际ThreadPoolExecutor线程池里核心线程任务处理结束 队列为空也不会销毁核心线程
                // 但是非核心线程在空闲时间超过参数值时会销毁
                System.out.println(thread.getName() + "被销毁");
                workers.remove(this);
            }
        }

        public Thread getThread() {
            return thread;
        }

        public void setThread(Thread thread) {
            this.thread = thread;
        }

        public Runnable getTask() {
            return task;
        }

        public void setTask(Runnable task) {
            this.task = task;
        }
    }

    private boolean compareAndIncrementWorkerCount(int expect) {
        return ctl.compareAndSet(expect, expect + 1);
    }

    private boolean compareAndDecrementWorkerCount(int expect) {
        return ctl.compareAndSet(expect, expect - 1);
    }

}
