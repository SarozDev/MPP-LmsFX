package com.mpp.librarysys.javafx.controllers;

import com.mpp.librarysys.javafx.helper.AppAbstractFxController;
import com.mpp.librarysys.javafx.util.AppFxUtil;
import com.mpp.librarysys.lms.entities.User;
import com.mpp.librarysys.lms.entities.enums.RoleEnum;
import com.mpp.librarysys.lms.entities.general.Address;
import com.mpp.librarysys.lms.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
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
    private Label roleNoteLabel;
    @FXML
    private CheckBox roleAdminCB;
    @FXML
    private CheckBox roleLibCB;


    @FXML
    private Button saveButton;


    @FXML
    public void initialize() {
        String roleNoteText = "If no any role is selected, it default to 'library-member' with no login access";
        roleNoteLabel.setText(roleNoteText);
        this.saveButton.setOnAction(actionEvent -> {
            onMemberSaveClick();
        });
    }

    private void onMemberSaveClick() {
        HashMap<String, Pair<String, String>> hashMap = new LinkedHashMap<>() {{
            put("Name", new Pair<>(userNameField.getText(), "Username can't be empty"));
            put("Password", new Pair<>(passwordField.getText(), "Password can't be empty"));
            put("FirstName", new Pair<>(streetField.getText(), "First Name can't be empty"));
            put("LastName", new Pair<>(streetField.getText(), "Last Name can't be empty"));
            put("Street", new Pair<>(streetField.getText(), "Street can't be empty"));
            put("City", new Pair<>(cityField.getText(), "City can't be empty"));
            put("State", new Pair<>(cityField.getText(), "State can't be empty"));
            put("Zip", new Pair<>(zipField.getText(), "Zip can't be empty"));
        }};
        boolean ifAnyFieldIsEmpty = AppFxUtil.showAlertIfAnyFieldIsEmpty(hashMap);
        if (ifAnyFieldIsEmpty) {
            return;
        }

        User user = new User();
        user.setUserName(userNameField.getText());
        user.setPassword(passwordField.getText());
        user.setFirstName(firstNameField.getText());
        user.setLastName(lastNameField.getText());
        Set<RoleEnum> roleEnumSet = new HashSet<>();
        if (roleAdminCB.isSelected()) {
            roleEnumSet.add(RoleEnum.ADMIN);
        }
        if (roleLibCB.isSelected()) {
            roleEnumSet.add(RoleEnum.LIBRARIAN);
        }
        if (!roleAdminCB.isSelected() && !roleLibCB.isSelected()) {
            roleEnumSet.clear();
        }
        user.setRoles(roleEnumSet);
        Address address = new Address();
        address.setStreet(this.streetField.getText());
        address.setCity(this.cityField.getText());
        address.setState(this.stateField.getText());
        address.setZip(this.zipField.getText());
        user.setAddress(address);
        user.setEnabled(true);
        user.setContact(contactField.getText());
        User userSaved = userService.addNewUser(user);
        clearForm();
        showUserSavedSuccessAlert(userSaved);
    }


    private void showUserSavedSuccessAlert(User userSaved) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("User saved successfully ");
        alert.setContentText(userSaved.toString());
        alert.showAndWait();
    }

    private void clearForm() {
        userNameField.clear();
        passwordField.clear();
        firstNameField.clear();
        lastNameField.clear();
        contactField.clear();
        streetField.clear();
        cityField.clear();
        stateField.clear();
        zipField.clear();
        roleLibCB.setSelected(false);
        roleAdminCB.setSelected(false);
    }


}
