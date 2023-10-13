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
    
    try {
        if (assetTypeName == null || assetTypeName.equals("")) {
            throw new Exception("Asset Type not found for the given name.");
        }

        AssetType assetType = AssetType.getWithName(assetTypeName);

        if (floorNumber < 0) {
            throw new Exception("Floor number cannot be negative.");
        }

        if (roomNumber < -1) {
            throw new Exception("Room number cannot be less than -1.");
        }

        if (assetNumber < 1) {
            throw new Exception("Asset number cannot be less than 1.");
        }

        boolean isAdded = ap.addSpecificAsset(ap.addSpecificAsset(assetNumber, floorNumber, roomNumber, purchaseDate, assetType));

        if (!isAdded) {
            throw new Exception("Duplicate specific asset. Specific asset has not been added.");
        }

        return "";
    } 
    catch (Exception e) {
        throw new Exception("An error has occurred. Please try again.");
    }
  }

  public static String updateSpecificAsset(int assetNumber, int newFloorNumber, int newRoomNumber,
  Date newPurchaseDate, String newAssetTypeName) throws Exception {
  try {
      if (newAssetTypeName == null || newAssetTypeName.equals("")) {
          throw new Exception("Asset Type not found for the given name.");
      }

      if (newFloorNumber < 0) {
            throw new Exception("Floor number cannot be negative.");
        }

      if (newRoomNumber < -1) {
          throw new Exception("Room number cannot be less than -1.");
      }

      AssetType newAssetType = AssetType.getWithName(newAssetTypeName);

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
      if (isFound == false){
        throw new Exception("No specific asset with this asset number. Please try again.");
      }
      return "";
    } 
    catch (Exception e) {
      throw new Exception("An error has occured. Please try again.");
    }
  }


public static void deleteSpecificAsset(int assetNumber) throws Exception {
  try {
      List<SpecificAsset> specificAssetsList = ap.getSpecificAssets();
      boolean isFound = false;
      for (SpecificAsset specificAsset : specificAssetsList) {
          if (specificAsset.getAssetNumber() == assetNumber) {
              specificAsset.delete();
              isFound = true;
              break;
          }
      }
      if (isFound == false){
        throw new Exception("No specific asset with this asset number. Please try again.");
      }
    } 
    catch (Exception e) {
      throw new Exception("An error has occurred. Please try again.");
    }
  }
}
