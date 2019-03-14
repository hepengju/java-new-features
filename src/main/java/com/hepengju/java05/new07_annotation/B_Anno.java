package com.hepengju.java05.new07_annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.hepengju.java05.new05_enum.WorkdayEnum;

/**
 * 
 * 简单注解, value为枚举
 * @author hepengju
 *
 */
@Retention(CLASS)
@Target({ TYPE, METHOD })
public @interface B_Anno {
    WorkdayEnum[] value() default {};
}
