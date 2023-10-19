
package ca.mcgill.ecse.assetplus.controller;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import ca.mcgill.ecse.assetplus.model.User;
import ca.mcgill.ecse.assetplus.model.Employee;
import ca.mcgill.ecse.assetplus.model.Guest;
import ca.mcgill.ecse.assetplus.model.MaintenanceNote;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.TicketImage;
import java.util.List;

public class AssetPlusFeatureSet6Controller {
  
  private static AssetPlus ap = AssetPlusApplication.getAssetPlus();

  /**
   * Using an email, deletes employee or guest account
   * 
   * @author Michael Rafferty
   * @param email
   * @return void
   */
  public static void deleteEmployeeOrGuest(String email) {
    List<Employee> employeeList = ap.getEmployees();
    List<Guest> guestList = ap.getGuests();
    boolean exists = false;
    User employeeOrGuest = null;
  for (int i=0; i<employeeList.size(); i++) {
      if (employeeList.get(i).getEmail().equals(email)){
        exists = true;
        employeeOrGuest = employeeList.get(i);
        break;
    }
  }
  for (int i=0; i<guestList.size(); i++) {
      if (guestList.get(i).getEmail().equals(email)){
        exists = true;
        employeeOrGuest = guestList.get(i);
        break;
    }
  }
  if (exists){
    employeeOrGuest.delete();
  }
  }

  /**
   * Returns all tickets
   * 
   * @author Michael Rafferty
   * @return List<TOMaintenanceTicket> all tickets
   */
  public static List<TOMaintenanceTicket> getTickets() {
    var tickets = new ArrayList<TOMaintenanceTicket>();

    for (var maintenanceticket : ap.getMaintenanceTickets()){
      // URLS
      List<String> imageUrls = new ArrayList<String>();
      if (maintenanceticket.hasTicketImages()){
      for (TicketImage ticketImage: maintenanceticket.getTicketImages()){
        imageUrls.add(ticketImage.getImageURL());
        }
      }
      // Notes
      TOMaintenanceNote[] notes = new TOMaintenanceNote[maintenanceticket.numberOfTicketNotes()];
      if (maintenanceticket.hasTicketNotes()){
        int i = 0;
      for (MaintenanceNote maintenanceNote : maintenanceticket.getTicketNotes()){
        notes[i] = new TOMaintenanceNote(maintenanceNote.getDate(), maintenanceNote.getDescription(), maintenanceNote.getNoteTaker().getEmail());
        i++;
        }
      }
      // List<TOMaintenanceTicket>
      tickets.add(new TOMaintenanceTicket(
        maintenanceticket.getId(), 
        maintenanceticket.getRaisedOnDate(), 
        maintenanceticket.getDescription(), 
        maintenanceticket.getTicketRaiser().getEmail(), 
        maintenanceticket.getAsset().getAssetType().getName(),
        maintenanceticket.getAsset().getAssetType().getExpectedLifeSpan(),
        maintenanceticket.getAsset().getPurchaseDate(),
        maintenanceticket.getAsset().getFloorNumber(),
        maintenanceticket.getAsset().getRoomNumber(),
        imageUrls,
        notes));
    }
    return tickets;

  }
}
