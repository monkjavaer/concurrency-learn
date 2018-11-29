package com.thread.thread;

/**
 * 竞态条件:当多个线程竞争同一资源时，如果对资源的访问顺序敏感，就称存在竞态条件。
 * 解决办法：同步 synchronized
 * @author tangquanbin
 * @date 2018/9/21 16:44
 */
public class Index3 {

    /**
     * volatile 只能保证可见性，无法保证原子性
     */
    private volatile int count = 0;

    public  void add(int adder) {
        this.count = this.count + adder;
        System.out.println("count = "+this.count);
    }


    public static void main(String[] args) {

        Index3 index = new Index3();

        for(int i=0; i<10; i++){
            new Thread("" + i){
                @Override
                public void run(){
                    index.add(1);
                }
            }.start();
        }
    }

}
