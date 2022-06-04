package com.mpp.librarysys.lms.services;

import com.mpp.librarysys.lms.entities.LibraryMember;
import com.mpp.librarysys.lms.entities.User;
import com.mpp.librarysys.lms.entities.enums.RoleEnum;
import com.mpp.librarysys.lms.repository.AddressRepository;
import com.mpp.librarysys.lms.repository.LibraryMemberRepository;
import com.mpp.librarysys.lms.repository.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private LibraryMemberRepository libraryMemberRepository;

    public Optional<User> findByUserName(String username) {
        return userRepository.findByUserName(username);
    }


    public User addNewUser(User reqUser) {
        User savedUser = userRepository.save(reqUser);

        if (!ObjectUtils.isEmpty(reqUser.getRoles())) {
            boolean hasLibrarianRole = reqUser.getRoles()
                    .stream().anyMatch(roleEnum -> roleEnum.equals(RoleEnum.LIBRARIAN));
            if (hasLibrarianRole) {
                LibraryMember libraryMember = new LibraryMember();
                libraryMember.setUser(savedUser);
                libraryMemberRepository.save(libraryMember);
            }
        }
        return savedUser;
    }

    public User updateUser(User reqUser) {
        Optional<User> optionalUser = userRepository.findById(reqUser.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUserName(reqUser.getUserName());
            userRepository.save(user);
            return user;
        }
        return null;
    }

    public <T> ObservableList<T> getUserObs() {
        ObservableList<User> userEntities = FXCollections.observableArrayList();

        User user = new User();
        user.setUserName("Admin");
        user.setRoles(Set.of(RoleEnum.ADMIN));
        user.setEnabled(true);

        User user1 = new User();
        user1.setUserName("Shrawan");
        user1.setRoles(Set.of(RoleEnum.LIBRARIAN));
        user1.setEnabled(true);

        User user2 = new User();
        user2.setUserName("Saroj");
        user2.setRoles(Set.of(RoleEnum.LIBRARIAN));
        user2.setEnabled(true);

        userEntities.add(user);
        userEntities.add(user1);
        userEntities.add(user2);
        return (ObservableList<T>) userEntities;
    }

}
