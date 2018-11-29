package com.thread.synchronizedmodel;

import java.util.concurrent.TimeUnit;

/**
 * 死锁实例
 * @author tangquanbin
 * @date 2018/11/26 22:37
 */
public class TestDeadlock {

    static final String resource1 = "resource1";
    static final String resource2 = "resource2";

    static class ThreadA extends Thread {
        @Override
        public void run() {
            synchronized (resource1) {
                System.out.println("ThreadA: locked resource 1");
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource2) {
                    System.out.println("ThreadA: locked resource 2");
                }
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            synchronized (resource2) {
                System.out.println("ThreadB: locked resource 2");
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resource1) {
                    System.out.println("ThreadB: locked resource 1");
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        threadA.setName("====ThreadA====");
        ThreadB threadB = new ThreadB();
        threadB.setName("====ThreadB====");
        threadA.start();
        threadB.start();
    }
}
