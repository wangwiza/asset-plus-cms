package ca.mcgill.ecse.assetplus.javafx.controllers;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet1Controller;
import static ca.mcgill.ecse.assetplus.javafx.controllers.ViewUtils.successful;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddUserController {

    @FXML
    private TextField AddEmail;

    @FXML
    private TextField AddName;

    @FXML
    private TextField AddPassword;

    @FXML
    private TextField AddPhone;

    @FXML
    private Button AddUser;

    @FXML
    private Button cancelAddUser;

    @FXML
    void addUserClicked(ActionEvent event) {
        String name = AddEmail.getText();
        String email = AddEmail.getText();
        String password = AddPassword.getText();
        String phone = AddPhone.getText();
        if (successful(AssetPlusFeatureSet1Controller.addEmployeeOrGuest(email, password, name, phone, true))) {
            AddEmail.setText("");
            AddName.setText("");
            AddPassword.setText("");
            AddPhone.setText("");
        }
        // NEED TO RETURN TO USER VIEW PAGE, IF APPLICABLE

    }

    @FXML
    void cancelAddUserClicked(ActionEvent event) {
            AddEmail.setText("");
            AddName.setText("");
            AddPassword.setText("");
            AddPhone.setText("");
            // NEED TO RETURN TO USER VIEW PAGE
    }

}
