package com.hepengju.java05.new06_generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

/**
 * 泛型: 参数化类型
 * 
 * <pre>
 *  泛型: 为了运行时期不出现类型异常,可以在定义容器时,就明确容器中元素的类型.
 *    JDK1.5之前: 容器什么类型的对象都可以存储. 但是在取出时,需要用到对象的特有内容时,需要做向下转型. 此时如果对象的类型不一致,就会导致ClassCastException异常.为了避免这个问题,只能主观上控制,往集合中存储的对象类型保存一致.
 *    JDK1.5之后: 解决了该问题,在定义集合时就直接明确集合中存储元素的具体类型,这样编译器在编译时,就可以对集合中存储的对象类型进行检查,一旦发现类型不匹配,就编译失败,这个技术就是泛型技术.
 *  好处: 1.将运行时期的问题转移到了编译时期,可以更好的让程序员发现问题并解决问题
 *        2.避免了向下转型的麻烦
 *  总结: 泛型就是应用在编译时期的一项安全机制
 * 
 *  泛型的擦除: 编译器通过泛型对元素类型进行检查,只要通过检查就会生成class文件,但是在class文件中就将泛型标识去掉了
 *  泛型的应用: 泛型类, 泛型接口, 泛型方法
 *  泛型通配符: 在不明确具体类型的情况下,可以使用通配符"?"来表示任意类型
 *  泛型的限定: <? extends E>: E类型或者E的子类型  (泛型上限)
 *            <? super E > : E类型或者E的父类型  (泛型下限)
 * 
 *  什么时候用到上限呢? 往容器存入
 *      一般往集合存储元素时,如果集合定义了E类型通常情况下应该存储E类型的对象,对于E的子类型的对象E类型也可以接受,所以这时可以将泛型从E改为? extends E
 *  什么时候用到下限呢? 从容器取出
 *      当从容器中取出元素操作时,可以用E类型接收,也可以用E的父类型接收
 *  
 *  演示泛型在API中的体现,
 *  	TreeSet的构造函数 
 *  	TreeSet(Collection<? extends E> coll)       E或E的子类都可以添加进去,返回的是E的集合
 *  	TreeSet(Comparator<? super E> comparator)   E或E的父类实现比较接口都可以
 *  
 *  注意:
 *      1. 泛型的类型参数只能是类类型，不能是简单类型。
 *      2. 不能对确切的泛型类型使用instanceof操作,例如 if(g01 instanceof Generic<String>){ } 
 *  
 *  常见泛型字母:
 *      * E - Element (在集合中使用，因为集合中存放的是元素)
 *      * T - Type（Java 类）
 *      * K - Key（键）
 *      * V - Value（值）
 *      * N - Number（数值类型）
 *      * ? - 表示不确定的java类型
 *      * S、U、V  - 2nd、3rd、4th types
 *      * R - 返回值
 *  
 *      
 *  反射获取泛型信息:
 *      * 获取父类的泛型步骤思路
 *          1. Class clazz         = getClass()                 获取本类
 *          2. Type  type          = getGenericSuperClass()     获取带有泛型的父类
 *          3. ParameterizedType p = (ParameterizedType)type    已知其为参数化类型(泛型)
 *          4. Type[] types        = p.getActualTypeArguments() 获取参数化类型的数组, 泛型可能有多个
 *      * 场景: public interface UserMapper extends OssMapper<User>{}
 *      
 *  备注: 
 *      1. Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
 *      
 *  疑问与待做: TODO 
 *      1. 泛型编译后已经擦除,如何通过反射获取到泛型<>中的类信息的?
 *      2. 泛型数组?  list.toArray(T[] arr)
 *      3. 泛型类型的反射获取
 * </pre>
 * 
 * @see <a href="https://www.cnblogs.com/coprince/p/8603492.html">java 泛型详解-绝对是对泛型方法讲解最详细的，没有之一</a>
 * @author hepengju
 *
 */
public class _Generic {

	/**
	 * 为什么需要泛型
	 */
	@Test public void testWhyNeedGeneric() {
		
		/*
		//不使用泛型时的类型转换异常: ClassCastException
		List<Object> list = new ArrayList<>();
		list.add("name");
		list.add(100);
		
		for (int i = 0; i < list.size(); i++) {
			String str = (String) list.get(i); // java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.String
			System.out.println(str);
		}
		*/
		
		//解决: 加入泛型, 编译时就可以发现问题
		List<String> newlist = new ArrayList<>();
		newlist.add("name");
		//newlist.add(100); //The method add(int, String) in the type List<String> is not applicable for the arguments (int)
		newlist.add("age");
		
		for (int i = 0; i < newlist.size(); i++) {
			String str = newlist.get(i); 
			System.out.println(str);
		}		
	}

