package com.thread.lock.producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tangquanbin
 * @date 2018/12/15 12:17
 */
public class ProducerConsumerTest {
    public static void main(String[] args) {
        List<Integer> container = new ArrayList<>();
        Lock lock = new ReentrantLock();
        Condition producerCondition = lock.newCondition();
        Condition consumerCondition = lock.newCondition();
        Thread producer = new Thread(new Producer(container,lock,producerCondition,consumerCondition));
        Thread consumer = new Thread(new Consumer(container,lock,producerCondition,consumerCondition));
        producer.start();
        consumer.start();
    }
}
