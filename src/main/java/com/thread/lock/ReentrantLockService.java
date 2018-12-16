package com.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tangquanbin
 * @date 2018/12/16 22:24
 */
public class ReentrantLockService{

    private Lock lock = new ReentrantLock();
    private int count = 0;

    public void getCount(){
        try {
            //获得锁
            lock.lock();
            System.out.println("thread :"+Thread.currentThread().getName()+" count = "+count++);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //在finally块中调用unlock语句，以确保即使在方法体中抛出异常（try块）也会释放锁。
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        ReentrantLockService service = new ReentrantLockService();
        for(int i=0; i<100; i++){
            new Thread("" + i){
                @Override
                public void run(){
                    service.getCount();
                }
            }.start();
        }
    }

}
