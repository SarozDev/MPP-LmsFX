package com.mpp.librarysys.lms.services;

import com.mpp.librarysys.lms.entities.LibraryMember;
import com.mpp.librarysys.lms.entities.User;
import com.mpp.librarysys.lms.repository.AddressRepository;
import com.mpp.librarysys.lms.repository.LibraryMemberRepository;
import com.mpp.librarysys.lms.repository.UserRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private LibraryMemberRepository libraryMemberRepository;

    public Optional<LibraryMember> findLibraryMemberByID(long libraryMemberId) {
        return libraryMemberRepository.findById(libraryMemberId);
    }

    public Optional<User> findByUserName(String username) {
        return userRepository.findByUserName(username);
    }


    public User addNewUser(User reqUser) {
        User savedUser = userRepository.save(reqUser);
        checkAndSaveLibraryMember(reqUser, savedUser);
        return savedUser;
    }

    private void checkAndSaveLibraryMember(User reqUser, User savedUser) {
        // if role is empty, then he/she is library member
        if (ObjectUtils.isEmpty(reqUser.getRoles())) {
            LibraryMember libraryMember = new LibraryMember();
            libraryMember.setUser(savedUser);
            libraryMemberRepository.save(libraryMember);
        }
    }

    public User updateUser(User reqUser) {
        return userRepository.save(reqUser);
    }

    public <T> ObservableList<T> getUserObs() {
        List<User> users = userRepository.findAll();
        return (ObservableList<T>) FXCollections.observableList(users);
    }

}
