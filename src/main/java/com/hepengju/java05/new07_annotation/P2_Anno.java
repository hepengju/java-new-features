package com.hepengju.java05.new07_annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 父类上的注解(不可被继承)
 * 
 * @author hepengju
 *
 */
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface P2_Anno {

}
