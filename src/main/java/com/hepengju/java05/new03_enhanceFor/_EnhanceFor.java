package com.hepengju.java05.new03_enhanceFor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

/**
 * 
 * 增强for循环
 * 
 * <pre>
 *  场景: 数组,实现 Iterable 接口的集合
 *  目的: 替代迭代器 (底层就是使用迭代器实现的)
 *  
 *  缺点: 1.不能遍历同时获取index
 *       2.不能集合逗号拼接时去掉最后一个
 *       3.不能遍历的同时删除元素
 * </pre>
 * 
 * @author hepengju
 *
 */
public class _EnhanceFor {

	/**
	 * 遍历数组
	 */
	@Test public void testArray() {
		int[] arr = {1,2,3,4,5};
		for (int i : arr) {
			System.out.println(i);
		}
	}

    /**
     * 新添加的Iterable接口
     * 
     * @see java.lang.Iterable<T>
     */
    @Test public void testIterable() {
        List<Integer> list = Arrays.asList(1,2,3,4);
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println(next);
        }
    }
    
	/**
	 * 遍历Iterable接口的集合
	 */
	@Test public void testList() {
		List<Integer> list = Arrays.asList(1,2,3,4);
		for (int i : list) {
			System.out.println(i);
		}
	}
	
}

