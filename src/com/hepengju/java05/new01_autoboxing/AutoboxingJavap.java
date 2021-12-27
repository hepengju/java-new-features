package com.hepengju.java05.new01_autoboxing;

/**
 * 反编译查看自动拆装箱的本质
 * 
 * 
 * <br> javac AutoboxingJavap.java  编译
 * <br> javap -c AutoboxingJavap    反编译
 * 
 * @author hepengju
 *
 */
public class AutoboxingJavap {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Integer i = 10;                 // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
		
		Integer m = Integer.valueOf(10);
		@SuppressWarnings("unused")
		int j = m;                     // Method java/lang/Integer.intValue:()I 
	}
}


/*
 * 反编译结果: 
 * 
Compiled from "AutoboxingJavap.java"
public class com.hepengju.java05.new01_autoboxing.AutoboxingJavap {
  public com.hepengju.java05.new01_autoboxing.AutoboxingJavap();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: bipush        10
       2: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
       5: astore_1
       6: bipush        10
       8: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
      11: astore_2
      12: aload_2
      13: invokevirtual #3                  // Method java/lang/Integer.intValue:()I
      16: istore_3
      17: return
}

*/