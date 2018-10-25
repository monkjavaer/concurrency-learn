package com.bingfabianchengdeyishu;

import java.util.concurrent.TimeUnit;

/**
 * Thread.join()的使用
 * 如果一个线程A执行了thread.join()语句，其含义是：当前线程A等待thread线程终止之后才
 * 从thread.join()返回。
 * Thread.join()源码是运用等待/通知机制
 * @author tangquanbin
 * @date 2018/10/25 10:45
 */
public class Join {
    public static void main(String[] args) throws Exception {
        //获取main线程
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            // 每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    static class Domino implements Runnable {
        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
}
