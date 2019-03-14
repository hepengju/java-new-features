package com.hepengju.java05.new05_enum;

import java.util.EnumMap;
import java.util.EnumSet;
import org.junit.Test;

/**
 * 枚举
 * 
 * <pre>
 *  定义: 表示一组相同类型的常量。如：性别、日期、月份、颜色等, 是一种实现线程安全的单例模式的好方式
 *  说明: 创建枚举类型要使用 enum 关键字，隐含了所创建的类型都是 java.lang.Enum 类的子类（java.lang.Enum 是一个抽象类）。
 *       枚举类型的每一个值都将映射到 protected Enum(String name, int ordinal) 构造函数中，
 *       在这里，每个值的名称都被转换成一个字符串，并且序数设置表示了此值被创建的顺序。
 * 
 *  枚举集合: EnumSet/EnumMap  EnumSet保证集合中的enum不重复. EnumMap中的key是enum类型,而value则可以是任意类型
 *  枚举比较: 枚举类型对象之间的值比较,是可以使用==.直接来比较值是否相等的,不是必须使用equals方法 (java.lang.Enum.equals(Object)的方法内部就是==)
 *  枚举类型比较: 当且仅当 e1.getDeclaringClass() == e2.getDeclaringClass() 时，两个枚举常量 e1 和 e2 的枚举类型才相同
 *  
 *  特殊枚举: 
 * 		枚举类里面有构造方法: 构造方法里面有参数,需要在每个实例上面都写参数
 *  	枚举类里面有抽象方法: 在枚举的每个实例里面都重写这个抽象方法
 * </pre>
 * 
 * @see <a href="https://www.cnblogs.com/hyl8218/p/5088287.html">java enum(枚举)使用详解 + 总结</a>
 * @see <a href="https://www.cnblogs.com/kailejun/p/6624471.html">Java基础——枚举详解</a>
 * 
 * @see <a href="https://www.jianshu.com/p/c4aaab21eaea">java 枚举类 getClass和getDeclaringClass的区别</a>
 * @see <a href="https://stackoverflow.com/questions/5758660/java-enum-getdeclaringclass-vs-getclass">Java Enum getDeclaringClass vs getClass</a>

 * @author hepengju
 *
 */
public class _Enum {

	/**
	 * 枚举定义
	 * 
	 * @see ColorEnum   最简单的枚举
	 * @see WorkdayEnum 带参数的枚举
	 */
	@Test public void testEnumDefine() {}
	
	/**
	 * 枚举API方法
	 * 
     * <br> {@link java.lang.Enum#name()}                  获取枚举名称
     * <br> {@link java.lang.Enum#ordinal()}               获取枚举下标,从0开始
     * <br> {@link java.lang.Enum#valueOf(Class, String)}  获取枚举对象
	 * <br> {@link java.lang.Enum#getDeclaringClass()}     获取枚举类型Class --> 参考下面的方法(详细解释)
	 *     
     * <br> 以下两个方法不在api里面,编译的时候生成这两个方法
     * <br> 	values()         获取所有枚举对象数组
     * <br> 	valueOf(String)  转换为枚举对象
	 */
	@Test public void testEnumApi() {
		// 获取名称和下标
		WorkdayEnum mon = WorkdayEnum.MON;
		String name = mon.name();
		int ordinal = mon.ordinal();
		System.out.println("枚举名称: " + name + "," + "枚举下标: " + ordinal);
		
		// 获取枚举对象
		WorkdayEnum w01 = Enum.valueOf(WorkdayEnum.class, "MON");
		//WorkdayEnum w02 = Enum.valueOf(WorkdayEnum.class, "ZZZ"); //No enum constant com.hepengju.java05.new05_enum.WorkdayEnum.ZZZ
		System.out.println("枚举对象: " + w01);
		//System.out.println("枚举对象: " + w02);
		
		// 遍历
		WorkdayEnum[] values = WorkdayEnum.values();
		for (WorkdayEnum wd : values) {
			System.out.println(wd.name() + ": " + wd.getValue());
		}
		
		// 转换为枚举对象
		WorkdayEnum we01 = WorkdayEnum.valueOf("TUE");
		//WorkdayEnum we02 = WorkdayEnum.valueOf("ZZZ"); //No enum constant com.hepengju.java05.new05_enum.WorkdayEnum.ZZZ
		System.out.println("枚举对象: " + we01);
		//System.out.println("枚举对象: " + we02);
	}
	
	
	/**
	 * 测试枚举对象 枚举名称 枚举下标 之间的转换
	 */
	@Test public void testEnumNameOrdinal() {
		
		//枚举对象 --> 枚举名称,枚举下标
		WorkdayEnum wd = WorkdayEnum.MON;
		wd.name();
		wd.ordinal();
		
		//枚举名称 --> 枚举对象
		String name = "SUN";
		WorkdayEnum w01 = WorkdayEnum.valueOf(name);
		System.out.println(w01);
		
		//枚举下标 --> 枚举对象
		int i = 1;
		WorkdayEnum w02 = getWorkdayEnumByOrdinal(i);
		System.out.println(w02);
	}
	
