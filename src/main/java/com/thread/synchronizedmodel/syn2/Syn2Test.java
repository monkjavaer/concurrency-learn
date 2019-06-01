package com.thread.synchronizedmodel.syn2;

/**
 *
 * synchronized修饰方法的两种情况：
 * (1).当A线程调用某个对象的synchronized方法，先持有某个对象的锁；这时B线程需要等待A线程执行完毕后释放这个对象锁才可调用这个对象的synchronized方法，即同步。
 * (2).当A线程调用某个对象的synchronized方法时，B线程调用这个对象的其他非synchronized方法，不需要等待。
 *
 * @author monkjavaer
 * @date 2018/11/26 21:19
 */
public class Syn2Test {
    public static void main(String[] args) {
        Service2 service = new Service2();
        //Syn2ThreadA调用了同步方法
        Syn2ThreadA threadA = new Syn2ThreadA(service);
        threadA.setName("threadA");
        //Syn2ThreadB调用非同步
        Syn2ThreadB threadB = new Syn2ThreadB(service);
        threadB.setName("threadB");
        //Syn2ThreadC调用了同步方法
        Syn2ThreadC threadC = new Syn2ThreadC(service);
        threadC.setName("threadC");
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
