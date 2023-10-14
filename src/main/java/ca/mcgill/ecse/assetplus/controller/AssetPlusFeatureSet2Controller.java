package ca.mcgill.ecse.assetplus.controller;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import java.util.List;

public class AssetPlusFeatureSet2Controller {

  private static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

  public static String addAssetType(String name, int expectedLifeSpanInDays) throws Exception {
    try {
      if (expectedLifeSpanInDays <= 0) {
        throw new Exception("The expected life span has to be greater than 0.");
      }

      if (name == null) {
        throw new Exception("Please provide a valid Asset Type name, it cannot be null.");
      }

      if (name.equals("")) {
        throw new Exception("Please provide a valid Asset Type name, it cannot be empty.");
      }

      Boolean operationSuccess = assetPlus.addAssetType(assetPlus.addAssetType(name, expectedLifeSpanInDays));

      if (!operationSuccess) {
        throw new Exception("A duplicate asset type was found, please try again.");
      }

      return "";

    } catch (Exception e) {
      throw new Exception("An error has occured, please try again: " + e);
    }
  }

  public static String updateAssetType(String oldName, String newName, int newExpectedLifeSpanInDays) throws Exception {
    List<AssetType> assetTypeList = assetPlus.getAssetTypes();
    Boolean updatedAssetType = false;
    try {
      if (newExpectedLifeSpanInDays <= 0) {
        throw new Exception("The expected life span has to be greater than 0.");
      }

      if (oldName == null || newName == null) {
        throw new Exception("Please provide a valid Asset Type name, it cannot be null.");
      }

      if (oldName.equals("") || newName.equals("")) {
        throw new Exception("Please provide a valid Asset Type name, it cannot be empty.");
      }

      for (AssetType assetType : assetTypeList) {
        if (assetType.getName().equals(oldName)) {
          assetType.setName(newName);
          assetType.setExpectedLifeSpan(newExpectedLifeSpanInDays);
          updatedAssetType = true;
          break;
        }
      }

      if (!updatedAssetType) {
        throw new Exception("Asset Type with the name " + oldName + " was not found.");
      }

      return "";

    } catch (Exception e) {
      throw new Exception("An error has occured, please try again: " + e);
    }
  }

  public static void deleteAssetType(String name) throws Exception {
    List<AssetType> assetTypeList = assetPlus.getAssetTypes();
    Boolean deletedAssetType = false;
    try {
      if (name == null) {
        throw new Exception("Please provide a valid Asset Type name, it cannot be null.");
      }

      if (name.equals("")) {
        throw new Exception("Please provide a valid Asset Type name, it cannot be empty.");
      }

      for (AssetType assetType : assetTypeList) {
        if (assetType.getName().equals(name)) {
          assetType.delete();
          deletedAssetType = true;
          break;
        }
      }

      if (!deletedAssetType) {
        throw new Exception("Asset Type with the name " + name + " was not found and could not be deleted.");
      }

    } catch (Exception e) {
      throw new Exception("An error has occured, please try again: " + e);
    }
  }

}
