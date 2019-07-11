package com.thread.thread;

/**
 * Thread.yield(),放弃当前CPU资源。如果一个线程不是那么重要，或者优先级非常低，而且又害怕它会占用太多的CPU资源，
 * 那么可以在适当的时候调用Thread.yield()，给予其他重要线程更多的工作机会。
 * @author monkjavaer
 * @date 2018/11/22 21:28
 */
public class YieldTest extends Thread{

    @Override
    public void run() {
        long beginTime =  System.currentTimeMillis();
        int count = 0;
        for (int i = 0;i<400000;i++){
            Thread.yield();
            count = count +(i+1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-beginTime);
    }

    public static void main(String[] args) {
        YieldTest yieldTest = new YieldTest();
        yieldTest.start();
    }

}
