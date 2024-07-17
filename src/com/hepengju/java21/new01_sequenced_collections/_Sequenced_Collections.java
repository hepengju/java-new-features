package com.hepengju.java21.new01_sequenced_collections;

import org.junit.Test;

import java.util.*;

/**
 * 有序集合
 */
public class _Sequenced_Collections {

    /**
     *  @see java.util.SequencedCollection
     */
    @Test
    public void testSequencedCollection() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list); // [a, b, c]

        // 获取
        System.out.println(list.get(0) + ":" + list.get(list.size() - 1));  // a:c
        System.out.println(list.getFirst() + ":" + list.getLast()); // a:c

        // 添加
        list.addFirst("newFirst");
        list.addLast("newLast");
        System.out.println(list); // [newFirst, a, b, c, newLast]

        // 删除
        list.removeFirst();
        list.removeLast();
        System.out.println(list); // [a, b, c]

        // 翻转
        List<String> reversed = list.reversed();
        System.out.println(reversed); // [c, b, a]
    }

    /**
     * @see java.util.SequencedSet
     */
    @Test
    public void testSequencedSet() {
        SequencedSet<String> set = new LinkedHashSet();
        set.add("a");
        set.add("b");
        set.add("c");

        System.out.println(set); // [a, b, c]

        // 获取
        System.out.println(set.getFirst()); // a
        System.out.println(set.getLast());  // c

        // 添加
        set.addFirst("newFirst");
        set.addLast("newLast");
        System.out.println(set); // [newFirst, a, b, c, newLast]

        // 删除
        set.removeFirst();
        set.removeLast();
        System.out.println(set); // [a, b, c]

        // 翻转
        SequencedSet reversed = set.reversed();
        System.out.println(reversed); // [c, b, a]
    }

    /**
     * @see java.util.SequencedMap
     */
    @Test
    public void testSequencedMap() {
        SequencedMap<String, Integer> map = new LinkedHashMap<>();
        map.put("src", 9);

        // put首尾
        map.putFirst("a", 1);
        map.putLast("c", 3);
        System.out.println(map);              // {a=1, src=9, c=3}

        // entry首尾
        System.out.println(map.firstEntry()); // a=1
        System.out.println(map.lastEntry());  // c=3

        // 翻转, keySet, values, entrySet
        System.out.println(map.reversed());          // {c=3, src=9, a=1}
        System.out.println(map.sequencedKeySet());   // [a, src, c]
        System.out.println(map.sequencedValues());   // [1, 9, 3]
        System.out.println(map.sequencedEntrySet()); // [a=1, src=9, c=3]
    }

}
