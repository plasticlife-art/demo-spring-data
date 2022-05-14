package com.haulmont.spring.data.demo.domain;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "reserve")
@Table(name = "reserve")
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "reserve", orphanRemoval = true)
    private Set<Forester> foresters = new LinkedHashSet<>();

    public Reserve() {
    }

    public Reserve(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Set<Forester> getForesters() {
        return foresters;
    }

    public void setForesters(Set<Forester> foresters) {
        this.foresters = foresters;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}