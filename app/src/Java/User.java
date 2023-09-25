/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;
import java.sql.Date;

/**
 * User
 */
// line 31 "AssetPlus.ump"
public abstract class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByEmail = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private String email;
  private String password;
  private String name;
  private String phone;

  //User Associations
  private List<MaintenanceTicket> createdTickets;
  private AssetPlus assetPlus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(String aEmail, String aPassword, String aName, String aPhone, AssetPlus aAssetPlus)
  {
    password = aPassword;
    name = aName;
    phone = aPhone;
    if (!setEmail(aEmail))
    {
      throw new RuntimeException("Cannot create due to duplicate email. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    createdTickets = new ArrayList<MaintenanceTicket>();
    boolean didAddAssetPlus = setAssetPlus(aAssetPlus);
    if (!didAddAssetPlus)
    {
      throw new RuntimeException("Unable to create user due to assetPlus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    String anOldEmail = getEmail();
    if (anOldEmail != null && anOldEmail.equals(aEmail)) {
      return true;
    }
    if (hasWithEmail(aEmail)) {
      return wasSet;
    }
    email = aEmail;
    wasSet = true;
    if (anOldEmail != null) {
      usersByEmail.remove(anOldEmail);
    }
    usersByEmail.put(aEmail, this);
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhone(String aPhone)
  {
    boolean wasSet = false;
    phone = aPhone;
    wasSet = true;
    return wasSet;
  }

  public String getEmail()
  {
    return email;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithEmail(String aEmail)
  {
    return usersByEmail.get(aEmail);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithEmail(String aEmail)
  {
    return getWithEmail(aEmail) != null;
  }

  public String getPassword()
  {
    return password;
  }

  public String getName()
  {
    return name;
  }

  public String getPhone()
  {
    return phone;
  }
  /* Code from template association_GetMany */
  public MaintenanceTicket getCreatedTicket(int index)
  {
    MaintenanceTicket aCreatedTicket = createdTickets.get(index);
    return aCreatedTicket;
  }

  public List<MaintenanceTicket> getCreatedTickets()
  {
    List<MaintenanceTicket> newCreatedTickets = Collections.unmodifiableList(createdTickets);
    return newCreatedTickets;
  }

  public int numberOfCreatedTickets()
  {
    int number = createdTickets.size();
    return number;
  }

  public boolean hasCreatedTickets()
  {
    boolean has = createdTickets.size() > 0;
    return has;
  }

  public int indexOfCreatedTicket(MaintenanceTicket aCreatedTicket)
  {
    int index = createdTickets.indexOf(aCreatedTicket);
    return index;
  }
  /* Code from template association_GetOne */
  public AssetPlus getAssetPlus()
  {
    return assetPlus;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCreatedTickets()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MaintenanceTicket addCreatedTicket(String aId, Date aCreationDate, String aDescription, String aImageURL, boolean aRequiresManagerApproval, Asset aAsset)
  {
    return new MaintenanceTicket(aId, aCreationDate, aDescription, aImageURL, aRequiresManagerApproval, this, aAsset);
  }

  public boolean addCreatedTicket(MaintenanceTicket aCreatedTicket)
  {
    boolean wasAdded = false;
    if (createdTickets.contains(aCreatedTicket)) { return false; }
    User existingUser = aCreatedTicket.getUser();
    boolean isNewUser = existingUser != null && !this.equals(existingUser);
    if (isNewUser)
    {
      aCreatedTicket.setUser(this);
    }
    else
    {
      createdTickets.add(aCreatedTicket);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCreatedTicket(MaintenanceTicket aCreatedTicket)
  {
    boolean wasRemoved = false;
    //Unable to remove aCreatedTicket, as it must always have a user
    if (!this.equals(aCreatedTicket.getUser()))
    {
      createdTickets.remove(aCreatedTicket);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCreatedTicketAt(MaintenanceTicket aCreatedTicket, int index)
  {  
    boolean wasAdded = false;
    if(addCreatedTicket(aCreatedTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCreatedTickets()) { index = numberOfCreatedTickets() - 1; }
      createdTickets.remove(aCreatedTicket);
      createdTickets.add(index, aCreatedTicket);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCreatedTicketAt(MaintenanceTicket aCreatedTicket, int index)
  {
    boolean wasAdded = false;
    if(createdTickets.contains(aCreatedTicket))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCreatedTickets()) { index = numberOfCreatedTickets() - 1; }
      createdTickets.remove(aCreatedTicket);
      createdTickets.add(index, aCreatedTicket);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCreatedTicketAt(aCreatedTicket, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAssetPlus(AssetPlus aAssetPlus)
  {
    boolean wasSet = false;
    if (aAssetPlus == null)
    {
      return wasSet;
    }

    AssetPlus existingAssetPlus = assetPlus;
    assetPlus = aAssetPlus;
    if (existingAssetPlus != null && !existingAssetPlus.equals(aAssetPlus))
    {
      existingAssetPlus.removeUser(this);
    }
    assetPlus.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    usersByEmail.remove(getEmail());
    for(int i=createdTickets.size(); i > 0; i--)
    {
      MaintenanceTicket aCreatedTicket = createdTickets.get(i - 1);
      aCreatedTicket.delete();
    }
    AssetPlus placeholderAssetPlus = assetPlus;
    this.assetPlus = null;
    if(placeholderAssetPlus != null)
    {
      placeholderAssetPlus.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "," +
            "name" + ":" + getName()+ "," +
            "phone" + ":" + getPhone()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "assetPlus = "+(getAssetPlus()!=null?Integer.toHexString(System.identityHashCode(getAssetPlus())):"null");
  }
}