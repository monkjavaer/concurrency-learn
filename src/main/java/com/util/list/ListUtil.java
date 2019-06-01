package com.util.list;

import java.util.*;

/**
 * @author monkjavaer
 * @date 2019/01/10 21:53
 */
public class ListUtil {

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        long start1 = System.currentTimeMillis();
        for (int i =0;i<10000000;i++){
            arrayList.add(i);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("ArrayList for循环添加10000000个元素耗时："+(end1-start1));

        loopList(arrayList);

        List<Integer> linkedList = new LinkedList<>();
        long start2 = System.currentTimeMillis();
        for (int i =0;i<10000000;i++){
            linkedList.add(i);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("LinkedList for循环添加10000000个元素耗时："+(end2-start2));

        loopList(linkedList);
        testSpeed();
    }


    /**
     * 便利速度测试
     */
    public static void testSpeed(){
        //add
        ArrayList<Integer> arrayList = new ArrayList<>();
        long start1 = System.currentTimeMillis();
        for (int i =0;i<10000000;i++){
            arrayList.add(i);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("ArrayList for循环添加10000000个元素耗时："+(end1-start1));

        LinkedList<Integer> linkedList = new LinkedList<>();
        long start2 = System.currentTimeMillis();
        for (int i =0;i<10000000;i++){
            linkedList.add(i);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("LinkedList for循环添加10000000个元素耗时："+(end2-start2));

        //ArrayList for(效率高)
        long start3 = System.currentTimeMillis();
        for (int i=0;i<arrayList.size();i++){
            arrayList.get(i);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("ArrayList for循环遍历10000000个元素耗时："+(end3-start3));

        //ArrayList iterator
        long start4 = System.currentTimeMillis();
        Iterator<Integer> arrayListIt = arrayList.iterator();
        while (arrayListIt.hasNext()){
            arrayListIt.next();
        }
        long end4 = System.currentTimeMillis();
        System.out.println("ArrayList iterator遍历10000000个元素耗时："+(end4-start4));

        //LinkedList for (效率非常低)
        long start5 = System.currentTimeMillis();
        for (int i=0;i<linkedList.size();i++){
            linkedList.get(i);
        }
        long end5 = System.currentTimeMillis();
        System.out.println("LinkedList for循环遍历10000000个元素耗时："+(end5-start5));

        //LinkedList iterator (效率高)
        long start6 = System.currentTimeMillis();
        Iterator<Integer> linkedListIt = arrayList.iterator();
        while (linkedListIt.hasNext()){
            linkedListIt.next();
        }
        long end6 = System.currentTimeMillis();
        System.out.println("LinkedList iterator遍历10000000个元素耗时："+(end6-start6));


    }

    /**
     * RandomAccess 标记接口 应用
     * @param list
     */
    public static void loopList(List<Integer> list){
        if (list instanceof RandomAccess){
            System.out.println("实现了RandomAccess");
            long start = System.currentTimeMillis();
            for (int i =0 ;i<list.size();i++){
                list.get(i);
            }
            long end = System.currentTimeMillis();
            System.out.println("ArrayList for 循环："+(end-start));
        }else {
            System.out.println("没有实现RandomAccess");
            long start = System.currentTimeMillis();
            Iterator iterator = list.iterator();
            while (iterator.hasNext()){
                iterator.next();
            }
            long end = System.currentTimeMillis();
            System.out.println("LinkedList iterator 循环："+(end-start));
        }
    }
}
