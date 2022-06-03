package com.mpp.librarysys.lms.entities;

import javax.persistence.*;

@Entity
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String copyNumber;
    private Boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

//    @OneToMany
//    private List<CheckOutRecordBook> checkOutRecordBooks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(String copyNumber) {
        this.copyNumber = copyNumber;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

//    public List<CheckOutRecordBook> getCheckOutRecordBooks() {
//        return checkOutRecordBooks;
//    }
//
//    public void setCheckOutRecordBooks(List<CheckOutRecordBook> checkOutRecordBooks) {
//        this.checkOutRecordBooks = checkOutRecordBooks;
//    }


}
