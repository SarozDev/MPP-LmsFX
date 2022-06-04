package com.mpp.librarysys;

import com.mpp.librarysys.javafx.SpringBootFXApplication;
import com.mpp.librarysys.lms.entities.User;
import com.mpp.librarysys.lms.entities.enums.RoleEnum;
import com.mpp.librarysys.lms.services.UserService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class LibrarySystemSpringBootApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        Application.launch(SpringBootFXApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Optional<User> admin = userService.findByUserName("admin");
        if (!admin.isPresent()) {
            System.out.println("Initial Admin-User Not Found, initializing default admin with username: admin");
            User defaultAdmin = new User();
            defaultAdmin.setUserName("admin");
            defaultAdmin.setPassword("admin");
            defaultAdmin.setFirstName("Test");
            defaultAdmin.setLastName("Admin");
            defaultAdmin.setRoles(Set.of(RoleEnum.ADMIN, RoleEnum.LIBRARIAN));
            userService.addNewUser(defaultAdmin);
            System.out.println("Default Admin-User Initialization Successful");
        }
    }
}
