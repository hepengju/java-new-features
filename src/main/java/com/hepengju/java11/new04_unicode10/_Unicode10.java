package com.hepengju.java11.new04_unicode10;

import org.junit.Test;

/**
 * 
 * JEP 327: Unicode 10
 * Unicode 10.0 增强
 * 
 * <pre>
 *  @See <a href="https://www.jianshu.com/p/a3f95b8214f2">
 *  @See <a href="http://openjdk.java.net/jeps/327">
 * </pre>
 * 
 * @author WGR
 * 
 */
public class _Unicode10 {
    
    /**
     * 对Unicode10的测试
     */
    @Test
    public void testUnicode10() {
        String emoj = "\ud83d\ude02\ud83d\ude0d\ud83c\udf89\ud83d\udc4d";
        System.out.println(emoj); // 😂😍🎉👍
    }

}
