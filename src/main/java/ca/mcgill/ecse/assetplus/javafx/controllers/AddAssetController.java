package ca.mcgill.ecse.assetplus.javafx.controllers;

import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet1Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet3Controller;
import static ca.mcgill.ecse.assetplus.javafx.controllers.ViewUtils.successful;
import java.sql.Date;
import static ca.mcgill.ecse.assetplus.javafx.controllers.ViewUtils.sceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddAssetController {

    @FXML
    private TextField AddAssetType;

    @FXML
    private TextField AddFloorNumber;

    @FXML
    private TextField AddRoomNumber;

    @FXML
    private TextField AddPurchaseDate;

    @FXML
    private Button AddAsset;

    @FXML
    private Button cancelAddAsset;

    @FXML
    private AnchorPane addAssetAnchorPane;

    @FXML
    void addAssetClicked(ActionEvent event) {
        String assetType = AddAssetType.getText();
        Integer floorNumber = Integer.parseInt(AddFloorNumber.getText());
        Integer roomNumber = Integer.parseInt(AddRoomNumber.getText());
        Date purchaseDate = Date.valueOf(AddPurchaseDate.getText());
        if (successful(AssetPlusFeatureSet3Controller.addSpecificAsset(1, floorNumber, roomNumber,
                purchaseDate, assetType))) {
                AddAssetType.setText("");
                AddFloorNumber.setText("");
                AddRoomNumber.setText("");
                AddPurchaseDate.setText("");
                sceneSwitch(addAssetAnchorPane, "../pages/AssetView.fxml");
            }      
    }

    @FXML
    void cancelAddAssetClicked(ActionEvent event) {
            AddAssetType.setText("");
            AddFloorNumber.setText("");
            AddRoomNumber.setText("");
            AddPurchaseDate.setText("");
            sceneSwitch(addAssetAnchorPane, "../pages/AssetView.fxml");
    }

}
