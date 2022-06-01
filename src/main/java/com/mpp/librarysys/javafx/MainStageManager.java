package com.mpp.librarysys.javafx;

import com.mpp.librarysys.javafx.util.FxmlView;
import com.mpp.librarysys.javafx.util.NavigationManager;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MainStageManager implements ApplicationListener<MppFxApplication.StageReadyEvent> {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(MppFxApplication.StageReadyEvent event) {

        Stage stage = event.getStage();
        stage.setTitle("Library Management System");
        NavigationManager.showFxmlView(applicationContext, stage, FxmlView.LOGIN_VIEW);
    }


}