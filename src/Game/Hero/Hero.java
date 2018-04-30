package Game.Hero;

import Game.Attack;
import Game.Items.*;
import Game.PlayerType;
import Game.Skill;
import Game.Stat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Hero implements Attack{
    private PlayerType type;
    private int maxHP;
    private int currentHP;
    private double xp;
    private int level;
    private int defence;
    private int stat;
    private int gold;
    private int damage;
    private double xpUntilNextlevel;

    private Stat primaryStat;
    private String name;
    private Weapon weapon;
    private Helmet helmet;
    private Vest vest;

    private HashMap<Item, Integer> inventory;
    private HashSet<Skill> skills;
    private ArrayList<Skill> allSkills = new ArrayList<>();


    Hero(String name) {
        setName(name);
        generateAllSkills();
        maxHP = 100;
        currentHP = maxHP;
        xp = 0;
        level = 1;
        defence = 0;
        stat = 1;
        gold = 0;
        xpUntilNextlevel = 1000;
        skills = new HashSet<>();
        inventory = new HashMap<>();
    }

    public abstract void createDefaultInventory();

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

    public HashSet<Skill> getSkills() {
        return skills;
    }

    public void setSkills(HashSet<Skill> skills) {
        this.skills = skills;
    }

    void learnSkill(Skill skill) {
        skills.add(skill);
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
            if(!(item instanceof Usable)) continue;
                System.out.printf("%d: %s - quantity %d ", counter++, item.getName(), inventory.get(item));
        }
        if (counter == 1) {
            System.out.println("You don't have any usable items. Press any key to continue.");
        }
    }

    public void printEquipables() {
        int counter = 1;
        for (Item item : inventory.keySet()) {
            if(!(item instanceof Equipable)) continue;
                System.out.printf("%d: %s - quantity ", counter++, item.toString(), inventory.get(item));
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
            if(!(item instanceof Equipable)) continue;
                if (number == counter) {
                    equip(item);
                    inventory.remove(item);
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

        }
        if (item instanceof Helmet) {
            addToInventory(helmet);
            helmet = (Helmet) item;
        }
        if (item instanceof Vest) {
            addToInventory(vest);
            vest = (Vest) item;
        }
    }

    public abstract void level();

    public void takeDamage(int damage) {
        currentHP -= (defence>damage)?1:damage-defence;
    }

    public int attack() {
        return damage;
    }

    public ArrayList<Skill> getAllSkills() {
        return allSkills;
    }

    private void generateAllSkills() {
        Skill fireball = new Skill(PlayerType.MAGE, "Fireball", 5, true);
        Skill stab = new Skill(PlayerType.WARRIOR, "Stab", 5, true);
        Skill icearrow = new Skill(PlayerType.ARCHER, "Ice Arrow", 5, true);
        Skill blizzard = new Skill(PlayerType.MAGE, "Blizzard", 15, true);
        Skill pulverize = new Skill(PlayerType.WARRIOR, "Pulverize", 10, true);
        Skill volley = new Skill(PlayerType.ARCHER, "Volley", 10, true);
        Skill meteorstrike = new Skill(PlayerType.MAGE, "Meteor Strike", 30, true);
        Skill rainofarrows = new Skill(PlayerType.ARCHER, "Rain of arrows", 20, true);
        Skill shockwave = new Skill(PlayerType.WARRIOR, "Shockwave", 20, true);
        Skill armageddon = new Skill(PlayerType.MAGE, "Armageddon", 200, false);
        Skill headshot = new Skill(PlayerType.ARCHER, "Headshot", 100, true);
        Skill earthquake = new Skill(PlayerType.WARRIOR, "Earthquake", 150, false);
        allSkills.add(fireball);
        allSkills.add(stab);
        allSkills.add(icearrow);
        allSkills.add(blizzard);
        allSkills.add(pulverize);
        allSkills.add(volley);
        allSkills.add(meteorstrike);
        allSkills.add(rainofarrows);
        allSkills.add(shockwave);
        allSkills.add(armageddon);
        allSkills.add(headshot);
        allSkills.add(earthquake);


    }
}
