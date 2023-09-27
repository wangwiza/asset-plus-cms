/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;
import java.sql.Date;

/**
 * Location
 */
// line 10 "AssetPlus.ump"
public abstract class HotelLocation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HotelLocation Associations
  private List<Asset> furnishing;
  private AssetPlus assetPlus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HotelLocation(AssetPlus aAssetPlus)
  {
    furnishing = new ArrayList<Asset>();
    boolean didAddAssetPlus = setAssetPlus(aAssetPlus);
    if (!didAddAssetPlus)
    {
      throw new RuntimeException("Unable to create location due to assetPlus. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Asset getFurnishing(int index)
  {
    Asset aFurnishing = furnishing.get(index);
    return aFurnishing;
  }

  public List<Asset> getFurnishing()
  {
    List<Asset> newFurnishing = Collections.unmodifiableList(furnishing);
    return newFurnishing;
  }

  public int numberOfFurnishing()
  {
    int number = furnishing.size();
    return number;
  }

  public boolean hasFurnishing()
  {
    boolean has = furnishing.size() > 0;
    return has;
  }

  public int indexOfFurnishing(Asset aFurnishing)
  {
    int index = furnishing.indexOf(aFurnishing);
    return index;
  }
  /* Code from template association_GetOne */
  public AssetPlus getAssetPlus()
  {
    return assetPlus;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfFurnishing()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Asset addFurnishing(Date aPurchaseDate, AssetPlus aAssetPlus, AssetType aAssetType)
  {
    return new Asset(aPurchaseDate, aAssetPlus, this, aAssetType);
  }

  public boolean addFurnishing(Asset aFurnishing)
  {
    boolean wasAdded = false;
    if (furnishing.contains(aFurnishing)) { return false; }
    HotelLocation existingHotelLocation = aFurnishing.getHotelLocation();
    boolean isNewHotelLocation = existingHotelLocation != null && !this.equals(existingHotelLocation);
    if (isNewHotelLocation)
    {
      aFurnishing.setHotelLocation(this);
    }
    else
    {
      furnishing.add(aFurnishing);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeFurnishing(Asset aFurnishing)
  {
    boolean wasRemoved = false;
    //Unable to remove aFurnishing, as it must always have a hotelLocation
    if (!this.equals(aFurnishing.getHotelLocation()))
    {
      furnishing.remove(aFurnishing);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addFurnishingAt(Asset aFurnishing, int index)
  {  
    boolean wasAdded = false;
    if(addFurnishing(aFurnishing))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFurnishing()) { index = numberOfFurnishing() - 1; }
      furnishing.remove(aFurnishing);
      furnishing.add(index, aFurnishing);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFurnishingAt(Asset aFurnishing, int index)
  {
    boolean wasAdded = false;
    if(furnishing.contains(aFurnishing))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFurnishing()) { index = numberOfFurnishing() - 1; }
      furnishing.remove(aFurnishing);
      furnishing.add(index, aFurnishing);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFurnishingAt(aFurnishing, index);
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
      existingAssetPlus.removeLocation(this);
    }
    assetPlus.addLocation(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=furnishing.size(); i > 0; i--)
    {
      Asset aFurnishing = furnishing.get(i - 1);
      aFurnishing.delete();
    }
    AssetPlus placeholderAssetPlus = assetPlus;
    this.assetPlus = null;
    if(placeholderAssetPlus != null)
    {
      placeholderAssetPlus.removeLocation(this);
    }
  }

}