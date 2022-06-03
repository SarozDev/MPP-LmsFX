package com.mpp.librarysys.javafx.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Component;

@Component
public class GenericAdminController {

    @FXML
    public HBox topBtnAndSearch;

    @FXML
    public TabPane tabPane;

    public Tab getTab() {
        ObservableList<Tab> tabs = this.tabPane.getTabs();
        Tab tab = tabs.get(0);
        return tab;
    }

}
