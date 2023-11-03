package ca.mcgill.ecse.assetplus.features;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet6Controller;
import ca.mcgill.ecse.assetplus.controller.AssetPlusTicketingController;
import ca.mcgill.ecse.assetplus.controller.TOMaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import ca.mcgill.ecse.assetplus.model.HotelStaff;
import ca.mcgill.ecse.assetplus.model.MaintenanceNote;
import ca.mcgill.ecse.assetplus.model.MaintenanceTicket;
import ca.mcgill.ecse.assetplus.model.Manager;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import ca.mcgill.ecse.assetplus.model.TicketImage;
import ca.mcgill.ecse.assetplus.model.User;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;

public class MaintenanceTicketsStepDefinitions {

  private static AssetPlus ap = AssetPlusApplication.getAssetPlus();
  private String errorMessage; // USE THIS TO STORE ERROR MESSAGES FROM CONTROLLER

  /**
   * @author William Wang
   * @param employeesDataTable
   */
  @Given("the following employees exist in the system")
  public void the_following_employees_exist_in_the_system(
      io.cucumber.datatable.DataTable employeesDataTable) {
    List<Map<String, String>> employeesToAdd = employeesDataTable.asMaps();
    for (Map<String, String> employee : employeesToAdd) {
      String email = employee.get("email");
      String name = employee.get("name");
      String password = employee.get("password");
      String phoneNumber = employee.get("phoneNumber");
      ap.addEmployee(email, name, password, phoneNumber);
    }
  }

  /**
   * @author Vlad Arama
   * @param managerDataTable
   */
  @Given("the following manager exists in the system")
  public void the_following_manager_exists_in_the_system(
      io.cucumber.datatable.DataTable managerDataTable) {
    String email = "";
    String password = "";
    List<Map<String, String>> rows = managerDataTable.asMaps();
    for (var row : rows) {
      email = row.get("email");
      password = row.get("password");
    }

    if (ap.hasManager()) {
      Manager existingManager = ap.getManager();
      existingManager.setEmail(email);
      existingManager.setPassword(password);
    } else {
      new Manager(email, "", password, "", ap);
    }
  }
  /**
   * 3
   * @param dataTable
   */
  @Given("the following asset types exist in the system")
  public void the_following_asset_types_exist_in_the_system(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }
  /**
   * 4
   * @author Michael Rafferty
   * @param dataTable
   */
  @Given("the following assets exist in the system")
  public void the_following_assets_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    AssetPlus ap = AssetPlusApplication.getAssetPlus();

    List<Map<String, String>> rows = dataTable.asMaps();

