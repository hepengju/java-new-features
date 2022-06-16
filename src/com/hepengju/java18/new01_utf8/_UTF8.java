package com.hepengju.java18.new01_utf8;

import org.junit.Test;

import java.nio.charset.Charset;

/**
 * 指定 UTF-8 作为标准 Java API 的默认字符集
 *
 * <pre>
 * 在 JDK 17 及更早版本中，默认字符集是在 Java 虚拟机运行时才确定的，取决于不同的操作系统、区域设置等因素，因此存在潜在的风险。
 * 从这个版本开始，依赖于默认字符集的 API 会在所有实现、操作系统、语言环境和配置中保持一致。
 *
 * 可以使用以下命令查看当前 JDK 的默认字符集：
 *      java -XshowSettings:properties -version | grep file.encoding
 *
 *      $JDK18_HOME/bin/java -XshowSettings:properties -version | grep file.encoding
 *          file.encoding = UTF-8
 *      $JDK17_HOME/bin/java -XshowSettings:properties -version | grep file.encoding
 *          file.encoding = GBK  (Windows简体中文版)
 * </pre>
 */
public class _UTF8 {

    @Test
    public void testUTF8() {
        System.out.println(System.getProperty("native.encoding")); // GBK
        System.out.println(System.getProperty("file.encoding"));   // UTF-8
        System.out.println(Charset.defaultCharset());              // UTF-8
    }
}
