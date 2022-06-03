package com.mpp.librarysys.javafx.controllers;

import com.mpp.librarysys.javafx.controllers.component.TableFxComponent;
import com.mpp.librarysys.javafx.helper.AppAbstractFxController;
import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MemberFxController extends AppAbstractFxController {

    @Autowired
    private TableFxComponent tableFxComponent;

    @FXML
    public void initialize() {
    }

    public void showNow() {
        tableFxComponent.setStage(getStage());


        // add elements
//        populateMemberTabs();
//        populateBookTabs();
    }

//    private void populateMemberTabs() {
//        membersTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
//
//        // Tab 1: User List view
//        List<String> columnList = Arrays.asList("id", "userName", "firstName");
//        ObservableList<User> userObs = userService.<User>getUserObs();
//        VBox usersTableView = tableFxComponent.createTableView(userObs, columnList);
//
//        Tab allMembersTab = membersTabPane.getTabs().get(0);
//        allMembersTab.setContent(usersTableView);
//
//        // Tab 2: User Add view
//        Tab addMemberTab = membersTabPane.getTabs().get(1);
//
//        VBox userForm = addMemberController.userFormVBox;
//        addMemberTab.setContent(userForm);
//
//    }
//
//    private void populateBookTabs() {
//
//        bookTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
//
//        // Tab 1: User List view
//        // Tab 1: Books List view
//        List<String> columnList = Arrays.asList("id", "title", "iSBNNumber");
//        ObservableList<Book> books = bookService.getBooksObs();
//        VBox booksTableView = tableFxComponent.createTableView(books, columnList);
//
//        Tab allBooksTab = bookTabPane.getTabs().get(0);
//        allBooksTab.setContent(booksTableView);
//
//        // Tab 2: User Add view
//        Tab addBookTab = bookTabPane.getTabs().get(1);
//
//        VBox bookFormVBox = addBookController.bookFormVBox;
//        addBookTab.setContent(bookFormVBox);
//
//
//    }
}
