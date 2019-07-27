package com.thinkinjava.synchronizedmodel.syn4;

import java.util.concurrent.TimeUnit;

/**
 * @author monkjavaer
 * @date 2018/11/28 21:52
 */
public class Service4 {

    private static final String name =new String();

    public void printName(){

        synchronized (name){
            System.out.println(Thread.currentThread().getName()+":start");
            try {
                TimeUnit.MILLISECONDS.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":stop");
        }
    }
}
