package Game.Entities;

import Game.*;
import Game.Items.*;
import Game.Entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static Game.Items.Names.allSkills;

public abstract class Hero extends Entity implements PrintableInventory, Skills {
    private PlayerType type;
    private int level;
    private int defence;
    private int stat;
    private int xpUntilNextlevel;

    private Stat primaryStat;
    private Weapon weapon;
    private Helmet helmet;
    private Vest vest;

    private HashMap<Item, Integer> inventory;
    private ArrayList<Skill> skills;

    Hero(String name) {
        setName(name);
        maxHP = 150;
        currentHP = maxHP;
        xp = 0;
        level = 1;
        defence = 0;
        stat = 1;
        gold = 0;
        xpUntilNextlevel = 1000;
        skills = new ArrayList<>();
        inventory = new HashMap<>();
    }

    public String statsToString() {
        return String.format("HP: %d/%d\nXP: %d/%d\nLevel: %d\nDefence: %d\nDamage: %d\nGold: %d\nWeapon: %s\nHelmet: %s\nVest: %s\n",
                currentHP, maxHP, xp, xpUntilNextlevel, level, getDefence(), getDamage(), gold, weapon.toString(), helmet.toString(), vest.toString());
    }

    abstract void createDefaultInventory();

    public PlayerType getType() {
        return type;
    }

    void setType(PlayerType type) {
        this.type = type;
    }

    private double getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp += xp;
        level();
    }

    public int getMaxHP() {
        return maxHP;
    }

    void setMaxHP(int hp) {
        this.maxHP = hp;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void changeCurrentHP(int hp) {
        currentHP += hp;
        currentHP = Math.min(currentHP, maxHP);
    }

    int getXpUntilNextlevel() {
        return xpUntilNextlevel;
    }

    void setXpUntilNextlevel(int xpUntilNextlevel) {
        this.xpUntilNextlevel = xpUntilNextlevel;
    }

    public Stat getPrimaryStat() {
        return primaryStat;
    }

    void setPrimaryStat(Stat primaryStat) {
        this.primaryStat = primaryStat;

    }

    private int getDamage() {
        damage = stat + weapon.getDamage();
        return damage;
    }

    private void setDamage(int damage) {
        this.damage = damage;
    }

    public Helmet getHelmet() {

        return helmet;
    }

    void setHelmet(Helmet helmet) {
        this.helmet = helmet;
    }

    public Vest getVest() {
        return vest;
    }

    void setVest(Vest vest) {
        this.vest = vest;
    }

    public void addToInventory(Item item) {
        if (inventory.containsKey(item)) {
            inventory.put(item, inventory.get(item) + 1);
        } else {
            inventory.put(item, 1);
        }
    }

    int getLevel() {
        return level;
    }

    void setLevel(int level) {
        this.level = level;
    }

    public int getDefence() {
        defence = helmet.getDefence() + vest.getDefence();
        return defence;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    void setWeapon(Weapon weapon) {
        this.weapon = weapon;
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

    @Override
    public void learnSkill() {
        for (Skill aSkill : allSkills) {
            if (aSkill.getType() != type || aSkill.getLevel() != level) continue;
            skills.add(aSkill);
            System.out.printf("Congratulations! You leveled up and learned a new skill %s!!!", aSkill.toString());
            return;
        }
    }

    @Override
    public int castSkill(Skill skill) {
        if (skill.usesWeapon()) {
            return (skill.getDamage() + getDamage());
        } else {
            return skill.getDamage();
        }
    }

    public ArrayList<Item> getUsables() {
        ArrayList<Item> usables = new ArrayList<>();
        for (Item item : inventory.keySet()) {
            if (!(item instanceof Usable)) continue;
            usables.add(item);
        }
        return usables;
    }

    public ArrayList<Item> getEquipables() {
        ArrayList<Item> equipables = new ArrayList<>();
        for (Item item : inventory.keySet()) {
            if (!(item instanceof Equipable)) continue;
            equipables.add(item);
        }
        return equipables;
    }

    public void printUsables() {
        int counter = 1;
        System.out.println("Here are your items with the quantities available in your invontory. Pick one by pressing a number. ");
        for (Item item : getUsables()) {
            System.out.printf("%d: %s - quantity %d \n", counter++, item.getName(), inventory.get(item));
        }
        if (counter == 1) {
            System.out.println("You don't have any usable items. Press any key to continue.");
        }
    }

    public void printEquipables() {
        int counter = 1;
        for (Item item : getEquipables()) {
            System.out.printf("%d: %s\n", counter++, item.toString(), inventory.get(item));
        }
        if (counter == 1) {
            System.out.println("You don't have any equipable items. Press any key to continue.");
        }
    }

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

    private void use(Item usable) {
        if (usable instanceof HPPotion) {
            if (inventory.containsKey(usable)) {
                changeCurrentHP(((HPPotion) usable).getValue());
                System.out.printf("You have recovered %d HP\n", ((HPPotion) usable).getValue());
                System.out.printf("Current HP: %d/%d\n", currentHP, maxHP);
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

    private void equip(Item item) {
        if (item instanceof Weapon) {
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


        }
        if (item instanceof Helmet) {
            stat -= helmet.getStatBonus();
            addToInventory(helmet);
            helmet = (Helmet) item;
            stat += helmet.getStatBonus();
            inventory.remove(item);

        }
        if (item instanceof Vest) {
            maxHP -= vest.getHPBonus();
            addToInventory(vest);
            vest = (Vest) item;
            maxHP += vest.getHPBonus();
            inventory.remove(item);

        }
    }

    public void level() {
        if (level % 2 == 0) {
            learnSkill();
        } else {
            System.out.println("Congratulations! You leveled up.");
        }
        currentHP = maxHP;
        System.out.printf("Here are your updated stats:\n%s", statsToString());
    }

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

    public void sellItem(int x) {
        int counter = 1;
        for (Item item : inventory.keySet()) {
            if (counter == x) {
                inventory.remove(item);
                System.out.printf("You sold %s and got %d gold\n", item.getName(), item.getCost());
                gold += item.getCost();
                break;
            }
            counter++;

        }


    }

    public void takeDamage(int damage) {
        currentHP -= (defence > damage) ? 1 : damage - defence;
    }

    public int attack() {
        return getDamage();
    }

    public void printInventory() {
        int counter = 1;
        for (Item item : inventory.keySet()) {
            System.out.printf("%d: %s - %d\n", counter++, item.toString(), inventory.get(item));

        }
    }
}
