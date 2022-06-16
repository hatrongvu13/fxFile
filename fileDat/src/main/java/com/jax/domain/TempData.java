package com.jax.domain;

import java.io.Serializable;

public class TempData implements Serializable {
    private String name;
    private String email;
    private double age;

    public TempData() {
    }

    public TempData(String name, String email, double age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }
}
