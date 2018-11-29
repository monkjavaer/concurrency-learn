package com.thread.priority;

/**
 * @author tangquanbin
 * @date 2018/11/22 21:48
 */
public class PriorityThread {

    static class A extends Thread{
        @Override
        public void run() {
           System.out.println("A getPriority:"+this.getPriority());
        }
    }

    static class B extends Thread{
        @Override
        public void run() {
            System.out.println("B getPriority:"+this.getPriority());
        }
    }

    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        //设置线程优先级
        b.setPriority(Thread.MIN_PRIORITY);
        a.start();
        b.start();
    }
}
