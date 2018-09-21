package com.thread.ge;

/**
 * 启动线程的顺序是有序的，但是执行的顺序并非是有序的
 * @author tangquanbin
 * @date 2018/9/21 15:19
 */
public class MyThread {

    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName());
        for(int i=0; i<10; i++){
            new Thread("" + i){
                @Override
                public void run(){
                    System.out.println("Thread: " + getName() + " running");
                }
            }.start();
        }
    }

}
