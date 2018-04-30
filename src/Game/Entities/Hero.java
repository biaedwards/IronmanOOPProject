package Game.Entities;

import Game.*;
import Game.Items.*;
import Game.Entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static Game.Items.Names.allSkills;

public abstract class Hero extends Entity {
    private PlayerType type;
    private int level;
    private int defence;
    private int stat;
    private double xpUntilNextlevel;

    private Stat primaryStat;
    private Weapon weapon;
    private Helmet helmet;
    private Vest vest;

    private HashMap<Item, Integer> inventory;
    private ArrayList<Skill> skills;

    Hero(String name) {
        setName(name);
        maxHP = 100;
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
        return String.format("HP: %d\nXP: %d\nLevel: %d\nXP until next level: %f\nDefence: %d\nDamage: %d\nGold: %d\nWeapon: %s\nHelmet: %s\nVest: %s\n",
                currentHP, xp, level, xpUntilNextlevel, defence, damage, gold, weapon.toString(), helmet.toString(), vest.toString());
    }

    abstract void createDefaultInventory();

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public double getXp() {
        return xp;
    }

    public void setXp(double xp) {
        this.xp += xp;
        level();
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int hp) {
        this.maxHP = hp;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void changeCurrentHP(int hp) {
        currentHP += hp;
        currentHP = Math.min(currentHP, maxHP);
    }

    public double getXpUntilNextlevel() {
        return xpUntilNextlevel;
    }

    public void setXpUntilNextlevel(double xpUntilNextlevel) {
        this.xpUntilNextlevel = xpUntilNextlevel;
    }

    public Stat getPrimaryStat() {
        return primaryStat;
    }

    public void setPrimaryStat(Stat primaryStat) {
        this.primaryStat = primaryStat;

    }

    public int getDamage() {
        damage = stat + weapon.getDamage();
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Helmet getHelmet() {

        return helmet;
    }

    public void setHelmet(Helmet helmet) {
        this.helmet = helmet;
    }

    public Vest getVest() {
        return vest;
    }

    public void setVest(Vest vest) {
        this.vest = vest;
    }

    public void addToInventory(Item item) {
        if (inventory.containsKey(item)) {
            inventory.put(item, inventory.get(item) + 1);
        } else {
            inventory.put(item, 1);
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDefence() {
        defence = helmet.getDefence() + vest.getDefence();
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
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

    public void setWeapon(Weapon weapon) {
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

    void learnSkill() {
        for (Skill aSkill : allSkills) {
            if (aSkill.getType() != type || aSkill.getLevel() != level) continue;
            skills.add(aSkill);
            System.out.printf("Congratulations! You leveled up and learned a new skill %s!!!", aSkill.toString());
            return;
        }
    }

    public int castSkill(Skill skill) {
        if (skill.usesWeapon()) {
            return (skill.getDamage() + getDamage());
        } else {
            return skill.getDamage();
        }

    }

    public void printUsables() {
        int counter = 1;
        System.out.println("Here are your items with the quantities available in your invontory. Pick one by pressing a number. ");
        for (Item item : inventory.keySet()) {
            if (!(item instanceof Usable)) continue;
            System.out.printf("%d: %s - quantity %d ", counter++, item.getName(), inventory.get(item));
        }
        if (counter == 1) {
            System.out.println("You don't have any usable items. Press any key to continue.");
        }
    }

    public void printEquipables() {
        int counter = 1;
        for (Item item : inventory.keySet()) {
            if (!(item instanceof Equipable)) continue;
            System.out.printf("%d: %s", counter++, item.toString(), inventory.get(item));
        }
        if (counter == 1) {
            System.out.println("You don't have any equipable items. Press any key to continue.");
        }
    }

    public void useUsables(int number) {
        int counter = 1;
        for (Item item : inventory.keySet()) {
            if (item instanceof HPPotion) {
                if (number == counter) {
                    use(item);
                    break;
                }
                counter++;
            }
            if (item instanceof Tome) {
                if (number == counter) {
                    use(item);
                    break;
                }
                counter++;
            }
        }
    }

    public void equipEquipables(int number) {
        int counter = 1;
        for (Item item : inventory.keySet()) {
            if (!(item instanceof Equipable)) continue;
            if (number == counter) {
                equip(item);
                break;
            }
            counter++;
        }
    }

    public void use(Item usable) {
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
                xp += ((Tome) usable).getValue();
                System.out.printf("You have gained %d XP\n", ((Tome) usable).getValue());
                inventory.put(usable, inventory.get(usable) - 1);
            }
            if (inventory.get(usable) == 0) {
                inventory.remove(usable);
            }
        }
    }

    public void equip(Item item) {
        if (item instanceof Weapon) {
            addToInventory(weapon);
            weapon = (Weapon) item;
            inventory.remove(item);


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
        System.out.printf("Here are your updated stats:\n%s", statsToString());
    }

    public void buyItem(Item item) {
        if (item.getCost() > gold) {
            System.out.println("You cannot afford this item");
        } else {
            gold -= item.getCost();
            addToInventory(item);
        }
    }
    public void sellItem(){
        showMyInventory();

    }

    public void takeDamage(int damage) {
        currentHP -= (defence > damage) ? 1 : damage - defence;
    }

    public int attack() {
        return getDamage();
    }
    private void showMyInventory() {
        int counter = 1;
        for (Item item:inventory.keySet()) {
            System.out.printf("%d: %s - %d\n",counter++,item.toString(),inventory.get(item));

        }
    }
}
