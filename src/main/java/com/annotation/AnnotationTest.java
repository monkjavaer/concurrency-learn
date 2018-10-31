package com.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 注解的可用的类型包括以下几种：所有基本类型、String、Class、enum、Annotation、以上类型的数组形式。
 * @author tangquanbin
 * @date 2018/10/31 19:28
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnnotationTest {

    String value();

    String message() default  "this is message";
}
