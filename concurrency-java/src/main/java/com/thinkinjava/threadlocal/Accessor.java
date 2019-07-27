package com.thinkinjava.threadlocal;

import com.thinkinjava.synchronizedmodel.syn1.Service1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author monkjavaer
 * @version V1.0
 * @date 2019/7/27 0027 21:12
 */
public class Accessor implements Runnable {
    private static Logger LOGGER = LoggerFactory.getLogger(Accessor.class);
    private final int id;

    public Accessor(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLoadHolder.increment();
            LOGGER.info(String.valueOf(this));
            Thread.yield();
        }
    }
}
