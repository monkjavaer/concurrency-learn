package com.thinkinjava.join;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author monkjavaer
 * @version V1.0
 * @date 2019/7/27 0027 11:44
 */
public class JoinTest {
    private static Logger LOGGER = LoggerFactory.getLogger(JoinTest.class);

    public static void main(String[] args) throws InterruptedException {
        JoinATherad joinA1 = new JoinATherad("joinA-1");
        joinA1.start();

        TimeUnit.MILLISECONDS.sleep(10);

        JoinBTherad joinB1 = new JoinBTherad("joinB-1",joinA1);
        joinB1.start();

    }
}
