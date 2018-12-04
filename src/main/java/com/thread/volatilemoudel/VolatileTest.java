package com.thread.volatilemoudel;

import java.util.concurrent.TimeUnit;

/**
 * @author tangquanbin
 * @date 2018/12/04 22:07
 */
public class VolatileTest {

    public static volatile boolean flag = false;

    public static void stop() {
        flag = true;
    }

    public static void work() {
        while (!flag) {
            System.out.println("start work");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        });
        thread1.start();

        TimeUnit.MILLISECONDS.sleep(1000);

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                stop();
            }
        });
        thread2.start();
    }
}
