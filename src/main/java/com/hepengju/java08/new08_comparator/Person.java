package com.hepengju.java08.new08_comparator;

public class Person {

    private String name;
    private Integer age;
    private Double salary;
    
    
    public Person(String name, Integer age, Double salary) {
        super();
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Double getSalary() {
        return salary;
    }
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    
    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + ", salary=" + salary + "]";
    }

    
}
