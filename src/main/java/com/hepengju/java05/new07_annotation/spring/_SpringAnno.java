package com.hepengju.java05.new07_annotation.spring;

import org.junit.Test;

/**
 * spring的常见注解
 *
 * <pre>
 * 基本注解: @Controller, @Service, @Repository, @Component
 *
 * spring4注解
 *      * @Configuration        --> applicationContext.xml
 *      * @Bean                 --> bean标签
 *          - @Scope            --> scope属性, singleton/prototype
 *          - @Lazy             --> 懒加载
 *          - initMethod
 *          - destoryMethod
 *      * @ComponentScan        --> <context:component-scan/>
 *          - value指定基础包, 对应base-package属性
 *          - excludeFilters    --> context:exclude-filter
 *          - includeFilters    --> context:include-filter, 需指定useDefaultFilters为false
 *              * @Filter, 指定过滤方式(注解,指定类型,切点表达式,正则及自定义)及对应的值
 *      * @Conditional          --> 按照条件在容器中添加bean
 *          - Condition         --> 接口, matches方法
 *      * @Import               --> 快速导入简单组件
 *          - 传入组件全类名      --> id默认是组件的全类名
 *          - ImportSelector    --> 返回需要导入的组件的全类名数组
 *          - ImportBeanDefinitionRegistrar --> 自定义注册bean信息
 *      *
 *
 *
 *  给容器中添加bean
 *      1. 包扫描 + 组件标注注解  --> 自己写的
 *      2. @Bean                --> 导入第三方包里面的组件
 *      3. @Import              --> 快速导入简单组件
 *      4. 工厂bean              --> 框架整合 (Spring提供的FactoryBean, 默认获取到的是getObject创建的对象, 要获取工厂bean本身, 需要id前面加入&)
 *
 *  bean的生命周期: bean创建,初始化(创建完成并赋值好之后调用),销毁; 容器管理的; 可以自定义初始化和销毁方法
 *      1. @Bean注解指定初始化和销毁方法名
 *      2. 实现InitializingBean接口和DisposableBean接口
 *      3. JSR250规范的@PostConstruct和@PreDestroy
 *      4. BeanPostProcessor接口: bean的后置处理器, 初始化前后 
 *
 * </pre>
 *
 */
public class _SpringAnno {

    @Test public void baseAnno(){}
}
