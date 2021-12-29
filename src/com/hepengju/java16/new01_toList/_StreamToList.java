package com.hepengju.java16.new01_toList;

import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream流新增 toList 方法
 *
 * @see com.hepengju.java08.new04_streamAPI._StreamAPI
 */
public class _StreamToList {

    @Test
    public void testToList() {
        var srcList = List.of("a", "b", "c");                       // Java9新增的of方法
        var list01 = srcList.stream().collect(Collectors.toList()); // Java8的搜集方法
        var list02 = srcList.stream().toList();                     // Java16 简化的搜集方法, 注意: 结果是不可变的List

        System.out.println(list01);
        System.out.println(list02);

        list01.add("d"); // OK
        //list02.add("d"); // java.lang.UnsupportedOperationException
    }

    @Test
    public void testMapMulti01() {
        List<Number> numbers = List.of(1, 2.0, 3.3, 9999999999L, 0);
        List<Integer> integers = numbers.stream().<Integer>mapMulti((number, consumer) -> {
            if (number instanceof Integer i) consumer.accept(i);
        }).collect(Collectors.toList());
        System.out.println(integers); // [1, 0]
    }

    @Test
    public void testMapMulti02(){
        var nestedList = List.of(1, List.of(2, List.of(3, 4)), 5);
        List<Object> list = nestedList.stream().mapMulti(C::expandIterable).toList();
        System.out.println(list); // [1, 2, 3, 4, 5]
    }
}

class C {
    static void expandIterable(Object e, Consumer<Object> c) {
        if (e instanceof Iterable<?> elements) {
            for (Object ie : elements) {
                expandIterable(ie, c);
            }
        } else if (e != null) {
            c.accept(e);
        }
    }
}