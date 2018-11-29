package com.thread.synchronizedmodel.syn1;

import java.util.concurrent.TimeUnit;

/**
 * @author tangquanbin
 * @date 2018/11/26 21:12
 */
public class Service1 {
    /**
     * 同步方法
     */
    public synchronized void printService(){
        System.out.println("start service thread:"+Thread.currentThread().getName());
        try {
            TimeUnit.MILLISECONDS.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end service");
    }
}
