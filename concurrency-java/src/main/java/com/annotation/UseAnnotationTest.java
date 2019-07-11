package com.annotation;

import java.lang.reflect.Field;

/**
 * @author monkjavaer
 * @date 2018/10/31 22:32
 */
public class UseAnnotationTest {

    @AnnotationTest(id = 1, value = "tom")
    private String name;

    public void getName(){
        System.out.println(name);
    }

    public static void main(String[] args) {
        //getFields(): 获取某个类的所有的public字段，其中是包括父类的public字段的。
        //getDeclaredFields()：获取某个类的自身的所有字段，不包括父类的字段。
        Field[] fields = UseAnnotationTest.class.getDeclaredFields();
        for (Field field : fields) {
            boolean flg = field.isAnnotationPresent(AnnotationTest.class);
            if (flg){
                AnnotationTest annotation = field.getAnnotation(AnnotationTest.class);
                if (annotation !=null) {
                    System.out.println(annotation.id() + annotation.value() + annotation.message());
                }
            }
        }
    }


}
