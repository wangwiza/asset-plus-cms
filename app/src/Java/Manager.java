/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;

// line 47 "AssetPlus.ump"
public class Manager extends Staff
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Manager Attributes
  private String password;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Manager(String aEmail, String aPassword, AssetPlus aAssetPlus, MaintenanceTicket aMaintenanceTicket)
  {
    super(aEmail, aPassword, aAssetPlus, aMaintenanceTicket);
    resetPassword();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template attribute_SetDefaulted */
  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean resetPassword()
  {
    boolean wasReset = false;
    password = getDefaultPassword();
    wasReset = true;
    return wasReset;
  }

  /**
   * allows easy reset when new manager
   */
  public String getPassword()
  {
    return password;
  }
  /* Code from template attribute_GetDefaulted */
  public String getDefaultPassword()
  {
    return "manager";
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "password" + ":" + getPassword()+ "]";
  }
}