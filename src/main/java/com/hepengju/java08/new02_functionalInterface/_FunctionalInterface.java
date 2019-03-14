package com.hepengju.java08.new02_functionalInterface;

import java.util.function.Function;

import org.junit.Test;

/**
 * 函数式接口
 * 
 * <pre>
 *  定义: 只有一个抽象方法的接口
 *  说明: Lambda表达式需要"函数式接口"的支持, 可以使用 @FunctionalInterface 注解修改,检查接口是否是函数式接口
 *  
 *  Java8内置的函数式接口
 * 
 *      四大核心函数式接口
 *          Consumer<T>   : 消费型接口   void accept(T t)
 *          Supplier<T>   : 供给型接口   T get()
 *          Function<T,R> : 函数型接口   R apply(T t)
 *          Predicate<T>  : 断言型接口   boolean test(T t)
 * 
 *      其他函数式接口
 *          BiConsumer<T,U>  : 消费两个, 无返回值
 *          UnaryOperator<T> : 一元操作符 (Function子接口, T与R是一种类型)
 *          BiFunction<T,U,R>: 消费两个, 返回一个
 *          BinaryOperator<T>: 二元操作符 (BiFunction子接口, T,U,R是一种类型)
 *          
 *          ToIntFunction<T>, ToLongFunction<T>, ToDoubleFunction<T>: 给定T类型数据,返回int,long,double
 *          IntFunction<R>  , LongFunction<R>  , DoubleFunction<R>  : 给定int,long,double,返回R类型
 * 
 *  作用: 编写方法当需要传递Lambda表达式时,不用再自定义函数式接口,直接使用内置的函数式接口即可
 * </pre>
 * 
 * @see java.lang.FunctionalInterface
 * 
 * @author hepengju
 *
 */
public class _FunctionalInterface {

    /**
     * 自定义一个函数式接口测试
     */
    @Test public void testMyFunctionInterface() {
        String src = "I'm a Boy";
        String tar01 = handleStr01(src, s -> s.toUpperCase());
        String tar02 = handleStr01(src, s -> s.toLowerCase());
        System.out.println(tar01); // I'M A BOY
        System.out.println(tar02); // i'm a boy
    }
 
    @FunctionalInterface
    interface MyFun<T> {
        T getValue(T src);
    }
    
    private String handleStr01(String src, MyFun<String> mf) {
        return mf.getValue(src);
    }
    
    /**
     * 使用jdk的函数式接口测试
     */
    @Test public void testJdkFunctionInterface() {
        String src = "I'm a Boy";
        String tar01 = handleStr02(src, s -> s.toUpperCase());
        String tar02 = handleStr02(src, s -> s.toLowerCase());
        System.out.println(tar01); // I'M A BOY
        System.out.println(tar02); // i'm a boy
    }

    private String handleStr02(String src,Function<String, String> fun) {
        return fun.apply(src);
    }

}
