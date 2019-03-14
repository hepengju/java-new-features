package com.hepengju.java11.new06_abandon;


/**
 * 废弃和移除Java类
 * 
 * 
 * <pre>
 * 
 *  JEP 320: Remove the Java EE and CORBA Modules
 *  在java11中将java9标记废弃的Java EE及CORBA模块移除掉，具体如下：
 * （1） xml相关的，
 *  java.xml.ws, java.xml.bind，java.xml.ws，
 *  java.xml.ws.annotation，jdk.xml.bind，jdk.xml.ws被移除，
 *  只剩下java.xml，java.xml.crypto,jdk.xml.dom这几个模块；
 * （2） EE及CORBA相关的
 *  java.corba，java.se.ee，java.activation，java.transaction被移除，
 *  但是java11新增一个java.transaction.xa模块.
 * 
 *            OTHER
 *  JEP 336: Deprecate the Pack200 Tools and API           
 *  移除项：
 *  1.移除了com.sun.awt.AWTUtilities
 *  2.移除了sun.misc.Unsafe.defineClass
 *    使用 java.lang.invoke.MethodHandles.Lookup.defineClass来替代
 *  3.移除了Thread.destroy()以及 Thread.stop(Throwable)方法
 *  4.移除了sun.nio.ch.disableSystemWideOverlappingFileLockCheck、sun.locale.formatasdefault属性
 *  5.移除了jdk.snmp模块
 *  6.移除了javafx，openjdk估计是从java10版本就移除了，oracle jdk10还尚未移除javafx，
 *    而java11版本则oracle的jdk版本也移除了javaf
 *  7.移除了Java Mission Control，从JDK中移除之后，需要自己单独下载
 *  8.移除了这些Root Certificates ：Baltimore Cybertrust Code Signing CA，SECOM ，AOL and Swisscom
 * 
 *  废弃项：
 *  1.废弃了Nashorn JavaScript Engine
 *  2.废弃了-XX+AggressiveOpts选项
 *  3.-XX:+UnlockCommercialFeatures以及-XX:+LogCommercialFeatures选项也不再需要
 *  4.废弃了Pack200工具及其API
 *  
 *  @See <a href="http://openjdk.java.net/jeps/320">
 *  @See <a href="http://openjdk.java.net/jeps/336">
 * 
 * </pre>
 * 
 *  @author WGR
 * 
 *
 */
public class _Abandon {

   
}
