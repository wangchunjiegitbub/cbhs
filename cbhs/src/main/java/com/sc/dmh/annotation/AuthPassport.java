package com.sc.dmh.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Inherited
// 自定义一个注解，这个注解只能修饰方法  *   */ 
@Target(ElementType.METHOD)
//编译器将Annotation储存于class档中，可由VM读入 
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthPassport {
    boolean validate() default true;
}