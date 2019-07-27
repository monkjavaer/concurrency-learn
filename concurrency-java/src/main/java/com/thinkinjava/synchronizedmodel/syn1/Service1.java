package com.thinkinjava.synchronizedmodel.syn1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author monkjavaer
 * @date 2018/11/26 21:12
 */
public class Service1 {
    private static Logger LOGGER = LoggerFactory.getLogger(Service1.class);
    /**
     * 同步方法
     */
    public synchronized void printService(){
        LOGGER.info("{} 开始。。。。:",Thread.currentThread().getName());
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("{} 结束。。。。:",Thread.currentThread().getName());
    }
}
