package com.hepengju.java12.new02_JVMConstantsAPI;

import org.junit.Test;

import java.lang.constant.ClassDesc;

/**
 * JVM 常量 API:
 *
 * <pre>
 *      Java 12 中引入 JVM 常量 API，用来更容易地对关键类文件 (key class-file) 和运行时构件（run-time artifacts）的名义描述
 * (nominal description) 进行建模，特别是对那些从常量池加载的常量，这是一项非常技术性的变化，能够以更简单、标准的方式处理可加载常量。
 *      具体来说就是java.base模块新增了java.lang.constant包（而非 java.lang.invoke.constant ）。包中定义了一系列基
 * 于值的符号引用（JVMS 5.1）类型，它们能够描述每种可加载常量。
 *
 *      - ConstantDesc: ClassDesc、MethodTypeDesc、MethodHandleDesc
 *      - Constable
 *
 *      String、Integer、Long、Float、Double均实现了这两个接口，而EnumDesc实现了ConstantDesc接口
 * </pre>
 *
 * @see <a href="http://cr.openjdk.java.net/~iris/se/12/latestSpec/api/java.base/java/lang/constant/package-summary.html">java.lang.constant</a>
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-5.html">jvms-5</a>
 * @see <a href="http://openjdk.java.net/jeps/334">JVM Constants API</a>
 *
 * @author hepengju
 */
public class _JVMConstantsAPI {

    @Test public void testConstantAPI(){
        // TODO 备注: 不是很理解, 从JEP334看好像是指class文件的解析和生成等情况下使用的
        ClassDesc jvmApi = ClassDesc.of("com.hepengju.java12.new02_JVMConstantsAPI", "_JVMConstantsAPI");
        System.out.println(jvmApi);
    }
}
