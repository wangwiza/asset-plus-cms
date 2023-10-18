package ca.mcgill.ecse.assetplus.controller;
import java.util.List;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.TicketImage;

public class AssetPlusFeatureSet5Controller {

  // access to root application object
  private static AssetPlus ap = AssetPlusApplication.getAssetPlus();

  // controller feature set shouldn't be instantiated
  private AssetPlusFeatureSet5Controller() {};


  public static String addImageToMaintenanceTicket(String imageURL, int ticketID) {
    // input validation
    var error = "";
    if (imageURL == null || imageURL.trim().isEmpty()) {
      error = "Image URL cannot be empty";
    }
    if (!(imageURL.startsWith("http://") || imageURL.startsWith("https://"))) {
      error = "Image URL must start with http:// or https://";
    }
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketID);
    if (ticket == null) {
      error = "Ticket does not exist";
    }
    List<TicketImage> ticketImages = ticket.getTicketImages();
    for (TicketImage e: ticketImages) {
      if (e.getImageURL() == imageURL) {
        error = "Image already exists for the ticket";
        break;
      }
    }
    if (!error.isEmpty()) {
      return error.trim(); // return error for invalid input
    }

    // call model
    try {
      ticket.addTicketImage(imageURL);
    } catch (RuntimeException e) {
      // error = e.getMessage();
      // if (error.startsWith("known error prefix")) {  // TODO: figure out what throws error
      //   error = "alternative text";
      // }
      return e.getMessage();
    }
    return error;
  }

  public static void deleteImageFromMaintenanceTicket(String imageURL, int ticketID) {
    // Remove this exception when you implement this method
    throw new UnsupportedOperationException("Not Implemented!");
  }

}
