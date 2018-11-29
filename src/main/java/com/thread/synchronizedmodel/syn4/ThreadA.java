package com.thread.synchronizedmodel.syn4;

/**
 * @author tangquanbin
 * @date 2018/11/28 21:58
 */
public class ThreadA extends Thread {
    private Service4 service;

    public ThreadA(Service4 service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.printName();
    }
}
