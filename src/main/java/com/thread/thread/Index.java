package com.thread.thread;

/**
 * 竞态条件:当多个线程竞争同一资源时，如果对资源的访问顺序敏感，就称存在竞态条件。
 * @author tangquanbin
 * @date 2018/9/21 16:44
 */
public class Index {
    private int count = 0;

    public void add(int adder) {
        this.count = this.count + adder;
        System.out.println("count = "+this.count);
    }


    public static void main(String[] args) {

        Index index = new Index();

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
