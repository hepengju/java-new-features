package com.hepengju.java05.new07_annotation;

import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Retention;

/**
 * 简单注解
 * 
 * @author hepengju
 *
 */
@Retention(SOURCE)
@interface A_Anno{
    String value() default "";
}  