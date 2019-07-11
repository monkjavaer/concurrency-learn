package com.thread.volatilemoudel;

/**
 * @author monkjavaer
 * @date 2018/12/04 22:37
 */
public class Singleton {

    private volatile static Singleton instance;

    public static Singleton getInstance(){
        if (instance ==null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
