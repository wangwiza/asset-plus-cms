package ca.mcgill.ecse.assetplus.features;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet6Controller;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.Guest;
import ca.mcgill.ecse.assetplus.model.Manager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteGuestStepDefinitions {
  // "global" variables to be used in step definitions
  private AssetPlus ap = AssetPlusApplication.getAssetPlus();

  /**
   * Initiates the scenario by creating a couple test Guests.
   * 
   * @author William Wang
   * @param dataTable
   */
  @Given("the following guests exist in the system \\(p8)")
  public void the_following_guests_exist_in_the_system_p8(
      io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> rows = dataTable.asMaps();
    for (var row : rows) {
      String email = row.get("email");
      String password = row.get("password");
      String name = row.get("name");
      String phoneNumber = row.get("phoneNumber");
      ap.addGuest(email, name, password, phoneNumber);
    }
  }

  /**
   * Initiates the scenario by creating a manager with specific email and password.
   * 
   * @author Krasimir Kirov, William Wang
   * @param dataTable The Manager's email and password
   */
  @Given("the following manager exists in the system \\(p8)")
  public void the_following_manager_exists_in_the_system_p8(
      io.cucumber.datatable.DataTable dataTable) {
    String email = "";
    String password = "";
    List<Map<String, String>> rows = dataTable.asMaps();
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
   * Using an email address, attempts to delete an employee or guest's account.
   * 
   * @author Michael Rafferty
   * @param string
   */
  @When("the guest attempts to delete their own account linked to the {string} \\(p8)")
  public void the_guest_attempts_to_delete_their_own_account_linked_to_the_p8(String string) {
    AssetPlusFeatureSet6Controller.deleteEmployeeOrGuest(string);
  }

  /**
   * Verifies that the provided email is not associated with any guest in the system. This is
   * achieved by iterating through the list of guests and ensuring the provided email does not match
   * any guest's email.
   *
   * @author Vlad Arama
   * @param string The email address to verify against the list of guests.
   */
  @Then("the guest account linked to {string} shall not exist in the system \\(p8)")
  public void the_guest_account_linked_to_shall_not_exist_in_the_system_p8(String string) {
    List<Guest> guestsList = ap.getGuests();
    for (Guest guest : guestsList) {
      assertNotEquals("Guest with the same email has been found in the system.", string,
          guest.getEmail());
    }
  }

  /**
   * Verifies that a manager exists and that its email matches the provided email
   * 
   * @author Li Yang Lei
   * @param string
   */
  @Then("the manager account linked to {string} shall exist in the system \\(p8)")
  public void the_manager_account_linked_to_shall_exist_in_the_system_p8(String string) {
    Manager manager = ap.getManager();
    assertNotNull("Manager does not exist in the system.", manager);
    assertEquals("The manager account linked to " + string + " does not exist in the system.",
        manager.getEmail(), string);
  }

  /**
   * @author Tim Pham
   * @param string
   */
  @Then("the number of guests in the system shall be {string} \\(p8)")
  public void the_number_of_guests_in_the_system_shall_be_p8(String string) {
    // Write code here that turns the phrase above into concrete actions
    assertEquals("Wrong number of guests", Integer.parseInt(string), ap.getGuests().size());
  }
}