	private WorkdayEnum getWorkdayEnumByOrdinal(int ordinal) {
		WorkdayEnum[] values = WorkdayEnum.values();
		if(ordinal < values.length ) {
			return values[ordinal];
		}
		
		//for (WorkdayEnum wd : WorkdayEnum.values()) {
		//	if(ordinal == wd.ordinal()) {
		//		return wd;
		//	}
		//}
		throw new IllegalArgumentException("No enum constant for ordinal " + ordinal);
	}


	/**
	 * 测试EnumSet
	 */
	@Test public void testEnumSet() {
		
		EnumSet<WorkdayEnum> e01 = EnumSet.allOf(WorkdayEnum.class);                //全部枚举值
		EnumSet<WorkdayEnum> e02 = EnumSet.noneOf(WorkdayEnum.class);               //空集合
		EnumSet<WorkdayEnum> e03 = EnumSet.of(WorkdayEnum.SAT, WorkdayEnum.SUN);    //指定元素
		EnumSet<WorkdayEnum> e04 = EnumSet.range(WorkdayEnum.MON, WorkdayEnum.FRI); //指定范围
		
		//complement 补充
		EnumSet<WorkdayEnum> e05 = EnumSet.complementOf(e04);                       //创建一个其元素类型与指定枚举 set 相同的枚举 set，最初包含指定set中所不包含的此类型的所有元素。
		EnumSet<WorkdayEnum> e06 = EnumSet.copyOf(e04);                             //创建一个其元素类型与指定枚举 set 相同的枚举 set，最初包含相同的元素（如果有的话）
		
		System.out.println(e01);  // [MON, TUE, WED, THU, FRI, SAT, SUN]
		System.out.println(e02);  // []
		System.out.println(e03);  // [SAT, SUN]
		System.out.println(e04);  // [MON, TUE, WED, THU, FRI]
		System.out.println(e05);  // [SAT, SUN]
		System.out.println(e06);  // [MON, TUE, WED, THU, FRI]
	}
	
	/**
	 * 测试EnumMap
	 */
	@Test public void testEnumMap() {
		EnumMap<WorkdayEnum, String> enumMap = new EnumMap<WorkdayEnum, String>(WorkdayEnum.class);
		enumMap.put(WorkdayEnum.MON, "孙悟空");
		enumMap.put(WorkdayEnum.TUE, "猪八戒");
		System.out.println(enumMap);  // {MON=孙悟空, TUE=猪八戒}
	}
	
	/**
	 * 测试getDeclaringClass方法
	 * 
     * <br> {@link java.lang.Enum#getDeclaringClass()}     获取枚举类型Class
     * <br>    	Enum#getDeclaringClass(): 返回与此枚举常量的枚举类型相对应的 Class 对象。
     * <br>                               当且仅当 e1.getDeclaringClass() == e2.getDeclaringClass() 时，两个枚举常量 e1 和 e2 的枚举类型才相同。（
     * <br>                               由该方法返回的值不同于由 Object.getClass() 方法返回的值，Object.getClass() 方法用于带有特定常量的类主体的枚举常量。）
     * <br>     Object#getClass: 返回此 Object 的运行时类。返回的 Class 对象是由所表示类的 static synchronized 方法锁定的对象。
	 * <br>                      实际结果类型是 Class<? extends |X|>，其中 |X| 表示清除表达式中的静态类型，该表达式调用 getClass。
	 * <br>
	 * 
     * @see <a href="https://www.jianshu.com/p/c4aaab21eaea">java 枚举类 getClass和getDeclaringClass的区别</a>
     * @see <a href="https://stackoverflow.com/questions/5758660/java-enum-getdeclaringclass-vs-getclass">Java Enum getDeclaringClass vs getClass</a>
     * 
	 */
	@Test public void testDeclareClass() {
		
		WorkdayEnum w01 = Enum.valueOf(WorkdayEnum.class, "MON");
		
		// 正常情况下取得的一致
		Class<? extends WorkdayEnum> clazz01 = w01.getClass();
		Class<WorkdayEnum> clazz02 = w01.getDeclaringClass();
		System.out.println(clazz01);            // class com.hepengju.java05.new05_enum.WorkdayEnum
		System.out.println(clazz02);            // class com.hepengju.java05.new05_enum.WorkdayEnum
		System.out.println(clazz01 == clazz02); // true
		
		// 当枚举含有抽象方法时, 枚举值必须实现这个方法, 枚举变为内部类了,就不相等了
		SpecialEnum one = SpecialEnum.ONE;
		Class<? extends SpecialEnum> c01 = one.getClass();
		Class<SpecialEnum> c02 = one.getDeclaringClass();
		System.out.println(c01);        // class com.hepengju.java05.new05_enum.SpecialEnum$1
		System.out.println(c02);        // class com.hepengju.java05.new05_enum.SpecialEnum
		System.out.println(c01 == c02); // false
	}
	

}
