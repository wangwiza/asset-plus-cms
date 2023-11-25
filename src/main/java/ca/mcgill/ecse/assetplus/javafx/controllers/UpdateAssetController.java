package ca.mcgill.ecse.assetplus.javafx.controllers;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet3Controller;
import static ca.mcgill.ecse.assetplus.javafx.controllers.ViewUtils.successful;
import java.sql.Date;
import static ca.mcgill.ecse.assetplus.javafx.controllers.ViewUtils.sceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class UpdateAssetController {

    @FXML
    private TextField UpdateAssetType;

    @FXML
    private TextField UpdateFloorNumber;

    @FXML
    private TextField UpdateRoomNumber;

    @FXML
    private TextField UpdatePurchaseDate;

    @FXML
    private Button cancelUpdateAsset;

    @FXML
    private Button updateAsset;

    @FXML
    private AnchorPane updateAssetAnchorPane;

    @FXML
    void cancelUpdateAssetClicked(ActionEvent event) {
        UpdateAssetType.setText("");
        UpdateFloorNumber.setText("");
        UpdateRoomNumber.setText("");
        UpdatePurchaseDate.setText("");
        sceneSwitch(updateAssetAnchorPane, "../pages/AssetView.fxml");
    }

    @FXML
    void updateAssetClicked(ActionEvent event) {
        String assetType = UpdateAssetType.getText();
        Integer floorNumber = Integer.parseInt(UpdateFloorNumber.getText());
        Integer roomNumber = Integer.parseInt(UpdateRoomNumber.getText());
        Date purchaseDate = Date.valueOf(UpdatePurchaseDate.getText());
        if (successful(AssetPlusFeatureSet3Controller.updateSpecificAsset(1, floorNumber, roomNumber,
            purchaseDate, assetType))) {
            UpdateAssetType.setText("");
            UpdateFloorNumber.setText("");
            UpdateRoomNumber.setText("");
            UpdatePurchaseDate.setText("");
            sceneSwitch(updateAssetAnchorPane, "../pages/AssetView.fxml");
        }
    }

    public void setAssetType(String assetType) {
        UpdateAssetType.setText(assetType);
    }

    public void setFloorNumber(Integer floorNumber) {
      UpdateFloorNumber.setText(floorNumber != null ? floorNumber.toString() : "");
    }

    public void setRoomNumber(Integer roomNumber) {
      UpdateRoomNumber.setText(roomNumber != null ? roomNumber.toString() : "");
    }

    public void setPurchaseDate(Date purchaseDate) {
      if (purchaseDate != null) {
        UpdatePurchaseDate.setText(purchaseDate.toString());
    } else {
        UpdatePurchaseDate.setText("");
    }
    }

}
