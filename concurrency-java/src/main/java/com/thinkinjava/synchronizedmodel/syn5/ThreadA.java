package com.thinkinjava.synchronizedmodel.syn5;

/**
 * @author monkjavaer
 * @date 2018/11/28 21:58
 */
public class ThreadA extends Thread {
    private Service5 service;

    public ThreadA(Service5 service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.printName1();
    }
}
