package ca.mcgill.ecse.assetplus.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import com.thoughtworks.xstream.mapper.Mapper.Null;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.controller.AssetPlusFeatureSet3Controller;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.AssetType;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;

// line 10 "../../../../../../AssetPlusPersistence.ump"
// line 16 "../../../../../../AssetPlus.ump"
public class TOAsset
{
  private static AssetPlus ap = AssetPlusApplication.getAssetPlus();
  //User Attributes
  private AssetType assetType;
  private Integer floorNumber;
  private Integer roomNumber;
  private Date purchaseDate;
  private Integer assetNumber;


  public TOAsset(Integer aAssetNumber, AssetType aAssetType, Integer aFloorNumber, Integer aRoomNumber, Date aPurhcaseDate)
  {
    assetNumber = aAssetNumber;
    assetType = aAssetType;
    floorNumber = aFloorNumber;
    roomNumber = aRoomNumber;
    purchaseDate = aPurhcaseDate;
  }

  public static ArrayList<TOAsset> getAllAssets() {
    ArrayList<TOAsset> assetList = new ArrayList<TOAsset>();
    for (var asset: ap.getSpecificAssets()) {
      assetList.add(new TOAsset(asset.getAssetNumber(), asset.getAssetType(), asset.getFloorNumber(), asset.getRoomNumber(), asset.getPurchaseDate()));
    }
    return assetList;

  }
  
  public boolean setAssetType(AssetType aAssetType)
  {
    boolean wasSet = false;
    assetType = aAssetType;
    wasSet = true;
    return wasSet;
  }

  public boolean setFloorNumber(Integer aFloorNumber)
  {
    boolean wasSet = false;
    floorNumber = aFloorNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setRoomNumber(Integer aRoomNumber)
  {
    boolean wasSet = false;
    roomNumber = aRoomNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setPurchaseDate(Date aPurchaseDate)
  {
    boolean wasSet = false;
    purchaseDate = aPurchaseDate;
    wasSet = true;
    return wasSet;
  }

  public AssetType getAssetType()
  {
    return assetType;
  }

  public Integer getAssetNumber()
  {
    return assetNumber;
  }

  public Integer getFloorNumber()
  {
    return floorNumber;
  }

  public Integer getRoomNumber()
  {
    return roomNumber;
  }

  public Date getPurchaseDate()
  {
    return purchaseDate;
  }

  public String toString(AssetType assetType){
    return (getAssetType()!=null?Integer.toHexString(System.identityHashCode(getAssetType())):"null");
  }
}