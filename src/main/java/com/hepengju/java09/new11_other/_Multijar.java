package com.hepengju.java09.new11_other;

import org.junit.Test;

/**
 * 多版本兼容jar包
 * 
 * <pre>
 * 
 *  使用说明：多版本兼容jar功能能让你创建仅在特定版本的Java环境中运行库程序选择使用的class版本。
 *  
 * </pre>
 * 
 * @author WGR
 *
 */
public class _Multijar {
    
    /**
     * 根据不同的jdk，执行不同的class版本
     * 当环境为jdk8时，输出：Generated strings: [Java, 8]
     * 当环境为jdk9时，输出：Generated strings: [Java, 9]
     */
    @Test
    public void testMultijatr() {
        //Application.testMultiJar();  
    }

}
