package com.thinkinjava.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *     通过实现Thread.UncaughtExceptionHandler内部类，实现uncaughtException()方法捕获未捕获的异常。
 * </p>
 * @author monkjavaer
 * @version V1.0
 * @date 2019/7/27 0027 12:55
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static Logger LOGGER = LoggerFactory.getLogger(MyUncaughtExceptionHandler.class);
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        LOGGER.error(e.getMessage());
    }
}
