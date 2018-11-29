package com.thread.priority;

import java.util.concurrent.TimeUnit;

/**
 * 守护线程测试类
 * @author tangquanbin
 * @date 2018/11/22 22:54
 */
public class DaemonsThread implements Runnable {

    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println("守护线程"+Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<10;i++) {
            Thread thread = new Thread(new DaemonsThread());
            thread.setDaemon(true);
            thread.start();
        }
        System.out.println("main线程执行完===");
        TimeUnit.MILLISECONDS.sleep(1000);
    }
}
