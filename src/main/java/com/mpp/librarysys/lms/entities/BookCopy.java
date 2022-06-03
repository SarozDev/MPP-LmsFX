package com.mpp.librarysys.lms.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int copyNumber;
    private boolean isAvailable;

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

    public int getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
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
