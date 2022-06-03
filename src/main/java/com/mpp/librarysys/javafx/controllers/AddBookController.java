package com.mpp.librarysys.javafx.controllers;

import com.mpp.librarysys.javafx.controllers.component.TableFxComponent;
import com.mpp.librarysys.javafx.helper.AppAbstractFxController;
import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.BookCopy;
import com.mpp.librarysys.lms.services.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AddBookController extends AppAbstractFxController {

    @Autowired
    private BookService bookService;

    @Autowired
    private TableFxComponent tableFxComponent;

    @FXML
    public VBox bookFormVBox;

    @FXML
    private TextField titleField;
    @FXML
    private TextField isbnField;

    @FXML
    private Button btnAddBook;

    // Book Copy
    @FXML
    private TextField bookCopyNumField;
    @FXML
    private CheckBox bookCopyIsAvailable;
    @FXML
    private Button btnAddBookCopy;

    @FXML
    private HBox bookCopiesHBox;

    private Book book = new Book();
    private Set<BookCopy> bookCopySet = new HashSet<>();

    private boolean isBookCopiesTableInitialized = false;


    @FXML
    public void initialize() {
        this.btnAddBookCopy.setOnAction(actionEvent -> {
            onBtnAddBookCopyClicked();
        });
        this.btnAddBook.setOnAction(actionEvent -> {
            this.onBookSaveClicked();
        });


    }

    public void onBookSaveClicked() {
        book.setTitle(this.titleField.getText());
        book.setISBNNumber(this.isbnField.getText());
        book.setAuthor(null);
        book.setBorrowRule(null);

        Book addedBook = bookService.addBookAndBookCopies(book, this.bookCopySet);
        if (null != addedBook) {
            this.book = new Book();
            System.out.println("Successfully added" + addedBook.getId());
        }
    }

    private void onBtnAddBookCopyClicked() {
        BookCopy bookCopy = new BookCopy();
        bookCopy.setCopyNumber(bookCopyNumField.getText());
        bookCopy.setAvailable(bookCopyIsAvailable.isSelected());
        bookCopy.setBook(this.book);
        this.bookCopySet.add(bookCopy);

        ObservableList<BookCopy> bookCopies = FXCollections.observableList(this.bookCopySet.stream().toList());
        List<String> columnList = Arrays.asList("copyNumber", "isAvailable");

        tableFxComponent.showScreen(this.tableFxComponent.getTableView(), bookCopies, columnList);

        if (!isBookCopiesTableInitialized) {
            TableView tableView = tableFxComponent.getTableView();
            tableView.setMinSize(100, 100);
            bookCopiesHBox.getChildren().add(0, tableView);
            this.isBookCopiesTableInitialized = true;
        }


    }


}