    for (var row : rows) {
      int aAssetNumber = Integer.parseInt(row.get("assetNumber"));
      AssetType aAssetType = AssetType.getWithName(row.get("type"));
      Date aPurchaseDate = Date.valueOf(row.get("purchaseDate"));
      int aFloorNumber = Integer.parseInt(row.get("floorNumber"));
      int aRoomNumber = Integer.parseInt(row.get("roomNumber"));
      new SpecificAsset(aAssetNumber, aFloorNumber, aRoomNumber, aPurchaseDate, ap, aAssetType);
      //ap.addSpecificAsset(aAssetNumber, aFloorNumber, aRoomNumber, aPurchaseDate, aAssetType);
    }
    //throw new io.cucumber.java.PendingException();
  }
  /**
   * 5
   * @param dataTable
   */
  @Given("the following tickets exist in the system")
  public void the_following_tickets_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }
  /**
   * Creates new notes
   * 
   * @author Li Yang Lei
   * @param dataTable
   */
  @Given("the following notes exist in the system")
  public void the_following_notes_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> notes = dataTable.asMaps();
    for (var note: notes) {
      String email = note.get("noteTaker");
      int ticketId = Integer.parseInt(note.get("ticketId"));
      Date addedOnDate  = Date.valueOf(note.get("addedOnDate"));
      String description = note.get("description");
      MaintenanceTicket ticket = MaintenanceTicket.getWithId(ticketId);
      HotelStaff staff = (HotelStaff) HotelStaff.getWithEmail(email);
      MaintenanceNote newNote = new MaintenanceNote(addedOnDate, description, ticket, staff);
      ticket.addTicketNote(newNote);
    }
  }


  /**
   * @author William Wang
   * @param imagesDataTable
   */
  @Given("the following ticket images exist in the system")
  public void the_following_ticket_images_exist_in_the_system(
      io.cucumber.datatable.DataTable imagesDataTable) {
    List<Map<String, String>> rows = imagesDataTable.asMaps();
    for (var row : rows) {
      String imageUrl = row.get("imageUrl");
      int ticketId = Integer.parseInt(row.get("ticketId"));
      new TicketImage(imageUrl, MaintenanceTicket.getWithId(ticketId));
    }
  }

  /**
   * @author Vlad Arama
   * @param ticketId
   * @param state
   * @param requiresApproval
   */
  @Given("ticket {string} is marked as {string} with requires approval {string}")
  public void ticket_is_marked_as_with_requires_approval(String ticketId, String state,
      String requiresApproval) {
        // Todo
        throw new io.cucumber.java.PendingException();

  }
  /**
   * 3
   * @param string
   * @param string2
   */
  @Given("ticket {string} is marked as {string}")
  public void ticket_is_marked_as(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
  /**
   * 4
   * @author Michael Rafferty
   */
  @When("the manager attempts to view all maintenance tickets in the system")
  public void the_manager_attempts_to_view_all_maintenance_tickets_in_the_system() {
    List<TOMaintenanceTicket> tickets = AssetPlusFeatureSet6Controller.getTickets();
    // should tickets be a private variable at the top?
    //throw new io.cucumber.java.PendingException();
  }
  /**
   * 5
   * @param string
   * @param string2
   * @param string3
   * @param string4
   * @param string5
   */
  @When("the manager attempts to assign the ticket {string} to {string} with estimated time {string}, priority {string}, and requires approval {string}")
  public void the_manager_attempts_to_assign_the_ticket_to_with_estimated_time_priority_and_requires_approval(
      String string, String string2, String string3, String string4, String string5) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
  /**
   * Update status of ticket to In Progress
   * 
   * @author Li Yang Lei
   * @param string
   */
  @When("the hotel staff attempts to start the ticket {string}")
  public void the_hotel_staff_attempts_to_start_the_ticket(String string) {
    AssetPlusTicketingController.startWorkOnMaintenanceTicket(Integer.parseInt(string));
  }

  /**
   * @author William Wang
   * @param string
   */
  @When("the manager attempts to approve the ticket {string}")
  public void the_manager_attempts_to_approve_the_ticket(String ticketId) {
    errorMessage = AssetPlusTicketingController.approveWorkOnMaintenanceTicket(Integer.parseInt(ticketId))
  }

  /**
   * @author Vlad Arama
   * @param ticketId
   */
  @When("the hotel staff attempts to complete the ticket {string}")
  public void the_hotel_staff_attempts_to_complete_the_ticket(String ticketId) {
    AssetPlusTicketingController.completeWorkOnMaintenanceTicket(Integer.parseInt(ticketId));
  }
  /**
   * 3
   * @param string
   * @param string2
   * @param string3
   */
  @When("the manager attempts to disapprove the ticket {string} on date {string} and with reason {string}")
  public void the_manager_attempts_to_disapprove_the_ticket_on_date_and_with_reason(String string,
      String string2, String string3) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
  /**
   * 4
   * @author Michael Rafferty
   * @param string
   * @param string2
   */
  @Then("the ticket {string} shall be marked as {string}")
  public void the_ticket_shall_be_marked_as(String string, String string2) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
  /**
   * 5
   * @param string
   */
  @Then("the system shall raise the error {string}")
  public void the_system_shall_raise_the_error(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
  /**
   * Verifies that there is no maintenance ticket associated with the given ticket ID
   * 
   * @author Li Yang Lei
   * @param string
   */
  @Then("the ticket {string} shall not exist in the system")
  public void the_ticket_shall_not_exist_in_the_system(String string) {
    int ticketId = Integer.parseInt(string);
    assertNull(MaintenanceTicket.getWithId(ticketId));
  }

  /**
   * @author William Wang
   * @param string
   * @param string2
   * @param string3
   * @param string4
   */
  @Then("the ticket {string} shall have estimated time {string}, priority {string}, and requires approval {string}")
  public void the_ticket_shall_have_estimated_time_priority_and_requires_approval(String ticketId,
      String expectedTimeEstimate, String expectedPriority, String expectedRequiresApproval) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(Integer.parseInt(ticketId));

  }
  /**
   * @author Vlad Arama
   * @param ticketId
   * @param employeeEmail
   */
  @Then("the ticket {string} shall be assigned to {string}")
  public void the_ticket_shall_be_assigned_to(String ticketId, String employeeEmail) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(Integer.parseInt(ticketId));
    HotelStaff ticketFixer = (HotelStaff) HotelStaff.getWithEmail(employeeEmail);
    assertEquals(ticketFixer, ticket.getTicketFixer());
  }
/**
 * 3
 * @param string
 */
  @Then("the number of tickets in the system shall be {string}")
  public void the_number_of_tickets_in_the_system_shall_be(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
  }
  /**
   * 4
   * @param dataTable
   */
  @Then("the following maintenance tickets shall be presented")
  public void the_following_maintenance_tickets_shall_be_presented(
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }
  /**
   * 5
   * @param string
   * @param dataTable
   */
  @Then("the ticket with id {string} shall have the following notes")
  public void the_ticket_with_id_shall_have_the_following_notes(String string,
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }
  /**
   * Verifies that the maintenance ticket has 0 notes
   * 
   * @author Li Yang Lei
   * @param string
   */
  @Then("the ticket with id {string} shall have no notes")
  public void the_ticket_with_id_shall_have_no_notes(String string) {
    int ticketId = Integer.parseInt(string);
    assertEquals(MaintenanceTicket.getWithId(ticketId).numberOfTicketNotes(),0);
  }
  /**
   * 1
   * @param string
   * @param dataTable
   */
  @Then("the ticket with id {string} shall have the following images")
  public void the_ticket_with_id_shall_have_the_following_images(String string,
      io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    //
    // For other transformations you can register a DataTableType.
    throw new io.cucumber.java.PendingException();
  }
  /**
   * @author Vlad Arama
   * @param ticketId
   */
  @Then("the ticket with id {string} shall have no images")
  public void the_ticket_with_id_shall_have_no_images(String ticketId) {
    MaintenanceTicket ticket = MaintenanceTicket.getWithId(Integer.parseInt(ticketId));
    assertEquals(0, ticket.getTicketImages().size());
  }

  private static
}
