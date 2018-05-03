package Game.Location.Shop;

import Game.Items.*;
import Game.PrintableInventory;

import java.util.ArrayList;
import java.util.HashSet;

public class Shop implements PrintableInventory{
    private ArrayList<Item> inventory;
    private String name;


    public Shop(String type) {
        name = type + "s";
        inventory = new ArrayList<>();
        generateRandomItems(type);

    }

    public String getName() {
        return name;
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
                    inventory.add(new HPPotion());
                }
                break;
            default:
                break;
        }


    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void printInventory() {
        int counter = 1;
        for (Item item : inventory) {
            System.out.printf("%d: %s\n", counter, item.toString());
            counter++;
        }

    }
}

