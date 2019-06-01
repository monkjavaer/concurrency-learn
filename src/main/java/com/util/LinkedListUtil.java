package com.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author monkjavaer
 * @date 2019/01/06 11:35
 */
public class LinkedListUtil {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(5,1,2);
        LinkedList<Integer> linkedList = new LinkedList<>(list);
        System.out.println(linkedList);
        //getFirst ，element完全一样，获取列表第一个元素，并不删除
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.element());
        System.out.println(linkedList.peek());
        System.out.println(linkedList);

        System.out.println(linkedList.poll());
        System.out.println(linkedList.remove());
        System.out.println(linkedList);


        linkedList.add(7);
        linkedList.add(8);
        System.out.println(linkedList);
        linkedList.addFirst(6);
        System.out.println(linkedList);
    }
}
