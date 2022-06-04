package com.mpp.librarysys.javafx.controllers;

import com.mpp.librarysys.javafx.helper.AppGeneralObjectConverter;
import com.mpp.librarysys.javafx.util.AppFxUtil;
import com.mpp.librarysys.lms.entities.BookCopy;
import com.mpp.librarysys.lms.entities.CheckOutRecordBook;
import com.mpp.librarysys.lms.entities.LibraryMember;
import com.mpp.librarysys.lms.services.BookService;
import com.mpp.librarysys.lms.services.CheckoutService;
import com.mpp.librarysys.lms.services.UserService;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class AddCheckOutController {


    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CheckoutService checkoutService;

    @FXML
    private TabPane tabViewCheckOut;

    @FXML
    private TabPane tabAddCheckOut;

    @FXML
    public VBox userFormVBox;

    @FXML
    private TextField memberIdField;
    @FXML
    private TextField isbnNumberField;

    @FXML
    private Label labelMsgBox;
    @FXML
    private ComboBox<BookCopy> comboBookCopy;

    @FXML
    private DatePicker dueDateField;

    @FXML
    private Button btnSearch;
    @FXML
    private Button btnSave;

    @FXML
    private Button btnReset;

    private CheckOutRecordBook checkOutRecordBook = new CheckOutRecordBook();


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

        this.btnReset.setOnAction(actionEvent -> {
            clearForm();
        });

//        this.comboBook.valueProperty().addListener((observableValue, book, t1) -> {
//            populateBookCopiesBasedOnBook();
//        });
    }


    private void onSearchClicked() {
        String memberID = memberIdField.getText();
        String isbn = isbnNumberField.getText();

        validateLoginInputFields(memberID, isbn);
        Optional<LibraryMember> libraryMember = userService.findLibraryMemberByID(Long.parseLong(memberID));
        if (!libraryMember.isPresent()) {
            labelMsgBox.setText("Sorry, Member not found !!!");
            return;
        }
        ObservableList<BookCopy> bookCopiesByIsbn = bookService.getAllAvailableBookCopiesByIsbn(isbn);
        if (bookCopiesByIsbn.size() <= 0) {
            labelMsgBox.setText("Sorry, No book copies available !!!");
            return;
        } else {
            labelMsgBox.setText("Success, Please select book copy to proceed !!!");
        }
        checkOutRecordBook.setLibraryMember(libraryMember.get());
        populateBookCopies(bookCopiesByIsbn);
    }

    private void populateBookCopies(ObservableList<BookCopy> bookCopiesByIsbn) {
        // populate book copies
        comboBookCopy.setItems(bookCopiesByIsbn);
        comboBookCopy.setConverter(AppGeneralObjectConverter.getBookCopyObjStringConverter());
    }

    private void validateLoginInputFields(String memberID, String isbn) {
        if (StringUtils.hasText(memberID) && !AppFxUtil.isLong(memberID)) {
            Alert alert = AppFxUtil.createAlert(Alert.AlertType.WARNING, "Oops !!!", "Member ID must be numeric", "");
            alert.showAndWait();
            return;
        }
        if (!StringUtils.hasText(memberID) || !StringUtils.hasText(isbn)) {
            Alert alert = AppFxUtil.createAlert(Alert.AlertType.WARNING, "Oops !!!", "Please provide both fields", "");
            alert.showAndWait();
            return;
        }
    }

    private void saveCheckOutRecordBook() {

        if (ObjectUtils.isEmpty(dueDateField.getValue())) {
            Alert alert = AppFxUtil.createAlert(Alert.AlertType.WARNING, "Oops !!!", "Please must select due date", "");
            alert.showAndWait();
            return;
        }
        checkOutRecordBook.setBookCopy(comboBookCopy.getValue());
        checkOutRecordBook.setDueDate(dueDateField.getValue());
        CheckOutRecordBook savedCheckOutRecordBook = checkoutService.addNewCheckOutRecordBook(checkOutRecordBook);
        if (null != savedCheckOutRecordBook) {
            Alert alert = AppFxUtil.createAlert(Alert.AlertType.INFORMATION, "Success", "CheckOutRecord Saved Successfully", "");
            alert.showAndWait();
            clearForm();
        }

    }

    private void clearForm() {
        checkOutRecordBook = new CheckOutRecordBook();
        memberIdField.clear();
        isbnNumberField.clear();
        labelMsgBox.setText("");
        dueDateField.setValue(LocalDate.now());
        comboBookCopy.getItems().clear();
    }


}
