package com.hepengju.java05.new01_autoboxing;

import org.junit.Test;

/**
 * 自动拆装箱(Autoboxing/Unboxing): 基本数据类型和包装类之间的自动转换
 * 
 * <pre>
 *  自动装箱：基本类型 --> 包装类型, 通过调用valueOf方法实现(可能从缓存中获取)
 *  自动拆箱：包装类型 --> 基本类型, 通过调用包装器的 xxxValue方法实现（如Integer.intValue(a)）
 * 
 *  对应: 基本数据类型对应的包装器类型
 *      byte（1字节）       Byte
 *      short（2字节）      Short	
 *      int（4字节）        Integer
 *      long（8字节）       Long
 *      float（4字节）      Float
 *      double（8字节）     Double
 *      char（2字节）       Character
 *      boolean（未定）     Boolean
 *  
 *  缓存: Integer、Short、Byte、Character、Long 包装类型有缓存机制（cache数组）
 *       Boolean类型有TRUE 和 FALSE两个静态成员
 *       Double和Float没有缓存机制, 因为它们是不连续的
 * 
 *  注意: 1. 我们知道，"=="运算符既可以用来比较基本类型变量和引用类型变量。当两个操作数都是包装器类型的变量时，判定标准为他们是否指向同一个对象；
 *       而如果其中有一个操作数是表达式（即包含算术运算）则会先进行自动拆箱，再进行对应基本类型变量比较。
 *       2. 包装类型重写了equals方法，包装类型的equals不会进行类型的转换，类型不同的包装类型的变量直接被判定为false，尽管他们的数值有可能是相等的。
 * </pre>
 * 
 * @see AutoboxingJavap 反编译查看自动拆装箱的本质
 * @see <a href="https://www.cnblogs.com/qcblog/p/7670159.html">关于java的自动拆装箱若干细节问题</a>
 * @see <a href="https://www.cnblogs.com/dolphin0520/p/3780005.html">深入剖析Java中的装箱和拆箱</a>
 * @see <a href="https://www.jianshu.com/p/2f663dc820d0">真的知道Java中boolean类型占用多少个字节吗?</a>
 * 
 * @author hepengju
 *
 */
public class _Autoboxing {
	
	/**
	 * 自动装箱
	 */
	@Test public void testAutoboxing() {
		@SuppressWarnings("unused")
		Integer i = 10;
	}
	
	/**
	 * 自动拆箱
	 */
	@Test public void testUnboxing() {
		@SuppressWarnings("unused")
		int i = Integer.valueOf(10);
	}
	
	/**
	 * 缓存机制(-128到127)
	 * 
	 * @see Integer#valueOf(int)
	 * @see Short#valueOf(short)
	 * @see Byte#valueOf(byte)
	 * @see Character#valueOf(char)
	 * @see Long#valueOf(long)
	 */
	@Test public void testIntegerCache() {
		Integer m = 100;
		Integer n = 100;
		Integer x = 300;
		Integer y = 300;
		
		System.out.println(m == n); // true
		System.out.println(x == y); // false
	}
	
	/**
	 * 自动拆箱的空指针异常示例
	 */
	@Test public void testNullPoint() {
	    printInt(10);
	    printInt(null);  // java.lang.NullPointerException
	}
	
	private void printInt(Integer m) {
	    int n = m;
	    System.out.println(n);
	}
	
}
