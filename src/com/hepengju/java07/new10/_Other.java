package com.hepengju.java07.new10;

import org.junit.Test;

/**
 * 其他新特性
 * 
 * <pre>
 *  1. @SafeVarargs 注解
 * 
 * </pre>
 * @author hepengju
 *
 */
public class _Other {

    @Test public void testSafeVarargs() {
        
    }
    
    // public static <T> T getFirstVar(@SuppressWarnings("unchecked") T... args) {
    // @SuppressWarnings("unchecked")
    @SafeVarargs
    public static <T> T getFirstVar(T... args) {
        return args.length > 0 ? args[0] : null;
    }
}
