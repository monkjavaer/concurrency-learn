package com.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author monkjavaer
 * @date 2018/12/21 22:55
 */
public class CollectionsUtil {

    public static void main(String[] args) {
        testShuffle();
    }

    public static void testAddAll() {
        List<Integer> collection = new ArrayList<>();
        Integer[] data = {2, 3, 4, 1, 6, 5};
        //Adds all of the specified elements to the specified collection.
        Collections.addAll(collection, data);
        for (Integer integer : collection) {
            System.out.println(integer);
        }
    }

    public static void testSort() {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(5);
        list.add(4);
        System.out.println(list.toString());
        Collections.sort(list);
/*        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else if (o1.equals(o2)) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });*/
        System.out.println(list.toString());
    }


    public static void testIndexOf(){
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(1);
        list.add(5);
        list.add(4);
        System.out.println(list.lastIndexOf(1));
    }

    public static void testShuffle(){
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(1);
        list.add(5);
        list.add(4);
        Random random = new Random();
        Collections.shuffle(list,random);
//        Collections.shuffle(list);
        System.out.println(list.toString());
    }

    public static void testRetainAll(){
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(5);
        list.add(4);
        List<Integer> copy = new ArrayList<>();
        copy.add(1);
        copy.add(4);
        copy.add(3);
        System.out.println(list.toString());
        copy.retainAll(list);
        System.out.println("copy = "+copy.toString());
        System.out.println("list = "+list.toString());
    }
    public static void testContainsAll(){
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.add(5);
        list.add(4);
        List<Integer> copy = new ArrayList<>();
        copy.add(1);
        copy.add(4);

        System.out.println(list.containsAll(copy));
    }
}
