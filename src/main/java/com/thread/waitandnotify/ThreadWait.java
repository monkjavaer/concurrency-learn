package com.thread.waitandnotify;

/**
 * @author tangquanbin
 * @date 2018/12/13 22:00
 */
public class ThreadWait extends Thread {
    private Object work;

    public ThreadWait(Object work) {
        this.work = work;
    }

    @Override
    public void run() {
        try {
            synchronized (work){
                System.out.println("...before wait...");
                work.wait();
                System.out.println("...after wait...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
