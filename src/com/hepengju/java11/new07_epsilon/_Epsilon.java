package com.hepengju.java11.new07_epsilon;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;



/**
 * JEP 318: Epsilon: A No-Op Garbage Collector
 * Epsilon垃圾收集器
 * 
 * <pre>
 *  引入名为Epsilon的垃圾收集器，该收集器不做任何垃圾回收，可用于性能测试、短生命周期的任务等.
 *  
 *  用法如下： 
 *            * -XX:+UseEpsilonGC  (程序很快就因为堆空间不足而退出)
 *  执行结果: 
 *            * Terminating due to java.lang.OutOfMemoryError: Java heap space。
 *  主要用途:
 *            * 性能测试(它可以帮助过滤掉GC引起的性能假象)
 *            * 内存压力测试
 *            * 非常短的JOB任务
 *            * VM接口测试
 * </pre>
 * @author WGR
 *
 */

public class _Epsilon {
    
    /**
     * 对Epsilon进行测试
     */
    @Test
    public void testEpsilon() {
        List<Garbage> list = new ArrayList<>();
        boolean flag = true;
        int count = 0;
        /*
         * 没有加-XX:+UseEpsilonGC时，会出现以前内容，最后出现java.lang.OutOfMemoryError.
         * 
         * com.hepengju.java11.new10_epsilon.Garbage@6227d4e5 collecting
         * com.hepengju.java11.new10_epsilon.Garbage@50952207 collecting
         * com.hepengju.java11.new10_epsilon.Garbage@28375449 collecting
         * com.hepengju.java11.new10_epsilon.Garbage@7204167b collecting
         */
        while (flag) {
            list.add(new Garbage());
            if (count++ == 500) {
                list.clear();
            }
        }
    }

}
