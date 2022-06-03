package com.mpp.librarysys.lms.repository;

import com.mpp.librarysys.lms.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @EntityGraph(attributePaths = "roles", type = EntityGraph.EntityGraphType.LOAD)
//    Optional<User> findByUsername(String username);

}
