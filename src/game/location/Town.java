package game.location;

import game.location.Shop.Shop;

import java.util.ArrayList;

public class Town {


  private ArrayList<Shop> shops;

  public Town() {
    shops = new ArrayList<>();
    shops.add(new Shop("Weapon"));
    shops.add(new Shop("Helmet"));
    shops.add(new Shop("Vest"));
    shops.add(new Shop("Potion"));

  }

  public ArrayList<Shop> getShops() {
    return shops;
  }

}
