package com.hepengju.java08.new07_optional;

import java.util.Optional;

/**
 * 汽车
 * 
 * @author hepengju
 *
 */
public class Car {

    private String name;
    private Insurance insurance;
    private Optional<Insurance> oinsurance;
    
    public Car(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Optional<Insurance> getOinsurance() {
        return oinsurance;
    }

    public void setOinsurance(Optional<Insurance> oinsurance) {
        this.oinsurance = oinsurance;
    }
    
    
}
