package com.thread.synchronizedmodel.syn1;

/**
 * @author tangquanbin
 * @date 2018/11/26 21:16
 */
public class Syn1ThreadB extends Thread{
    private Service1 service1;
    public Syn1ThreadB(Service1 service1) {
        this.service1 = service1;
    }

    @Override
    public void run() {
        super.run();
        service1.printService();
    }
}
