package com.thinkinjava.join;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author monkjavaer
 * @version V1.0
 * @date 2019/7/27 0027 11:52
 */
public class JoinATherad extends Thread {
    private static Logger LOGGER = LoggerFactory.getLogger(JoinATherad.class);

    public JoinATherad(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            LOGGER.info("{} 线程开始工作。。。。", getName());
            sleep(3000);
            LOGGER.info("{} 线程完成工作", getName());
        } catch (InterruptedException e) {
            LOGGER.info("{} 线程被中断， isInterrupted = {}", getName(), isInterrupted());
        }
    }
}
