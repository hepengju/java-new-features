package com.hepengju.java08.new01_lambda;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

import org.junit.Test;

/**
 * Lambda表达式
 * 
 * <pre>
 *  说明: lambda是一个匿名函数, 可以理解为一段可以传递的代码(像数据一样进行传递)
 *  作用: 更简洁,更灵活,提升Java语言的表达能力
 *  语法:
 *      左侧: Lambda参数
 *      中间: Lambda操作符 -> ,也称为箭头操作符或
 *      右侧: Lambda体       ,即要执行的功能
 *
 *  其他: lambda表达式比匿名内部类更加节省内存空间(Java8的JVM内存结构发生变化)
 * </pre>
 * 
 * @author hepengju
 *
 */
public class _Lambda {
    
    /**
     * 从匿名类到Lambda表达式的转变
     */
    @Test public void testLambdaHelloWorld() {
        
        // 匿名类01
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        }).start();;
        
        // lambda01
        new Thread(() -> System.out.println("Hello")).start();
        
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // 匿名类02
        TreeSet<String> ts01 = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
        
        // lambda02
        TreeSet<String> ts02 = new TreeSet<>((o1,o2) -> Integer.compare(o1.length(), o2.length()));
        
        ts01.add("aaa");    ts02.add("aaa");
        ts01.add("bb");     ts02.add("bb"); 
        ts01.add("z");      ts02.add("z");  
        System.out.println(ts01); // [z, bb, aaa]
        System.out.println(ts02); // [z, bb, aaa]
    }
    
    /**
     * lambda语法: 能省则省
     */
    @SuppressWarnings("unused")
    @Test public void testLambdaSyntax() {
        // 语法
        BinaryOperator<Integer> bo = (Integer x,Integer y) -> {
            return x+y;
        };
        
        // 简化1: 由于类型推断(编译器javac根据上下文环境推断出类型), 可以省略参数的类型 
        bo = (x,y) -> {
            return x+y;
        };
        
        // 简化2: 当Lambda体只有一条语句的时候可以省略return和大括号{}
        bo = (x,y) -> x + y;
        
        // 简化3: 当参数只有一个时, 可以省略小括号
        Consumer<String> fun = args -> System.out.println(args);
        
        // 简化4: 当参数个数为零时, 使用()即可
        Runnable r1 = () -> System.out.println("Hello Lambda"); 
        
        // 简化5: 方法引用(下个新特性)
        Consumer<String> fun02 = System.out::println;
    }
    
}
