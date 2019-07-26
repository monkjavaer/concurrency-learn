package com.thinkinjava.executor;

import com.thinkinjava.yield.YieldTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * juc中Executor执行器，无需显示的管理线程的生命周期
 * <p>
 * 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors返回的线程池对象的弊端如下：
 * 1）FixedThreadPool和SingleThreadPool:
 *   允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
 * 2）CachedThreadPool:
 *   允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM。
 * <p>
 * Positive example 1： 实现Java调度线程池
 * //org.apache.commons.lang3.concurrent.BasicThreadFactory
 * ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
 * new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
 * <p>
 * Positive example 2：
 * ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
 * .setNameFormat("demo-pool-%d").build();
 * //Common Thread Pool
 * ExecutorService pool = new ThreadPoolExecutor(5, 200,
 * 0L, TimeUnit.MILLISECONDS,
 * new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
 * pool.execute(()-> System.out.println(Thread.currentThread().getName()));
 * pool.shutdown();//gracefully shutdown
 *
 * @author monkjavaer
 * @version V1.0
 * @date 2019/7/26 0026 22:21
 */
public class ThreadPool {

    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        int time = 10;
        for (int i = 0; i < time; i++) {
            //使用线程池执行任务
            executorService.execute(new YieldTask());
        }
        executorService.shutdown();
    }


}
