package ca.mcgill.ecse.assetplus.controller;

import java.util.List;
import ca.mcgill.ecse.assetplus.application.AssetPlusApplication;
import ca.mcgill.ecse.assetplus.model.AssetPlus;
import ca.mcgill.ecse.assetplus.model.Guest;

public class Util {
  private static AssetPlus ap = AssetPlusApplication.getAssetPlus();

  public static Guest getGuest(String email) {
    List<Guest> guests = ap.getGuests();
    for (Guest guest: guests) {
      if (guest.getEmail().equals(email)) {
        return guest;
      }
    }
    return null;
  }
}
