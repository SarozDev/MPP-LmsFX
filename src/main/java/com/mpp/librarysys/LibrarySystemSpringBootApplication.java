package com.mpp.librarysys;

import com.mpp.librarysys.javafx.SpringBootFXApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrarySystemSpringBootApplication {

    public static void main(String[] args) {
        Application.launch(SpringBootFXApplication.class, args);
    }

}
