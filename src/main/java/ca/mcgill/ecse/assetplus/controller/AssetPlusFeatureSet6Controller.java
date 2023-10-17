
package ca.mcgill.ecse.assetplus.controller;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import ca.mcgill.ecse.assetplus.model.User;
import ca.mcgill.ecse.assetplus.model.Employee;
import ca.mcgill.ecse.assetplus.model.Guest;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.TicketImage;
import java.util.List;

public class AssetPlusFeatureSet6Controller {
  
  private static AssetPlus ap = AssetPlusApplication.getAssetPlus();
  public static void deleteEmployeeOrGuest(String email) {
    List<Employee> employeeList = ap.getEmployees();
    List<Guest> guestList = ap.getGuests();
    boolean exists = false;
    boolean isEmployee = false;
    boolean isGuest = false;
    User employeeOrGuest = null;
  for (int i=0; i<employeeList.size(); i++) {
      if (employeeList.get(i).getEmail()==email){
        exists = true;
        isEmployee = true;
        employeeOrGuest = employeeList.get(i);
        break;
    }
  }
  for (int i=0; i<guestList.size(); i++) {
      if (guestList.get(i).getEmail()==email){
        exists = true;
        isGuest = true;
        employeeOrGuest = guestList.get(i);
        break;
    }
  }
  if (!exists){
    throw new RuntimeException("Email provided does not correspond to a user");  // need the correct error message?... Also is RuntimeException correct?
  }
  if (isEmployee){
    ap.removeEmployee((Employee) employeeOrGuest);
  }
  if (isGuest){
    ap.removeGuest((Guest) employeeOrGuest);
  }
    // Questions:
    // According to the class diagram, a maintenance ticket has to have one ticketRaiser user. So if we delete that user, then do we need to delete the ticket too?
    // Also, a maintenance note has to have a noteTaker hotel staff. So if we delete that hotel staff, then do we need to delete the note too?
    // What if the user is not removed successfully? This won't happen because of the checks, but idk.
  }

  // returns all tickets
  public static List<TOMaintenanceTicket> getTickets() {
    // var tickets = new ArrayList<TOMaintenanceTicket>();
    // for (var maintenanceticket : ap.getMaintenanceTickets()){
    //   List<String> imageUrls = new ArrayList<String>();
    //   for (TicketImage ticketImage: maintenanceticket.getTicketImages()){
    //     imageUrls.add(ticketImage.getImageURL());
    //   }
    //   tickets.add(new TOMaintenanceTicket(
    //     maintenanceticket.getId(), 
    //     maintenanceticket.getRaisedOnDate(), 
    //     maintenanceticket.getDescription(), 
    //     maintenanceticket.getTicketRaiser().getEmail(), 
    //     maintenanceticket.getAsset().getAssetType().getName(),
    //     maintenanceticket.getAsset().getAssetType().getExpectedLifeSpan(),
    //     maintenanceticket.getAsset().getPurchaseDate(),
    //     maintenanceticket.getAsset().getFloorNumber(),
    //     maintenanceticket.getAsset().getRoomNumber(),
    //     imageUrls,
    //     //maintenanceticket.getTicketNotes()
    //     )
    //     );
    // }
    // return tickets;
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }
}
