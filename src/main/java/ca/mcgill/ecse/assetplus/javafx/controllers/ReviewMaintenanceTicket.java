package ca.mcgill.ecse.assetplus.javafx.controllers;

import java.time.LocalDate;
import ca.mcgill.ecse.assetplus.controller.TOAsset;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceNote;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;

public class ReviewMaintenanceTicket {

    @FXML
    private TextField assetNumberField;

    @FXML
    private TextField assignedField;

    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker dateField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField imageField;

    @FXML
    private ListView<TOMaintenanceNote> noteList;

    @FXML
    private ChoiceBox<MaintenanceTicket.PriorityLevel> prioritySelect;

    @FXML
    private CheckBox requireApprovalSelect;

    @FXML
    private ChoiceBox<MaintenanceTicket.TimeEstimate> timeEstimateSelect;

    @FXML
    private Button updateButton;

    private TOMaintenanceTicket currentTicket;

    public void initialize(TOMaintenanceTicket ticket) {
        //Needed asset id
        currentTicket = ticket;
        assetNumberField.setText(String.valueOf(TOAsset.getAssetFromTicket(currentTicket.getId())));
        dateField.setValue(ticket.getRaisedOnDate().toLocalDate());
        descriptionField.setText(ticket.getDescription());
        imageField.setText("NOT IMPLEMENTED");
        assignedField.setText(currentTicket.getFixedByEmail());
        prioritySelect.setValue(MaintenanceTicket.PriorityLevel.valueOf(currentTicket.getPriority()));
        timeEstimateSelect.setValue(MaintenanceTicket.TimeEstimate.valueOf(currentTicket.getTimeToResolve()));
        requireApprovalSelect.setSelected(currentTicket.isApprovalRequired());
    }
}
