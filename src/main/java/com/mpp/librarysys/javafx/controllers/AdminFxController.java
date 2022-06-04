package com.mpp.librarysys.javafx.controllers;

import com.mpp.librarysys.javafx.constants.FxmlEnums;
import com.mpp.librarysys.javafx.controllers.component.TableFxComponent;
import com.mpp.librarysys.javafx.helper.AppAbstractFxController;
import com.mpp.librarysys.javafx.helper.NavigationManager;
import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.User;
import com.mpp.librarysys.lms.services.BookService;
import com.mpp.librarysys.lms.services.UserService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AdminFxController extends AppAbstractFxController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private TableFxComponent tableFxComponent;

    // Add book: addBookController and Add User/Member: addMemberController
    @Autowired
    private AddBookController addBookController;
    @Autowired
    private AddMemberController addMemberController;


    // ALl the FXML attributes from here

    @FXML
    private MenuItem menuItemLogoutBtn;

    @FXML
    private TabPane adminMainTabPane;

    @FXML
    private TabPane bookTabPane;

    @FXML
    private TabPane membersTabPane;

    @FXML
    private AnchorPane allMembersAnchorPane;

    @FXML
    private Tab allMemberTabBtn;

    @FXML
    private Tab allBookTabBtn;


    @FXML
    public void initialize() {
        menuItemLogoutBtn.setOnAction(actionEvent -> {
            getStage().close();
            Stage loginStage = new Stage();
            loginStage.setTitle("Library Management System");
            NavigationManager.switchScene(applicationContext, loginStage, FxmlEnums.FxmlView.LOGIN_VIEW);

        });
    }

    public void showNow() {
        populateMemberTabs();
        populateBookTabs();

        allBookTabBtn.setOnSelectionChanged(event -> {
            if (allBookTabBtn.isSelected()) {
                populateBookTabs();
            }
        });
        allMemberTabBtn.setOnSelectionChanged(event -> {
            if (allMemberTabBtn.isSelected()) {
                populateMemberTabs();
            }
        });

    }

    private void populateMemberTabs() {
        membersTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Tab 1: User List view
        List<String> columnList = Arrays.asList("id", "userName", "firstName", "lastName", "contact");
        ObservableList<User> userObs = userService.<User>getUserObs();

        TableFxComponent tableFxComponent = applicationContext.getBean(TableFxComponent.class);
        TableView tableView = tableFxComponent.getTableView();
        tableFxComponent.showScreen(tableView, userObs, columnList);
        Tab allMembersTab = membersTabPane.getTabs().get(0);
        allMembersTab.setContent(tableView);

        // Tab 2: User Add view
        Tab addMemberTab = membersTabPane.getTabs().get(1);

        VBox userForm = addMemberController.userFormVBox;
        addMemberTab.setContent(userForm);

    }

    private void populateBookTabs() {

        bookTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Tab 1: Books List view
        List<String> columnList = Arrays.asList("id", "title", "isbnNumber");
        ObservableList<Book> books = bookService.getBooksObs();

        TableFxComponent tableFxComponent = applicationContext.getBean(TableFxComponent.class);
        TableView tableView = tableFxComponent.getTableView();
        tableFxComponent.showScreen(tableView, books, columnList);
        Tab allBooksTab = bookTabPane.getTabs().get(0);
        allBooksTab.setContent(tableView);

        // Tab 2: Book Add view
        Tab addBookTab = bookTabPane.getTabs().get(1);

        VBox bookFormVBox = addBookController.bookFormVBox;
        addBookTab.setContent(bookFormVBox);


    }


}
