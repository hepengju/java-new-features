package com.hepengju.java14.new05_record;

import org.junit.Test;

/**
 * 记录类
 */
public class _Record {

    @Test
    public void testRecord() {
        Point p1 = new Point(10, 10);
        Point p2 = new Point(10, 10);

        System.out.println(p1);            // Point[x=10, y=10]
        System.out.println(p1 == p2);      // false
        System.out.println(p1.equals(p2)); // true
    }
}


record Point(int x, int y) {}

