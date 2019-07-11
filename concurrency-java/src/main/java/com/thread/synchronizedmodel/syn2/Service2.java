package com.thread.synchronizedmodel.syn2;

import java.util.concurrent.TimeUnit;

/**
 * @author monkjavaer
 * @date 2018/11/26 21:12
 */
public class Service2 {
    /**
     * 同步方法
     */
    public synchronized void printService() {
        System.out.println(Thread.currentThread().getName() + " " + "start printService thread");
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " " + "printService end ");
    }

    /**
     * 同步方法
     */
    public synchronized void printServiceOther() {
        System.out.println(Thread.currentThread().getName() + " " + "start printServiceOther thread");
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " " + "printServiceOther end ");
    }

    /**
     * 非同步方法
     */
    public void printServiceNotSynchronized() {
        System.out.println(Thread.currentThread().getName() + " " + "start printServiceNotSynchronized thread");
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " " + "printServiceNotSynchronized end ");
    }
}
