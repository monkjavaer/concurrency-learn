package com.util;

import java.util.*;

/**
 * @author tangquanbin
 * @date 2018/12/21 22:55
 */
public class CollectionsUtil {

    public static void main(String[] args) {
        List<Integer> collection = new ArrayList<>();
        Integer[] data = {2,3,4,1,6,5};
        //Adds all of the specified elements to the specified collection.
        Collections.addAll(collection,data);
        for (Integer integer : collection) {
            System.out.println(integer);
        }
    }
}
