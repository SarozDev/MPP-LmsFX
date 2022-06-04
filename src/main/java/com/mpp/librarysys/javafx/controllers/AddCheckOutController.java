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
        this.btnSave.setOnAction(actionEvent -> {
            saveCheckOutRecordBook();
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
        comboBook.setConverter(AppGeneralObjectConverter.getBookObjStringConverter());
    }

    private void saveCheckOutRecordBook() {
        CheckOutRecordBook checkOutRecordBook = new CheckOutRecordBook();

        checkOutRecordBook.setBookCopy(comboBookCopy.getValue());
        checkOutRecordBook.setCheckOutDate(checkOutDateField.getValue());
        checkOutRecordBook.setDueDate(dueDateField.getValue());
        checkoutService.addNewCheckOutRecordBook(checkOutRecordBook);

    }

    private void populateBookCopiesBasedOnBook() {
        Book selectedBook = comboBook.getSelectionModel().getSelectedItem();
        ObservableList<BookCopy> copies = bookService.getAllBookCopiesForSelectedBook(selectedBook);
        comboBookCopy.setItems(copies);
        comboBookCopy.setConverter(AppGeneralObjectConverter.getBookCopyObjStringConverter());
    }


}