	/**
	 * 泛型擦除: 泛型只在编译阶段有效(不同的泛型类编译后的class相同)
	 * 
	 * <br> 在编译之后程序会采取去泛型化的措施。也就是说Java中的泛型，只在编译阶段有效。
	 * <br> 在编译过程中，正确检验泛型结果后，会将泛型的相关信息擦除，并且在对象进入和离开方法的边界处添加类型检查和类型转换的方法。
	 * <br> 也就是说，泛型信息不会进入到运行时阶段。
	 * 
	 * <br> 总结: 泛型类型在逻辑上可以看成是多个不同的类型，实际上都是相同的基本类型
	 */
	@Test public void testGenericCompile() {
		List<String> strList = new ArrayList<String>();
		List<Integer> intList = new ArrayList<Integer>();

		Class<?> clazzStrList = strList.getClass();
		Class<?> clazzIntList = intList.getClass();

		System.out.println(clazzStrList == clazzIntList);      // true
		System.out.println(clazzStrList.equals(clazzIntList)); // true
	}
	
	
	/**
	 * 泛型类
	 * 
	 * <br> 泛型类型用于类的定义中，被称为泛型类。通过泛型可以完成对一组类的操作对外开放相同的接口。最典型的就是各种容器类，如：List、Set、Map
	 * 
	 * <br> 定义的泛型类，就一定要传入泛型类型实参么？并不是这样，
	 * <br> 	在使用泛型的时候如果传入泛型实参，则会根据传入的泛型实参做相应的限制，此时泛型才会起到本应起到的限制作用。
	 * <br> 	如果不传入泛型类型实参的话，在泛型类中使用泛型的方法或成员变量定义的类型可以为任何的类型。
	 * 
	 * <br> 注意：
	 * <br> 	泛型的类型参数只能是类类型，不能是简单类型。
	 * <br> 	不能对确切的泛型类型使用instanceof操作。如下面的操作是非法的，编译时会出错。
	 * <br>     if(g01 instanceof Generic<String>){ } 
	 * 
	 * @see Generic 泛型类
	 */
	@Test public void testGenericClass() {
		Generic<String> g01 = new Generic<>("name"); 
		String k01 = g01.getKey();
		System.out.println("key is " + k01);         // key is name
		
		Generic<Integer> g02 = new Generic<>(180); 
		Integer k02 = g02.getKey();
		System.out.println("key is " + k02);         // key is 180
		
		//不传入泛型
		@SuppressWarnings({ "rawtypes", "unchecked" })
		Generic g03 = new Generic(true);
		System.out.println(g03.getKey());            // true
		System.out.println(g03.getKey().getClass()); // class java.lang.Boolean
		
		//Cannot perform instanceof check against parameterized type Generic<String>. 
		//Use the form Generic<?> instead since further generic type information will be erased at runtime
		//if(g01 instanceof Generic<String>){ } 
	}

	/**
	 * 泛型接口
	 * 
	 * @see Generator         泛型接口
	 * @see Fruit01Generator  泛型接口的实现类01, 不传入具体类型
	 * @see Fruit02Generator  泛型接口的实现类01, 传入具体类型
	 */
	@Test public void testGenericInteface() {}
	
	/**
	 * 泛型通配符?
	 * 
	 * <br> Generic<Integer>不能被看作为Generic<Number>的子类
	 * <br> 我们需要一个在逻辑上可以表示同时是Generic<Integer>和Generic<Number>父类的引用类型, 由此类型通配符应运而生
	 */
	@Test public void testGenericWildcard() {
		Generic<Number>  g01 = new Generic<>(100);
		Generic<Integer> g02 = new Generic<>(200);
		
		showKeyValue01(g01);
		//showKeyValue01(g02);//The method showKeyValue(Generic<Number>) in the type GenericDemo is not applicable for the arguments (Generic<Integer>)
		showKeyValue02(g01);  //key is: 100
		showKeyValue02(g02);  //key is: 200
	}
	
