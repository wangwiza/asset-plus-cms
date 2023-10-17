package ca.mcgill.ecse.assetplus.controller;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import java.util.List;

public class AssetPlusFeatureSet2Controller {

  private static AssetPlus assetPlus = AssetPlusApplication.getAssetPlus();

  public static String addAssetType(String name, int expectedLifeSpanInDays) throws Exception {
    if (expectedLifeSpanInDays <= 0) {
      throw new Exception("The expected life span must be greater than 0 days");
    }

    if (name == null || name.equals("")) {
      throw new Exception("The name must not be empty");
    }

    Boolean operationSuccess =
        assetPlus.addAssetType(assetPlus.addAssetType(name, expectedLifeSpanInDays));

    if (!operationSuccess) {
      throw new Exception("The asset type already exists");
    }

    return "";
  }

  public static String updateAssetType(String oldName, String newName,
      int newExpectedLifeSpanInDays) throws Exception {
    List<AssetType> assetTypeList = assetPlus.getAssetTypes();
    Boolean updatedAssetType = false;

    if (newExpectedLifeSpanInDays <= 0) {
      throw new Exception("The expected life span must be greater than 0 days");
    }

    if (oldName == null || newName == null) {
      throw new Exception("The name must not be empty");
    }

    if (oldName.equals("") || newName.equals("")) {
      throw new Exception("The name must not be empty");
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
  }

  public static void deleteAssetType(String name) throws Exception {
    List<AssetType> assetTypeList = assetPlus.getAssetTypes();
    Boolean deletedAssetType = false;

    if (name == null || name.equals("")) {
      throw new Exception("The name must not be empty");
    }

    for (AssetType assetType : assetTypeList) {
      if (assetType.getName().equals(name)) {
        assetType.delete();
        deletedAssetType = true;
        break;
      }
    }

    if (!deletedAssetType) {
      throw new Exception("The asset type does not exist");
    }
    
  }
}
