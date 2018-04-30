package Game.Location;

import Game.Location.Shop.Shop;

import java.util.HashSet;

public class Town {


  private HashSet<Shop> shops = new HashSet<>();

  public Town() {
    shops.add(new Shop("Weapon"));
    shops.add(new Shop("Helmet"));
    shops.add(new Shop("Vest"));
    shops.add(new Shop("Potion"));

  }

  public HashSet<Shop> getShops() {
    return shops;
  }

}
