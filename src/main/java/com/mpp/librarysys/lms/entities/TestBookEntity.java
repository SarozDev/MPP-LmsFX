package com.mpp.librarysys.lms.entities;

public class TestBookEntity {

    private int id;
    private String bookName;
    private long isbn;

    public TestBookEntity(int id, String bookName, long isbn) {
        this.id = id;
        this.bookName = bookName;
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }
}
