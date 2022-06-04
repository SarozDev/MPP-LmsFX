package com.mpp.librarysys.lms.repository;

import com.mpp.librarysys.lms.entities.CheckOutRecordBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckOutRecordBookRepository extends JpaRepository<CheckOutRecordBook, Long> {

    @Query("SELECT cb FROM CheckOutRecordBook cb WHERE cb.libraryMember.id = :libraryMemberId and cb.bookCopy.book.isbnNumber = :isbnNumber")
    List<CheckOutRecordBook> getAllCheckOutRecordBookByLibraryMemberIdAndIsbn(@Param("libraryMemberId") Long libraryMemberId, @Param("isbnNumber") String isbnNumber);

    @Query("SELECT cb FROM CheckOutRecordBook cb WHERE cb.libraryMember.id = :libraryMemberId")
    List<CheckOutRecordBook> getAllCheckOutRecordBookByLibraryMemberId(@Param("libraryMemberId") Long libraryMemberId);

    @Query("SELECT cb FROM CheckOutRecordBook cb WHERE cb.bookCopy.book.isbnNumber = :isbnNumber")
    List<CheckOutRecordBook> getAllCheckOutRecordBookByIsbn(@Param("isbnNumber") String isbnNumber);

}
