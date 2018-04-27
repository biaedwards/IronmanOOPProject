package Game.Location;

import Game.Attack;
import Game.Items.Helmet;
import Game.Items.Item;
import Game.Items.Vest;
import Game.Items.Weapon;
import Game.Stat;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Enemy implements Attack {

  private int maxHP;
  static final int HP_DEFAULT = 100;
  private String name;
  private int currentHP;
  private double damage;
  private int gold;
  private Item item;
  private int XP;
  private Stat stat;

  private ArrayList<String> allNames = new ArrayList<>();


  public Enemy() {
    generateNames();
    this.name = getRandomName();
    maxHP = HP_DEFAULT;
    this.damage = getRandomDamage();
    this.gold = (int) damage * 10;
    this.item = getRandomItem();
    this.XP = (int)damage*2;
    Stat stat;
  }

  public Item dropItem() {
    System.out.printf("%s dropped item: %s\n", getName(), getItem().getName());
    return item;
  }

  public double getDamage() {
    return damage;
  }

  public void setDamage(double damage) {
    this.damage = damage;
  }

  public int getGold() {
    return gold;
  }

  public int getXP() {
    return XP;
  }

  public int getMaxHP() {
    return maxHP;
  }

  public Item getItem() {
    return item;
  }

  public String getName() {
    return name;
  }

  public int getCurrentHP() {
    return currentHP;
  }

  private int getRandomDamage() {
    return ThreadLocalRandom.current().nextInt(50, 100);
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
  public int attack(){
    return (int)damage ;
  }

  private String getRandomName() {
    int index = getName().hashCode() % allNames.size();
    name = allNames.get(index);
    return name;
  }

  private Item getRandomItem() {
    int index = ThreadLocalRandom.current().nextInt(0, 3);
    Item item;
    switch (index) {
      case 0:
        item = new Weapon();
        break;
      case 1:
        item = new Vest();
        break;
      case 2:
        item = new Helmet();
        break;
        default: item = null;
          break;
    }
    return item;
  }
}

