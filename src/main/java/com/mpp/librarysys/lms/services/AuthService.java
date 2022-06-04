package com.mpp.librarysys.lms.services;

import com.mpp.librarysys.lms.entities.User;
import com.mpp.librarysys.lms.repository.UserRepository;
import com.mpp.librarysys.lms.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // make password comparison more secured using password encoder
    public User authenticateUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUserName(username);
        if (!optionalUser.isPresent()) {
            return null;
        } else {
            User user = optionalUser.get();
            if (user.getPassword().equals(password)) {
                AppUtil.setAuthenticatedUser(user);
                User authenticatedUser = AppUtil.getAuthenticatedUser();
                return user;
            }
        }
        return null;
    }

}
