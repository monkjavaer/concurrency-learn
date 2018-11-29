package com.thread.synchronizedmodel.syn3;

/**
 * @author tangquanbin
 * @date 2018/11/26 21:54
 */
public class Apple extends Fruit {
    @Override
    public synchronized void dosomething() {
        super.dosomething();
        System.out.println("apple");
    }
}
