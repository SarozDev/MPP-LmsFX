package com.mpp.librarysys.javafx.controllers;

import com.mpp.librarysys.javafx.controllers.component.TableFxComponent;
import com.mpp.librarysys.javafx.helper.AppGeneralObjectConverter;
import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.BookCopy;
import com.mpp.librarysys.lms.entities.CheckOutRecordBook;
import com.mpp.librarysys.lms.entities.LibraryMember;
import com.mpp.librarysys.lms.services.BookService;
import com.mpp.librarysys.lms.services.CheckoutService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AddCheckOutController {

    @Autowired
    private BookService bookService;
    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private TableFxComponent tableFxComponent;

    @Autowired
    private AppGeneralObjectConverter appGeneralObjectConverter;

    @FXML
    private TabPane tabViewCheckOut;

    @FXML
    private TabPane tabAddCheckOut;

    @FXML
    public VBox userFormVBox;
    @FXML
    public TextField bookField;
    @FXML
    public ComboBox<Book> comboBook;
    @FXML
    public ComboBox<BookCopy> comboBookCopy;
    @FXML
    public ComboBox<LibraryMember> libMemberField;
    @FXML
    public DatePicker checkOutDateField;

    @FXML
    public DatePicker dueDateField;

    @FXML
    public Button btnSearch;
    @FXML
    public Button btnSave;

    @FXML
    public Button btnCancel;


    public void initialize() {
        //populateCheckOutTabs();
        this.btnSave.setOnAction(actionEvent -> {

        });

        this.btnSearch.setOnAction(actionEvent -> {
            onSearchClicked();
        });
        this.btnSearch.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                onSearchClicked();
            }
        });

        this.comboBook.valueProperty().addListener((observableValue, book, t1) -> {
            populateBookCopiesBasedOnBook();
        });
    }



    private void onSearchClicked() {
        ObservableList<Book> booksList = bookService.getAllBooksByBookName("Java");
        comboBook.setItems(booksList);
        comboBook.setConverter(appGeneralObjectConverter.getBookObjStringConverter());
    }

    private void saveCheckOutRecordBook() {
        CheckOutRecordBook checkOutRecordBook = new CheckOutRecordBook();

        checkOutRecordBook.setCheckOutDate(checkOutRecordBook.getCheckOutDate());
        checkOutRecordBook.setDueDate(checkOutRecordBook.getDueDate());
        checkoutService.addNewCheckOutRecordBook(checkOutRecordBook);

    }

    private void populateBookCopiesBasedOnBook() {
        Book selectedBook = comboBook.getSelectionModel().getSelectedItem();
        ObservableList<BookCopy> copies = bookService.getAllBookCopiesForSelectedBook(selectedBook);
        comboBookCopy.setItems(copies);
        comboBookCopy.setConverter(appGeneralObjectConverter.getBookCopyObjStringConverter());
    }

    private void populateCheckOutTabs() {
        tabViewCheckOut.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Tab 1: User List view
        List<String> columnList = Arrays.asList("Id", "userName", "firstName");
        ObservableList<CheckOutRecordBook> userObs = checkoutService.<CheckOutRecordBook>getCheckOutObs();
        VBox usersTableView = tableFxComponent.createTableViewVBox(userObs, columnList);

        Tab allMembersTab = tabViewCheckOut.getTabs().get(0);
        allMembersTab.setContent(usersTableView);

//        // Tab 2: User Add view
//        Tab addMemberTab = membersTabPane.getTabs().get(1);
//
//        VBox userForm = addMemberController.userFormVBox;
        //addMemberTab.setContent(userForm);

    }


}
