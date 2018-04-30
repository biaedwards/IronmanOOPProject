package Game.Location.Shop;

import Game.Items.*;

import java.util.HashSet;

public class Shop {
  HashSet<Item> inventory;


  public Shop(String type) {
    inventory = new HashSet<>();
    generateRandomItems(type);

  }

  void generateRandomItems(String item) {
    switch (item) {
      case "Weapon":
        for (int i = 0; i < 5; i++) {
          inventory.add(new Weapon());
        }
        break;
      case "Helmet":
        for (int i = 0; i < 5; i++) {
          inventory.add(new Helmet());
        }
        break;
      case "Vest":
        for (int i = 0; i < 5; i++) {
          inventory.add(new Vest());
        }
        break;
      case "Potion":
        for (int i = 0; i < 5; i++) {
          inventory.add(new HPPotion());
        }
        break;
      default:
        break;
    }


  }

  public HashSet<Item> getInventory() {
    return inventory;
  }
}
