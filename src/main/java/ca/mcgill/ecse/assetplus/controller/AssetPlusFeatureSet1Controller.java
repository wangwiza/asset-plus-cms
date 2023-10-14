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
  public static String updateManager(String password) throws Exception {
    try {
      ap.getManager().setPassword(password);
      return "";
    } catch (Exception e) {
      throw new Exception("An error has occured. Please try again: " + e);
    }
  }

  public static String addEmployeeOrGuest(String email, String password, String name, String phoneNumber,
        boolean isEmployee) throws Exception {
    try {
      boolean status;
      if (isEmployee) {
        status = ap.addEmployee(ap.addEmployee(email, name, password, phoneNumber));
      } else {
        status = ap.addGuest(ap.addGuest(email, name, password, phoneNumber));
      } 
      if (status==false) {
        throw new Exception("Duplicate user. User has not been added");
      }
      return "";
    } catch (Exception e) {
      throw new Exception("An error has occured. Please try again.");
    }
  }

  public static String updateEmployeeOrGuest(String email, String newPassword, String newName, String newPhoneNumber) throws Exception {
    try {
      List<Employee> employeeList = ap.getEmployees();
      for (int i=0; i<employeeList.size(); i++) {
        if (employeeList.get(i).getEmail()==email){
          employeeList.get(i).setPassword(newPassword);
          employeeList.get(i).setPhoneNumber(newPhoneNumber);
          employeeList.get(i).setName(newName);
          break;
        }
      }
    } catch (Exception e) {
      throw new Exception("An error has occured. Please try again.");
    }
    return "";
  }

}