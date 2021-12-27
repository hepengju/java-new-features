package com.hepengju.java08.new13_repeatalAnnotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 保存可重复注解的容器
 * 
 * @author hepengju
 *
 */
@Retention(RUNTIME)
@Target({ ANNOTATION_TYPE, METHOD })
public @interface MyAnnos {
    MyAnno[] value();
}
