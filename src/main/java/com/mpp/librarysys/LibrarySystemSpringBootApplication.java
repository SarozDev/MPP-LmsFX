package com.mpp.librarysys;

import com.mpp.librarysys.javafx.SpringBootFXApplication;
import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.BookCopy;
import com.mpp.librarysys.lms.entities.User;
import com.mpp.librarysys.lms.entities.enums.RoleEnum;
import com.mpp.librarysys.lms.services.BookService;
import com.mpp.librarysys.lms.services.UserService;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class LibrarySystemSpringBootApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

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
            populateInitialData();
        }
    }

    void populateInitialData() {

        Book book = new Book();
        book.setTitle("Java");
        book.setIsbnNumber("900");
        book.setAuthor(null);
        book.setDefaultRentDays(25);

        Set<BookCopy> bookCopySet = new HashSet<>();
        BookCopy bookCopy = new BookCopy();
        bookCopy.setAvailable(true);
        bookCopy.setCopyNumber("01");
        BookCopy bookCopy2 = new BookCopy();
        bookCopy2.setAvailable(true);
        bookCopy2.setCopyNumber("02");
        bookCopySet.add(bookCopy2);
        bookService.addBookAndBookCopies(book, bookCopySet);

        // initial member data
        User member = new User();
        member.setUserName("testMember");
        member.setFirstName("member");
        member.setLastName("member");
        member.setPassword("dummyPass");
        userService.addNewUser(member);
    }
}
