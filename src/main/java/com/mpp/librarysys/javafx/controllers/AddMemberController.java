package com.mpp.librarysys.javafx.controllers;

import com.mpp.librarysys.javafx.helper.AppAbstractFxController;
import com.mpp.librarysys.lms.entities.User;
import com.mpp.librarysys.lms.entities.enums.RoleEnum;
import com.mpp.librarysys.lms.entities.general.Address;
import com.mpp.librarysys.lms.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AddMemberController extends AppAbstractFxController {

    @Autowired
    private UserService userService;

    @FXML
    public VBox userFormVBox;

    @FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;
    @FXML
    private TextField contactField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField zipField;

    @FXML
    private TextField stateField;

    @FXML
    private TextField cityField;

    @FXML
    private Button saveButton;


    @FXML
    public void initialize() {
        this.saveButton.setOnAction(actionEvent -> {
            onMemberSaveClick();
        });
    }

    private void onMemberSaveClick() {
        User user = new User();
        user.setUserName(this.userNameField.getText());
        user.setPassword(this.passwordField.getText());
        user.setRoles(Set.of(RoleEnum.LIBRARIAN));
        Address address = new Address();
        address.setStreet(this.streetField.getText());
        address.setCity(this.cityField.getText());
        address.setState(this.stateField.getText());
        address.setZip(this.zipField.getText());
        user.setAddress(address);
        user.setEnabled(true);
        user.setContact(contactField.getText());
        userService.addNewUser(user);
    }


}
