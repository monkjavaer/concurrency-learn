package com.bingfabianchengdeyishu.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author monkjavaer
 * @date 2018/10/25 15:19
 */
public class LockUseCase {

    public void lock() {

        //不要将获取锁的过程写在try块中，因为如果在获取锁（自定义锁的实现）时发生了异常，
        //异常抛出的同时，也会导致锁无故释放。
        Lock lock = new ReentrantLock();
        lock.lock();
        try {

        } finally {
            //在finally块中释放锁，目的是保证在获取到锁之后，最终能够被释放。
            lock.unlock();
        }
    }

}
