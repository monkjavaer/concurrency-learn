package com.thinkinjava.synchronizedmodel.syn4;

/**
 * 锁不同的对象，异步的
 * @author monkjavaer
 * @date 2018/11/28 22:00
 */
public class Test4 {
    public static void main(String[] args) {
        Service4 service = new Service4();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("threadA");
        ThreadB threadB = new ThreadB(service);
        threadB.setName("threadB");
        threadA.start();
        threadB.start();
    }
}