	private void showKeyValue01(Generic<Number> obj) {
		System.out.println("key is: " + obj.getKey());
	}
	private void showKeyValue02(Generic<?> obj) {  //此处的?和Number、String、Integer一样都是一种实际的类型,可以把看成所有类型的父类,是一种真实的类型
		System.out.println("key is: " + obj.getKey());
	}
	
	
	/**
	 * 泛型方法
	 * 
	 * <br> 泛型类:  是在实例化类的时候指明泛型的具体类型
	 * <br> 泛型方法: 是在调用方法的时候指明泛型的具体类型
	 * 
	 * @see GenericMethodBase    泛型方法基本用法
	 * @see GenericMethodInClass 类中的泛型方法
	 * @see printlnAll()        泛型方法与可变参数
	 *                              JDK参考: Arrays.asList(T... a)
	 *                                      Collections.addAll(Collection<? super T> c, T... elements
	 *                                      EnumSet.of(E first, E... rest)
	 *                              更多参考: http://www.bubuko.com/infodetail-2496122.html
	 * @see #showTStatic         静态方法与泛型
	 *                              静态方法无法访问类上定义的泛型；如果静态方法操作的引用数据类型不确定的时候，必须要将泛型定义在方法上。
	 */
	@Test public void testGenericMethod() {
		// 泛型方法与可变参数
		// printlnAll("123", "abc", true);

		// Idea,Java12中编译提示如下警告,提示如下修改
		// 对于类型为java.lang.Object&java.io.Serializable&java.lang.Comparable<? extends java.lang.Object&java.io
		// .Serializable&java.lang.Comparable<?>>[]的 varargs 参数, 泛型数组创建未经过检查
		printlnAll(new java.io.Serializable[]{"123", "abc", true});
	}

	@SuppressWarnings("unchecked")
	private <T> void printlnAll(T... args){
		for (int i = 0; i < args.length; i++) {
			T t = args[i];
			System.out.println(t);
		}
	}
	
	private static <T> void showTStatic(T t) {
		System.out.println(t);
	}

	
	/**
	 * 泛型上限: ? extends T
	 */
	@Test public void testGenericExtends() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("唐僧"));
		persons.add(new Person("猪八戒"));
		persons.add(new Student("孙悟空", 18));
		showPersons(persons);
		
		List<Student> students = new ArrayList<>();
		//students.add(new Person("唐僧"));   //The method add(_GenericDemo.Student) in the type List<_GenericDemo.Student> is not applicable for the arguments (_GenericDemo.Person)
		students.add(new Student("孙悟空", 18));
		students.add(new Student("猪八戒", 28));
		showPersons(students);
	}

	private void showPersons(List<? extends Person> persons) {
		for (Person person : persons) {
			System.out.println(person.getName());
		}
		System.out.println("******");
	}
	
	/**
	 * 泛型下限
	 */
	@Test public void testGenericSuper() {
		List<Student> students = new ArrayList<>();
		students.add(new Student("z孙悟空", 18));
		students.add(new Student("z孙悟空", 20));
		students.add(new Student("a猪八戒", 28));
		Person max01 = maxStudent(students, new PersonComparator());  //能比较父类也行
		Person max02 = maxStudent(students, new StudentComparator());
		System.out.println(max01);
		System.out.println(max02);
	}
	
	private Person maxStudent(List<Student> students,Comparator<? super Student> comparator) {
		Student max = students.get(0);
		for (int i = 1; i < students.size(); i++) {
			Student student = students.get(i);
			if(comparator.compare(student, max) > 0){
				max = student;
			}
		}
		return max;
	}
	
	/**
	 * 反射获取泛型信息
	 */
	@Test public void getGenericTypeOfSuperClass() {
	    // 字符串
	    StringGenerator sg = new StringGenerator("孙悟空");
	    Class<? extends StringGenerator> clazz = sg.getClass();
	    Type type = clazz.getGenericSuperclass();
	    if(type instanceof ParameterizedType) {
	        ParameterizedType pt = (ParameterizedType) type;
	        Type[] types = pt.getActualTypeArguments();
	        for (int i = 0; i < types.length; i++) {
	            System.out.println(types[i]);                // class java.lang.String
                System.out.println(types[i].getTypeName());  // java.lang.String
            }
	    }
	    
	    System.out.println("***********************************");
	    
	    // 人
	    PersonGeneric pg = new PersonGeneric(new Person("猪八戒"));
	    Class<? extends PersonGeneric> clazz2 = pg.getClass();
	    Type type2 = clazz2.getGenericSuperclass();
	    if(type2 instanceof ParameterizedType) {
	        ParameterizedType pt2 = (ParameterizedType) type2;
	        Type[] types2 = pt2.getActualTypeArguments();
	        for (Type type3 : types2) {
	            System.out.println(type3);                 // class com.hepengju.java05.new06_generic.Person
	            System.out.println(type3.getTypeName());   // com.hepengju.java05.new06_generic.Person
            }
	    }
	   
	}

}
