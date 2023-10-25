package ca.mcgill.ecse.assetplus.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.SpecificAsset;
import ca.mcgill.ecse.assetplus.model.User;

public class Util {
  private static AssetPlus ap = AssetPlusApplication.getAssetPlus();

  public static User getUser(String email) {
    ArrayList<User> allUsers = new java.util.ArrayList<>(Collections.emptyList());
    allUsers.addAll(ap.getGuests());
    allUsers.addAll(ap.getEmployees());
    allUsers.add(ap.getManager());

    for (User u: allUsers) {
      if (u.getEmail().equals(email)) {
        return u;
      }
    }
    return null;
  }

  public static SpecificAsset getSpecificAsset(int id) {
    List<SpecificAsset> allAsset = ap.getSpecificAssets();
    for (SpecificAsset a: allAsset) {
      if (a.getAssetNumber() == id) {
        return a;
      }
    }
    return null;
  }

}
