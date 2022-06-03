package com.mpp.librarysys.lms.services;

import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.BookCopy;
import com.mpp.librarysys.lms.repository.BookRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public Book addNewBook(Book book) {
        bookRepository.save(book);
        return book;
    }



    public <T> ObservableList<T> getAllBooksByBookName(String bookName) {
        ObservableList<Book> bookEntities = FXCollections.observableArrayList();
        Book book = new Book();
        book.setTitle("Java");
        book.setId(1);
        bookEntities.add(book);
        return (ObservableList<T>) bookEntities;
    }

    public <T> ObservableList<T> getAllBookCopiesForSelectedBook(String bookName) {
        ObservableList<BookCopy> bookEntities = FXCollections.observableArrayList();
        BookCopy book = new BookCopy();
        book.setCopyNumber(4);
        book.setId(1);

        BookCopy book1 = new BookCopy();
        book.setCopyNumber(2);
        book.setId(2);
        bookEntities.add(book);
        return (ObservableList<T>) bookEntities;
    }

    public <T> ObservableList<T> getBooksObs() {
        ObservableList<Book> bookEntities = FXCollections.observableArrayList();
        Book book = new Book();
        book.setTitle("Java");
        book.setId(1);
        Book book1 = new Book();
        book1.setTitle("Spring");
        book1.setId(2);
        bookEntities.add(book);
        bookEntities.add(book1);
        return (ObservableList<T>) bookEntities;
    }
}
