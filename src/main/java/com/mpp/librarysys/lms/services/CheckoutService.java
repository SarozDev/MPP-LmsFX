package com.mpp.librarysys.lms.services;

import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.BookCopy;
import com.mpp.librarysys.lms.entities.CheckOutRecordBook;
import com.mpp.librarysys.lms.entities.User;
import com.mpp.librarysys.lms.repository.*;
import com.mpp.librarysys.lms.util.AppUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CheckoutService {

    @Autowired
    private CheckOutRecordBookRepository checkOutRecordBookRepository;

    @Autowired
    private LibraryMemberRepository libraryMemberRepository;

    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public CheckOutRecordBook addNewCheckOutRecordBook(CheckOutRecordBook checkOutRecordBook) {
        checkOutRecordBook.setCheckOutDate(LocalDate.now());
        User authenticatedUser = AppUtil.getAuthenticatedUser();
        Optional<User> optionalUser = userRepository.findById(authenticatedUser.getId());
        checkOutRecordBook.setLibrarianUser(optionalUser.get());

        Optional<BookCopy> bookCopyOptional = bookCopyRepository.findById(checkOutRecordBook.getBookCopy().getId());
        BookCopy bookCopy = bookCopyOptional.get();
        bookCopy.setAvailable(false);
        bookCopyRepository.save(bookCopy);
        checkOutRecordBookRepository.save(checkOutRecordBook);
        return checkOutRecordBook;
    }

    public List<Book> getAllBooks() {
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }

    public static <T> ObservableList<T> getCheckOutObs() {
        ObservableList<CheckOutRecordBook> userEntities = FXCollections.observableArrayList();

        CheckOutRecordBook check = new CheckOutRecordBook();
        check.setCheckOutDate(LocalDate.now());
        check.setDueDate(LocalDate.now().plusDays(5));


        CheckOutRecordBook check1 = new CheckOutRecordBook();
        check1.setCheckOutDate(LocalDate.now());
        check1.setDueDate(LocalDate.now().plusDays(7));

        CheckOutRecordBook check2 = new CheckOutRecordBook();
        check2.setCheckOutDate(LocalDate.now());
        check2.setDueDate(LocalDate.now().plusDays(8));

        userEntities.add(check);
        userEntities.add(check1);
        userEntities.add(check2);
        return (ObservableList<T>) userEntities;
    }

    public <T> ObservableList<T> getCheckoutRecordBookByMembersByIdOrIsbn(long libraryMemberId, String isbnNumber) {
        List<CheckOutRecordBook> checkOutRecordBookByLibraryMember;
        if(libraryMemberId > 0 && StringUtils.hasText(isbnNumber)) {
            checkOutRecordBookByLibraryMember = checkOutRecordBookRepository.getAllCheckOutRecordBookByLibraryMemberIdAndIsbn(libraryMemberId, isbnNumber);
        } else if(libraryMemberId > 0) {
            checkOutRecordBookByLibraryMember = checkOutRecordBookRepository.getAllCheckOutRecordBookByLibraryMemberId(libraryMemberId);
        } else {
            checkOutRecordBookByLibraryMember = checkOutRecordBookRepository.getAllCheckOutRecordBookByIsbn(isbnNumber);
        }
        ObservableList<CheckOutRecordBook> checkOutRecordBooks = FXCollections.observableList(checkOutRecordBookByLibraryMember);
        return (ObservableList<T>) checkOutRecordBooks;
    }

}

