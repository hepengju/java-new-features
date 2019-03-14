package com.hepengju.java09.new01_jigsaw.module_01;

/**
 * 
 * @author WGR
 *
 */
public class _Person {
    
    private String name;
    private int age;



    public _Person() {
        super();
    }

    public _Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
