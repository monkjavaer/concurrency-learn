package com.thread.ge;

/**
 * @author tangquanbin
 * @date 2018/09/29 22:30
 */
public class MyThread extends Thread{

    public MyThread() {
        System.out.println("this.getName():"+this.getName());
    }

    @Override
    public void run() {
        super.run();
    }

    /**
     * 线程start之后才是Alive状态
     * @param args
     */
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        System.out.println("myThread is Alive:"+myThread.isAlive());
        myThread.start();
        System.out.println("Thread.currentThread().getName():"+Thread.currentThread().getName());
        System.out.println("mythread name:"+myThread.getName()+"mythread id:"+myThread.getId());
        System.out.println("myThread is Alive:"+myThread.isAlive());
    }
}
