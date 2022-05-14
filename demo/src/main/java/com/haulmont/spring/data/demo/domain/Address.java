package com.haulmont.spring.data.demo.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class Address {


    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    private Address() {
    }

    public Address(String country, String city) {
        this.city = city;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}