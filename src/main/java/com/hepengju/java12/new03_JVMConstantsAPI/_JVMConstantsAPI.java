package com.hepengju.java12.new03_JVMConstantsAPI;

import org.junit.Test;

/**
 * JVM 常量 API:
 *
 * <pre>
 *     引入 API 来对关键类文件（key class-file）和运行时工件（run-time artifacts）的名义描述（nominal descriptions）进行建模，
 * 特别是可从常量池加载的常量。
 *     在新的 java.lang.invoke.constant 包中定义了一系列基于值的符号引用（JVMS 5.1）类型，它们能够描述每种可加载常量。
 *     符号引用以纯 nominal 形式描述可加载常量，与类加载或可访问性上下文区分开。有些类可以作为自己的符号引用（例如 String），
 * 而对于可链接常量，定义了一系列符号引用类型（ClassDesc、MethodTypeDesc、MethodHandleDesc 和 DynamicConstantDesc），
 * 它们包含描述这些常量的 nominal 信息。
 * </pre>
 *
 * TODO 没看明白, 晚点再弄
 *
 * @see <a href="https://blog.csdn.net/zhenghhgz/article/details/88735827">JDK 12 正式发布，八大重大更新及部分代码示例</a>
 *
 * @author hepengju
 */
public class _JVMConstantsAPI {

    @Test public void test01(){
        System.out.println("later");
    }
}
