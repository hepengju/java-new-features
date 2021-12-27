package com.hepengju.java05.new07_annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.hepengju.java05.new05_enum.WorkdayEnum;

/**
 * 复杂注解
 * 
 * @author hepengju
 *
 */
@Retention(RUNTIME)                // 保留: 运行时
@Target({TYPE,FIELD,METHOD})       // 限定: 类(含接口,注解,枚举),字段,方法
public @interface C_Anno{ 
    String name() default "";                   // 添加属性
    int age() default 18;                       // 添加默认值
    boolean value() default true;               // 特殊的value属性
    WorkdayEnum[] workdayArray() default {WorkdayEnum.SAT,WorkdayEnum.SUN};  // 工作日枚举数组
}