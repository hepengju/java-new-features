package com.hepengju.java05.new11_other;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Test;

/**
 * JDK5的其他新特性
 * 
 * <pre>
 *  1. 支持%s %d等格式化输出
 *  2. StringBuilder
 *  3. Arrays工具类的toString,hashCode等
 *  4. Queue
 * </pre>
 * 
 * @author hepengju
 *
 */
public class _Other {

	/**
	 * 1. 支持%s %d等格式化输出
	 */
	@Test public void testPrintf() {
		System.out.printf("my name is %s, age is %d","hepengju", 28);
	}
	
	/**
	 * 2. StringBuilder
	 * 
	 * 一个可变的字符序列。
	 * 此类提供一个与 StringBuffer 兼容的 API，但不保证同步。
	 * 该类被设计用作 StringBuffer 的一个简易替换，用在字符串缓冲区被单个线程使用的时候（这种情况很普遍）。
	 * 如果可能，建议优先采用该类，因为在大多数实现中，它比 StringBuffer 要快。
	 * 
	 * 主要操作是 append 和 insert 方法
	 */
	@Test public void testStringBuilder() {
		StringBuilder sb = new StringBuilder();
		sb.append("I'm StringBuilder!")
		  .append("\t")
		  .append("I'm not thread safe, but faster than StringBuffer");
		
		sb.insert(4, "the ");
		
		System.out.println(sb.toString());
	}
	
	/**
	 * 3. Arrays工具类的toString,hashCode等
	 */
	@Test public void testArrays() {
		//toString, hashCode
		int[] arr = {3,2,5,6,9,1,2};
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr)); 
		System.out.println(Arrays.hashCode(arr));
		
		//deepToString, deepHashCode
		int[][] arr2 = {{1,2,3},{4,5,6}};
		System.out.println(Arrays.toString(arr2)); 
		System.out.println(Arrays.deepToString(arr2));
		System.out.println(Arrays.hashCode(arr2));
		System.out.println(Arrays.deepHashCode(arr2));
	}
	
	/**
	 * 4. Queue
	 * 
	 * 	    抛出异常	    返回特殊值
	 *	插入	add(e)	    offer(e)
	 *	移除	remove()	poll()
	 *	检查	element()	peek()
	 */
	@Test public void testQueue() {
		Queue<String> queue = new ArrayBlockingQueue<>(2);
		
		queue.add("xiaoming");
		queue.add("xiaoming");
		//queue.add("xiaoming");    //java.lang.IllegalStateException: Queue full
		
		boolean offer = queue.offer("xiaoming");
		System.out.println(offer); // 返回false
	}
}
