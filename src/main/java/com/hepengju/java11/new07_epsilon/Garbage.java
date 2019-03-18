package com.hepengju.java11.new07_epsilon;

public class Garbage {
    
    private double d1 = 1;
    private double d2 = 2;
    
    // 这个方法是GC在清除本对象时, 会调用一次
    @Override
    public void finalize() {
        System.out.println(this + " collecting");
    }
}
