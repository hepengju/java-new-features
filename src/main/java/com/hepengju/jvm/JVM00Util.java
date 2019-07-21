package com.hepengju.jvm;

import ch.qos.logback.core.util.FileSize;

/**
 * JVM测试的工具类
 */
public class JVM00Util {

    /**
     * 打印运行时内存
     */
    public static void printMemory(String message){
        Runtime runtime = Runtime.getRuntime();
        if(message != null) System.out.println(message);
        System.out.println("Max  : " + getReadSize(runtime.maxMemory()));
        System.out.println("Free : " + getReadSize(runtime.freeMemory()));
        System.out.println("Total: " + getReadSize(runtime.totalMemory()));
    }

    public static void printMemory(){
        printMemory(null);
    }

    /**
     * 人类可读的数字
     */
    public static String getReadSize(long size){
        return new FileSize(size).toString();
    }
}
