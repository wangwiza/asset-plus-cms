// package ca.mcgill.ecse.assetplus.controller;
package ca.mcgill.ecse.assetplus.javafx.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet1Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet6Controller;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceNote;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceTicket;
import ca.mcgill.ecse.assetplus.controller.TOUser;
import ca.mcgill.ecse.assetplus.controller.Util;
import ca.mcgill.ecse.assetplus.javafx.AssetPlusFxmlView;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class TicketsPageController {

  @FXML
  private AnchorPane maintenanceTicketsViewAnchorPane;
  // Table and columns
  @FXML
  private TableView<TOMaintenanceTicket> ticketsTable;
  @FXML
  private TableColumn<TOMaintenanceTicket, Integer> id; 
  @FXML
  private TableColumn<TOMaintenanceTicket, String> status;
  @FXML
  private TableColumn<TOMaintenanceTicket, Date> raisedOnDate;
  @FXML
  private TableColumn<TOMaintenanceTicket, String> description;
  @FXML
  private TableColumn<TOMaintenanceTicket, Integer> floorNumber;
  @FXML
  private TableColumn<TOMaintenanceTicket, Integer> roomNumber;
  @FXML
  private TableColumn<TOMaintenanceTicket, String> assetName;
  @FXML
  private TableColumn<TOMaintenanceTicket, List<String>> imageURLs;
  @FXML
  private TableColumn<TOMaintenanceTicket, String> raisedByEmail;
  @FXML
  private TableColumn<TOMaintenanceTicket, String> fixerEmail;
  @FXML
  private TableColumn<TOMaintenanceTicket, String> priority;
  @FXML
  private TableColumn<TOMaintenanceTicket, String> timeToResolve;
  @FXML
  private TableColumn<TOMaintenanceTicket, Boolean> approvalRequired;
  @FXML
  private TableColumn<TOMaintenanceTicket, Integer> lifeSpan;
  @FXML
  private TableColumn<TOMaintenanceTicket, Date> purchaseDate;
  @FXML
  private TableColumn<TOMaintenanceTicket, TOMaintenanceNote> notes;

  // Buttons and fields
  @FXML
  private TextField hotelStaffIDTextField;
  @FXML
  private Button startButton;
  @FXML
  private Button completeButton;

  // For accessing all the maintenance tickets
  ObservableList<TOMaintenanceTicket> maintenanceTicketsList;

  public void refresh() {
    maintenanceTicketsList = FXCollections.observableArrayList(AssetPlusFeatureSet6Controller.getTickets());
    id.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, Integer>("id"));
    status.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, String>("status"));
    raisedOnDate.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, Date>("raisedOnDate"));
    description.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, String>("description"));
    floorNumber.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, Integer>("floorNumber"));
    roomNumber.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, Integer>("roomNumber"));
    assetName.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, String>("assetName"));
    imageURLs.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, List<String>>("imageURLs"));
    raisedByEmail.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, String>("raisedByEmail"));
    priority.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, String>("priority"));
    fixerEmail.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, String>("fixerEmail"));
    timeToResolve.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, String>("timeToResolve"));
    approvalRequired.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, Boolean>("approvalRequired"));
    lifeSpan.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, Integer>("lifeSpan"));
    purchaseDate.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, Date>("purchaseDate"));
    notes.setCellValueFactory(new PropertyValueFactory<TOMaintenanceTicket, TOMaintenanceNote>("notes"));
    ticketsTable.setItems(maintenanceTicketsList);
  }

  public void initialize() {
    refresh();
}

  @FXML
  public void sortByStaffIDButton(ActionEvent event) {
    String staffID = hotelStaffIDTextField.getText();     // change hotel staff id for the email of the person it's assigned to.
    if (staffID == ""){
      refresh();
    }
    else{
      int staffIDInt = Integer.parseInt(staffID);
      List<TOMaintenanceTicket> sortedTickets = new ArrayList<>();
      for (TOMaintenanceTicket ticket : AssetPlusFeatureSet6Controller.getTickets()) {
        if (ticket.getId() == staffIDInt){
          sortedTickets.add(ticket);
        }
    }
    ticketsTable.setItems(FXCollections.observableArrayList(sortedTickets));
    }
  
  }

  @FXML
  void startClicked(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../pages/TicketsPage.fxml"));
    // AnchorPane nextAnchorPane = (AnchorPane) loader.load();
    Integer ticketId = ticketsTable.getSelectionModel().getSelectedItem().getId();

    Util.startTicket(ticketId);

    // UpdateUserController startTicketController = loader.getController();
    // updateUserController.setEmail((userTableView.getSelectionModel().getSelectedItem()).getEmail());
    // updateUserController.setName((userTableView.getSelectionModel().getSelectedItem()).getName());
    // updateUserController
    // .setPassword((userTableView.getSelectionModel().getSelectedItem()).getPassword());
    // updateUserController
    // .setPhone((userTableView.getSelectionModel().getSelectedItem()).getPhoneNumber());
    // startTicketController.getChildren().removeAll();
    // startTicketController.getChildren().setAll(nextAnchorPane);
  }

  @FXML
  void completeClicked(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../pages/TicketsPage.fxml"));
    // AnchorPane nextAnchorPane = (AnchorPane) loader.load();
    Integer ticketId = ticketsTable.getSelectionModel().getSelectedItem().getId();

    Util.completeTicket(ticketId);

    // UpdateUserController startTicketController = loader.getController();
    // updateUserController.setEmail((userTableView.getSelectionModel().getSelectedItem()).getEmail());
    // updateUserController.setName((userTableView.getSelectionModel().getSelectedItem()).getName());
    // updateUserController
    // .setPassword((userTableView.getSelectionModel().getSelectedItem()).getPassword());
    // updateUserController
    // .setPhone((userTableView.getSelectionModel().getSelectedItem()).getPhoneNumber());
    // startTicketController.getChildren().removeAll();
    // startTicketController.getChildren().setAll(nextAnchorPane);
  }

}


