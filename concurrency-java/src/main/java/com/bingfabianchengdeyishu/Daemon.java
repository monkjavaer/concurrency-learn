package com.bingfabianchengdeyishu;

import java.util.concurrent.TimeUnit;

/**
 * Daemon属性需要在启动线程之前设置，不能在启动线程之后设置。
 *
 * Daemon线程是一种支持型线程，因为它主要被用作程序中后台调度以及支持性工作。这
 * 意味着，当一个Java虚拟机中不存在非Daemon线程的时候，Java虚拟机将会退出
 *
 * 下面finally中代码没执行，main线程（非Daemon线程）在启动了线程DaemonRunner之后
 * 随着main方法执行完毕而终止，而此时Java虚拟
 * 机中已经没有非Daemon线程，虚拟机需要退出。
 *
 * @author monkjavaer
 * @date 2018/10/25 11:15
 */
public class Daemon {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new DaemonRunner());
        thread.setDaemon(true);
        thread.start();
//        TimeUnit.SECONDS.sleep(5);
    }

    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
