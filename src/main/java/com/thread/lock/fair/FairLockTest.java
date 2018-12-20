package com.thread.lock.fair;

/**
 * @author tangquanbin
 * @date 2018/12/20 21:56
 */
public class FairLockTest {
    public static void main(String[] args) {
        //创建公平锁
        FairLockService service = new FairLockService(false);
        for (int i=0;i<10;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread:"+Thread.currentThread().getName()+"run");
                    service.testFairLock();
                }
            },"thread-"+i);
            thread.start();
        }

    }
}
