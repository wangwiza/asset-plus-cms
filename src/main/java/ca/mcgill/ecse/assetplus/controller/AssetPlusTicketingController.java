package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.HotelStaff;
import ca.mcgill.ecse.assetplus.model.MaintenanceNote;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket.PriorityLevel;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket.Status;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket.TimeEstimate;
import ca.mcgill.ecse.assetplus.model.User;
import ca.mcgill.ecse.assetplus.persistence.AssetPlusPersistence;

public class AssetPlusTicketingController {
  // MAKE SURE YOU READ ProcessMaintenanceTickets.feature before starting to work
  private static AssetPlus ap = AssetPlusApplication.getAssetPlus();

  public static String assignHotelStaffToMaintenanceTicket(int ticketId, String employeeEmail,
      TimeEstimate timeEstimate, PriorityLevel priority, Boolean requriesApproval) {
    
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    if (ticket == null) {
      return "Maintenance ticket does not exist.";
    }

    // state validation
    if (ticket.getStatusFullName().equals("Assigned")) {
      return "The maintenance ticket is already assigned.";
    }
    if (ticket.getStatusFullName().equals("Resolved")) {
      return "Cannot assign a maintenance ticket which is resolved.";
    }
    if (ticket.getStatusFullName().equals("Closed")) {
      return "Cannot assign a maintenance ticket which is closed.";
    }
    if (ticket.getStatusFullName().equals("InProgress")) {
      return "Cannot assign a maintenance ticket which is in progress.  ";
    }

    //employee validation
    User ticketFixer = HotelStaff.getWithEmail(employeeEmail);
    HotelStaff hotelStaff;
    if (ticketFixer instanceof HotelStaff) {
      hotelStaff = (HotelStaff) ticketFixer;
    } else {
      return "Staff to assign does not exist.";
    }


    // assign the ticket
    try {
      Boolean result = ticket.assign(hotelStaff, priority, timeEstimate, ap.getManager());
      if (!result) {
        return "Could not assign maintenance ticket.";
      }
      AssetPlusPersistence.save();
    } catch (Exception e) {
      return e.getMessage();
    }
    return "";
  } 
  
  /**
   * Starts work on a maintenance ticket.
   * 
   * @author Vlad Arama
   * @param ticketId
   * @return an empty string if the operation was successful or an error
   */
  public static String startWorkOnMaintenanceTicket(int ticketId) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
    if (ticket == null) {
      return "Maintenance ticket does not exist.";
    }
    // state validation
    if (ticket.getStatusFullName().equals("Open")) {
      return "Cannot start a maintenance ticket which is open.";
    }
    if (ticket.getStatusFullName().equals("Resolved")) {
      return "Cannot start a maintenance ticket which is resolved.";
    }
    if (ticket.getStatusFullName().equals("Closed")) {
      return "Cannot start a maintenance ticket which is closed.";
    }
    if (ticket.getStatusFullName().equals("InProgress")) {
      return "The maintenance ticket is already in progress.  ";
    }
    // start work on the ticket
    Boolean result = ticket.start();
    if (!result) {
      return "Could not start work on maintenance ticket.";
    }
    return "";
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
      if (ticket == null) {
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
