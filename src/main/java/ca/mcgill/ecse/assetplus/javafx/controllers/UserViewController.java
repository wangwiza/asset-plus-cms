package ca.mcgill.ecse.assetplus.javafx.controllers;

import static ca.mcgill.ecse.assetplus.javafx.controllers.ViewUtils.sceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class UserViewController {

    @FXML
    private Button addUser;

    @FXML
    private Button updatePassword;

    @FXML
    private TableView<?> userTableView;

    @FXML
    private AnchorPane userViewAnchorPane;

    @FXML
    void addUserClicked(ActionEvent event) {
        sceneSwitch(userViewAnchorPane, "../pages/AddUser.fxml");
    }

    @FXML
    void updatePasswordClicked(ActionEvent event) {
        sceneSwitch(userViewAnchorPane, "../pages/UpdatePassword.fxml");
    }

}
