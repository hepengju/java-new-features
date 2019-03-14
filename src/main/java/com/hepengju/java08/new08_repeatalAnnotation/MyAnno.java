package com.hepengju.java08.new08_repeatalAnnotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 * 可重复的注解, 类型注解
 * 
 * @author hepengju
 *
 */

@Repeatable(MyAnnos.class)
@Retention(RUNTIME)
@Target({ TYPE, METHOD,TYPE_PARAMETER, TYPE_USE })
public @interface MyAnno {
    String value();
}
