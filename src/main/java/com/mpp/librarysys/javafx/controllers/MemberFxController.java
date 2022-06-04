package com.mpp.librarysys.javafx.controllers;

import com.mpp.librarysys.javafx.controllers.component.TableFxComponent;
import com.mpp.librarysys.javafx.helper.AppAbstractFxController;
import com.mpp.librarysys.javafx.util.AppFxUtil;
import com.mpp.librarysys.javafx.util.CheckOutHistoryDTO;
import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.BookCopy;
import com.mpp.librarysys.lms.entities.CheckOutRecordBook;
import com.mpp.librarysys.lms.entities.LibraryMember;
import com.mpp.librarysys.lms.services.CheckoutService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberFxController extends AppAbstractFxController {

    @Autowired
    private CheckoutService checkoutService;

    @Autowired
    private ApplicationContext applicationContext;

    @FXML
    private Tab tabViewCheckOut;
    @FXML
    private Tab tabAddCheckOut;

    @FXML
    private ScrollPane tblMemberScrollPane;

    @FXML
    private Button btnMemberSearch;

    @FXML
    private TextField fieldMemberId;

    @FXML
    private TextField isbnTextField;

    @FXML
    private TableView tblMemberView;


    @FXML
    public void initialize() {
        this.btnMemberSearch.setOnAction(actionEvent -> {
            onCheckoutRecordBookHistorySearch();
        });
    }


    public void showNow() {
        ObservableList<Object> objects = FXCollections.emptyObservableList();
        TableFxComponent tableFxComponent = applicationContext.getBean(TableFxComponent.class);
        tableFxComponent.showScreen(tblMemberView, objects, new ArrayList<>());
    }

    private void onCheckoutRecordBookHistorySearch() {
        String memberID = fieldMemberId.getText();
        String isbn = isbnTextField.getText();
        if (StringUtils.hasText(memberID) && !AppFxUtil.isLong(memberID)) {
            Alert alert = AppFxUtil.createAlert(Alert.AlertType.WARNING, "Oops !!!", "Member ID must be numeric", "");
            alert.showAndWait();
            return;
        }
        if (!StringUtils.hasText(memberID) && !StringUtils.hasText(isbn)) {
            Alert alert = AppFxUtil.createAlert(Alert.AlertType.WARNING, "Oops !!!", "You must provide at least one search input", "");
            alert.showAndWait();
            return;
        }

        Long libraryMemberId = Long.parseLong(fieldMemberId.getText());
        String isbnNumber = isbnTextField.getText();
        ObservableList<CheckOutRecordBook> checkOutObs = checkoutService.<CheckOutRecordBook>getCheckoutRecordBookByMembersByIdOrIsbn(libraryMemberId, isbnNumber);
        List<CheckOutHistoryDTO> checkOutHistoryDTOS = checkOutObs.stream().parallel().map(checkOutRecordBook -> {
            BookCopy bookCopy = checkOutRecordBook.getBookCopy();
            Book book = checkOutRecordBook.getBookCopy().getBook();
            LibraryMember libraryMember = checkOutRecordBook.getLibraryMember();
            CheckOutHistoryDTO checkOutHistoryDTO = new CheckOutHistoryDTO();
            checkOutHistoryDTO.setCheckOutDate(checkOutRecordBook.getCheckOutDate());
            checkOutHistoryDTO.setDueDate(checkOutRecordBook.getDueDate());
            checkOutHistoryDTO.setBookCopyNumber(bookCopy.getCopyNumber());
            checkOutHistoryDTO.setBookName(book.getTitle());
            checkOutHistoryDTO.setIsbnNumber(book.getISBNNumber());
            checkOutHistoryDTO.setMemberId(String.valueOf(libraryMember.getId()));
            String memberFullName = libraryMember.getUser().getFirstName() + " " + libraryMember.getUser().getLastName();
            checkOutHistoryDTO.setMemberName(memberFullName);
            checkOutHistoryDTO.setLibrarianId(String.valueOf(checkOutRecordBook.getLibrarianUser().getId()));
            boolean dataHasPassed = checkOutRecordBook.getDueDate().isAfter(LocalDate.now());
            checkOutHistoryDTO.setDatePassed(dataHasPassed ? "Yes" : "No");
            return checkOutHistoryDTO;
        }).collect(Collectors.toList());

        List<String> columnList = Arrays.asList("id", "bookName", "isbnNumber", "bookCopyNumber", "memberId", "memberName", "datePassed");
        TableFxComponent tableFxComponent = applicationContext.getBean(TableFxComponent.class);
        tableFxComponent.showScreen(tblMemberView, FXCollections.observableList(checkOutHistoryDTOS), columnList);

    }


}
