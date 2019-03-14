package com.hepengju.java05.new04_staticimport;

import static java.lang.Math.min;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsFirst;
import static java.util.stream.Collectors.joining;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 静态导入
 * 
 * <pre>
 *  通过使用 import static 就可以不用指定类名而直接使用静态成员,包括静态方法和常量
 * 
 *  区别: import xxxx 和 import static xxxx
 *     前者一般导入的是类文件   import java.util.Scanner
 *     后者一般是导入静态的方法 import static java.lang.System.out
 * <pre>
 * 
 * @author hepengju
 *
 */
public class _StaticImport {

	/**
	 * 静态方法
	 */
	@Test public void testMethod() {
		//int m = Math.min(10, 20);
		int m = min(10, 20);    // import static java.lang.Math.min;
		System.out.println(m);  // 10
	}
	
	/**
	 * 静态常量
	 */
	@Test public void testConstant() {
		//Charset utf8 = StandardCharsets.UTF_8;
		Charset utf8 = UTF_8;        // import static java.nio.charset.StandardCharsets.UTF_8;
		System.out.println(utf8);    // UTF-8
	}
	
	/**
	 * 在Jdk8的Stream流处理中使用静态导入, 极大的减少代码量, 提高代码的可读性
	 */
	@Test public void testStreamInJDK8(){
        List<String> list = Arrays.asList("bbbb", null, "zz", "abc");
	    //String result = list.stream().sorted(Comparator.nullsFirst(Comparator.naturalOrder())).collect(Collectors.joining(","));
	    /*
	     * import static java.util.Comparator.naturalOrder;
         * import static java.util.Comparator.nullsFirst;
         * import static java.util.stream.Collectors.joining;
	     */
	    String result = list.stream().sorted(nullsFirst(naturalOrder())).collect(joining(","));
	    System.out.println(result); // null,abc,bbbb,zz
	}
}
