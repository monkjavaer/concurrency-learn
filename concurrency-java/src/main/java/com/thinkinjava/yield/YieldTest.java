package com.thinkinjava.yield;

/**
 * @author monkjavaer
 * @version V1.0
 * @date 2019/7/25 0025 23:04
 */
public class YieldTest {
    public static void main(String[] args) {
        int time = 10;
        for (int i = 0; i < time; i++) {
            new Thread(new YieldTask()).start();
        }
        System.out.println("end....");
    }
}
