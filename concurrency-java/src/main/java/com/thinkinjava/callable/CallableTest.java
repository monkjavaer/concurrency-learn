package com.thinkinjava.callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
            List<Future<String>> list = new ArrayList<>();
            int time = 10;
            for (int i = 0; i < time; i++) {
                Future<String> future = executorService.submit(new CallableTask(i));
                list.add(future);
            }
            for (Future<String> future : list) {
                LOGGER.info(future.get());
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }
}
