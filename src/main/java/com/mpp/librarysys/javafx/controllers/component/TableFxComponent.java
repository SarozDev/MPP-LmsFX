package com.mpp.librarysys.javafx.controllers.component;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class TableFxComponent {

    private TableView tableView;

    public void showScreen(TableView tableView, ObservableList<?> obsDataList, List<String> columns) {
        tableView.setItems(obsDataList);
        tableView.getColumns().clear();
        for (String column : columns) {
            TableColumn tc = new TableColumn(column);
            tc.setCellValueFactory(new PropertyValueFactory(column));
            tableView.getColumns().add(tc);
        }

    }

    public TableView getTableView() {
        if (null == this.tableView) {
            this.tableView = new TableView();
        }
        return tableView;
    }
}
