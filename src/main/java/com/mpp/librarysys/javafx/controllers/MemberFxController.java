package com.mpp.librarysys.javafx.controllers;

import com.mpp.librarysys.javafx.controllers.component.TableFxComponent;
import com.mpp.librarysys.javafx.helper.AppAbstractFxController;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberFxController extends AppAbstractFxController {

    @Autowired
    private TableFxComponent tableFxComponent;

    @FXML
    public void initialize() {
    }

    public void showNow() {
        tableFxComponent.setStage(getStage());

    }
}
