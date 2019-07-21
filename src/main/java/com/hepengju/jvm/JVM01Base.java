package com.hepengju.jvm;

/**
 * JVM虚拟机参数01
 *
 * <pre>
 *    说明:
 *      -XX JVM级别的虚拟机参数
 *          + : 启用XXX
 *          - : 禁用XXX
 *      -X  应用级别的虚拟机参数
 *
 *    示例:
 *      -XX:+PrintGC                虚拟机启动后遇到GC就会打印日志
 *      -XX:+useSerialGC            配置串行GC
 *      -XX:+PrintGCDetails         查看GC详细信息, 包括各个区的情况
 *      -XX:+PrintCommandLineFlags  将隐式或显示传给虚拟机的参数输出
 * </pre>
 */
public class JVM01Base {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Free: " + runtime.freeMemory());
        System.out.println("Max:  " + runtime.maxMemory());
        System.out.println("Total: " + runtime.totalMemory());
    }
}
