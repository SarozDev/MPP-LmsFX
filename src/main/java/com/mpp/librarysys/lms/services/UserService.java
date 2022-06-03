package com.mpp.librarysys.lms.services;

import com.mpp.librarysys.lms.entities.User;
import com.mpp.librarysys.lms.repository.AddressRepository;
import com.mpp.librarysys.lms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    public User addNewUser(User reqUser) {
        if(!ObjectUtils.isEmpty(reqUser.getRoles())) {

        }
        User savedUser = userRepository.saveAndFlush(reqUser);
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
}
