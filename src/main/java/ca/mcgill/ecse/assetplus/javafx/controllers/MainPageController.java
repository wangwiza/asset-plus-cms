package ca.mcgill.ecse.assetplus.javafx.controllers;

import ca.mcgill.ecse.assetplus.javafx.AssetPlusFxmlView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

public class MainPageController {
  @FXML
  private ToggleGroup dashboardGroup;
  @FXML
  private ToggleButton dashboardUsersButton;
  @FXML
  private ToggleButton dashboardAssetsButton;
  @FXML
  private ToggleButton dashboardAssetTypesButton;
  @FXML
  private ToggleButton dashboardTicketsButton;
  @FXML
  private ToggleButton dashboardImagesButton;
  // Panes
  @FXML
  private Pane usersPane;
  @FXML
  private Pane imagesPane;
  @FXML
  private Pane ticketsPane;
  @FXML
  private Pane assetTypesPane;
  @FXML
  private Pane assetsPane;
  @FXML
  private Pane maintenanceTicketsPane;

  @FXML
  public void initialize() {
    // always keep one button selected
    dashboardGroup.selectedToggleProperty().addListener((obsVal, oldVal, newVal) -> {
      if (newVal == null)
        oldVal.setSelected(true);
    });
  }

  @FXML
  public void dashboardUsersSelected(ActionEvent event) {
    usersPane.toFront();
  }

  @FXML
  public void dashboardImagesSelected(ActionEvent event) {
    imagesPane.toFront();
  }

  @FXML
  public void dashboardTicketsSelected(ActionEvent event) { 
    ticketsPane.toFront(); 
  }

  @FXML
  public void dashboardAssetTypesSelected(ActionEvent event) {
    assetTypesPane.toFront();
  }

  @FXML
  public void dashboardAssetsSelected(ActionEvent event) {
    assetsPane.toFront();
  }

  public void dashboardViewMaintenanceTicketsSelected(ActionEvent event) {
    maintenanceTicketsPane.toFront();
  }

}
