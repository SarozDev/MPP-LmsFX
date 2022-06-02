package com.mpp.librarysys.lms.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String iSBNNumber;
    @OneToMany
    private List<BookCopy> bookCopies;
    @OneToMany
    private List<BookAuthor> bookAuthors;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBNNumber() {
        return iSBNNumber;
    }

    public void setISBNNumber(String iSBNNumber) {
        this.iSBNNumber = iSBNNumber;
    }
}
