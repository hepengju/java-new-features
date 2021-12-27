package com.hepengju.java14.new01_instanceof;

import org.junit.Test;

/**
 * instanceof模式匹配(预览)
 *
 * <pre>
 *     说明: 通过对 instanceof 运算符进行模式匹配来增强Java编程语言
 *     优点: 更简洁, 更安全
 * </pre>
 */
public class _Instanceof {

    private Object obj = "Java";

    /**
     * 旧版写法
     */
    @Test
    public void testOld() {
        if (obj instanceof String) {
            String str = (String) obj;
            System.out.println("旧版写法结果: " + str.toUpperCase());
        } else {
            System.out.println("not string: " + obj.hashCode());
        }
    }

    @Test
    public void testNew() {
        if (obj instanceof String str) {
            System.out.println("新版写法结果: " + str.toUpperCase());
        } else {
            System.out.println("not string: " + obj.hashCode());
        }
    }
}
