package com.thinkinjava.daemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * <p>守护线程</p>
 *
 * @author monkjavaer
 * @version V1.0
 * @date 2019/7/27 0027 9:31
 */
public class DaemoTask implements Runnable {
    private static Logger LOGGER = LoggerFactory.getLogger(DaemoTask.class);

    @Override
    public void run() {

        try {
            TimeUnit.MICROSECONDS.sleep(1000);
            LOGGER.info("[{}] is completed.", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            LOGGER.error("InterruptedException:{}", e.getLocalizedMessage());
        } finally {
            LOGGER.info("finally块输出。");
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new DaemoTask());
        //thread.setDaemon(true) 守护线程必须在start()方法之前设置。
        // 当非守护线程全部结束时，守护线程也就结束了。main线程就是一个非守护线程
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(1000);
    }
}
