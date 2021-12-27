package com.hepengju.java11.new10_lowoverhead;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * JEP 331: Low-Overhead Heap Profiling
 * 对低消耗堆分析
 * 
 * @See <a href="https://www.jianshu.com/p/a3f95b8214f2">
 * @See <a href="http://openjdk.java.net/jeps/331">
 * 
 * @author WGR
 *
 */
public class _LowOverhead {
    
    /**
     * 测试方法
     */
    @Test
    public void testLowOverhead() {
        TimeUnit tu = TimeUnit.DAYS;
        // 将 50 小时转换为天数
        System.out.println(tu.convert(Duration.ofHours(50))); // 2
    }
}
