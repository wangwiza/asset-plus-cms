// package ca.mcgill.ecse.assetplus.controller;
package ca.mcgill.ecse.assetplus.javafx.controllers;

import java.time.LocalDate;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet1Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet6Controller;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceTicket;
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
  private TableView<TOMaintenanceTicket> ticketsTable;

  @FXML
  private TableColumn<MaintenanceTicket, String> ticketColumn;

  @FXML
  private TableColumn<MaintenanceTicket, String> statusColumn;

  @FXML
  private TableColumn<MaintenanceTicket, String> descriptionColumn;

  @FXML
  private TextField enteredstaffID;

  @FXML
  private Button submitStaffID;

  @FXML
  private Button startButton;

  @FXML
  private Button completeButton;

  @FXML
  public void initialize() {
    // Reset TextField
    enteredstaffID = new TextField();
    enteredstaffID.setText("");
    // Refresh
    AssetPlusFxmlView.getInstance().registerRefreshEvent(enteredstaffID);
    ticketsTable.addEventHandler(AssetPlusFxmlView.REFRESH_EVENT,
        e -> ticketsTable.setItems(getTicketsTableItems()));
    // register refreshable nodes
    AssetPlusFxmlView.getInstance().registerRefreshEvent(ticketsTable);
  }

  @FXML
  public void hotelStaffIDentered(ActionEvent event) {
    // get tickets given the enteredstaffID
    // display them on the table
    // still needs work
    AssetPlusFxmlView.getInstance().refresh();
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


  // For refreshing the table
  public ObservableList<TOMaintenanceTicket> getTicketsTableItems() {
    String staffID = enteredstaffID.getSelectedText();
    int staffIDInt = Integer.parseInt(staffID);
    if (staffID == null) {
      ViewUtils.showError("Please select a valid staffID");
      return FXCollections.emptyObservableList();
    }
    return FXCollections.observableList(AssetPlusFeatureSet6Controller.getTickets());
  }

}


