package game.entities;

import static game.items.Names.allSkills;

import game.PlayerType;
import game.PrintableInventory;
import game.Skill;
import game.Stat;
import game.items.Equipable;
import game.items.Helmet;
import game.items.HpPotion;
import game.items.Item;
import game.items.Tome;
import game.items.Usable;
import game.items.Vest;
import game.items.Weapon;

import java.util.ArrayList;
import java.util.HashMap;


public abstract class Hero extends Entity implements PrintableInventory, Skills {
  private PlayerType type;
  private int level;
  private int defence;
  private int stat;
  private int xpUntilNextlevel;
  private Weapon weapon;
  private Helmet helmet;
  private Vest vest;

  private HashMap<Item, Integer> inventory;
  private ArrayList<Skill> skills;

  Hero(String name) {
    setName(name);
    maxHp = 150;
    currentHp = maxHp;
    xp = 0;
    level = 1;
    defence = 0;
    stat = 1;
    gold = 0;
    xpUntilNextlevel = 1000;
    skills = new ArrayList<>();
    inventory = new HashMap<>();
  }

  abstract void createDefaultInventory();

  /**
   * This is method printing stats of our Hero.
   */
  public String statsToString() {
    return String.format("HP: %d/%d\nXP: %d/%d\nLevel: %d\nDefence: %d\nDamage: %d\n"
        + "Gold: %d\nWeapon: %s\nHelmet: %s\nVest: %s\n",
      currentHp, maxHp, xp, xpUntilNextlevel, level, getDefence(), getDamage(),
      gold, weapon.toString(), helmet.toString(), vest.toString());
  }

  public void takeDamage(int damage) {
    currentHp -= (defence > damage) ? 1 : damage - defence;
  }

  public int attack() {
    return getDamage();
  }

  /**
   * Method to print the inventory of our Hero.
   */
  public void printInventory() {
    int counter = 1;
    for (Item item : inventory.keySet()) {
      System.out.printf("%d: %s - %d\n", counter++, item.toString(), inventory.get(item));
    }
  }

  public PlayerType getType() {
    return type;
  }

  public void setXp(int xp) {
    this.xp += xp;
    level();
  }

  public int getMaxHp() {
    return maxHp;
  }

  public void changeCurrentHp(int hp) {
    currentHp += hp;
    currentHp = Math.min(currentHp, maxHp);
  }

  /**
   * Method for adding an inventory.
   */
  public void addToInventory(Item item) {
    if (inventory.containsKey(item)) {
      inventory.put(item, inventory.get(item) + 1);
    } else {
      inventory.put(item, 1);
    }
  }

  public int getDefence() {
    defence = helmet.getDefence() + vest.getDefence();
    return defence;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getGold() {
    return gold;
  }

  public void setGold(int gold) {
    this.gold += gold;
  }

  public HashMap<Item, Integer> getInventory() {
    return inventory;
  }

  public ArrayList<Skill> getSkills() {
    return skills;
  }

  /**
   * Method for getting all the usable items of our Hero.
   */
  public ArrayList<Item> getUsables() {
    ArrayList<Item> usables = new ArrayList<>();
    for (Item item : inventory.keySet()) {
      if (!(item instanceof Usable)) {
        continue;
      }
      usables.add(item);
    }
    return usables;
  }

  /**
   * Method for getting all the equipable items of our Hero.
   */
  public ArrayList<Item> getEquipables() {
    ArrayList<Item> equipables = new ArrayList<>();
    for (Item item : inventory.keySet()) {
      if (!(item instanceof Equipable)) {
        continue;
      }
      equipables.add(item);
    }
    return equipables;
  }

  /**
   * Method for printing all usable items.
   */
  public void printUsables() {
    int counter = 1;
    System.out.println("Here are your items with the quantities available in your invontory. "
        + "Pick one by pressing a number. ");
    for (Item item : getUsables()) {
      System.out.printf("%d: %s - quantity %d \n", counter++, item.getName(), inventory.get(item));
    }
    if (counter == 1) {
      System.out.println("You don't have any usable items. Press any key to continue.");
    }
  }

  /**
   * Method for printing all equipable items.
   */
  public void printEquipables() {
    int counter = 1;
    for (Item item : getEquipables()) {
      System.out.printf("%d: %s\n", counter++, item.toString());
    }
    if (counter == 1) {
      System.out.println("You don't have any equipable items. Press any key to continue.");
    }
  }

  /**
   * Method for using usable item.
   * */
  public void useUsables(int number) {
    int counter = 1;
    for (Item item : getUsables()) {
      if (number == counter) {
        use(item);
        break;
      }
      counter++;
    }
  }

  /**
   * Method for equip equipable item.
   * */
  public void equipEquipables(int number) {
    int counter = 1;
    for (Item item : getEquipables()) {
      if (number == counter) {
        equip(item);
        break;
      }
      counter++;
    }
  }

  /**
   * Method for adding skill on even level
   * and also showing your level upgrade once you reach next level.
   * */
  public void level() {
    if (level % 2 == 0) {
      learnSkill();
    } else {
      System.out.println("Congratulations! You leveled up.");
    }
    currentHp = maxHp;
    System.out.printf("Here are your updated stats:\n%s", statsToString());
    level();
  }

  /**
   * Method for buing an item.
   * */
  public void buyItem(Item item) {
    if (item.getCost() > gold) {
      System.out.println("You cannot afford this item");
    } else {
      gold -= item.getCost();
      addToInventory(item);
      System.out.printf("You successfully bought %s\n", item.toString());
      System.out.printf("Remaining gold: %d\n", getGold());
    }
  }

  /**
   * Method for selling an item.
   * */
  public void sellItem(int x) {
    int counter = 1;
    for (Item item : inventory.keySet()) {
      if (counter == x) {
        if (inventory.get(item) > 1) {
          inventory.put(item, inventory.get(item) - 1);
        } else {
          inventory.remove(item);
        }
        System.out.printf("You sold %s and got %d gold\n", item.getName(), item.getCost());
        gold += item.getCost();
        break;
      }
      counter++;
    }
  }

  public int getCurrentHp() {
    return currentHp;
  }

  @Override
  public int castSkill(Skill skill) {
    if (skill.usesWeapon()) {
      return (skill.getDamage() + getDamage());
    } else {
      return skill.getDamage();
    }
  }

  @Override
  public void learnSkill() {
    for (Skill oneSkill : allSkills) {
      if (oneSkill.getType() != type || oneSkill.getLevel() != level) {
        continue;
      }
      skills.add(oneSkill);
      System.out.printf("Congratulations! You leveled up and learned a new skill %s!!!",
          oneSkill.toString());
      return;
    }
  }

  void setType(PlayerType type) {
    this.type = type;
  }

  void setMaxHp(int hp) {
    this.maxHp = hp;
  }

  void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }

