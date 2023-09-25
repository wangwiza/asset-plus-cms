/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;
import java.sql.Date;

/**
 * Maintenance
 */
// line 82 "AssetPlus.ump"
public class MaintenanceTicket
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum PriorityLevel { Urgent, Normal, Low }
  public enum Status { Open, Resolved, Closed }
  public enum TimeEstimate { LessThanADay, OneToThreeDays, ThreeToSevenDays, OneToThreeWeeks, MoreThanThreeWeeks }

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, MaintenanceTicket> maintenanceticketsById = new HashMap<String, MaintenanceTicket>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MaintenanceTicket Attributes
  private String id;
  private Date creationDate;
  private String description;
  private String imageURL;
  private boolean requiresManagerApproval;

  //MaintenanceTicket Associations
  private List<Staff> assignees;
  private List<MaintenanceNote> notes;
  private User user;
  private Asset asset;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MaintenanceTicket(String aId, Date aCreationDate, String aDescription, String aImageURL, boolean aRequiresManagerApproval, User aUser, Asset aAsset)
  {
    creationDate = aCreationDate;
    description = aDescription;
    imageURL = aImageURL;
    requiresManagerApproval = aRequiresManagerApproval;
    if (!setId(aId))
    {
      throw new RuntimeException("Cannot create due to duplicate id. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    assignees = new ArrayList<Staff>();
    notes = new ArrayList<MaintenanceNote>();
    boolean didAddUser = setUser(aUser);
    if (!didAddUser)
    {
      throw new RuntimeException("Unable to create createdTicket due to user. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAsset = setAsset(aAsset);
    if (!didAddAsset)
    {
      throw new RuntimeException("Unable to create maintenanceHistory due to asset. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setId(String aId)
  {
    boolean wasSet = false;
    String anOldId = getId();
    if (anOldId != null && anOldId.equals(aId)) {
      return true;
    }
    if (hasWithId(aId)) {
      return wasSet;
    }
    id = aId;
    wasSet = true;
    if (anOldId != null) {
      maintenanceticketsById.remove(anOldId);
    }
    maintenanceticketsById.put(aId, this);
    return wasSet;
  }

  public boolean setCreationDate(Date aCreationDate)
  {
    boolean wasSet = false;
    creationDate = aCreationDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setImageURL(String aImageURL)
  {
    boolean wasSet = false;
    imageURL = aImageURL;
    wasSet = true;
    return wasSet;
  }

  public boolean setRequiresManagerApproval(boolean aRequiresManagerApproval)
  {
    boolean wasSet = false;
    requiresManagerApproval = aRequiresManagerApproval;
    wasSet = true;
    return wasSet;
  }

  public String getId()
  {
    return id;
  }
  /* Code from template attribute_GetUnique */
  public static MaintenanceTicket getWithId(String aId)
  {
    return maintenanceticketsById.get(aId);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithId(String aId)
  {
    return getWithId(aId) != null;
  }

  public Date getCreationDate()
  {
    return creationDate;
  }

  public String getDescription()
  {
    return description;
  }

  public String getImageURL()
  {
    return imageURL;
  }

  public boolean getRequiresManagerApproval()
  {
    return requiresManagerApproval;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isRequiresManagerApproval()
  {
    return requiresManagerApproval;
  }
  /* Code from template association_GetMany */
  public Staff getAssignee(int index)
  {
    Staff aAssignee = assignees.get(index);
    return aAssignee;
  }

  public List<Staff> getAssignees()
  {
    List<Staff> newAssignees = Collections.unmodifiableList(assignees);
    return newAssignees;
  }

  public int numberOfAssignees()
  {
    int number = assignees.size();
    return number;
  }

  public boolean hasAssignees()
  {
    boolean has = assignees.size() > 0;
    return has;
  }

  public int indexOfAssignee(Staff aAssignee)
  {
    int index = assignees.indexOf(aAssignee);
    return index;
  }
  /* Code from template association_GetMany */
  public MaintenanceNote getNote(int index)
  {
    MaintenanceNote aNote = notes.get(index);
    return aNote;
  }

  public List<MaintenanceNote> getNotes()
  {
    List<MaintenanceNote> newNotes = Collections.unmodifiableList(notes);
    return newNotes;
  }

  public int numberOfNotes()
  {
    int number = notes.size();
    return number;
  }

  public boolean hasNotes()
  {
    boolean has = notes.size() > 0;
    return has;
  }

  public int indexOfNote(MaintenanceNote aNote)
  {
    int index = notes.indexOf(aNote);
    return index;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_GetOne */
  public Asset getAsset()
  {
    return asset;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAssignees()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addAssignee(Staff aAssignee)
  {
    boolean wasAdded = false;
    if (assignees.contains(aAssignee)) { return false; }
    MaintenanceTicket existingMaintenanceTicket = aAssignee.getMaintenanceTicket();
    boolean isNewMaintenanceTicket = existingMaintenanceTicket != null && !this.equals(existingMaintenanceTicket);
    if (isNewMaintenanceTicket)
    {
      aAssignee.setMaintenanceTicket(this);
    }
    else
    {
      assignees.add(aAssignee);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAssignee(Staff aAssignee)
  {
    boolean wasRemoved = false;
    //Unable to remove aAssignee, as it must always have a maintenanceTicket
    if (!this.equals(aAssignee.getMaintenanceTicket()))
    {
      assignees.remove(aAssignee);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAssigneeAt(Staff aAssignee, int index)
  {  
    boolean wasAdded = false;
    if(addAssignee(aAssignee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignees()) { index = numberOfAssignees() - 1; }
      assignees.remove(aAssignee);
      assignees.add(index, aAssignee);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAssigneeAt(Staff aAssignee, int index)
  {
    boolean wasAdded = false;
    if(assignees.contains(aAssignee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAssignees()) { index = numberOfAssignees() - 1; }
      assignees.remove(aAssignee);
      assignees.add(index, aAssignee);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAssigneeAt(aAssignee, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfNotes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MaintenanceNote addNote(String aDescription, Date aDate, Staff aStaff)
  {
    return new MaintenanceNote(aDescription, aDate, aStaff, this);
  }

  public boolean addNote(MaintenanceNote aNote)
  {
    boolean wasAdded = false;
    if (notes.contains(aNote)) { return false; }
    MaintenanceTicket existingMaintenanceTicket = aNote.getMaintenanceTicket();
    boolean isNewMaintenanceTicket = existingMaintenanceTicket != null && !this.equals(existingMaintenanceTicket);
    if (isNewMaintenanceTicket)
    {
      aNote.setMaintenanceTicket(this);
    }
    else
    {
      notes.add(aNote);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeNote(MaintenanceNote aNote)
  {
    boolean wasRemoved = false;
    //Unable to remove aNote, as it must always have a maintenanceTicket
    if (!this.equals(aNote.getMaintenanceTicket()))
    {
      notes.remove(aNote);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addNoteAt(MaintenanceNote aNote, int index)
  {  
    boolean wasAdded = false;
    if(addNote(aNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNotes()) { index = numberOfNotes() - 1; }
      notes.remove(aNote);
      notes.add(index, aNote);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveNoteAt(MaintenanceNote aNote, int index)
  {
    boolean wasAdded = false;
    if(notes.contains(aNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfNotes()) { index = numberOfNotes() - 1; }
      notes.remove(aNote);
      notes.add(index, aNote);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addNoteAt(aNote, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setUser(User aUser)
  {
    boolean wasSet = false;
    if (aUser == null)
    {
      return wasSet;
    }

    User existingUser = user;
    user = aUser;
    if (existingUser != null && !existingUser.equals(aUser))
    {
      existingUser.removeCreatedTicket(this);
    }
    user.addCreatedTicket(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAsset(Asset aAsset)
  {
    boolean wasSet = false;
    if (aAsset == null)
    {
      return wasSet;
    }

    Asset existingAsset = asset;
    asset = aAsset;
    if (existingAsset != null && !existingAsset.equals(aAsset))
    {
      existingAsset.removeMaintenanceHistory(this);
    }
    asset.addMaintenanceHistory(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    maintenanceticketsById.remove(getId());
    for(int i=assignees.size(); i > 0; i--)
    {
      Staff aAssignee = assignees.get(i - 1);
      aAssignee.delete();
    }
    while (notes.size() > 0)
    {
      MaintenanceNote aNote = notes.get(notes.size() - 1);
      aNote.delete();
      notes.remove(aNote);
    }
    
    User placeholderUser = user;
    this.user = null;
    if(placeholderUser != null)
    {
      placeholderUser.removeCreatedTicket(this);
    }
    Asset placeholderAsset = asset;
    this.asset = null;
    if(placeholderAsset != null)
    {
      placeholderAsset.removeMaintenanceHistory(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "description" + ":" + getDescription()+ "," +
            "imageURL" + ":" + getImageURL()+ "," +
            "requiresManagerApproval" + ":" + getRequiresManagerApproval()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "creationDate" + "=" + (getCreationDate() != null ? !getCreationDate().equals(this)  ? getCreationDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "asset = "+(getAsset()!=null?Integer.toHexString(System.identityHashCode(getAsset())):"null");
  }
}