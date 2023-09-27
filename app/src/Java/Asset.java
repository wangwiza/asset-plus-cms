/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.sql.Date;
import java.util.*;

// line 70 "AssetPlus.ump"
public class Asset
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Asset Attributes
  private Date purchaseDate;

  //Autounique Attributes

  /**
   * automatic Integer
   */
  private int id;

  //Asset Associations
  private List<MaintenanceTicket> maintenanceHistory;
  private AssetPlus assetPlus;
  private HotelLocation hotelLocation;
  private AssetType assetType;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Asset(Date aPurchaseDate, AssetPlus aAssetPlus, HotelLocation aHotelLocation, AssetType aAssetType)
  {
    purchaseDate = aPurchaseDate;
    id = nextId++;
    maintenanceHistory = new ArrayList<MaintenanceTicket>();
    boolean didAddAssetPlus = setAssetPlus(aAssetPlus);
    if (!didAddAssetPlus)
    {
      throw new RuntimeException("Unable to create asset due to assetPlus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddHotelLocation = setHotelLocation(aHotelLocation);
    if (!didAddHotelLocation)
    {
      throw new RuntimeException("Unable to create furnishing due to hotelLocation. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAssetType = setAssetType(aAssetType);
    if (!didAddAssetType)
    {
      throw new RuntimeException("Unable to create asset due to assetType. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPurchaseDate(Date aPurchaseDate)
  {
    boolean wasSet = false;
    purchaseDate = aPurchaseDate;
    wasSet = true;
    return wasSet;
  }

  public Date getPurchaseDate()
  {
    return purchaseDate;
  }

  /**
   * automatic Integer
   */
  public int getId()
  {
    return id;
  }
  /* Code from template association_GetMany */
  public MaintenanceTicket getMaintenanceHistory(int index)
  {
    MaintenanceTicket aMaintenanceHistory = maintenanceHistory.get(index);
    return aMaintenanceHistory;
  }

  public List<MaintenanceTicket> getMaintenanceHistory()
  {
    List<MaintenanceTicket> newMaintenanceHistory = Collections.unmodifiableList(maintenanceHistory);
    return newMaintenanceHistory;
  }

  public int numberOfMaintenanceHistory()
  {
    int number = maintenanceHistory.size();
    return number;
  }

  public boolean hasMaintenanceHistory()
  {
    boolean has = maintenanceHistory.size() > 0;
    return has;
  }

  public int indexOfMaintenanceHistory(MaintenanceTicket aMaintenanceHistory)
  {
    int index = maintenanceHistory.indexOf(aMaintenanceHistory);
    return index;
  }
  /* Code from template association_GetOne */
  public AssetPlus getAssetPlus()
  {
    return assetPlus;
  }
  /* Code from template association_GetOne */
  public HotelLocation getHotelLocation()
  {
    return hotelLocation;
  }
  /* Code from template association_GetOne */
  public AssetType getAssetType()
  {
    return assetType;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMaintenanceHistory()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MaintenanceTicket addMaintenanceHistory(Date aCreationDate, String aDescription, boolean aRequiresManagerApproval, User aUser)
  {
    return new MaintenanceTicket(aCreationDate, aDescription, aRequiresManagerApproval, aUser, this);
  }

  public boolean addMaintenanceHistory(MaintenanceTicket aMaintenanceHistory)
  {
    boolean wasAdded = false;
    if (maintenanceHistory.contains(aMaintenanceHistory)) { return false; }
    Asset existingAsset = aMaintenanceHistory.getAsset();
    boolean isNewAsset = existingAsset != null && !this.equals(existingAsset);
    if (isNewAsset)
    {
      aMaintenanceHistory.setAsset(this);
    }
    else
    {
      maintenanceHistory.add(aMaintenanceHistory);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMaintenanceHistory(MaintenanceTicket aMaintenanceHistory)
  {
    boolean wasRemoved = false;
    //Unable to remove aMaintenanceHistory, as it must always have a asset
    if (!this.equals(aMaintenanceHistory.getAsset()))
    {
      maintenanceHistory.remove(aMaintenanceHistory);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMaintenanceHistoryAt(MaintenanceTicket aMaintenanceHistory, int index)
  {  
    boolean wasAdded = false;
    if(addMaintenanceHistory(aMaintenanceHistory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMaintenanceHistory()) { index = numberOfMaintenanceHistory() - 1; }
      maintenanceHistory.remove(aMaintenanceHistory);
      maintenanceHistory.add(index, aMaintenanceHistory);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMaintenanceHistoryAt(MaintenanceTicket aMaintenanceHistory, int index)
  {
    boolean wasAdded = false;
    if(maintenanceHistory.contains(aMaintenanceHistory))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMaintenanceHistory()) { index = numberOfMaintenanceHistory() - 1; }
      maintenanceHistory.remove(aMaintenanceHistory);
      maintenanceHistory.add(index, aMaintenanceHistory);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMaintenanceHistoryAt(aMaintenanceHistory, index);
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
      existingAssetPlus.removeAsset(this);
    }
    assetPlus.addAsset(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setHotelLocation(HotelLocation aHotelLocation)
  {
    boolean wasSet = false;
    if (aHotelLocation == null)
    {
      return wasSet;
    }

    HotelLocation existingHotelLocation = hotelLocation;
    hotelLocation = aHotelLocation;
    if (existingHotelLocation != null && !existingHotelLocation.equals(aHotelLocation))
    {
      existingHotelLocation.removeFurnishing(this);
    }
    hotelLocation.addFurnishing(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAssetType(AssetType aAssetType)
  {
    boolean wasSet = false;
    if (aAssetType == null)
    {
      return wasSet;
    }

    AssetType existingAssetType = assetType;
    assetType = aAssetType;
    if (existingAssetType != null && !existingAssetType.equals(aAssetType))
    {
      existingAssetType.removeAsset(this);
    }
    assetType.addAsset(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (maintenanceHistory.size() > 0)
    {
      MaintenanceTicket aMaintenanceHistory = maintenanceHistory.get(maintenanceHistory.size() - 1);
      aMaintenanceHistory.delete();
      maintenanceHistory.remove(aMaintenanceHistory);
    }
    
    AssetPlus placeholderAssetPlus = assetPlus;
    this.assetPlus = null;
    if(placeholderAssetPlus != null)
    {
      placeholderAssetPlus.removeAsset(this);
    }
    HotelLocation placeholderHotelLocation = hotelLocation;
    this.hotelLocation = null;
    if(placeholderHotelLocation != null)
    {
      placeholderHotelLocation.removeFurnishing(this);
    }
    AssetType placeholderAssetType = assetType;
    this.assetType = null;
    if(placeholderAssetType != null)
    {
      placeholderAssetType.removeAsset(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "purchaseDate" + "=" + (getPurchaseDate() != null ? !getPurchaseDate().equals(this)  ? getPurchaseDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "assetPlus = "+(getAssetPlus()!=null?Integer.toHexString(System.identityHashCode(getAssetPlus())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "hotelLocation = "+(getHotelLocation()!=null?Integer.toHexString(System.identityHashCode(getHotelLocation())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "assetType = "+(getAssetType()!=null?Integer.toHexString(System.identityHashCode(getAssetType())):"null");
  }
}