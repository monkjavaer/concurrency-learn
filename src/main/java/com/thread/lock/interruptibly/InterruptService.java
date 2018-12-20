package com.thread.lock.interruptibly;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tangquanbin
 * @date 2018/12/20 22:30
 */
public class InterruptService {
    private ReentrantLock lock = new ReentrantLock();

    public void testInterrupt(){
        try {
            lock.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
