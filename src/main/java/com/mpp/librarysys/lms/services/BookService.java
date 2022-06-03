package com.mpp.librarysys.lms.services;

import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.repository.BookRepository;
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
}
