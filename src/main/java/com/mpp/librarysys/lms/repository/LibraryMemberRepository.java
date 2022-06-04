package com.mpp.librarysys.lms.repository;

import com.mpp.librarysys.lms.entities.LibraryMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryMemberRepository extends JpaRepository<LibraryMember, Long> {

    LibraryMember getLibraryMemberByUserId(Long userId);

}
