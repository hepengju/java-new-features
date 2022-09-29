package com.hepengju.java19.new03_newmapset;

import org.junit.Test;

import java.util.*;

/**
 * 新的创建Map和Set的静态方法（指定元素个数）
 */
public class _NewMapSet {

    /**
     * The int-argument constructors for these classes set the "capacity" (internal table size)
     * which is not the same as the number of elements that can be accommodated.
     * The capacity is related to the number of elements by a simple but error-prone calculation.
     * For this reason, programs should use these new static factory methods in preference to the int-argument constructors.
     */
    @Test
    @SuppressWarnings("unused")
    public void testHashMap(){
        // 构造函数传入的整数是设置”容量“（内部表大小）
        var oldMap = new HashMap<String, String>(1);

        // 容量与元素个数的关系：容量=Math.ceil(个数 / 负载因子) ，默认负载因子为0.75
        // 静态方法传入的整数是元素的个数 ==> 更推荐
        var newMap = HashMap.<String, String>newHashMap(1);
    }

    @Test
    public void testOther(){
        LinkedHashMap.newHashMap(10);
        WeakHashMap.newWeakHashMap(10);
        HashSet.newHashSet(10);
        LinkedHashSet.newLinkedHashSet(10);
    }
}
