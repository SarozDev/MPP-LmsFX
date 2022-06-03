package com.mpp.librarysys.lms.services;

import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.BookCopy;
import com.mpp.librarysys.lms.repository.BookCopyRepository;
import com.mpp.librarysys.lms.repository.BookRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;


    public Book addBookAndBookCopies(Book book, Set<BookCopy> bookCopySet) {
        Book savedBook = bookRepository.save(book);
        List<BookCopy> bookCopies = bookCopyRepository.saveAll(bookCopySet);
        return book;
    }

    public BookCopy addNewBookCopy(BookCopy bookCopy) {
        bookCopyRepository.save(bookCopy);
        return bookCopy;
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
