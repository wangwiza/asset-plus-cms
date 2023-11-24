package ca.mcgill.ecse.assetplus.controller;

import java.util.ArrayList;
import java.util.Collections;
import com.thoughtworks.xstream.mapper.Mapper.Null;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet6Controller;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.Employee;
import ca.mcgill.ecse.assetplus.model.Guest;
import ca.mcgill.ecse.assetplus.model.User;

// line 10 "../../../../../../AssetPlusPersistence.ump"
// line 16 "../../../../../../AssetPlus.ump"
public class TOUser
{
  private static AssetPlus ap = AssetPlusApplication.getAssetPlus();
  //User Attributes
  private String email;
  private String name;
  private String password;
  private String phoneNumber;

  public TOUser(String aEmail, String aName, String aPassword, String aPhoneNumber)
  {
    name = aName;
    password = aPassword;
    phoneNumber = aPhoneNumber;
    email = aEmail;
  }

  public static ArrayList<TOUser> getAllUsers() {
    ArrayList<TOUser> userList = new ArrayList<TOUser>();
    for (var user: ap.getEmployees()) {
      userList.add(new TOUser(user.getEmail(), user.getName(), user.getPassword(), user.getPhoneNumber()));
    }
    for (var user: ap.getGuests()) {
      userList.add(new TOUser(user.getEmail(), user.getName(), user.getPassword(), user.getPhoneNumber()));
    }
    if (ap.getManager()!=null) {
      var user = ap.getManager();
      userList.add(new TOUser(user.getEmail(), user.getName(), user.getPassword(), user.getPhoneNumber()));
    }
    return userList;

  }
  
  // public static ArrayList<String> getAllHotelStaffNames() {
  //   ArrayList<String> userList = new ArrayList<String>();
  //   for (var user: ap.getEmployees()) {
  //     userList.add(user.getName());
  //   }
  // }

  // public static Boolean removeUser(TOUser user) {
  //   ArrayList<User> allUsers = new java.util.ArrayList<>(Collections.emptyList());
  //   allUsers.addAll(ap.getGuests());
  //   allUsers.addAll(ap.getEmployees());
  //   allUsers.add(ap.getManager());

  //   for (Employee e: ap.getEmployees()) {
  //     if (e.getEmail().equals(user.getEmail())) {
  //       Boolean status = ap.removeEmployee(e);
  //       return status;
  //     }
  //   }

  //   for (Guest g: ap.getGuests()) {
  //     if (g.getEmail().equals(user.getEmail())) {
  //       Boolean status = AssetPlusFeatureSet6Controller.deleteEmployeeOrGuest(g);
  //       return status;
  //     }
  //   }
  //   return false;
  // }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public String getEmail()
  {
    return email;
  }

  public String getName()
  {
    return name;
  }

  public String getPassword()
  {
    return password;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }




  public String toString()
  {
    return getName();
  }
}