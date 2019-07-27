package com.thinkinjava.synchronizedmodel.syn2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author monkjavaer
 * @date 2018/11/26 21:12
 */
public class Service2 {
    private static Logger LOGGER = LoggerFactory.getLogger(Service2.class);
    /**
     * 同步方法
     */
    public synchronized void printService() {
        LOGGER.info("{} 开始执行 printService()",Thread.currentThread().getName());
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("{} 结束 printService()",Thread.currentThread().getName());
    }

    /**
     * 同步方法
     */
    public  synchronized void printServiceOther() {
        LOGGER.info("{} 开始执行 printServiceOther()",Thread.currentThread().getName());
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("{} 结束 printServiceOther()",Thread.currentThread().getName());
    }

    /**
     * 非同步方法
     */
    public void printServiceNotSynchronized() {
        LOGGER.info("{} 开始执行 printServiceNotSynchronized()",Thread.currentThread().getName());
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("{} 结束 printServiceNotSynchronized()",Thread.currentThread().getName());
    }
}
