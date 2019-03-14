package com.hepengju.java06.new12_overrideBug;

import org.junit.Test;

/**
 * 
 * <pre>
 *    @Override是JDK5就已经有了，但有个小小的Bug，就是不支持对接口的实现，认为这不是Override 
 *    而JDK6修正了这个Bug，无论是对父类的方法覆盖还是对接口的实现都可以加上@Override
 * </pre>
 * @author hepengju
 *
 */
public class _OverrideBug {

    @Test public void testOverride() {
        Thread t01 = new MyThread01();
        t01.start(); // MyThread01 override method run of Thread
        
        Thread t02 = new Thread(new MyThread02());
        t02.start(); // MyThread02 override method run of Runnable
        
    }
    
    class MyThread01 extends Thread{
        @Override
        public void run() {
            System.out.println("MyThread01 override method run of Thread");
        }
        
    }
    
    class MyThread02 implements Runnable{
        @Override // jdk5此处编译不通过
        public void run() {
            System.out.println("MyThread02 override method run of Runnable");
        }
        
    }
}
