package ca.mcgill.ecse.assetplus.controller;

import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import java.sql.Date;
import java.util.List;

public class AssetPlusFeatureSet3Controller {

  private static AssetPlus ap = AssetPlusApplication.getAssetPlus();

  public static String addSpecificAsset(int assetNumber, int floorNumber, int roomNumber,
      Date purchaseDate, String assetTypeName) throws Exception {
      
    // Should this be there even if it is not in Step definitions
    if (assetTypeName == null || assetTypeName.equals("")) {
        throw new Exception("Please provide a valid Asset Type name, it cannot be null or empty.");
    }

    AssetType assetType = AssetType.getWithName(assetTypeName);

    if (assetType == null) {
        //should we throw or return
        throw new Exception("The asset type does not exist");
    }

    if (floorNumber < 0) {
        throw new Exception("The floor number shall not be less than 0");
    }

    if (roomNumber < -1) {
        throw new Exception("The room number shall not be less than -1");
    }

    if (assetNumber < 1) {
        throw new Exception("The asset number shall not be less than 1");
    }

    boolean isAdded = ap.addSpecificAsset(ap.addSpecificAsset(assetNumber, floorNumber, roomNumber, purchaseDate, assetType));
    
    // Should this be there even if it is not in Step definitions
    if (!isAdded) {
        throw new Exception("A duplicate specific asset was found. Specific asset has not been added. Please try again.");
    }

    return "";

    //Should there be a try catch block (is it possible to have more errors than in the Cucumber)
  }

  public static String updateSpecificAsset(int assetNumber, int newFloorNumber, int newRoomNumber,
      Date newPurchaseDate, String newAssetTypeName) throws Exception {
      
    // Should this be there even if it is not in Step definitions
    if (newAssetTypeName == null || newAssetTypeName.equals("")) {
        throw new Exception("Please provide a valid Asset Type name, it cannot be null or empty.");
    }

    AssetType newAssetType = AssetType.getWithName(newAssetTypeName);

    if (newAssetType == null) {
        throw new Exception("The asset type does not exist");
    }

    if (newFloorNumber < 0) {
        throw new Exception("The floor number shall not be less than 0");
    }

    if (newRoomNumber < -1) {
        throw new Exception("The room number shall not be less than -1");
    }

    List<SpecificAsset> specificAssetsList = ap.getSpecificAssets();
    boolean isFound = false;
    for (SpecificAsset specificAsset : specificAssetsList) {
        if (specificAsset.getAssetNumber() == assetNumber) {
            specificAsset.setFloorNumber(newFloorNumber);
            specificAsset.setRoomNumber(newRoomNumber);
            specificAsset.setPurchaseDate(newPurchaseDate);
            specificAsset.setAssetType(newAssetType);
            isFound = true;
            break;
        }
    }

    // Should this be there even if it is not in Step definitions
    if (!isFound) {
        throw new Exception("No specific asset with this asset number was found. Please try again.");
    }
    return "";
  }

  public static void deleteSpecificAsset(int assetNumber) throws Exception {
    List<SpecificAsset> specificAssetsList = ap.getSpecificAssets();
    boolean isFound = false;
    for (SpecificAsset specificAsset : specificAssetsList) {
        if (specificAsset.getAssetNumber() == assetNumber) {
            specificAsset.delete();
            isFound = true;
            break;
        }
    }
    //In cucumber it says successfully delete asset that does not exist, so what happens in controller if asset is not found

    // Should this be there even if it is not in Step definitions
    if (!isFound) {
        throw new Exception("No specific asset with this asset number was found. Please try again.");
    }
  } 
}
