package com.thread.synchronizedmodel.syn5;

/**
 * 锁不同的对象，异步的
 * @author monkjavaer
 * @date 2018/11/28 22:00
 */
public class Test5 {
    public static void main(String[] args) {
        String a = "a";
        String b = "a";
        System.out.println(a==b);
        Service5 service = new Service5();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("threadA");
        ThreadB threadB = new ThreadB(service);
        threadB.setName("threadB");
        ThreadC threadC = new ThreadC(service);
        threadC.setName("threadC");
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
