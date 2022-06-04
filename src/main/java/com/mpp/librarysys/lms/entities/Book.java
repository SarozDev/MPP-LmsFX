package com.mpp.librarysys.lms.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String iSBNNumber;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


//    @OneToMany(cascade = CascadeType.ALL)
//    private Set<BookCopy> bookCopies;

    @ManyToOne
    @JoinColumn(name = "borrow_rule_id")
    private BorrowRule borrowRule;

    public BorrowRule getBorrowRule() {
        return borrowRule;
    }

    public void setBorrowRule(BorrowRule borrowRule) {
        this.borrowRule = borrowRule;
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
//    @OneToMany
//    private List<BookAuthor> bookAuthors;

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

//    public Set<BookCopy> getBookCopies() {
//        return bookCopies;
//    }
//
//    public void setBookCopies(Set<BookCopy> bookCopies) {
//        this.bookCopies = bookCopies;
//    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", iSBNNumber='" + iSBNNumber + '\'' +
                ", author=" + author +
                ", borrowRule=" + borrowRule +
                '}';
    }
}
