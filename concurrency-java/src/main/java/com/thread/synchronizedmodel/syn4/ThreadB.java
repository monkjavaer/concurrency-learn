package com.thread.synchronizedmodel.syn4;

/**
 * @author monkjavaer
 * @date 2018/11/28 21:58
 */
public class ThreadB extends Thread {
    private Service4 service;

    public ThreadB(Service4 service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.printName();
    }
}
