package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import ca.mcgill.ecse.assetplus.model.User;

public class AssetPlusFeatureSet4Controller {
  private static AssetPlus ap = AssetPlusApplication.getAssetPlus();

  /**
   * Adds a ticket based on the argument
   *
   * @author Tim Pham
   * @param id              Ticket's unique id
   * @param raisedOnDate    Ticket's date
   * @param description     Ticket's description
   * @param email           The raiser's email
   * @param assetNumber     ID of the specific asset with -1 means no asset
   * @return A message indicating the status of the operation or an empty string if successful.
   */
  public static String addMaintenanceTicket(int id, Date raisedOnDate, String description, String email, int assetNumber) {
    User g = Util.getUser(email);
    if (g.equals(null)) {
      return "The ticket raiser does not exist";
    }
    MaintenanceTicket t = ap.addMaintenanceTicket(id, raisedOnDate, description, g);
    t.setAsset(ap.getSpecificAsset(assetNumber));
    return "";
  }

  /**
   * Update a ticket based on the argument
   *
   * @author Tim Pham
   * @param id                 Ticket's unique id
   * @param newRaisedOnDate    Ticket's new date
   * @param newDescription     Ticket's new description
   * @param newEmail           The raiser's email
   * @param newAssetNumber     ID of the new specific asset, -1 means removing the asset
   * @return A message indicating the status of the operation or an empty string if successful.
   */
  public static String updateMaintenanceTicket(int id, Date newRaisedOnDate, String newDescription,
      String newEmail, int newAssetNumber) {
      MaintenanceTicket current = ap.getMaintenanceTicket(id);
      if (current == null) {
        return "";
      }

      current.setRaisedOnDate(newRaisedOnDate);

      if (newDescription.isBlank()) {
        return "Ticket description cannot be empty";
      }

      current.setDescription(newDescription);

      User u = Util.getUser(newEmail);
      if (u == null) {
        return "Email does not match any guests";
      }

      SpecificAsset a;
      if (newAssetNumber == -1) {
        a = null;
      } else {
        a = ap.getSpecificAsset(newAssetNumber);
        if (a == null) {
          return "The asset does not exist";
        }
      }

      current.setTicketRaiser(u);
      current.setAsset(a);
      return "";
  }

  /**
   * Delete a ticket based on id
   *
   * @author Tim Pham
   * @param id                 Ticket's unique id
   */
  public static void deleteMaintenanceTicket(int id) {
    // Remove this exception when you implement this method
    MaintenanceTicket t = ap.getMaintenanceTicket(id);
    if (t == null) {
      return;
    }
    t.delete();
  }
}
