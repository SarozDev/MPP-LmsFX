package com.mpp.librarysys.lms.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class BorrowRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private int allowedDay;
//    @OneToMany
//    private List<Book> books;
//
//    public List<Book> getBooks() {
//        return books;
//    }
//
//    public void setBooks(List<Book> books) {
//        this.books = books;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAllowedDay() {
        return allowedDay;
    }

    public void setAllowedDay(int allowedDay) {
        this.allowedDay = allowedDay;
    }
}
