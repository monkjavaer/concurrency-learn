package com.thread.synchronizedmodel.syn1;

/**
 * Syn1ThreadA线程先持有Service1对象的锁，Syn1ThreadB对象调用synchronized方法需要等待
 * @author monkjavaer
 * @date 2018/11/26 21:19
 */
public class Syn1Test {
    public static void main(String[] args) {
        Service1 service1 = new Service1();
        Syn1ThreadA threadA = new Syn1ThreadA(service1);
        threadA.setName("threadA");
        Syn1ThreadB threadB = new Syn1ThreadB(service1);
        threadB.setName("threadB");
        threadA.start();
        threadB.start();
    }
}
