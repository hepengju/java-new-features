package com.hepengju.java06.new02_dynamicCompile;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.junit.Test;

/**
 * 动态编译
 * 
 * <pre>
 * 
 *  场景: 1. JSP页面
 * </pre>
 * 
 * @see <a href="https://www.cnblogs.com/flyoung2008/archive/2011/11/14/2249017.html">Java SE6调用动态编译</a>
 * @author hepengju
 *
 */
public class _DynamicCompile {

    /**
     * 动态编译示例01
     */
    @SuppressWarnings("unused")
    @Test public void testDynamicCompile01() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    }
}
