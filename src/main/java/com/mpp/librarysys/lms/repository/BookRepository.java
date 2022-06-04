package com.mpp.librarysys.lms.repository;

import com.mpp.librarysys.lms.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleLike(String title);
}
