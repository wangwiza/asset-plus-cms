package ca.mcgill.ecse.assetplus.controller;

import java.util.List;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.User;
import ca.mcgill.ecse.assetplus.model.Manager;
import ca.mcgill.ecse.assetplus.model.Guest;
import ca.mcgill.ecse.assetplus.model.Employee;



public class AssetPlusFeatureSet1Controller {
  private static AssetPlus ap = AssetPlusApplication.getAssetPlus();
  private AssetPlusFeatureSet1Controller() {};


  /**
   * Updates the manager's password
   * 
   * @author Li Yang Lei
   * @param password
   * @return Error message for failure and empty string for success
   */
  public static String updateManager(String password) {
    var error = "";
    try {
      // validates that the new password meet the requirements
      if (password.length()<4) {
        error = "Password must be at least four characters long";
      }
      if (password==password.toLowerCase()) {
        error = "Password must contain one upper-case character";
      }
      if (password==password.toUpperCase()) {
        error = "Password must contain one lower-case character";
      }
      if (!(password.contains("!")|| password.contains("#") || password.contains("$"))) {
        error = "Password must contain one character out of !#$";
      }
      // return error for password that does not meet requirements
      if (error!="") {
        return error;
      }
      // otherwise, set update the password
      ap.getManager().setPassword(password);
      return error;
    } catch (Exception e) {
      error = "An error has occured. Please try again: " + e.getMessage();
      return error;
    }
  }


  /**
   * Adds new employee or guest to the system
   * 
   * @author Li Yang Lei
   * @param email
   * @param password
   * @param name
   * @param phoneNumber
   * @param isEmployee
   * @return Error message if unsuccessful and empty string if successful
   */

  public static String addEmployeeOrGuest(String email, String password, String name, String phoneNumber,
        boolean isEmployee) {
    var error = "";
    try {
      boolean status;
      // based on isEmployee, check if the employee or guest already exists
      // if employee/guest does not exist, then create new; otherwise, doesn't create new employee/guest
      if (isEmployee) {
        status = ap.addEmployee(ap.addEmployee(email, name, password, phoneNumber));
      } else {
        status = ap.addGuest(ap.addGuest(email, name, password, phoneNumber));
      } 
      if (status==false) {
        error = "Duplicate user. User has not been added";
      }
      return error;
    } catch (Exception e) {
      error = "An error has occured. Please try again: " + e.getMessage();
      return error;
    }
  }


  /**
   * Update the employee/guest information with the provided information
   * @author Li Yang Lei
   * @param email
   * @param newPassword
   * @param newName
   * @param newPhoneNumber
   * @return Error message if unsuccessful and empty string if successful
  */

  public static String updateEmployeeOrGuest(String email, String newPassword, String newName, String newPhoneNumber) {
    var error = "";
    try {
      // get all the employee/guest list, and go through them to find a matching email
      List<Employee> employeeList = ap.getEmployees();
      List<Guest> guestList = ap.getGuests();
      boolean isFound = false;
      for (int i=0; i<employeeList.size(); i++) {
        if (employeeList.get(i).getEmail()==email){
          employeeList.get(i).setPassword(newPassword);
          employeeList.get(i).setPhoneNumber(newPhoneNumber);
          employeeList.get(i).setName(newName);
          isFound = true;
          break;
        }
      }
      if (!isFound) {
        for (int i=0; i<guestList.size(); i++) {
          if (guestList.get(i).getEmail()==email){
            guestList.get(i).setPassword(newPassword);
            guestList.get(i).setPhoneNumber(newPhoneNumber);
            guestList.get(i).setName(newName);
            isFound = true;
            break;
          }
        }
      }
      // if nothing has been found, display that situation in the error
      if (!isFound) {
        error = "The email address does not match any user in our system. No update has been made.";
      }
      return error;
    } catch (Exception e) {
      error = "An error has occured. Please try again: " + e.getMessage();
      return error;
    }
  }

}