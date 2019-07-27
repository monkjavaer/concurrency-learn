package com.thinkinjava.synchronizedmodel.syn5;

import java.util.concurrent.TimeUnit;

/**
 * @author monkjavaer
 * @date 2018/11/28 21:52
 */
public class Service5 {


    public synchronized static void printName1() {
        System.out.println(Thread.currentThread().getName() + ":start");
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":stop");
    }

    public synchronized static void printName2() {
        System.out.println(Thread.currentThread().getName() + ":start");
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":stop");
    }

    public synchronized  void printName3() {
        System.out.println(Thread.currentThread().getName() + ":start");
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":stop");
    }
}
