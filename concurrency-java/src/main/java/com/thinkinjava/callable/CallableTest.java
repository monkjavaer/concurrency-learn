package com.thinkinjava.callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author monkjavaer
 * @version V1.0
 * @date 2019/7/26 0026 23:13
 */
public class CallableTest {

    private static Logger LOGGER = LoggerFactory.getLogger(CallableTest.class);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            //executorService.submit(Callable<T> task) 返回Future对象
            Future<String> future = executorService.submit(new CallableTask());
            //future.isDone()查询Future完成没有
            LOGGER.info("main 线程开始做一些工作,将会花费4秒....");
            TimeUnit.SECONDS.sleep(4);
            LOGGER.info("main 线程已完成！");
            if (future.isDone()) {
                //future.get()获取结果
                LOGGER.info("CallableTask 任务线程返回结果 = {}.",future.get());
            }


        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }
}
