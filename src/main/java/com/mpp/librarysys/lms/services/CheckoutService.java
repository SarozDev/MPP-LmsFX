package com.mpp.librarysys.lms.services;

import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.CheckOutRecordBook;
import com.mpp.librarysys.lms.entities.LibraryMember;
import com.mpp.librarysys.lms.entities.User;
import com.mpp.librarysys.lms.entities.enums.RoleEnum;
import com.mpp.librarysys.lms.repository.BookRepository;
import com.mpp.librarysys.lms.repository.CheckOutRecordBookRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class CheckoutService {

    @Autowired
    private CheckOutRecordBookRepository checkOutRecordBookRepository;

    @Autowired
    private BookRepository bookRepository;

    public CheckOutRecordBook addNewCheckOutRecordBook(CheckOutRecordBook checkOutRecordBook) {
        checkOutRecordBookRepository.save(checkOutRecordBook);
        return checkOutRecordBook;
    }

    public List<Book> getAllBooks(){
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

    public static <T> ObservableList<T> getMembersById(long id) {
        ObservableList<CheckOutRecordBook> checkOutRecordBooks = FXCollections.observableArrayList();
        CheckOutRecordBook checkOutRecordBook = new CheckOutRecordBook();

        checkOutRecordBook.setCheckOutDate(LocalDate.now());
        checkOutRecordBook.setDueDate(LocalDate.now().plusDays(7));
        checkOutRecordBooks.add(checkOutRecordBook);

        return (ObservableList<T>) checkOutRecordBooks;
    }

}

