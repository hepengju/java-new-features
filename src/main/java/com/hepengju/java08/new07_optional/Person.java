package com.hepengju.java08.new07_optional;

import java.util.Optional;

public class Person {
    
    private String name;
    private Car car;
    private Optional<Car> ocar;
    
    
    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<Car> getOcar() {
        return ocar;
    }

    public void setOcar(Optional<Car> ocar) {
        this.ocar = ocar;
    }
    
    
}