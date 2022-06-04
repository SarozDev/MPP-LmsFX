package com.mpp.librarysys.javafx.util;

import java.time.LocalDate;

public class CheckOutHistoryDTO {

    private Long id;
    private LocalDate checkOutDate;
    private LocalDate dueDate;

    private String bookName;

    private String isbnNumber;
    private String bookCopyNumber;

    private String memberId;
    private String memberName;

    private String librarianId;

    private String datePassed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public String getBookCopyNumber() {
        return bookCopyNumber;
    }

    public void setBookCopyNumber(String bookCopyNumber) {
        this.bookCopyNumber = bookCopyNumber;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getLibrarianId() {
        return librarianId;
    }

    public void setLibrarianId(String librarianId) {
        this.librarianId = librarianId;
    }

    public String getDatePassed() {
        return datePassed;
    }

    public void setDatePassed(String datePassed) {
        this.datePassed = datePassed;
    }
}
