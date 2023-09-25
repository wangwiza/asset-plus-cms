/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;

// line 23 "AssetPlus.ump"
public class HotelRoom extends HotelLocation
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, HotelRoom> hotelroomsByRoomNumber = new HashMap<Integer, HotelRoom>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HotelRoom Attributes
  private int roomNumber;

  //HotelRoom Associations
  private HotelFloor hotelFloor;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HotelRoom(AssetPlus aAssetPlus, int aRoomNumber, HotelFloor aHotelFloor)
  {
    super(aAssetPlus);
    if (!setRoomNumber(aRoomNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate roomNumber. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddHotelFloor = setHotelFloor(aHotelFloor);
    if (!didAddHotelFloor)
    {
      throw new RuntimeException("Unable to create room due to hotelFloor. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setRoomNumber(int aRoomNumber)
  {
    boolean wasSet = false;
    Integer anOldRoomNumber = getRoomNumber();
    if (anOldRoomNumber != null && anOldRoomNumber.equals(aRoomNumber)) {
      return true;
    }
    if (hasWithRoomNumber(aRoomNumber)) {
      return wasSet;
    }
    roomNumber = aRoomNumber;
    wasSet = true;
    if (anOldRoomNumber != null) {
      hotelroomsByRoomNumber.remove(anOldRoomNumber);
    }
    hotelroomsByRoomNumber.put(aRoomNumber, this);
    return wasSet;
  }

  public int getRoomNumber()
  {
    return roomNumber;
  }
  /* Code from template attribute_GetUnique */
  public static HotelRoom getWithRoomNumber(int aRoomNumber)
  {
    return hotelroomsByRoomNumber.get(aRoomNumber);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithRoomNumber(int aRoomNumber)
  {
    return getWithRoomNumber(aRoomNumber) != null;
  }
  /* Code from template association_GetOne */
  public HotelFloor getHotelFloor()
  {
    return hotelFloor;
  }
  /* Code from template association_SetOneToMany */
  public boolean setHotelFloor(HotelFloor aHotelFloor)
  {
    boolean wasSet = false;
    if (aHotelFloor == null)
    {
      return wasSet;
    }

    HotelFloor existingHotelFloor = hotelFloor;
    hotelFloor = aHotelFloor;
    if (existingHotelFloor != null && !existingHotelFloor.equals(aHotelFloor))
    {
      existingHotelFloor.removeRoom(this);
    }
    hotelFloor.addRoom(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    hotelroomsByRoomNumber.remove(getRoomNumber());
    HotelFloor placeholderHotelFloor = hotelFloor;
    this.hotelFloor = null;
    if(placeholderHotelFloor != null)
    {
      placeholderHotelFloor.removeRoom(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "roomNumber" + ":" + getRoomNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "hotelFloor = "+(getHotelFloor()!=null?Integer.toHexString(System.identityHashCode(getHotelFloor())):"null");
  }
}