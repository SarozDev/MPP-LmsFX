package com.mpp.librarysys.lms.services;

import com.mpp.librarysys.lms.entities.Author;
import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.BookCopy;
import com.mpp.librarysys.lms.repository.AuthorRepository;
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
    private AuthorRepository authorRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;


    public Book addBookAndBookCopies(Book book, Set<BookCopy> bookCopySet) {
        Book savedBook = bookRepository.save(book);
        List<BookCopy> bookCopies = bookCopyRepository.saveAll(bookCopySet);
        return book;
    }

    public Author addNewAuthor(Author author) {
        authorRepository.save(author);
        return author;
    }

    public BookCopy addNewBookCopy(BookCopy bookCopy) {
        bookCopyRepository.save(bookCopy);
        return bookCopy;
    }

    public Book getBookByIsbn(String isbn) {
        Book book = bookRepository.findByISBNNumber(isbn);
        return book;
    }

    public <T> ObservableList<T> getAllAvailableBookCopiesByIsbn(String isbn) {
        List<BookCopy> bookCopies = bookCopyRepository.getAllBookCopyByAvailabilityAndBookISBNNumber(isbn);
        return (ObservableList<T>) FXCollections.observableList(bookCopies);
    }

    public <T> ObservableList<T> getAllBooksByBookName(String bookName) {
        List<Book> books = bookRepository.findByTitleLike(bookName);
        return (ObservableList<T>) FXCollections.observableList(books);
    }

    public <T> ObservableList<T> getAllBookCopiesForSelectedBook(Book selectedBook) {
        List<BookCopy> bookCopies = bookCopyRepository.findAllByBookId(selectedBook.getId());
        return (ObservableList<T>) FXCollections.observableList(bookCopies);
    }

    public <T> ObservableList<T> getBooksObs() {
        List<Book> books = bookRepository.findAll();
        return (ObservableList<T>) FXCollections.observableList(books);
    }

    public <T> ObservableList<T> getAllAuthorsObs() {
        List<Author> authors = authorRepository.findAll();
        return (ObservableList<T>) FXCollections.observableList(authors);
    }
}
