package com.hepengju.java09.new07_stream;

import java.util.stream.Stream;

import org.junit.Test;

/**
 * Java9对Stream流的增强
 * 
 * <pre>
 *  新增流的方法：
 *                * 引入ofNullable方法
 *                * 新增takewhile和dropwhile方法
 *                * iterate(种子 、判定器 、单目运算)的有限迭代
 * </pre>
 *                
 * @author WGR
 * 
 */

public class _Stream {
   
   /**
    * 新增方法iterate(种子 、判定器 、单目运算)的有限迭代
    * iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next)
    */
   @Test
   public void testIterate() {
       //没有新方法时，需要添加limit进行限制
       Stream<Integer> stream1 = Stream.iterate(1, t -> (2 * t) + 1);
       stream1.limit(10).forEach(System.out::println);
       
       //有限的迭代
       Stream<Integer> stream2 = Stream.iterate(1, t -> t < 1000, t -> (2 * t) + 1);
       stream2.forEach(System.out::println);
   }
   
   
   /**
    * 新方法,dropWhile
    * 从流中一直获取元素,遇到真的元素就drop，一旦遇到假的，就取剩下部分。
    */
   @Test
   public void testDropWhile() {
       Stream<Integer>  stream1 = Stream.of(3, 9, 20, 22, 40, 7);
       Stream<Integer> stream3 = stream1.dropWhile(t -> t % 2 != 0);
       stream3.forEach(System.out::println); // 20 22 40 7
       
   }
   
   
   /**
    * 新方法,takeWhile
    * 从流中一直获取判定器为真的元素, 一旦遇到元素为假, 就终止处理。
    */
   @Test
   public void testTakeWhile() {
       Stream<Integer> stream1 = Stream.of(3, 9, 20, 22, 40, 7);
       Stream<Integer> stream2 = stream1.takeWhile(t -> t % 2 != 0);
       stream2.forEach(System.out::println); // 3 9
       
   }
   
   
   /**
    * 引入ofNullable方法，避免空指针异常
    */
   @Test
   public void testOfNullable() {
       //传入null会被解析为是一个数组对象, 会进一步访问它的长度信息
       //Stream<Object> stream3 = Stream.of(null);
       //stream3.forEach(System.out::println);
       
       Stream<Object> stream3 = Stream.ofNullable(null);
       stream3.forEach(System.out::println);
       
       
   }
       

}
