package com.hepengju.java09.new11_other;

/**
 * 代码分段缓存
 * 
 * <pre>
 *  使用说明：Java 9的另一个性能提升来自于JIT(Just-in-time)编译器。当某段代码被大量重复执行的时候, 
 *           虚拟机会把这段代码编译成机器码并储存在代码缓存里面, 进而通过访问缓存中不同分段的代码来提升编译器的效率。
 *
 *  生命周期：
 *            * 永驻代码(JVM 内置 / 非方法代码)
 *            * 短期代码(仅在某些条件下适用的配置性(profiled)代码)
 *            * 长期代码(非配置性代码)
 * </pre>
 * 
 * @see <a href="http://openjdk.java.net/jeps/197">  
 *   
 * @author WGR
 *
 */
public class _SegCodeCache {

}
