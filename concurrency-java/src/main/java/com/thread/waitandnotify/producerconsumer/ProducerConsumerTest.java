package com.thread.waitandnotify.producerconsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author monkjavaer
 * @date 2018/12/15 12:17
 */
public class ProducerConsumerTest {
    public static void main(String[] args) {
        List<Integer> container = new ArrayList<>();
        Thread producer = new Thread(new Producer(container));
        Thread consumer = new Thread(new Consumer(container));
        producer.start();
        consumer.start();
    }
}
