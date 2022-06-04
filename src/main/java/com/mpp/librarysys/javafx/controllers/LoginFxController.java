package com.mpp.librarysys.javafx.controllers;

import com.mpp.librarysys.javafx.constants.FxmlEnums;
import com.mpp.librarysys.javafx.helper.AppAbstractFxController;
import com.mpp.librarysys.javafx.helper.NavigationManager;
import com.mpp.librarysys.javafx.util.AppFxUtil;
import com.mpp.librarysys.lms.entities.User;
import com.mpp.librarysys.lms.entities.enums.RoleEnum;
import com.mpp.librarysys.lms.services.AuthService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public void initialize() {
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
        User user = this.authService.authenticateUser(username, password);
        if (null == user) {
            String titleText = "Oops !!!";
            String headerText = "Invalid Username/ Password";
            String contentText = "Please recheck your username and password, \n if you forgot contact library administrator.";
            Alert alert = AppFxUtil.createAlert(Alert.AlertType.ERROR, titleText, headerText, contentText);
            alert.showAndWait();
            return;
        }

        checkAuthorizationLevel(user);

    }

    private void checkAuthorizationLevel(User user) {
        // authorization level selection
        boolean hasAdminPrivilege = user.getRoles()
                .stream().anyMatch(roleEnum -> roleEnum.equals(RoleEnum.ADMIN));
        if (user.getRoles().size() > 1) {
            selectAuthorizationLevel();
        } else if (hasAdminPrivilege) {
            navigateToAdminDashboard();
        } else {
            navigateToLibrarianDashboard();
        }
    }


    private void selectAuthorizationLevel() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Action Required");
        alert.setHeaderText("Please choose the authorization level ");
        String contentText = "You have multiple privileges, \n" +
                "Please select the one you want to login with.";
        alert.setContentText(contentText);

        ButtonType adminDashButton = new ButtonType("Admin");
        ButtonType librarianDashButton = new ButtonType("Librarian");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(adminDashButton, librarianDashButton, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == adminDashButton) {
            navigateToAdminDashboard();
        } else if (result.get() == librarianDashButton) {
            navigateToLibrarianDashboard();
        } else {
            return;
        }
    }

    private void navigateToAdminDashboard() {
        // closes current stage
        getStage().close();
        Stage adminDashStage = new Stage();
        adminDashStage.setTitle("Admin View");
        adminDashStage.getProperties().put("backStage", getStage());
        AdminFxController adminFxController = NavigationManager.
                <AdminFxController>switchScene(applicationContext, adminDashStage, FxmlEnums.FxmlView.ADMIN_DASH_VIEW);
        adminFxController.showNow();
    }

    private void navigateToLibrarianDashboard() {
        // closes current stage
        getStage().close();
        Stage memberDashStage = new Stage();
        memberDashStage.setTitle("Librarian View");
        memberDashStage.getProperties().put("backStage", getStage());
        MemberFxController memberFxController = NavigationManager.
                <MemberFxController>switchScene(applicationContext, memberDashStage, FxmlEnums.FxmlView.MEMBER_DASH_VIEW);
        memberFxController.showNow();
    }


}