package com.hepengju.java21.new02_record_patterns;

import org.junit.Test;

/**
 * 记录模式
 */
public class _RecordPatterns {

    @Test
    public void testBase() {
        Point point = new Point(1, 2);
        printSumJava16(point);
        printSumJava21(point);
    }

    void printSumJava16(Object obj) {
        if (obj instanceof Point p) {
            int x = p.x();
            int y = p.y();
            System.out.println(x + y);
        }
    }

    void printSumJava21(Object obj) {
        if (obj instanceof Point (int x, int y)) { // 模式匹配解构（支持深层次嵌套）
            System.out.println(x + y);
        }
    }


    @Test
    public void testType() {
        Pair pair = new Pair(1, 2);

        if (pair instanceof Pair(String s, String t)) {
            System.out.println(s + ", " + t);
        } else {
            System.out.println("Not a pair of Strings: " + pair); // Not a pair of Strings: Pair[x=1, y=2]
        }
    }
}


record Point(int x, int y) {}
record Pair(Object x, Object y) {}