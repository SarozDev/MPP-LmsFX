package com.mpp.librarysys.javafx.controllers;

import com.mpp.librarysys.javafx.controllers.component.TableFxComponent;
import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.BookCopy;
import com.mpp.librarysys.lms.entities.CheckOutRecordBook;
import com.mpp.librarysys.lms.entities.User;
import com.mpp.librarysys.lms.services.BookService;
import com.mpp.librarysys.lms.services.CheckoutService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddCheckOutController {

    @Autowired
    private BookService bookService;
    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private TableFxComponent tableFxComponent;

    @FXML
    private TabPane tabViewCheckOut;

    @FXML
    private TabPane tabAddCheckOut;

    @FXML
    public VBox userFormVBox;
    @FXML
    public TextField bookField;
    @FXML
    public ComboBox comboBook;
    @FXML
    public ComboBox comboBookCopy;
    @FXML
    public ComboBox libMemberField;
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

    private void populateComboBoxes() {
        ObservableList<Object> booksObs = bookService.getBooksObs();
        comboBook.setItems(booksObs);

    }



    public void initialize() {
        //populateCheckOutTabs();
        this.btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        this.btnSearch.setOnAction(actionEvent -> {
            onSearchClicked();
        });
        this.btnSearch.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                onSearchClicked();
            }
        });

        this.comboBook.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                populateBookCopiesBasedOnBook();
            }
        });
    }

    private void onSearchClicked(){
        ObservableList<Book> booksList = bookService.getAllBooksByBookName("Java");
//
//        ComboBox<Book> books = new ComboBox<>();
//        books.valueProperty().bindBidirectional(booksList);

        List<String> stringList = booksList.stream().map(o -> o.getTitle()).collect(Collectors.toList());
        //comboBook.setConverter();
        comboBook.setItems(booksList);
        //comboBook.
//        comboBook.valueProperty().bindBidirectional(new PropertyValueFactory<>());
    }

    private void saveCheckOutRecordBook() {
        CheckOutRecordBook checkOutRecordBook = new CheckOutRecordBook();

//        BookCopy bookCopy = new BookCopy();
//        bookCopy.
        checkOutRecordBook.setCheckOutDate(checkOutRecordBook.getCheckOutDate());
        checkOutRecordBook.setDueDate(checkOutRecordBook.getDueDate());
        checkoutService.addNewCheckOutRecordBook(checkOutRecordBook);

    }

    private void populateBookCopiesBasedOnBook(){
        Object book = comboBook.getSelectionModel().getSelectedItem();
        ObservableList<Object> copies =  bookService.getAllBookCopiesForSelectedBook("book");

        comboBookCopy.setItems(copies);
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
