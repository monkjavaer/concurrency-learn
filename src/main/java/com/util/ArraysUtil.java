package com.util;

import java.util.Arrays;
import java.util.List;

/**
 * @author tangquanbin
 * @date 2018/12/21 22:30
 */
public class ArraysUtil {

    public static void main(String[] args) {
        Integer data[] = {7,2,3,4,5};
        List<Integer> list = ArraysUtil.arrayToList(data);
        //数组排序
        Arrays.sort(data);
        System.out.println(data);
    }

    /**
     * Arrays.asList(array)数组转换为集合对象
     * @param array
     * @return
     */
    public static List<Integer> arrayToList(Integer[] array){
        return Arrays.asList(array);
    }
}
