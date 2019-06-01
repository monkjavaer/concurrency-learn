package com.thread.lock.producerconsumer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 生产者
 *
 * @author monkjavaer
 * @date 2018/12/18 22:10
 */
public class Producer implements Runnable {
    /**
     * 产品容器
     */
    private List<Integer> container;
    private Lock lock;
    /**
     * 生产者条件
     */
    private Condition producerCondition;
    /**
     * 消费者条件
     */
    private Condition consumerCondition;

    public Producer(List<Integer> container, Lock lock, Condition producerCondition, Condition consumerCondition) {
        this.container = container;
        this.lock = lock;
        this.producerCondition = producerCondition;
        this.consumerCondition = consumerCondition;
    }

    public void produce() {
        //产品容器容量大小
        int capacity = 5;
        try {
            //获得锁
            lock.lock();
            //容器满了，不在生产
            if (container.size() == capacity) {
                System.out.println("生产满了。。。。");
                producerCondition.await();
            }
            Random random = new Random();
            int p = random.nextInt(50);
            //模拟1秒生产一个产品
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("生产产品：" + p);
            container.add(p);
            //生产一个产品，通知消费者
            consumerCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }

    }

    @Override
    public void run() {
        while (true) {
            produce();
        }
    }
}
