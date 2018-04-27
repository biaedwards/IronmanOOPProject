package Game.Location;

import Game.Items.Item;
import Game.Stat;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Enemy {


  static final int HP_DEFAULT = 100;


  private String name;
  private int maxHP;
  private int currentHP;
  private double damage;
  private int gold;
  private Item item;
  private Stat stat;

  private ArrayList<String> allItems = new ArrayList<>();
  private ArrayList<String> allNames = new ArrayList<>();


  public Enemy(double damage) {
    generateNames();
    this.name = getRandomName();
    maxHP = HP_DEFAULT;
    this.damage = damage;
    this.gold = (int) damage / 10;
    // generateItems();
    // this.item = getRandomItem();
    Stat stat;


  }

  //  void drop() {
  //   System.out.printf("%s dropped item: %s\n", getName(), getItem().getName());
  // } TODO void drop

  public String getName() {
    return name;
  }

  // private Item getItem() {
  //  return item; TODO getItem
  //}

  //private Item getRandomItem() {
  // int index = getName().hashCode() % itemsArrayList.size();
  //  item = itemsArrayList.get(index);
  //  return item; TODO getRandomItem
  //}

  // private void generateItems() {
//TODO SET ARRAY LIST OF ITEMS
// }
  public int getMaxHP() {
    return maxHP;
  }

  public void setMaxHP(int HP) {
    this.maxHP = HP;
  }

  public int getCurrentHP() {
    return currentHP;
  }

  public void setCurrentHP(int currentHP) {
    this.currentHP = currentHP;
  }

  public void setName(String name) {
    this.name = name;
  }


  private void generateNames() {
    allNames.add("Adrian");
    allNames.add("Borislav");
    allNames.add("Dido");
    allNames.add("Drago");
    allNames.add("Edward");
    allNames.add("Elizar");
    allNames.add("Kiril");
    allNames.add("Krasimir");
    allNames.add("MariaONE");
    allNames.add("MariaTWO");
    allNames.add("MartinONE");
    allNames.add("MartinTwo");
    allNames.add("RadoONE");
    allNames.add("RadoTWO");
    allNames.add("Oxana");
    allNames.add("Nasko");
    allNames.add("Sasho");
    allNames.add("Danail");
    allNames.add("Irina");
    allNames.add("Doncho");
    allNames.add("Georgi");
    allNames.add("Ivan");
    allNames.add("Ivelin");
    allNames.add("Ivo");
    allNames.add("Krisi");
    allNames.add("Lu");
    allNames.add("Penyo");
    allNames.add("Pepi");
    allNames.add("Pesho");
    allNames.add("Rosi");
    allNames.add("Steven");
    allNames.add("Maya");
    allNames.add("Viktor");
    allNames.add("Yana");
    allNames.add("Yani");
    allNames.add("Yasen");


  }

  private String getRandomName() {
    int index = getName().hashCode() % allNames.size();
    name = allNames.get(index);
    return name;
  }




}

