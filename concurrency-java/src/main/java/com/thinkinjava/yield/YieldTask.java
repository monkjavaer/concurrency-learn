package com.thinkinjava.yield;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author monkjavaer
 * @version V1.0
 * @date 2019/7/25 0025 23:01
 */
public class YieldTask implements Runnable {
    private static Logger LOGGER = LoggerFactory.getLogger(YieldTask.class);
    protected int total = 10;

    @Override
    public void run() {
        while (total-- > 0) {
            LOGGER.info(Thread.currentThread().getName() + "   :" + total);
            // yield()让步。它能让当前线程由“运行状态”进入到“就绪状态”
            Thread.yield();
        }
    }
}
