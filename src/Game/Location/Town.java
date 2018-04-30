package Game.Location;

import Game.Location.Shop.Shop;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

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
