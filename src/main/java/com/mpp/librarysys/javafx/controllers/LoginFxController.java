package com.mpp.librarysys.javafx.controllers;

import com.mpp.librarysys.javafx.constants.FxmlEnums;
import com.mpp.librarysys.javafx.helper.AppAbstractFxController;
import com.mpp.librarysys.javafx.helper.NavigationManager;
import com.mpp.librarysys.javafx.util.AppFxUtil;
import com.mpp.librarysys.lms.entities.User;
import com.mpp.librarysys.lms.services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class LoginFxController extends AppAbstractFxController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AuthService authService;

    @FXML
    private VBox loginBox;

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;


    @FXML
    public void initialize()  {
        this.loginButton.setOnAction(actionEvent -> {
            onLoginClick();
        });
        this.loginBox.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                onLoginClick();
            }
        });
    }

    public void onLoginClick() {

        // check username/password
        String username = usernameField.getText();
        String password = passwordField.getText();
        User user = authService.authenticateUser(username, password);
        if (null == user) {
            String headerText = "Please recheck your username and password, \n if you forgot contact library administrator";
            String titleText = "Invalid Username/ Password";
            Alert alert = AppFxUtil.createAlert(Alert.AlertType.ERROR, titleText, headerText, "");
            alert.showAndWait();
            return;
        }

        // closes current stage
        getStage().close();

        if (false) {
            // opens admin dash view
            Stage adminDashStage = new Stage();
            adminDashStage.setTitle("Admin Dash View");
            adminDashStage.getProperties().put("Test", "Ok Working");
            AdminFxController adminFxController = NavigationManager.
                    <AdminFxController>switchScene(applicationContext, adminDashStage, FxmlEnums.FxmlView.ADMIN_DASH_VIEW);
            adminFxController.showNow();
        } else {
            // open member checkoutView
            Stage memberDashStage = new Stage();
            memberDashStage.setTitle("Member Dash View");
            memberDashStage.getProperties().put("Test", "Ok Working");
            MemberFxController memberFxController = NavigationManager.
                    <MemberFxController>switchScene(applicationContext, memberDashStage, FxmlEnums.FxmlView.MEMBER_DASH_VIEW);
            memberFxController.showNow();

        }


    }

}