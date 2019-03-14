package com.hepengju.java05.new06_generic;

/**
 * 泛型接口
 * 
 * <br> 泛型接口与泛型类的定义及使用基本相同。泛型接口常被用在各种类的生产器中
 * 
 * @author hepengju
 *
 * @param <T>
 */
public interface Generator<T> {
    public T next();
}