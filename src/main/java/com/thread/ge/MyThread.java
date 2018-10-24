package com.thread.ge;

/**
 * @author tangquanbin
 * @date 2018/09/29 22:30
 */
public class MyThread extends Thread{

    @Override
    public void run() {
        super.run();
        System.out.println("MyThread is Alive:"+this.isAlive());
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        System.out.println("myThread is Alive:"+myThread.isAlive());
        myThread.start();
        System.out.println("mythread name:"+myThread.getName()+"mythread id:"+myThread.getId());
        System.out.println("myThread is Alive:"+myThread.isAlive());
    }
}
