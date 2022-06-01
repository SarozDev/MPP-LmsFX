package com.mpp.librarysys.javafx.login;

import com.mpp.librarysys.javafx.util.FxmlView;
import com.mpp.librarysys.javafx.util.NavigationManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginFxController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField passwordField;

    @Autowired
    private ApplicationContext applicationContext;

    @FXML
    private Button loginButton;


//    @FXML
//    public void initialize () {
//        this.button.setOnAction(actionEvent -> this.label.setText(this.hostServices.getDocumentBase()));
//    }

    @FXML
    public void onLoginClick() throws IOException {

        System.out.println("On Login Clicked");
        // check username/password
        if (false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Oops!!!");
            alert.setHeaderText("Invalid Username/ Password ");
            alert.setContentText("Please recheck your username and password, if you forgot contact library administrator");
            alert.showAndWait();
            return;
        }

        // closes current stage
        NavigationManager.closeStage(loginButton);

        // opens admin dash view
        Stage adminDashStage = new Stage();
        adminDashStage.setTitle("Admin Dash View");
        NavigationManager.showFxmlView(applicationContext, adminDashStage, FxmlView.ADMIN_DASH_VIEW);

    }

}