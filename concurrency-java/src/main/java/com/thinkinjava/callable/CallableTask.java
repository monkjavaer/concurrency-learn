package com.thinkinjava.callable;

import java.util.concurrent.Callable;

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

    private int id;

    public CallableTask(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "TaskResult's id is " + id;
    }
}
