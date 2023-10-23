package ca.mcgill.ecse.assetplus.controller;
import java.sql.Date;
import java.util.List;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import ca.mcgill.ecse.assetplus.model.Employee;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.Manager;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import ca.mcgill.ecse.assetplus.model.TicketImage;

public class AssetPlusFeatureSet5Controller {

  // controller feature set shouldn't be instantiated
  private AssetPlusFeatureSet5Controller() {};

  /**
   * Adds a ticket image to a specific ticket
   * 
   * @author William Wang
   * @param imageURL
   * @param ticketID
   * @return Error message from either invalid input or RuntimeException
   */
  public static String addImageToMaintenanceTicket(String imageURL, int ticketID) {
    
    // input validation
    var error = "";
    if (imageURL == null || imageURL.trim().isEmpty()) {
      error = "Image URL cannot be empty. ";
    }
    if (!(imageURL.startsWith("http://") || imageURL.startsWith("https://"))) {
      error += "Image URL must start with http:// or https://. ";
    }
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketID);
    if (ticket == null) {
      error += "Ticket does not exist. ";
    } else {
      List<TicketImage> ticketImages = ticket.getTicketImages();
      for (TicketImage e: ticketImages) {
        if (e.getImageURL().equals(imageURL)) {
          error += "Image already exists for the ticket. ";
          break;
        }
      } 
    }
    if (!error.isEmpty()) {
      return error.trim(); // return error for invalid input
    }

    // call model
    try {
      ticket.addTicketImage(imageURL);
    } catch (RuntimeException e) {
      String caughtError = e.getMessage();
      if (caughtError.startsWith("Unable to create ticketImage due to ticket.")) {
        caughtError = "There must be at least one ticket for a ticket image to exist. ";
      }
      return caughtError;
    }
    return "";
  }

  /**
   * Deletes an image from a maintenance ticket if the specified ticket exists and its ticket images contains the image to be deleted.
   * 
   * @author Willian Wang
   * @param imageURL
   * @param ticketID
   */
  public static void deleteImageFromMaintenanceTicket(String imageURL, int ticketID) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketID);
    if (ticket != null) {
      List<TicketImage> ticketImages = ticket.getTicketImages();
      for (TicketImage ticketImage: ticketImages) {
        if (ticketImage.getImageURL().equals(imageURL)) {
          ticketImage.delete();
          break;
        }
      }
    }
  }

    public static void main(String[] args) {
    // testing
    // Given
    AssetPlus ap = AssetPlusApplication.getAssetPlus();
    ap.addEmployee("jeff@ap.com", "pass1", "Jeff", "(555)555-5555");
    Employee smith = ap.addEmployee("smith@ap.com", "pass1", "Smith", "(555)555-5555");
    Manager manager = new Manager("manager@ap.com", null, "manager", null, ap);
    AssetType lamp = ap.addAssetType("lamp", 1800);
    AssetType bed = ap.addAssetType("bed", 5000);
    SpecificAsset aLamp = ap.addSpecificAsset(1, 9, 23, new Date(2022, 3, 20), lamp);
    SpecificAsset aBed = ap.addSpecificAsset(2, 10, 35, new Date(2022, 1, 30), bed);
    MaintenanceTicket ticket1 = ap.addMaintenanceTicket(1, new Date(2023,7,20), "haha funny", manager);
    MaintenanceTicket ticket2 = ap.addMaintenanceTicket(2, new Date(2023,7,10), "haha funny", smith);
    ticket1.addTicketImage("https://imageurl.com/i.jpg");
    ticket1.addTicketImage("http://thisimage.com/1.png");
    // When
    System.out.println(AssetPlusFeatureSet5Controller.addImageToMaintenanceTicket("https://imageurl.com/i.jpg", 1));
  }

}
