package com.util;

import java.util.Arrays;
import java.util.List;

/**
 * @author monkjavaer
 * @date 2018/12/21 22:30
 */
public class ArraysUtil {

    public static void main(String[] args) {
        Integer data[] = {7, 2, 3, 4, 5};
        List<Integer> list = ArraysUtil.arrayToList(data);
        //数组排序
        Arrays.sort(data);
        System.out.println("Arrays.sort: "+Arrays.toString(data));
        System.out.println("binarySearch(Object[] a, Object key) : "+Arrays.binarySearch(data,3));

        Integer[] newArr = Arrays.copyOf(data, 3);
        System.out.println("copyOf(T[] original, int newLength) ： "+ Arrays.toString(newArr));

        int[] val = new int[4];
        Arrays.fill(val,3);
        System.out.println("fill(int[] a, int val) : "+Arrays.toString(val));

        //定义和初始化数组
        int[] arr1 = new int[]{2,3,1,4};
        //简化操作
        int[] arr2 = {2,3,1,4};

        //动态初始化
        int[] arr3 = new int[3];
        arr3[0] = 1;
        arr3[1] = 5;
        arr3[2] = 3;
        for (int i =0 ;i<arr3.length;i++){
            arr3[1] = 4;
            System.out.println(arr3[i]);
        }
        for (int anArr3 : arr3) {
            System.out.println(anArr3);
        }
        //二维数组
        int[][] arr4 =new int[4][];
        arr4[0] = new int[2];
    }

    /**
     * Arrays.asList(array)数组转换为集合对象
     *
     * @param array
     * @return
     */
    public static List<Integer> arrayToList(Integer[] array) {
        return Arrays.asList(array);
    }
}
