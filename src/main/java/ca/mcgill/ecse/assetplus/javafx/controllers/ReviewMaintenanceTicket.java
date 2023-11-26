package ca.mcgill.ecse.assetplus.javafx.controllers;

import static ca.mcgill.ecse.assetplus.javafx.controllers.ViewUtils.sceneSwitch;
import ca.mcgill.ecse.assetplus.controller.TOAsset;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ReviewMaintenanceTicket {

    @FXML
    private AnchorPane ReviewMaintenanceTicketPane;

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
    private ChoiceBox<MaintenanceTicket.PriorityLevel> prioritySelect;

    @FXML
    private CheckBox requireApprovalSelect;

    @FXML
    private ChoiceBox<MaintenanceTicket.TimeEstimate> timeEstimateSelect;

    @FXML
    private Button updateButton;

    @FXML
    private TextField idField;

    private TOMaintenanceTicket currentTicket;

    public void initialize(TOMaintenanceTicket ticket) {
        // Needed asset id
        currentTicket = ticket;
        idField.setText(String.valueOf(currentTicket.getId()));

        TOAsset a = TOAsset.getAssetFromTicket(currentTicket.getId());
        if (a != null) {
            assetNumberField.setText(String.valueOf(TOAsset.getAssetFromTicket(currentTicket.getId()).getAssetNumber()));
        }

        dateField.setValue(ticket.getRaisedOnDate().toLocalDate());
        descriptionField.setText(ticket.getDescription());
        imageField.setText("NOT IMPLEMENTED");
        assignedField.setText(currentTicket.getFixedByEmail());

        prioritySelect.setItems(FXCollections.observableArrayList(MaintenanceTicket.PriorityLevel.values()));
        if (currentTicket.getPriority() != null) {
            prioritySelect
                    .setValue(MaintenanceTicket.PriorityLevel.valueOf(currentTicket.getPriority()));
        }

        timeEstimateSelect.setItems(FXCollections.observableArrayList(MaintenanceTicket.TimeEstimate.values()));
        if (currentTicket.getTimeToResolve() != null) {
            timeEstimateSelect.setValue(
                    MaintenanceTicket.TimeEstimate.valueOf(currentTicket.getTimeToResolve()));
        }

        requireApprovalSelect.setSelected(currentTicket.isApprovalRequired());
    }

    @FXML
    void cancelButtonClicked() {
        reset();
    }

    @FXML
    void reset() {
        sceneSwitch(ReviewMaintenanceTicketPane, "../pages/TicketsPage.fxml");
    }
}
