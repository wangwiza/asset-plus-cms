package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.MaintenanceNote;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket.PriorityLevel;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket.Status;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket.TimeEstimate;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;

public class AssetPlusTicketingController {
  // MAKE SURE YOU READ ProcessMaintenanceTickets.feature before starting to work
  private static AssetPlus ap = AssetPlusApplication.getAssetPlus();

  public static String assignHotelStaffToMaintenanceTicket(int ticketId, String employeeEmail, TimeEstimate timeEstimate, PriorityLevel priority, Boolean requriesApproval) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }

  public static String startWorkOnMaintenanceTicket(int ticketId) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }

  public static String completeWorkOnMaintenanceTicket(int ticketId) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }

  public static String approveWorkOnMaintenanceTicket(int ticketId) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }

  /**
   * Disapprove work and include a maintenance note explaining the reason.
   * 
   * @author Li Yang Lei
   * @param ticketId
   * @param date
   * @param reason
   * @return
   */
  public static String disapproveWorkOnMaintenanceTicket(int ticketId, Date date, String reason) {
    String error = "";
    try {
      // get the ticket and its current status
      MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
      if (ticket==null) {
        error = "Maintenance ticket does not exist.";
        return error;
      }
      ticket.disapprove(date, reason, ap.getManager());
      AssetPlusPersistence.save();
      return error;
    } catch (RuntimeException e) {
      error = e.getMessage();
      return error;
    }
  }
}
