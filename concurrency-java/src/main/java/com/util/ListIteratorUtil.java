package com.util;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * list的 ListIterator双向移动
 * @author monkjavaer
 * @date 2019/01/06 11:15
 */
public class ListIteratorUtil {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(0,1,2);
        ListIterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next()+":nextIndex = "+iterator.nextIndex()+"previousIndex = "+iterator.previousIndex()+"  ");
        }
        System.out.println();
        while (iterator.hasPrevious()){
            System.out.print(iterator.previous());
        }
        System.out.println();
        //list.listIterator(2) 一开始就指向index
        iterator = list.listIterator(2);
        while (iterator.hasNext()){
            iterator.next();
            //set()替换元素
            iterator.set(1);
        }
        System.out.println(list);
    }
}
