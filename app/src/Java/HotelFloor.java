/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.32.1.6535.66c005ced modeling language!*/


import java.util.*;

// line 16 "AssetPlus.ump"
public class HotelFloor extends HotelLocation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //HotelFloor Associations
  private List<HotelRoom> rooms;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public HotelFloor(AssetPlus aAssetPlus)
  {
    super(aAssetPlus);
    rooms = new ArrayList<HotelRoom>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public HotelRoom getRoom(int index)
  {
    HotelRoom aRoom = rooms.get(index);
    return aRoom;
  }

  public List<HotelRoom> getRooms()
  {
    List<HotelRoom> newRooms = Collections.unmodifiableList(rooms);
    return newRooms;
  }

  public int numberOfRooms()
  {
    int number = rooms.size();
    return number;
  }

  public boolean hasRooms()
  {
    boolean has = rooms.size() > 0;
    return has;
  }

  public int indexOfRoom(HotelRoom aRoom)
  {
    int index = rooms.indexOf(aRoom);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRooms()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public HotelRoom addRoom(AssetPlus aAssetPlus, int aRoomNumber)
  {
    return new HotelRoom(aAssetPlus, aRoomNumber, this);
  }

  public boolean addRoom(HotelRoom aRoom)
  {
    boolean wasAdded = false;
    if (rooms.contains(aRoom)) { return false; }
    HotelFloor existingHotelFloor = aRoom.getHotelFloor();
    boolean isNewHotelFloor = existingHotelFloor != null && !this.equals(existingHotelFloor);
    if (isNewHotelFloor)
    {
      aRoom.setHotelFloor(this);
    }
    else
    {
      rooms.add(aRoom);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRoom(HotelRoom aRoom)
  {
    boolean wasRemoved = false;
    //Unable to remove aRoom, as it must always have a hotelFloor
    if (!this.equals(aRoom.getHotelFloor()))
    {
      rooms.remove(aRoom);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRoomAt(HotelRoom aRoom, int index)
  {  
    boolean wasAdded = false;
    if(addRoom(aRoom))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRooms()) { index = numberOfRooms() - 1; }
      rooms.remove(aRoom);
      rooms.add(index, aRoom);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRoomAt(HotelRoom aRoom, int index)
  {
    boolean wasAdded = false;
    if(rooms.contains(aRoom))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRooms()) { index = numberOfRooms() - 1; }
      rooms.remove(aRoom);
      rooms.add(index, aRoom);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRoomAt(aRoom, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (rooms.size() > 0)
    {
      HotelRoom aRoom = rooms.get(rooms.size() - 1);
      aRoom.delete();
      rooms.remove(aRoom);
    }
    
    super.delete();
  }

}