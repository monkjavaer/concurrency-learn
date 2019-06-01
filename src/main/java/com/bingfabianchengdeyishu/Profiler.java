package com.bingfabianchengdeyishu;

import java.util.concurrent.TimeUnit;

/**
 * 调用耗时分析统计工具
 *
 * ThreadLocal，即线程变量，是一个以ThreadLocal对象为键、任意对象为值的存储结构。这
 * 个结构被附带在线程上，也就是说一个线程可以根据一个ThreadLocal对象查询到绑定在这个
 * 线程上的一个值。
 *
 * @author monkjavaer
 * @date 2018/10/25 10:57
 */
public class Profiler {
    // 第一次get()方法调用时会进行初始化（如果set方法没有调用），每个线程会调用一次
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static final void begin() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    public static final long end() {
        return System.currentTimeMillis() - TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws Exception {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Profiler.end() + " mills");
    }
}
