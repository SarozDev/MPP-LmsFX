package com.mpp.librarysys.javafx.controllers.component;

import com.mpp.librarysys.javafx.helper.AppAbstractFxController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TableFxComponent extends AppAbstractFxController {

    @FXML
    public void initialize() {
    }

    public VBox createTableView(ObservableList<?> observableList, List<String> columns) {

        TableView tableView = new TableView();
        showScreen(tableView, observableList, columns);
        VBox vBox = new VBox(tableView);
        return vBox;
    }

    public void showScreen(TableView tableView, ObservableList<?> obsDataList, List<String> columns) {
        tableView.setItems(obsDataList);
        tableView.getColumns().clear();
        for (String column : columns) {
            TableColumn tc = new TableColumn(column);
            tc.setCellValueFactory(new PropertyValueFactory(column));
            tableView.getColumns().add(tc);
        }

    }

}
