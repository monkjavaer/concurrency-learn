package com.thread.lock.fair;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author monkjavaer
 * @date 2018/12/19 22:35
 */
public class FairLockService {
    private Lock lock;

    public FairLockService() {
    }

    public FairLockService(boolean isFair) {
        lock = new ReentrantLock(isFair);
    }

    public void testFairLock() {
        try {
            lock.lock();
            System.out.println("Thread:" + Thread.currentThread().getName()+"FairLockService");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
