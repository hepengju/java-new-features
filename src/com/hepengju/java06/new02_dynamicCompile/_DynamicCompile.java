package com.hepengju.java06.new02_dynamicCompile;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.junit.Test;

/**
 * 动态编译
 * 
 * <pre>
 * 
 *  场景:
 *      * JSP页面
 *      * 热部署/热加载: 
 *          - spring-boot-devtools
 *          - JRebel 
 *          - OSGI 架构
 *           
 * </pre>
 * 
 * @see <a href="https://www.cnblogs.com/flyoung2008/archive/2011/11/14/2249017.html">Java SE6调用动态编译</a>
 * @see <a href="https://www.cnblogs.com/chulung/p/5651172.html">java的热部署和热加载</a>
 * 
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
