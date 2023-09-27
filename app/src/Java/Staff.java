/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;
import java.sql.Date;

// line 40 "AssetPlus.ump"
public abstract class Staff extends User
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Staff Associations
  private List<MaintenanceNote> createdNotes;
  private MaintenanceTicket maintenanceTicket;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Staff(String aEmail, String aPassword, AssetPlus aAssetPlus, MaintenanceTicket aMaintenanceTicket)
  {
    super(aEmail, aPassword, aAssetPlus);
    createdNotes = new ArrayList<MaintenanceNote>();
    boolean didAddMaintenanceTicket = setMaintenanceTicket(aMaintenanceTicket);
    if (!didAddMaintenanceTicket)
    {
      throw new RuntimeException("Unable to create assignee due to maintenanceTicket. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public MaintenanceNote getCreatedNote(int index)
  {
    MaintenanceNote aCreatedNote = createdNotes.get(index);
    return aCreatedNote;
  }

  public List<MaintenanceNote> getCreatedNotes()
  {
    List<MaintenanceNote> newCreatedNotes = Collections.unmodifiableList(createdNotes);
    return newCreatedNotes;
  }

  public int numberOfCreatedNotes()
  {
    int number = createdNotes.size();
    return number;
  }

  public boolean hasCreatedNotes()
  {
    boolean has = createdNotes.size() > 0;
    return has;
  }

  public int indexOfCreatedNote(MaintenanceNote aCreatedNote)
  {
    int index = createdNotes.indexOf(aCreatedNote);
    return index;
  }
  /* Code from template association_GetOne */
  public MaintenanceTicket getMaintenanceTicket()
  {
    return maintenanceTicket;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCreatedNotes()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MaintenanceNote addCreatedNote(String aDescription, Date aDate, MaintenanceTicket aMaintenanceTicket)
  {
    return new MaintenanceNote(aDescription, aDate, this, aMaintenanceTicket);
  }

  public boolean addCreatedNote(MaintenanceNote aCreatedNote)
  {
    boolean wasAdded = false;
    if (createdNotes.contains(aCreatedNote)) { return false; }
    Staff existingStaff = aCreatedNote.getStaff();
    boolean isNewStaff = existingStaff != null && !this.equals(existingStaff);
    if (isNewStaff)
    {
      aCreatedNote.setStaff(this);
    }
    else
    {
      createdNotes.add(aCreatedNote);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCreatedNote(MaintenanceNote aCreatedNote)
  {
    boolean wasRemoved = false;
    //Unable to remove aCreatedNote, as it must always have a staff
    if (!this.equals(aCreatedNote.getStaff()))
    {
      createdNotes.remove(aCreatedNote);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCreatedNoteAt(MaintenanceNote aCreatedNote, int index)
  {  
    boolean wasAdded = false;
    if(addCreatedNote(aCreatedNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCreatedNotes()) { index = numberOfCreatedNotes() - 1; }
      createdNotes.remove(aCreatedNote);
      createdNotes.add(index, aCreatedNote);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCreatedNoteAt(MaintenanceNote aCreatedNote, int index)
  {
    boolean wasAdded = false;
    if(createdNotes.contains(aCreatedNote))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCreatedNotes()) { index = numberOfCreatedNotes() - 1; }
      createdNotes.remove(aCreatedNote);
      createdNotes.add(index, aCreatedNote);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCreatedNoteAt(aCreatedNote, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMaintenanceTicket(MaintenanceTicket aMaintenanceTicket)
  {
    boolean wasSet = false;
    if (aMaintenanceTicket == null)
    {
      return wasSet;
    }

    MaintenanceTicket existingMaintenanceTicket = maintenanceTicket;
    maintenanceTicket = aMaintenanceTicket;
    if (existingMaintenanceTicket != null && !existingMaintenanceTicket.equals(aMaintenanceTicket))
    {
      existingMaintenanceTicket.removeAssignee(this);
    }
    maintenanceTicket.addAssignee(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=createdNotes.size(); i > 0; i--)
    {
      MaintenanceNote aCreatedNote = createdNotes.get(i - 1);
      aCreatedNote.delete();
    }
    MaintenanceTicket placeholderMaintenanceTicket = maintenanceTicket;
    this.maintenanceTicket = null;
    if(placeholderMaintenanceTicket != null)
    {
      placeholderMaintenanceTicket.removeAssignee(this);
    }
    super.delete();
  }

}