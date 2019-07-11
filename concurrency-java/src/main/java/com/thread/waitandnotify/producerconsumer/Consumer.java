package com.thread.waitandnotify.producerconsumer;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 消费者
 * @author monkjavaer
 * @date 2018/12/15 11:13
 */
public class Consumer implements Runnable{

    /**
     * 产品容器
     */
    private final List<Integer> container;

    public Consumer(List<Integer> container) {
        this.container = container;
    }

    /**
     * 消费者消费产品
     */
    private void consume() throws InterruptedException {
        synchronized (container){
            while (container.isEmpty()){
                System.out.println("...容器是空的，暂停消费...");
                container.wait();
            }
            Integer p = container.remove(0);
            //模拟1秒消费一个产品
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println("消费产品：" + p);
            container.notifyAll();
        }
    }
    @Override
    public void run() {
        while (true){
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("consume error");
            }
        }
    }
}
