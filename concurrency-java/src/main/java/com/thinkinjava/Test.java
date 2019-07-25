package com.thinkinjava;

/**
 * @author monkjavaer
 * @version V1.0
 * @date 2019/7/25 0025 23:04
 */
public class Test {
    public static void main(String[] args) {
        for(int i =0 ; i< 10;i++){
            new Thread(new CountDown()).start();
        }
        System.out.println("end....");
    }
}
