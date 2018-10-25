package com.bingfabianchengdeyishu.executor;

import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
    private static int corePoolSize = 4;

    private static int maximumPoolSize = 12;

    private static long keepAliveTime = 50;

    private static TimeUnit unit = TimeUnit.SECONDS;

    private static BlockingQueue<Runnable> taskServiceQueue = new LinkedBlockingQueue<Runnable>(100);

    /**
     * 数据接收队列
     */
    private static BlockingQueue<String> dataQueue = new LinkedBlockingQueue<String>(20000);

    private static ThreadPoolExecutor taskService = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
            unit, taskServiceQueue, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("ThreadPoolExecutorTest");
            return thread;
        }
    });
    public static void main(String[] args) {
        //TODO 开启20个线程
        int threadNum = 20;
        for (int i = 0; i < threadNum; i++) {
            Work work = new Work();
            taskService.execute(work);
        }
    }
    /**
     * 数据处理
     */
    static class Work implements Runnable {
        @Override
        public void run() {
            try {
                String data = dataQueue.take();
                System.out.println("===处理取出的数据===");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
