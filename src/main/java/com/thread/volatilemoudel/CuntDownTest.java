package com.thread.volatilemoudel;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Title: CuntDownTest
 * @Package: com.thread.volatilemoudel
 * @Description: TODO（添加描述）
 * @Author: monkjavaer
 * @Data: 2019/4/9 19:54
 * @Version: V1.0
 */
public class CuntDownTest {
    private static int threadCount = 10;

    public static void main(String[] args) {

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                        System.out.println("线程开始了---"+Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            countDownLatch.countDown();
        }

    }

}
