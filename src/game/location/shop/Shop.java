package game.location.shop;

import game.PrintableInventory;

import game.items.Helmet;
import game.items.HpPotion;
import game.items.Item;
import game.items.Vest;
import game.items.Weapon;

import java.util.ArrayList;

public class Shop implements PrintableInventory {
  private ArrayList<Item> inventory;
  private String name;

  /**
   * Public constructor with String type parameter.
   */
  public Shop(String type) {
    name = type + "s";
    inventory = new ArrayList<>();
    generateRandomItems(type);

  }

  public String getName() {
    return name;
  }

  public ArrayList<Item> getInventory() {
    return inventory;
  }

  /**
   * Method for printing the inventory of the Shop.
   * */
  public void printInventory() {
    int counter = 1;
    for (Item item : inventory) {
      System.out.printf("%d: %s\n", counter, item.toString());
      counter++;
    }
  }

  private void generateRandomItems(String item) {
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
          inventory.add(new HpPotion());
        }
        break;
      default:
        break;
    }
  }
}

