package com.hepengju.java10.new01_varinference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;



/**
 * JDK10新特性讲解：局部变量类型推断
 * JEP 286: Local-Variable Type Inference
 * 
 * <pre>
 *  使用说明：
 *            * 引入了 var，既保持 Java 对静态类型安全的承诺，又能让开发者省略不必要的局部变量类型的声明。 
 *  用法示例：
 *            * 声明的同时赋值
 *            * 增强的 for循环中的索引
 *            * 传统 for循环中声明的本地变量
 *       @See <a href="http://openjdk.java.net/jeps/286">     
 *  </pre>
 *  
 * @author WGR
 */

class Users{
    private String username;
    private Integer userage;
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public Integer getUserage() {
        return userage;
    }
    public void setUserage(Integer userage) {
        this.userage = userage;
    }
    @Override
    public String toString() {
        return "Users [username=" + username + ", userage=" + userage + "]";
    }
    
}

public class _VarInference {
    
    /**
     * 
     * 该特定注意点：
     * 1.只针对局部变量
     * 2.var 是保留类型不是关键字。意味着我们还可以用var来定义变量名或者是方法名
     * 3.var 不允许赋值null
     *
     */
    @Test
    public void testVarInferencer() {
        
        
        var i = 10;
        var str="abc";
        var list = new ArrayList<>();
        list.add("list test var");
        
        var set = new HashSet<>();
        set.add("set test var");
        
        var map = new HashMap<String,String>();
        map.put("test", "map test var");
        
        var users = new Users();
        users.setUserage(20);
        users.setUsername("user test var");
        
        System.out.println(i);       // 10
        System.out.println(str);     // abc
        
        for(var i1=0;i1<list.size();i1++) {
            System.out.println(list.get(i1)); // list test var
        }
        
        for(var temp :set) {
            System.out.println(temp);  // set test var
        }
        
        Set<String> keys = map.keySet();
        
        for(var key :keys) {
            System.out.println(map.get(key)); // map test var
        }
        
        System.out.println(users); // Users [username=user test var, userage=20]
    }

}
