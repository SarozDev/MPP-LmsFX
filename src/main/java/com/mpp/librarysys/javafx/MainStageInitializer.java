package com.mpp.librarysys.javafx;

import com.mpp.librarysys.javafx.constants.FxmlEnums;
import com.mpp.librarysys.javafx.helper.NavigationManager;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MainStageInitializer implements ApplicationListener<SpringBootFXApplication.StageReadyEvent> {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(SpringBootFXApplication.StageReadyEvent event) {

        Stage stage = event.getStage();
        stage.setTitle("Library Management System");
        NavigationManager.switchScene(applicationContext, stage, FxmlEnums.FxmlView.LOGIN_VIEW);
    }


}