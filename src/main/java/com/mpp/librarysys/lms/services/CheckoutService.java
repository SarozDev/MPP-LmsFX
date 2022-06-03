package com.mpp.librarysys.lms.services;

import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.CheckOutRecordBook;
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

    public <T> ObservableList<T> getCheckOutObs() {
        ObservableList<CheckOutRecordBook> userEntities = FXCollections.observableArrayList();

        CheckOutRecordBook user = new CheckOutRecordBook();
        user.setCheckOutDate(LocalDate.now());
        user.setDueDate(LocalDate.now().plusDays(5));


        CheckOutRecordBook user1 = new CheckOutRecordBook();
        user.setCheckOutDate(LocalDate.now());
        user.setDueDate(LocalDate.now().plusDays(7));

        CheckOutRecordBook user2 = new CheckOutRecordBook();
        user.setCheckOutDate(LocalDate.now());
        user.setDueDate(LocalDate.now().plusDays(8));

        userEntities.add(user);
        userEntities.add(user1);
        userEntities.add(user2);
        return (ObservableList<T>) userEntities;
    }

}

