package com.mpp.librarysys.lms.entities;

import javax.persistence.*;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String FirstName;
    private String LastName;

    @OneToOne
    private Address address;
    @OneToOne
    private Contact contact;



    public void setId(long id)
    {
        this.id =id;
    }

    public long getId(){
        return this.id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }
}
