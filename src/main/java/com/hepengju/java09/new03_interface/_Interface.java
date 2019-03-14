package com.hepengju.java09.new03_interface;

import org.junit.Test;

/**
 * 接口的私有方法
 * 
 * <pre>
 * 
 *  说明：方法的访问权限修饰符都可以声明为private的了，此时方法将不会成为你对外暴露的API的一部分。
 *  
 * </pre>
 * 
 * @author WGR
 *
 */
interface MyInterface{

    //jdk 7 : 只能声明全局常量(public static final)和抽象方法(public abstract)
    void method1();

    // jdk 8 : 声明静态方法 和 默认方法
    public static void method2(){
        System.out.println("method2");
    }

    default void method3(){
        System.out.println("method3");
        method4();
    }

    //jdk 9 : 声明私有方法
    private void method4(){
        System.out.println("私有方法");
    }


}

class MyInterfaceImpl implements  MyInterface{

    @Override
    public void method1() {
        System.out.println("实现接口中的抽象方法method1()");
    }
}

public class _Interface {
    
    /**
     * 不能直接调用接口的私有方法
     */
    @Test
    public void testInterface() {
        MyInterfaceImpl impl = new MyInterfaceImpl();
        impl.method3(); //method3 私有方法

       // impl.method4();
    }

}
