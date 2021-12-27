package com.hepengju.java08.new03_methodReference;

import java.io.PrintStream;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Test;

/**
 * 方法引用, 构造器引用, 数组引用
 * 
 * <pre>
 *  方法引用
 *      说明: 当要传递给Lambda体的操作, 已经有实现的方法时可以使用操作符"::"进行方法引用
 *      注意: 实现抽象方法的参数列表, 必须与方法引用方法的参数列表保持一致
 *      分类:
 *          对象::实例方法
 *          类::静态方法
 *          类::实例方法    --> 当需要引用方法的第一个参数是调用对象, 并且第二个参数是需要引用方法的参数(或无参数)时, 可使用 ClassName::methodName
 *  
 *  构造器引用: ClassName::new
 *  数组引用:   Type[]::new
 * </pre> 
 * 
 * @author hepengju
 *
 */
public class _MethodReference {

    /**
     * 方法引用示例
     */
    @SuppressWarnings("unused")
    @Test public void testMethodReference() {
        // 对象::实例方法
        PrintStream ps = System.out;
        Consumer<Object> con01 = x -> ps.println(x);
        Consumer<Object> con02 = ps::println; // 即 System.out::println
        
        // 类::静态方法
        BinaryOperator<Double> bo1 = (x,y) -> Math.pow(x,y);
        BinaryOperator<Double> bo2 = Math::pow;
        
        // 类::实例方法
        BiFunction<String, String, Boolean> bi01 = (x,y) -> x.equals(y);
        BiFunction<String, String, Boolean> bi02 = String::equals;
    }
    
    /**
     * 构造器引用
     */
    @SuppressWarnings("unused")
    @Test public void testConstructorReference() {
        Function<Integer,Person> fun01 = n -> new Person(n);
        Function<Integer,Person> fun02 = Person::new;
    }
    
    /**
     * 数组引用
     */
    @SuppressWarnings("unused")
    @Test public void testArrayReference() {
        Function<Integer,Integer[]> fun01 = n -> new Integer[n];
        Function<Integer,Integer[]> fun02 = Integer[]::new;
    }
    
    class Person{
        Integer age;
        
        public Person(Integer age) {
            super();
            this.age = age;
        }
        
        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
    
}
