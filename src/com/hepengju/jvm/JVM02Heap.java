package com.hepengju.jvm;

import static com.hepengju.jvm.JVM00Util.printMemory;

/**
 * JVM虚拟机参数: 堆
 *
 * <pre>
 *     -Xms 启动时初始化堆大小
 *     -Xmx 最大的堆大小
 *     -Xmn 设置新生代大小, 一般新生代设置为整个堆空间的1/3与1/4之间
 *
 *     -XX:SurvivorRatio                  设置新生代中的eden区/from区或eden区/to区的比例
 *     -XX:NewRaito                       设置老年代/新生代的比例
 *     -XX:+HeapDumpOnOutOfMemoryError    设置OOM异常时打印dump文件
 *     -XX:HeapDumpPath                   设置OOM异常时dump文件的路径

 * </pre>
 */
public class JVM02Heap {
    public static void main(String[] args) {
        // -Xlog:gc -Xlog:gc* -XX:+UseSerialGC -Xms5M -Xmx10M

        printMemory();

        byte[] buf1 = new byte[1*1024*1024];
        printMemory("分配1M内存");

        byte[] buf2 = new byte[4*1024*1024];
        printMemory("分配4M内存");
    }

}
