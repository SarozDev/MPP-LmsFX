package com.mpp.librarysys;

import com.mpp.librarysys.javafx.MppFxApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibrarysysApplication {

    public static void main(String[] args) {
        Application.launch(MppFxApplication.class, args);
    }

}
