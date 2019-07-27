package com.thinkinjava.threadlocal;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author monkjavaer
 * @version V1.0
 * @date 2019/7/27 0027 21:15
 */
public class ThreadLoadHolder {
    private static ThreadLocal<Integer> threadLocal =
            new ThreadLocal<Integer>() {
                private Random random = new Random(47);

                protected synchronized Integer init() {
                    return random.nextInt(10000);
                }
            };
    public static void increment(){
        threadLocal.set(threadLocal.get() + 1);
    }

    public static int get(){
        return threadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i =0 ;i<5;i++){
            executorService.execute(new Accessor(i));
        }
        TimeUnit.MILLISECONDS.sleep(3000);
        executorService.shutdown();
    }
}
