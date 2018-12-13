package com.thread.waitandnotify;

/**
 * @author tangquanbin
 * @date 2018/12/13 22:10
 */
public class WaitNotifyTest {
    public static void main(String[] args) {
        Object work = new Object();
        ThreadWait wait = new ThreadWait(work);
        wait.start();
        ThreadNotify notify = new ThreadNotify(work);
        notify.start();
    }
}
