package com.mpp.librarysys.lms.repository;

import com.mpp.librarysys.lms.entities.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, Long> {

    List<BookCopy> findAllByBookId(long bookId);
}
