package com.error;

import java.util.HashSet;
import java.util.Set;

/**
 * @author monkjavaer
 * @version V1.0
 * @date 2019/7/25 0025 21:52
 */
public class ErrorTest {

    public static void main(String[] args) {
/*        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            set.add(i);
            set.remove(i-1);
        }

        System.out.println(set.size());*/

        Set<Short> set = new HashSet<>();
        for (short i = 0; i < 100; i++) {
            set.add(i);
            set.remove(i-1);
        }
        System.out.println(set.size());
    }


}
