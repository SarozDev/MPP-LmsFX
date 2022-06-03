package com.mpp.librarysys.javafx.controllers;

import com.mpp.librarysys.javafx.constants.FxmlEnums;
import com.mpp.librarysys.javafx.controllers.component.TableFxComponent;
import com.mpp.librarysys.javafx.helper.AppAbstractFxController;
import com.mpp.librarysys.javafx.helper.NavigationManager;
import com.mpp.librarysys.lms.entities.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    private TableFxComponent tableFxComponent;

    @Autowired
    private GenericAdminController genericAdminController;

    @FXML
    private VBox rightVBox;

    @FXML
    private HBox genericAdminHBoxView;

    @FXML
    public void initialize() {
//        FXMLLoader fxmlLoader = NavigationManager.getFxmlLoader(applicationContext, getStage(), FxmlEnums.FxmlContainer.TABLE_COMPONENT);
//        TableFxComponent tableFxComponent = fxmlLoader.<TableFxComponent>getController();
    }

    public void populateNewData() {
        tableFxComponent.setStage(getStage());

        System.out.println(getStage().getProperties().get("Test"));
        List<String> columnList = Arrays.asList("id", "title", "iSBNNumber");
        ObservableList<Book> books = getBooks();

        Tab tab = genericAdminController.getTab();
        tab.setContent(tableFxComponent.createTableView(books, columnList));
    }

    private ObservableList<Book> getBooks() {
        ObservableList<Book> bookEntities = FXCollections.observableArrayList();
        Book book = new Book();
        book.setTitle("Java");
        book.setId(1);
        Book book1 = new Book();
        book1.setTitle("Spring");
        book1.setId(2);
        bookEntities.add(book);
        bookEntities.add(book1);
        return bookEntities;
    }

}
