package com.hepengju.java05.new07_annotation.spring;

import org.junit.Test;

/**
 * spring的常见注解
 *
 * <pre>
 *     基本注解: @Controller, @Service, @Repository, @Component
 *
 *     spring4注解
 *      * @Configuration     --> applicationContext.xml
 *      * @Bean              --> bean标签
 *          - @Scope         --> scope属性, singleton/prototype
 *      * @ComponentScan     --> <context:component-scan/>
 *          - value指定基础包, 对应base-package属性
 *          - excludeFilters --> context:exclude-filter
 *          - includeFilters --> context:include-filter, 需指定useDefaultFilters为false
 *              * @Filter, 指定过滤方式(注解,指定类型,切点表达式,正则及自定义)及对应的值
 *
 * </pre>
 *
 */
public class _SpringAnno {

    @Test public void baseAnno(){}
}
