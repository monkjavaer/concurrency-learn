package com.thread.thread;

/**
 * @author tangquanbin
 * @date 2018/9/21 15:10
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("my Runnable");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable  = new MyRunnable();
        Thread thread = new Thread(myRunnable,"Thread110");
        thread.start();

        System.out.println(thread.getId()+" ；thread name:"+thread.getName()+" ；thread Priority："+thread.getPriority());

        System.out.println(Thread.currentThread().getName());
    }
}
