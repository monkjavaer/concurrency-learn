package com.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tangquanbin
 * @date 2018/12/18 21:14
 */
public class ConditionService {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void awaitTest(){
        try {
            lock.lock();
            System.out.println("before await()");
            condition.await();
            System.out.println("after await()");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void signalTest(){
        try {
            lock.lock();
            System.out.println("before signal()");
            condition.signal();
            System.out.println("after signal()");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionService service = new ConditionService();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                service.awaitTest();
            }
        });
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                service.signalTest();
            }
        });
        thread2.start();
    }
}
