package com.annotation;

/**
 * @author tangquanbin
 * @date 2018/10/31 22:32
 */
public class UseAnnotationTest {

    @AnnotationTest("tom")
    public String name;

    @AnnotationTest("tt")
    public void getName(){
        System.out.println(name);
    }

    public static void main(String[] args) {
        UseAnnotationTest useAnnotationTest = new UseAnnotationTest();
        useAnnotationTest.getName();
    }


}
