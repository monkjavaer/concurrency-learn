package com.util;

import java.util.*;

/**
 * @author monkjavaer
 * @date 2019/01/07 21:13
 */
public class SetUtil {
    public static void main(String[] args) {
        hashSet();
        treeSet();
        linkedHashSet();
    }

    public static void hashSet(){
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<10;i++){
            set.add(random.nextInt(30));
        }
        System.out.println(set);
    }

    public static void linkedHashSet(){
        Random random = new Random();
        Set<Integer> set = new LinkedHashSet<>();
        for (int i=0;i<10;i++){
            set.add(random.nextInt(30));
        }
        System.out.println(set);
    }

    /**
     * TreeSet 用于排序/87*
     */
    public static void treeSet(){
        Random random = new Random();
        Set<Integer> set = new TreeSet<>();
        for (int i=0;i<10;i++){
            set.add(random.nextInt(30));
        }
        System.out.println(set);
    }
}
