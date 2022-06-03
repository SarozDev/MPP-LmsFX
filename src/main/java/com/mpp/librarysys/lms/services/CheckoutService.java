package com.mpp.librarysys.lms.services;

import com.mpp.librarysys.lms.entities.CheckOutRecordBook;
import com.mpp.librarysys.lms.repository.CheckOutRecordBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService {

    @Autowired
    private CheckOutRecordBookRepository checkOutRecordBookRepository;

    public CheckOutRecordBook addNewCheckOutRecordBook(CheckOutRecordBook checkOutRecordBook) {
        checkOutRecordBookRepository.save(checkOutRecordBook);
        return checkOutRecordBook;
    }
}
