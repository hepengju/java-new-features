package com.hepengju.jvm;

//import ch.qos.logback.core.util.FileSize;

//import static com.hepengju.jvm.JVM00Util.printMemory;

/**
 * JVM虚拟机参数: 基本参数
 *
 * <pre>
 *    说明:
 *      -XX JVM级别的虚拟机参数
 *          + : 启用XXX
 *          - : 禁用XXX
 *      -X  应用级别的虚拟机参数
 *
 *    示例:
 *      -XX:+PrintGC                虚拟机启动后遇到GC就会打印日志   --> -Xlog:gc
 *      -XX:+PrintGCDetails         查看GC详细信息, 包括各个区的情况 --> -Xlog:gc*
 *      -XX:+PrintCommandLineFlags  将隐式或显示传给虚拟机的参数输出
 *      -XX:+UseSerialGC            配置串行GC
 * </pre>
 */
public class JVM01Base {

    public static void main(String[] args) {

        // 打印虚拟机参数, 使用串行GC, 打印GC, 打印GC详细
        // -XX:+PrintCommandLineFlags -XX:+UseSerialGC -XX:+PrintGC -XX:+PrintGCDetails

        // Java12的警告记录
        // -XX:+PrintGC is deprecated. Will use -Xlog:gc instead.
        // -XX:+PrintGCDetails is deprecated. Will use -Xlog:gc* instead.

        // 修正为:
        // -XX:+PrintCommandLineFlags -XX:+UseSerialGC -Xlog:gc -Xlog:gc*

        //printMemory();

        /**
         * 默认G1GC
         * -XX:+PrintCommandLineFlags Xlog:gc -Xlog:gc*
         */
        // -XX:G1ConcRefinementThreads=8 -XX:GCDrainStackTargetSize=64 -XX:InitialHeapSize=265908032 -XX:MaxHeapSize=4254528512 -XX:+PrintCommandLineFlags -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseG1GC -XX:-UseLargePagesIndividualAllocation
        // [0.010s][info][gc,heap] Heap region size: 1M
        // [0.014s][info][gc     ] Using G1
        // [0.014s][info][gc,heap,coops] Heap address: 0x0000000702600000, size: 4058 MB, Compressed Oops mode: Zero based, Oop shift amount: 3
        // [0.033s][info][gc           ] Periodic GC disabled
        // Max  : 3 GB
        // Free : 251 MB
        // Total: 254 MB
        // [0.112s][info][gc,heap,exit ] Heap
        // [0.112s][info][gc,heap,exit ]  garbage-first heap   total 260096K, used 2048K [0x0000000702600000, 0x0000000800000000)
        // [0.112s][info][gc,heap,exit ]   region size 1024K, 3 young (3072K), 0 survivors (0K)
        // [0.112s][info][gc,heap,exit ]  Metaspace       used 985K, capacity 4635K, committed 4864K, reserved 1056768K
        // [0.112s][info][gc,heap,exit ]   class space    used 97K, capacity 415K, committed 512K, reserved 1048576K

        /**
         * 使用串行GC
         * -XX:+PrintCommandLineFlags -XX:+UseSerialGC -Xlog:gc -Xlog:gc*
         */
        //
        // -XX:InitialHeapSize=265908032 -XX:MaxHeapSize=4254528512 -XX:+PrintCommandLineFlags -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseSerialGC
        // [0.016s][info][gc] Using Serial
        // [0.016s][info][gc,heap,coops] Heap address: 0x0000000702600000, size: 4058 MB, Compressed Oops mode: Zero based, Oop shift amount: 3
        // Max  : 3 GB
        // Free : 240 MB
        // Total: 245 MB
        // [0.118s][info][gc,heap,exit ] Heap
        // [0.118s][info][gc,heap,exit ]  def new generation   total 78016K, used 6938K [0x0000000702600000, 0x0000000707aa0000, 0x0000000756ea0000)
        // [0.118s][info][gc,heap,exit ]   eden space 69376K,  10% used [0x0000000702600000, 0x0000000702cc6ac0, 0x00000007069c0000)
        // [0.118s][info][gc,heap,exit ]   from space 8640K,   0% used [0x00000007069c0000, 0x00000007069c0000, 0x0000000707230000)
        // [0.118s][info][gc,heap,exit ]   to   space 8640K,   0% used [0x0000000707230000, 0x0000000707230000, 0x0000000707aa0000)
        // [0.118s][info][gc,heap,exit ]  tenured generation   total 173440K, used 0K [0x0000000756ea0000, 0x0000000761800000, 0x0000000800000000)
        // [0.118s][info][gc,heap,exit ]    the space 173440K,   0% used [0x0000000756ea0000, 0x0000000756ea0000, 0x0000000756ea0200, 0x0000000761800000)
        // [0.118s][info][gc,heap,exit ]  Metaspace       used 978K, capacity 4635K, committed 4864K, reserved 1056768K
        // [0.118s][info][gc,heap,exit ]   class space    used 97K, capacity 415K, committed 512K, reserved 1048576K

    }


}
