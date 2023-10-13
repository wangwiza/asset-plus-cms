package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.Guest;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;

public class AssetPlusFeatureSet4Controller {
  private static AssetPlus ap = AssetPlusApplication.getAssetPlus();

  // assetNumber -1 means that no asset is specified
  public static String addMaintenanceTicket(int id, Date raisedOnDate, String description, String email, int assetNumber) {
    Guest g = Util.getGuest(email);
    if (g.equals(null)) {
      return "User does not exists";
    }
    MaintenanceTicket t = ap.addMaintenanceTicket(id, raisedOnDate, description, g);
    t.setAsset(ap.getSpecificAsset(assetNumber));
    return "";
  }

  // newAssetNumber -1 means that no asset is specified
  public static String updateMaintenanceTicket(int id, Date newRaisedOnDate, String newDescription,
      String newEmail, int newAssetNumber) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }

  public static void deleteMaintenanceTicket(int id) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }

}
