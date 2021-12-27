package com.hepengju.java05.new06_generic;

/**
 * 泛型类
 * 
 * <br> 此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
 * <br> 在实例化泛型类时，必须指定T的具体类型
 * 
 * @author hepengju
 *
 * @param <T>
 */
public class Generic<T>{ 
	
    private T key;          //key这个成员变量的类型为T, T的类型由外部指定  
    
    public Generic(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public T getKey(){      //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }
}