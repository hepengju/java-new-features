package com.hepengju.java05.new06_generic;

/**
 * 
 * 实现泛型接口的类, 未传入泛型实参
 * 
 * <br> 在声明类的时候，需将泛型的声明也一起加到类中
 * 
 * @author hepengju
 *
 * @param <T>
 */
public class Fruit01Generator<T> implements Generator<T>{
	
    @Override
    public T next() {
        return null;
    }
}