package com.mpp.librarysys.javafx.helper;

import javafx.stage.Stage;
import org.springframework.stereotype.Component;

@Component
public abstract class AppAbstractFxController {

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
