package com.thread.lock.producerconsumer;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author monkjavaer
 * @date 2018/12/18 22:16
 */
public class Consumer implements Runnable{
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

    public Consumer(List<Integer> container, Lock lock, Condition producerCondition, Condition consumerCondition) {
        this.container = container;
        this.lock = lock;
        this.producerCondition = producerCondition;
        this.consumerCondition = consumerCondition;
    }

    /**
     * 消费者消费产品
     */
    private void consume(){
        try {
            //获得锁
            lock.lock();
            //容器大小为null，不消费
            if (container.size() == 0) {
                System.out.println("消费完了。。。。");
                consumerCondition.await();
            }
            Integer p = container.remove(0);
            //模拟1秒消费一个产品
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("消费产品：" + p);
            //消费了，通知生产者
            producerCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }
    }
    @Override
    public void run() {
        while (true){
           consume();
        }
    }
}
