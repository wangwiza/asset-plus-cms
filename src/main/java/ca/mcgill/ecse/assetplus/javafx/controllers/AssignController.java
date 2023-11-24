package ca.mcgill.ecse.assetplus.javafx.controllers;

import java.util.ArrayList;
import ca.mcgill.ecse.assetplus.controller.TOUser;
import ca.mcgill.ecse.assetplus.controller.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

public class AssignController {

    @FXML
    private Button assign;

    @FXML
    private ChoiceBox<TOUser> assignedHotelStaffSelect;

    @FXML
    private ChoiceBox<String> prioritySelect;

    @FXML
    private CheckBox requireApprovalSelect;

    @FXML
    private ChoiceBox<String> timeEstimateSelect;

    @FXML
    void assignClicked(ActionEvent event) {
        
    }

    public void initialize() {
        ArrayList<TOUser> user = TOUser.getAllUsers();
        assignedHotelStaffSelect.getItems().addAll(user);
        prioritySelect.getItems().addAll(Util.getPriorities());
        timeEstimateSelect.getItems().addAll(Util.getTimeEstimate());

    }

}
