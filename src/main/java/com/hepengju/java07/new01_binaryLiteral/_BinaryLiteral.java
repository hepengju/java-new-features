package com.hepengju.java07.new01_binaryLiteral;

import org.junit.Test;

/**
 * 二进制字面量
 * 
 * <pre>
 * 
 *  进制字面量: 
 *      * 二进制: 0b 或 0B 开头
 *      * 八进制: 0 开头
 *      * 十六进制: 0x 或 0X 开头
 *      
 * </pre>
 * 
 * @author hepengju
 *
 */
public class _BinaryLiteral {

    /**
     * 十进制字面量
     */
    @Test public void testOLiteral() {
        Integer m = 255;
        Integer n = 128;
        System.out.println("十进制: " + m); // 255
        System.out.println("十进制: " + n); // 128
        System.out.println("二进制: " + Integer.toBinaryString(m)); // 11111111
        System.out.println("二进制: " + Integer.toBinaryString(n)); // 10000000
        System.out.println("八进制: " + Integer.toOctalString(m));  // 377
        System.out.println("八进制: " + Integer.toOctalString(n));  // 200
        System.out.println("十六进制: " + Integer.toHexString(m));  // ff
        System.out.println("十六进制: " + Integer.toHexString(n));  // 80
    }
    
    /**
     * 二进制字面量: 0b 或 0B 开头
     */
    @Test public void testBinaryLiteral() {
        int m = 0b11111111;
        int n = 0B10000000;
        System.out.println(m); // 255
        System.out.println(n); // 128
    }
    
    /**
     * 八进制字面量: 0 开头
     */
    @Test public void testOctalLiteral() {
        int m = 0377;
        int n = 0200;
        System.out.println(m); // 255
        System.out.println(n); // 128
    }
    

    
    /**
     * 十六进制字面量: 0x 或 0X 开头
     */
    @Test public void testHexLiteral() {
        int m = 0xff;
        int n = 0X80;
        System.out.println(m); // 255
        System.out.println(n); // 128
    }
    
    
}