  void setDefence(int defence) {
    this.defence = defence;
  }

  int getStat() {
    return stat;
  }

  void setStat(int stat) {
    this.stat = stat;
  }

  int getLevel() {
    return level;
  }

  void setLevel(int level) {
    this.level = level;
  }

  int getXpUntilNextlevel() {
    return xpUntilNextlevel;
  }

  void setXpUntilNextlevel(int xpUntilNextlevel) {
    this.xpUntilNextlevel = xpUntilNextlevel;
  }

  void setPrimaryStat(Stat primaryStat) {
    Stat primaryStat1 = primaryStat;
  }

  void setHelmet(Helmet helmet) {
    this.helmet = helmet;
  }

  void setVest(Vest vest) {
    this.vest = vest;
  }

  private void equip(Item item) {
    if (item instanceof Weapon) {
      if (((Weapon) item).getLevelRequirement() <= level) {
        if (this instanceof Archer && ((Weapon) item).getType() == PlayerType.ARCHER) {
          addToInventory(weapon);
          weapon = (Weapon) item;
          inventory.remove(item);
        } else if (this instanceof Warrior && ((Weapon) item).getType() == PlayerType.WARRIOR) {
          addToInventory(weapon);
          weapon = (Weapon) item;
          inventory.remove(item);
        } else if (this instanceof Mage && ((Weapon) item).getType() == PlayerType.MAGE) {
          addToInventory(weapon);
          weapon = (Weapon) item;
          inventory.remove(item);
        } else {
          System.out.println("You cannot equip this weapon!");
        }
      } else {
        System.out.println("Your level is too low to equip this item.");
      }

    }
    if (item instanceof Helmet) {
      if (((Helmet) item).getLevelRequirement() <= level) {
        stat -= helmet.getStatBonus();
        addToInventory(helmet);
        helmet = (Helmet) item;
        stat += helmet.getStatBonus();
        inventory.remove(item);
      } else {
        System.out.println("Your level is too low to equip this item.");
      }
    }
    if (item instanceof Vest) {
      if (((Vest) item).getLevelRequirement() <= level) {
        maxHp -= vest.getHpBonus();
        addToInventory(vest);
        vest = (Vest) item;
        maxHp += vest.getHpBonus();
        inventory.remove(item);
      } else {
        System.out.println("Your level is too low to equip this item.");
      }

    }
  }

  private int getDamage() {
    damage = stat + weapon.getDamage();
    return damage;
  }

  private void use(Item usable) {
    if (usable instanceof HpPotion) {
      if (inventory.containsKey(usable)) {
        changeCurrentHp(((HpPotion) usable).getValue());
        System.out.printf("You have recovered %d HP\n", ((HpPotion) usable).getValue());
        System.out.printf("Current HP: %d/%d\n", currentHp, maxHp);
        inventory.put(usable, inventory.get(usable) - 1);
      }
      if (inventory.get(usable) == 0) {
        inventory.remove(usable);
      }
    } else if (usable instanceof Tome) {
      if (inventory.containsKey(usable)) {
        setXp(((Tome) usable).getValue());
        System.out.printf("You have gained %d XP\n", ((Tome) usable).getValue());
        inventory.put(usable, inventory.get(usable) - 1);
      }
      if (inventory.get(usable) == 0) {
        inventory.remove(usable);
      }
    }
  }
}