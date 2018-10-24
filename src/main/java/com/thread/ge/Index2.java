package com.thread.ge;

/**
 * 竞态条件:当多个线程竞争同一资源时，如果对资源的访问顺序敏感，就称存在竞态条件。
 * 解决办法：同步 synchronized
 * @author tangquanbin
 * @date 2018/9/21 16:44
 */
public class Index2 {
    private int count = 0;

    /**
     * synchronized保证原子性
     * @param adder
     */
    public synchronized void add(int adder) {
        this.count = this.count + adder;
        System.out.println("count = "+this.count);
    }


    public static void main(String[] args) {

        Index2 index = new Index2();

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
