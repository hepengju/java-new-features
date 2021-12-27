package com.hepengju.java09.new06_collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * 不可变集合工厂方法
 * 
 * <pre>
 *  说明：
 *        * Java9增加了List.of()、Set.of()、Map.of()和Map.ofEntries()等工厂方法来创建不可变集合。
 * </pre>
 * 
 * @author WGR
 *
 */
public class _Collection {
    
    /**
     * jdk9 中：创建一个只读特点的集合
     * 创建的集合，使用add都会报错：UnsupportedOperationException
     */
    @SuppressWarnings("unused")
    @Test
    public void testCollection2(){
        List<Integer> list = List.of(1, 2, 3);
        list.forEach(System.out::println); // 1 2 3

        Set<Integer> set = Set.of(2, 3, 4);
        set.forEach(System.out::println);  // 2 3 4
        
        //创建只读集合的方式一：
        Map<String, Integer> map = Map.of("Tom", 23, "Jerry", 22, "Lilei", 12, "HanMeimei", 18);

        //创建只读集合的方式二：
        Map<String, Integer> map1 = Map.ofEntries(Map.entry("Tom", 23), Map.entry("Jerry", 21));

        System.out.println(map1); // {Tom=23, Jerry=21}

    }
    
    /**
     * jdk 8 以及之前：创建一个只读特点的集合
     */
    @SuppressWarnings({ "serial", "unused" })
    @Test
    public void testCollection1(){
        List<String> list = new ArrayList<>();
        list.add("Tom");
        list.add("Jerry");
        list.add("Lilei");
        list.add("HanMeimei");

        //调用Collections中的方法，将list变为只读的
        List<String> newList = Collections.unmodifiableList(list);
        //newList.add("Tim");//不能执行，否则报异常
        //遍历：jdk 8
        newList.forEach(System.out::println);
        
        List<Integer> list1 = Collections.unmodifiableList(Arrays.asList(1, 2, 3));

        Set<Integer> set = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(1, 2, 3)));

        Map<Object, Object> map = Collections.unmodifiableMap(new HashMap<>() {
            {
                put("Tom", 78);
                put("Jerry", 88);
                put("Tim", 68);
            }
        });

        map.forEach((k,v) -> System.out.println(k + ":" + v));
    }
      

}
