package com.thinkinjava.join;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CyclicBarrier;

/**
 * @author monkjavaer
 * @version V1.0
 * @date 2019/7/27 0027 11:52
 */
public class JoinBTherad extends Thread {
    private static Logger LOGGER = LoggerFactory.getLogger(JoinBTherad.class);
    private JoinATherad joinATherad;

    public JoinBTherad(String name, JoinATherad joinATherad) {
        super(name);
        this.joinATherad = joinATherad;
    }

    @Override
    public void run() {
        LOGGER.info("{} 线程开始调用 {} 线程join()", getName() ,joinATherad.getName());
        try {
            //线程Thread的方法public final void join() throws InterruptedException {
            //和 public final synchronized void join(long millis) throws InterruptedException {
            //join()源码通过线程Thread的isAlive()和Object的wait()方法实现
            //在B线程的run方法内调用A线程的join()方法，join()方法会一直等待A线程醒来，
            // 也就是A线程sleep()结束由阻塞状态进入就绪状态。
            joinATherad.join();
            //CyclicBarrier
        } catch (InterruptedException e) {
            LOGGER.info("{} 线程被中断， isInterrupted = {}", getName(), isInterrupted());
        }
        LOGGER.info("{} 线程调用 {} 线程join()完成", getName() ,joinATherad.getName());
    }
}
