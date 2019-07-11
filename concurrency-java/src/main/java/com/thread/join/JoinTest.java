package com.thread.join;

/**
 * @author monkjavaer
 * @date 2018/12/15 16:38
 */
public class JoinTest {
    public static void main(String[] args) {
        try {
            Thread thread = new Thread(new JoinThread());
            thread.start();
            thread.join();
            System.out.println("....after JoinThread.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
