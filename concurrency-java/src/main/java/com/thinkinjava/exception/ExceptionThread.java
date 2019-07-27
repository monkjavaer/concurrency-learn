package com.thinkinjava.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * 无法捕获线程中抛出的异常
 * </p>
 *
 * @author monkjavaer
 * @version V1.0
 * @date 2019/7/27 0027 12:45
 */
public class ExceptionThread implements Runnable {

    private static Logger LOGGER = LoggerFactory.getLogger(ExceptionThread.class);

    @Override
    public void run() {
        throw new RuntimeException("Runtime Exception");
    }

    public static void main(String[] args) {
        try {
            Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(new ExceptionThread());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("无法捕获线程中抛出的异常。。。");
        }
    }
}
