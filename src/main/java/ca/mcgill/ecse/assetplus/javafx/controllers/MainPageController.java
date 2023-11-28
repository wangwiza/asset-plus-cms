package ca.mcgill.ecse.assetplus.javafx.controllers;

import ca.mcgill.ecse.assetplus.javafx.AssetPlusFxmlView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
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
  private AnchorPane viewAnchorPane;

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
    ViewUtils.sceneSwitch((AnchorPane) viewAnchorPane, "../pages/UserView.fxml");
  }

  @FXML
  public void dashboardAssetTypesSelected(ActionEvent event) {
    ViewUtils.sceneSwitch((AnchorPane) viewAnchorPane, "../pages/AssetTypeView.fxml");
  }

  @FXML
  public void dashboardAssetsSelected(ActionEvent event) {
    ViewUtils.sceneSwitch((AnchorPane) viewAnchorPane, "../pages/AssetView.fxml");
  }

  public void dashboardViewMaintenanceTicketsSelected(ActionEvent event) {
    ViewUtils.sceneSwitch((AnchorPane) viewAnchorPane, "../pages/TicketsPage.fxml");
  }

}
