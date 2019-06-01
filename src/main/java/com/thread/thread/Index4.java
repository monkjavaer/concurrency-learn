package com.thread.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author monkjavaer
 * @date 2018/10/24 14:42
 */
public class Index4 {
    private  int count = 0;
    /**
     * Lock保证原子性
     */
    Lock lock = new ReentrantLock();

    public  void add(int adder) {
        lock.lock();
        this.count = this.count + adder;
        System.out.println("count = "+this.count);
        lock.unlock();
    }


    public static void main(String[] args) {

        Index4 index = new Index4();

        for(int i=0; i<10; i++){
            new Thread("" + i){
                @Override
                public void run(){
                    index.add(1);
                }
            }.start();
        }
    }
}
