package com.thread.lock.count;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tangquanbin
 * @date 2018/12/20 22:12
 */
public class CountService {
    private ReentrantLock reentrantLock = new ReentrantLock();

    public void testCount(){
        try {
            System.out.println("before lock reentrantLock.getHoldCount() = "+reentrantLock.getHoldCount());
            reentrantLock.lock();
            System.out.println("after lock reentrantLock.getHoldCount() = "+reentrantLock.getHoldCount());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        CountService service = new CountService();
        service.testCount();
    }
}
