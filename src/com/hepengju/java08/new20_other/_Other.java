package com.hepengju.java08.new20_other;

import org.junit.Test;

/**
 * 其他改进
 * 
 * <pre>
 *  HashMap数据结构的改善
 *      之前: 数组 + 链表
 *      现在: 数组 + 链表 + 红黑树
 * </pre>
 * 
 * @author hepengju
 */

public class _Other {

    /**
     * char和unicode的最值
     * 
     * <pre>
     *  char   : min[0], max[65535]
     *  unicode: min[0], max[1114111]
     * </pre>
     */
    @Test public void printMinMax() {
        System.out.printf("char   : min[%d], max[%d]", (int)Character.MIN_VALUE, (int)Character.MAX_VALUE);
        System.out.println();
        System.out.printf("unicode: min[%d], max[%d]", Character.MIN_CODE_POINT,Character.MAX_CODE_POINT);
    }
    
    /**
     * 打印所有char字符
     */
    @Test public void printAllChar() {
        System.out.println("all char: " + (Character.MAX_VALUE - Character.MIN_VALUE));
        for (char c = Character.MIN_VALUE; c < Character.MAX_VALUE; c++) {
            System.out.print(Character.toString(c) + ",");
            if(c % 100 == 0)  System.out.println();
        }
    }
    
    /**
     * 打印所有Unicode字符
     */
    @Test public void printAllUnicode() {
        System.out.println("all unicode: " + (Character.MAX_CODE_POINT - Character.MIN_CODE_POINT));
        for (int i = Character.MIN_CODE_POINT; i < Character.MAX_CODE_POINT; i++) {
            System.out.print(Character.toString(i) + ",");
            if(i % 100 == 0)  System.out.println();
        }    
    }

}
