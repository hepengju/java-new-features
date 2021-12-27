package com.hepengju.java09.new02_jshell;

import org.junit.Test;

/**
 * Java9拥有 REPL工具:Jshell
 * 
 * <pre>
 *  设计理念：即写即得
 *  实现目标：
 *         * 利用jShell在没有创建类的情况下直接声明变量，计算表达式，执行语句。
 *         * 可以从文件中加载语句或者将语句保存到文件中。
 *         * 可以是tab键进行自动补全和自动添加分号。   
 *  使用举例：
 *         * 基本使用
 *         * 导入指定的包
 *         * 默认已经导入如下的所有包
 *         * 只需按下Tab键，就能自动补全代码
 *         * 列出当前session里所有有效的代码片段
 *         * 使用外部代码编辑器来编写Java代码
 *         * 使用/open命令调     
 *  
 * </pre>
 * 
 * @author WGR
 *
 */
public class _Jshell {
    
    /**
     * 调用Jshell
     */
    @Test
    public void testJshell() {
        
        /*jshell> System.out.println("hello world");
        hello world
        
        jshell> int i =10;
        i ==> 10
        jshell> int j =20;
        j ==> 20
        jshell> int k = i + j;
        k ==> 30
        jshell> System.out.println(k);
        30
        jshell> public void add(int a,int b,int c){System.out.println(a + b +c);}
        |  已创建 方法 add(int,int,int)
        jshell> add(10,20,30);
        60
        jshell> /imports
        |    import java.io.*
        |    import java.math.*
        |    import java.net.*
        |    import java.nio.file.*
        |    import java.util.*
        |    import java.util.concurrent.*
        |    import java.util.function.*
        |    import java.util.prefs.*
        |    import java.util.regex.*
        |    import java.util.stream.*
        
        jshell> /list
        
           1 : System.out.println("hello world");
           2 : int i =10;
           3 : int j =20;
           4 : int k = i + j;
           5 : System.out.println(k);
           6 : public void add(int i,int j){}
           7 : public void add(int a,int b,int c){System.out.println(a + b +c);}
           8 : add(10,20,30);
        
        jshell> /open E:\workspaceide\HelloWorld.java
        Javad 新特性！
        jshell>
        */
        
    }

}
