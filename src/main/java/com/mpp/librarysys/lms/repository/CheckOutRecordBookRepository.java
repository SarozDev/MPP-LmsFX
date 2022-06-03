package com.mpp.librarysys.lms.repository;

import com.mpp.librarysys.lms.entities.CheckOutRecordBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckOutRecordBookRepository extends JpaRepository<CheckOutRecordBook, Long> {

}
