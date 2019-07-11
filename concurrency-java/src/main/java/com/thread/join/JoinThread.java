package com.thread.join;

import java.util.concurrent.TimeUnit;

/**
 * @author monkjavaer
 * @date 2018/12/15 16:36
 */
public class JoinThread implements Runnable{
    @Override
    public void run() {
        System.out.println("I am JoinThread run ...");
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
