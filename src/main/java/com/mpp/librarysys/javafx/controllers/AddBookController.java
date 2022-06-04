package com.mpp.librarysys.javafx.controllers;

import com.mpp.librarysys.javafx.controllers.component.TableFxComponent;
import com.mpp.librarysys.javafx.helper.AppAbstractFxController;
import com.mpp.librarysys.javafx.helper.AppGeneralObjectConverter;
import com.mpp.librarysys.javafx.util.AppFxUtil;
import com.mpp.librarysys.lms.entities.Author;
import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.BookCopy;
import com.mpp.librarysys.lms.services.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class AddBookController extends AppAbstractFxController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BookService bookService;

    @FXML
    public VBox bookFormVBox;


    // fxml tags
    @FXML
    private TextField titleField;
    @FXML
    private TextField isbnField;

    @FXML
    public ComboBox<Author> comboAuthorBox;

    @FXML
    private TextField authorName;

    @FXML
    private TextField authorBio;

    @FXML
    private Button btnAddAuthor;

    @FXML
    private Button btnAddBook;

    @FXML
    private TextField bookCopyNumField;
    @FXML
    private CheckBox bookCopyIsAvailable;
    @FXML
    private Button btnAddBookCopy;

    @FXML
    private HBox bookCopiesHBox;

    // instance trackers
    private Book book = new Book();
    private ObservableList<BookCopy> obsBookCopyList = FXCollections.observableArrayList();


    @FXML
    public void initialize() {

        ObservableList<Author> allAuthorsObs = bookService.getAllAuthorsObs();
        comboAuthorBox.setItems(allAuthorsObs);
        comboAuthorBox.setConverter(AppGeneralObjectConverter.getAuthorObjStringConverter());

        bookCopyIsAvailable.setSelected(true);
        constructBookCopyTableViewComponent();
        this.btnAddBookCopy.setOnAction(actionEvent -> {
            onBtnAddBookCopyClicked();
        });
        this.btnAddAuthor.setOnAction(actionEvent -> {
            this.onAuthorAddClicked();
        });
        this.btnAddBook.setOnAction(actionEvent -> {
            this.onBookSaveClicked();
        });


    }

    private void constructBookCopyTableViewComponent() {
        List<String> columnList = Arrays.asList("copyNumber", "isAvailable");
        TableFxComponent tableFxComponent = applicationContext.getBean(TableFxComponent.class);
        TableView tableView = tableFxComponent.getTableView();
        tableFxComponent.showScreen(tableView, obsBookCopyList, columnList);
//        tableView.setMinSize(100, 100);
        bookCopiesHBox.getChildren().add(0, tableView);
    }

    public void onBookSaveClicked() {

        Author selectedAuthor = comboAuthorBox.getSelectionModel().getSelectedItem();
        Set<BookCopy> bookCopySet = obsBookCopyList.stream().collect(Collectors.toSet());
        HashMap<String, Pair<String, String>> hashMap = new LinkedHashMap<>() {{
            put("titleField", new Pair<>(titleField.getText(), "Book title can't be empty"));
            put("isbnField", new Pair<>(isbnField.getText(), "Book ISBN can't be empty"));
            put("author", new Pair<>(null == selectedAuthor ? null: "ok", "Author should be added or selected"));
            put("bookCopy", new Pair<>(bookCopySet.size() <=0 ? null: "ok", "At least 1 book copy is required"));

        }};
        boolean ifAnyFieldIsEmpty = AppFxUtil.showAlertIfAnyFieldIsEmpty(hashMap);
        if (ifAnyFieldIsEmpty) {
            return;
        }

        book.setTitle(titleField.getText());
        book.setISBNNumber(isbnField.getText());
        book.setAuthor(selectedAuthor);
        book.setBorrowRule(null);

        Book addedBook = bookService.addBookAndBookCopies(book, bookCopySet);
        showBookSavedSuccessAlert(addedBook);
        clearForm();
    }

    private void onBtnAddBookCopyClicked() {

        HashMap<String, Pair<String, String>> hashMap = new LinkedHashMap<>() {{
            put("BookCopyNum", new Pair<>(bookCopyNumField.getText(), "Book Copy Number can't be empty"));
        }};
        boolean ifAnyFieldIsEmpty = AppFxUtil.showAlertIfAnyFieldIsEmpty(hashMap);
        if (ifAnyFieldIsEmpty) {
            return;
        }

        BookCopy bookCopy = new BookCopy();
        bookCopy.setCopyNumber(bookCopyNumField.getText());
        bookCopy.setAvailable(bookCopyIsAvailable.isSelected());
        bookCopy.setBook(this.book);
        this.obsBookCopyList.add(bookCopy);
        clearBookCopyForm();

    }

    private void onAuthorAddClicked() {

        HashMap<String, Pair<String, String>> hashMap = new LinkedHashMap<>() {{
            put("authorName", new Pair<>(authorName.getText(), "author name can't be empty"));
        }};
        boolean ifAnyFieldIsEmpty = AppFxUtil.showAlertIfAnyFieldIsEmpty(hashMap);
        if (ifAnyFieldIsEmpty) {
            return;
        }

        Author author = new Author();
        author.setAuthorName(authorName.getText());
        author.setShortBio(authorBio.getText());
        Author savedAuthor = bookService.addNewAuthor(author);
        comboAuthorBox.getItems().add(savedAuthor);
        comboAuthorBox.getSelectionModel().select(savedAuthor);
        clearAuthorForm();
    }

    private void showBookSavedSuccessAlert(Book bookSaved) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Book saved successfully ");
        alert.setContentText(bookSaved.toString());
        alert.showAndWait();
    }

    private void clearAuthorForm() {
        authorName.clear();
        authorBio.clear();
    }
    private void clearBookCopyForm() {
        bookCopyNumField.clear();
        bookCopyIsAvailable.setSelected(true);
    }

    private void clearForm() {
        titleField.clear();
        isbnField.clear();
        obsBookCopyList.clear();
        book = new Book();
    }

}
