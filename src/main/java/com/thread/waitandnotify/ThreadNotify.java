package com.thread.waitandnotify;

import java.util.concurrent.TimeUnit;

/**
 * @author tangquanbin
 * @date 2018/12/13 22:04
 */
public class ThreadNotify extends Thread {
    private Object work;

    public ThreadNotify(Object work) {
        this.work = work;
    }

    @Override
    public void run() {
        synchronized (work){
            System.out.println("...before notify...");
            work.notify();
            System.out.println("...after notify...");
            //此处可测试，notify执行后并没有释放此对象的锁，需要等到notify的synchronized方法执行完全后，wait的线程才能获得对象锁。
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("...after notify synchronized...");
    }
}
