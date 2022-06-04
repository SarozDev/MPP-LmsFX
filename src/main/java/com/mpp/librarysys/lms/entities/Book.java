package com.mpp.librarysys.lms.entities;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String isbnNumber;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<BookCopy> bookCopies;

    @ManyToOne
    @JoinColumn(name = "borrow_rule_id")
    private BorrowRule borrowRule;

    private long defaultRentDays;

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

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public BorrowRule getBorrowRule() {
        return borrowRule;
    }

    public void setBorrowRule(BorrowRule borrowRule) {
        this.borrowRule = borrowRule;
    }

    public long getDefaultRentDays() {
        return defaultRentDays;
    }

    public void setDefaultRentDays(long defaultRentDays) {
        this.defaultRentDays = defaultRentDays;
    }
}
