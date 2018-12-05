package com.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用AtomicInteger原子类代替i++使用synchronized的线程安全操作
 * @author tangquanbin
 * @date 2018/12/05 22:07
 */
public class AtomicIntegerTest {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void add() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(count.incrementAndGet());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    AtomicIntegerTest.add();
                }
            });
            thread.start();
        }
    }
}
