package com.util;

import java.util.*;

/**
 * 迭代器统一了对容器的访问方式，Iterator单向移动
 * @author tangquanbin
 * @date 2019/01/06 10:56
 */
public class IteratorUtil {

    public static void  display(Iterator<Integer> iterator){
        while (iterator.hasNext()){
            System.out.print(iterator.next());
        }
        System.out.println();
    }
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(0,1,2);
        ArrayList<Integer> arrayList = new ArrayList<>(list);
        LinkedList<Integer> linkedList = new LinkedList<>(list);
        HashSet<Integer> hashSet = new HashSet<>(list);
        TreeSet<Integer> treeSet = new TreeSet<>(list);
        display(arrayList.iterator());
        display(linkedList.iterator());
        display(hashSet.iterator());
        display(treeSet.iterator());
    }

}
