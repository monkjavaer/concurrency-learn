package com.thinkinjava.callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 线程通信，从任务中产生返回值
 * Callable是一种具有类型参数的泛型
 * </p>
 *
 * @author monkjavaer
 * @version V1.0
 * @date 2019/7/26 0026 23:08
 */
public class CallableTask implements Callable<String> {
    private static Logger LOGGER = LoggerFactory.getLogger(CallableTask.class);

    @Override
    public String call() throws Exception {
        LOGGER.info("CallableTask 开始工作了,将会花费3秒. ....");
        TimeUnit.MILLISECONDS.sleep(3000);
        LOGGER.info("CallableTask 工作完成了!");
        return "这是 CallableTask 返回结果";
    }
}
