package com.mpp.librarysys.lms.entities.general;

import javax.persistence.*;
import java.util.List;

@Entity
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
//    @OneToMany
//    private List<City> cities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<City> getCities() {
//        return cities;
//    }
//
//    public void setCities(List<City> cities) {
//        this.cities = cities;
//    }
}
