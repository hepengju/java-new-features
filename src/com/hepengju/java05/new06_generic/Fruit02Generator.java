package com.hepengju.java05.new06_generic;

import java.util.Random;

/**
 * 
 * 实现泛型接口的类, 传入泛型实参
 * 
 * <br> 在实现类实现泛型接口时，如已将泛型类型传入实参类型，则所有使用泛型的地方都要替换成传入的实参类型
 * <br> 即：Generator<T>，public T next();中的的T都要替换成传入的String类型。
 * 
 * @author hepengju
 *
 */
public class Fruit02Generator implements Generator<String> {

    private String[] fruits = new String[]{"Apple", "Banana", "Pear"};

    @Override
    public String next() {
        Random rand = new Random();
        return fruits[rand.nextInt(3)];
    }
}