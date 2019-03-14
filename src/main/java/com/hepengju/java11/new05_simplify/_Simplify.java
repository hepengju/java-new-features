package com.hepengju.java11.new05_simplify;

/**
 * 
 * 更简化的编译运行程序
 * JEP 330: Launch Single-File Source-Code Programs
 *
 * <pre>
 * 
 *  JEP 330: 增强java启动器支持运行单个java源代码文件的程序。
 *  阐述：
 *        * 执行源文件中的第一个类, 第一个类必须包含主方法。
 *        * 并且不可以使用别源文件中的自定义类, 本文件中的自定义类是可以使用的。
 *  @See <a href="http://openjdk.java.net/jeps/330">
 *  
 * </pre>
 * 
 * @author WGR
 */
public class _Simplify {
    
    public void testSimplify() {
        
        /*         OLD
         * 编译 javac Javastack.java 
         * 运行 java  Javastack
         */
        
        /*         NOW
         * 编译+运行 java Javastack.java 
         */
        
        
     
    }
    



}
