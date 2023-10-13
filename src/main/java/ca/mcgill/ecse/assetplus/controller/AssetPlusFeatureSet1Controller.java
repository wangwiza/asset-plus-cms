package ca.mcgill.ecse.assetplus.controller;

import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.User;
import ca.mcgill.ecse.assetplus.model.Manager;
import ca.mcgill.ecse.assetplus.model.Guest;
import ca.mcgill.ecse.assetplus.model.Employee;



public class AssetPlusFeatureSet1Controller {
  private static AssetPlus ap = AssetPlusApplication.getAssetPlus();
  private AssetPlusFeatureSet1Controller() {};
  public static String updateManager(String password) {
    try {
      ap.getManager().setPassword(password);
    } catch (exception e) {
      throw new Exception("An error has occured. Please try again.");
    }
    return ""
  }

  public static String addEmployeeOrGuest(String email, String password, String name, String phoneNumber,
        boolean isEmployee) {
    try {
      if (isEmployee) {
        status = ap.addEmployee(ap.addEmployee(email, name, password, phoneNumber));
      } else {
        status = ap.addGuest(ap.addGuest(email, name, password, phoneNumber));
      } 
      if (status==false) {
        throw new Exception("Duplicate user. User has not been added")
      }
    } catch (exception e) {
      throw new Exception("An error has occured. Please try again.")
    }
    return ""
  }

  public static String updateEmployeeOrGuest(String email, String newPassword, String newName, String newPhoneNumber) {
    try {
      List<Employee> employeeList = ap.getEmployees()
      for (let i=0; i<employeeList.size; i++) {
        if (employeeList[i].getEmail==email){
          employeeList[i].setPassword(newPassword)
          employeeList[i].setPhoneNumber(newPhoneNumber)
          employeeList[i].setName(newName)
          break
        }
      }
    } catch (exception e) {
      throw new Exception("An error has occured. Please try again.")
    }
    return ""
  }

}