package com.mpp.librarysys.javafx.controllers;

import com.mpp.librarysys.javafx.controllers.component.TableFxComponent;
import com.mpp.librarysys.javafx.helper.AppAbstractFxController;
import com.mpp.librarysys.lms.entities.Book;
import com.mpp.librarysys.lms.entities.CheckOutRecordBook;
import com.mpp.librarysys.lms.entities.User;
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
public class MemberFxController extends AppAbstractFxController {

    @Autowired
    private TableFxComponent tableFxComponent;

    @Autowired
    private CheckoutService checkoutService;

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
    private TableView tblMemberView;


    @FXML
    public void initialize() {


        this.btnMemberSearch.setOnAction(actionEvent -> {
            onMemberSearchClicked();
        });
    }


    public void showNow() {
        tableFxComponent.setStage(getStage());
        // add elements
        populateCheckoutRecord();

    }

    private void populateCheckoutRecord() {
//        membersTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Tab 1: User List view
        List<String> columnList = Arrays.asList("id", "checkOutDate", "DueDate");
        ObservableList<CheckOutRecordBook> checkOutObs = CheckoutService.<CheckOutRecordBook>getCheckOutObs();
        //VBox checkOutRecordTableView = tableFxComponent.createTableViewVBox(checkOutObs, columnList);
        tableFxComponent.showScreen(tblMemberView,checkOutObs,columnList);
    }

    private void onMemberSearchClicked() {
        ObservableList<CheckOutRecordBook> checkOutObs = CheckoutService.<CheckOutRecordBook>getMembersById(1);
        List<String> columnList = Arrays.asList("id", "checkOutDate", "DueDate");
        tableFxComponent.showScreen(tblMemberView,checkOutObs,columnList);

    }


}
