package com.thread.thread;

/**
 *
 * @author tangquanbin
 * @date 2018/10/24 14:57
 */
public class Index5 extends Thread{
    /**
     * volatile如果没有，程序会一直循环
     */
    private  volatile static  boolean flag = false;

    @Override
    public void run() {
        while (!flag) {
            int a =1;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Index5 index5 = new Index5();
        index5.start();
        Thread.sleep(1000);
        flag = true;
    }
}
