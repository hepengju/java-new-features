package com.hepengju.java05.new02_varargs;

import org.junit.Test;

/**
 * 
 * 可变参数(Varargs)
 * 
 * <pre>
 *  说明: 只要在一个形参的“类型”与“参数名”之间加上三个连续的“.”就可以让它和不确定个实参相匹配
 * 
 *  注意: 1.不定项参数必须放在参数列表最后一个
 *       2.不定项参数只能有一个（多个，必然有一个不是最后一个）
 *       3.重载时优先调用参数个数固定的方法
 * </pre>
 * 
 * @see <a href="https://www.cnblogs.com/uptownBoy/articles/1698335.html">深入了解JAVA可变长度的参数(Varargs)</a>
 * 
 * @author hepengju
 *
 */
public class _Varargs {

	/**
	 * 可变参数
	 */
	@Test public void testVarargs() {
        // 传入0或多个参数
        printAll();
        printAll("孙悟空", "猪八戒");                 // 优先调用参数个数固定的方法: printAll(String str1,String str2)
        printAll("孙悟空", "猪八戒", "唐僧");
        printAll("孙悟空", "猪八戒", "唐僧", "沙和尚");

        // 直接传入数组
        String[] arr = new String[] { "孙悟空", "猪八戒" };
        printAll(arr);
	}
	
    public void printAll(String... strs) {
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            System.out.println(str);
        }
        System.out.println("------");
    }

    public void printAll(String str1, String str2) {
        System.out.println(str1);
        System.out.println(str2);
        System.out.println("******");
    }
	
	/**
	 * 求和示例
	 */
    @Test public void testBaseType() {
        System.out.println(sum());
        System.out.println(sum(1));
        System.out.println(sum(1, 2));

        int[] arr = { 1, 2, 3 };
        System.out.println(sum(arr));

        // The method sum(int...) in the type _Varargs is not applicable for the arguments (int, int[])
        // System.out.println(sum(1,arr));
    }

    public int sum(int... arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += i;
        }
        return sum;
    }
	
	//Duplicate method printAll(String[]) in type _Varargs
	/*
	public void printAll(String[] strs) {
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i];
			System.out.println(str);
		}
		System.out.println("-------");
	}
	*/
}
