package com.hepengju.java11.new02_varforlambda;

import java.util.Arrays;

import org.junit.Test;

/**
 * 对Lambda表达式的Var类型推断
 * 
 * <pre>
 * 
 *  JEP 323: Local-Variable Syntax for Lambda Parameters
 *  局部变量类型推断就是左边的类型直接使用 var 定义
 *  而不用写具体的类型，编译器能根据右边的表达式自动推断类型
 *  
 * </pre>
 * 
 * @author WGR
 *
 */
public class _VarForLambda {
    
    /**
     * 对Lambda表达式的Var类型推断
     */
    @Test
    public void testVarForLambda() {
        var numbers = new int[]{1, 2, 3, 4, 5, 6, 7};

        var subset = Arrays.stream(numbers).filter((var a) -> a > 5).toArray();
        for (int i = 0; i < subset.length; i++) {
            System.out.println(subset[i]);  // 6 7
        }
    }
    
    
    /**
     * var对局部变量的类型推断
     */
    @Test
    public void testVar() {
        
        var javastack = "javastack";
        System.out.println(javastack);              // javastack
        System.out.println(javastack.getClass());   // class java.lang.String
        
       // var javastack = "javastack";  等价于    String javastack = "javastack";


        
    }

}
