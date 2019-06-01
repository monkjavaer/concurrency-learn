package com.thread.synchronizedmodel.syn5;

/**
 * @author monkjavaer
 * @date 2018/11/28 21:58
 */
public class ThreadC extends Thread {
    private Service5 service;

    public ThreadC(Service5 service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.printName3();
    }
}
