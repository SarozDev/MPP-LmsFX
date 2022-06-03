package com.mpp.librarysys.lms.entities.general;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String state;

    private String street;

    private String zip;

    private String city;


    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public java.lang.String getStreet() {
        return street;
    }

    public void setStreet(java.lang.String street) {
        this.street = street;
    }

    public java.lang.String getZip() {
        return zip;
    }

    public void setZip(java.lang.String zip) {
        this.zip = zip;
    }

    public java.lang.String getCity() {
        return city;
    }

    public void setCity(java.lang.String city) {
        this.city = city;
    }
}
